package AStar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AstarAttempt2 {
	private final int minBounds = -10;
	private final int maxBounds = 10;
	private final int costMin = 1;//must be positive
	private final int costMax = 10;
	private final int maxCost = 1000;
	
	private final int v = 30;
	private final int e = 15;
	
	List<Node> closedList = new ArrayList<Node>();
    List<Pair<Double,Node>> openList = new ArrayList<Pair<Double,Node>>();
	
	Node[] graph;
	
	public static void main(String[] args) {
		AstarAttempt2 AStar = new AstarAttempt2();
		AStar.aStarSearch(0, 29);
	}
	
	public AstarAttempt2() {
		
		graph = new Node[v];
		
		for(int i = 0; i<v; i++) {
			double x = (double) StdRandom.uniform(minBounds, maxBounds);
			double y = (double) StdRandom.uniform(minBounds, maxBounds);
			double z = (double) StdRandom.uniform(minBounds, maxBounds);
			
			Node n = new Node(new Vector3D(x,y,z), new ArrayList<Pair<Double,Node>>());
			graph[i] = n;
		}
				
		int epn = v/e;
		int remainder = v%e;
		
		for(int i = 0; i<v; i++) {
			int j = 0;
			if(remainder > 0) {//use up remaining unassigned edges
				j--;
				remainder--;
			}
			for(; j< epn; j++) {
				int index = StdRandom.uniform(v);
				double cost = StdRandom.uniform(costMin, costMax);
				graph[i].addEdge(cost, graph[index]);
			}
		}
	}
	
	public boolean isConnected(Node a, Node b){
		return a.conected(b) != -1;
	}
	
	public boolean isOnGraph(Node a) {//TODO: really costly is there a better way?
		for(Node n:graph) {
			if(n.equals(a)) {
				return true;
			}
		}
		return false;
	}
	
	//TODO: add an is valid check for both verts and edges
	
	public double movementCost(Node a, Node b) {
		double cost = a.conected(b);
		if(cost >= 0) {
			return cost;
		}
		
		return maxCost;
	}
	
	public double distanceCost(Node a, Node b) {//TODO: check that a and b are on the graph
		return Distance.Euclidian(a.location, b.location);
	}
	
	void aStarSearch(int s, int f) {//s,f = start, finish indexes
		
		if(s > graph.length || f > graph.length) {
			//invalid start / end
		}
		
		boolean noPath = false;
		
		if(s == f) {
			//we are already at the finish!
			return;
		}
		
	    Node curr = graph[s];
	    Node finish = graph[f];
	    List<Pair<Double,Node>> nextChoices;
	    
		while(!noPath) {
			nextChoices = (List<Pair<Double, Node>>) curr.Edges.clone();
			for(Pair<Double,Node> p:nextChoices) {
				p = new Pair((movementCost(curr, p.getY()) + distanceCost(p.getY(), finish)), p.getY());
				addToOpenList(nextChoices); //TODO: have this add only those that are not already on the list
			}
			
			Iterator<Pair<Double,Node>> it = openList.iterator();
			
			while(it.hasNext()) {
				Pair<Double,Node> choice = it.next();
				if(!closedList.contains(choice.getY())) {
					curr = choice.getY();
					closedList.add(curr);
				}
			}
			
			StdOut.println("before");
			it = nextChoices.iterator();
			Pair<Double,Node> first = it.next();
			StdOut.println(first.getX());
			Collections.sort(nextChoices);
			StdOut.println("after");
			it = nextChoices.iterator();
			Pair<Double,Node> second = it.next();
			StdOut.println(second.getX());
			
			noPath = true;//temp for testing
		}
		
		for(Pair<Double,Node> p:openList) {
			StdOut.println(p.getX());
			StdOut.println(p.getY());
		}
	}
	
	private void addToOpenList(List<Pair<Double, Node>> newList) {
		for(Pair<Double, Node> newPair:newList) {
			for(Pair<Double, Node> openPair:openList) {
				if(newPair.getY().equals(openPair.getY())) {
					if(newPair.getX()< openPair.getX()) {
						openPair.setX(newPair.getX());
					}
					continue;
				}
			}
			
			openList.add(newPair);
		}
		
		Collections.sort(openList);
	}
}
