package AStar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AstarAttempt2 {
	private final int minBounds = -10;
	private final int maxBounds = 10;
	private final int costMin = 1;//must be positive
	private final int costMax = 10;
	private final int maxCost = 1000;
	
	private final int v = 30;
	private final int e = 60;
	
	List<Node> closedList = new ArrayList<Node>();
    List<Vector3D<Double,Node, Node>> openList = new ArrayList<Vector3D<Double,Node, Node>>();
	
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
			
			Node n = new Node(new Vector3D<Double, Double, Double>(x,y,z), new ArrayList<Pair<Double,Node>>());
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
			//TODO: invalid start / end
		}
		
		boolean noPath = false;//this is temp for testing
		
		if(s == f) {
			//we are already at the finish!
			return;
		}
		
	    Node curr = graph[s];
	    StdOut.println("Going from: " + curr.location);
	    Node finish = graph[f];
	    StdOut.println("To: " + finish.location);
	    List<Vector3D<Double, Node, Node>> nextChoices = new  ArrayList<Vector3D<Double, Node, Node>>();
	    
	    int i = 0;
	    
		while(!noPath) {
			if(!(curr.Edges instanceof List)) {
				//TODO: throw error
				nextChoices = null;//temp
				StdOut.println("Edges is not a list");
			}
			
			for(Pair<Double, Node> p:curr.Edges) {
				Vector3D<Double, Node, Node> v = new Vector3D<Double, Node, Node>((movementCost(curr, p.getY()) + distanceCost(p.getY(), finish)), curr, p.getY());
				nextChoices.add(v);
			}
			addToOpenList(nextChoices);
			
			StdOut.println(i);
			StdOut.println(curr);
			StdOut.println("Open List: " + openListToString());
			
			Iterator<Vector3D<Double,Node, Node>> it = openList.iterator();
			
			curr = null;
			while(it.hasNext()) {
				Vector3D<Double,Node, Node> choice = it.next();
				if(!(closedList.contains(choice.z))) {
					Node parent = choice.y;
					StdOut.println("parent: " + parent.location);
					curr = choice.z;
					curr.Parent = parent;
					closedList.add(curr);
					break;
				}
			}
			if(curr == null) {
				//TODO: there is no path
				noPath = true;//this is temp for testing
			}
			
			
			
			if(graph[f].equals(curr)) {
				//we finished
				StdOut.println("Finished");
				noPath = true;//this is temp for testing
			}
			
			i++;
		}
		
		List<Node> path = ListPath(curr);
		Collections.reverse(path);
		StdOut.println(path);
	}
	
	public ArrayList<Node> ListPath(Node end) {
		Node next = end;
		ArrayList<Node> results = new ArrayList<Node>();
		while(next != null) {
			results.add(next);
			next = next.Parent;
		}
		
		return results;
	}
	
	public String openListToString() {
		String res = "";
		
		for(Vector3D<Double,Node, Node> p:openList) {
			res += p.x + " ";
			res += p.y + " ";
		}
		return res;
	}
	
	private void addToOpenList(List<Vector3D<Double,Node, Node>> newList) {
		for(Vector3D<Double,Node, Node> newVec:newList) {
			boolean onList = false;
			for(Vector3D<Double,Node, Node> openVec:openList) {
				if(newVec.z.equals(openVec.z)) {
					onList = true;
					if(newVec.x< openVec.x) {
						openVec.x = newVec.x;
						openVec.y = newVec.y;
					}
				}
			}
			
			if(!onList) {
				openList.add(newVec);
			}
		}
		
		Collections.sort(openList);
	}
}
