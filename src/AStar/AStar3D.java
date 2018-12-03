package AStar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AStar3D {

	private final int minBounds = -10;
	private final int maxBounds = 10;
	private final int maxCost = 1000;
	
	private final int v = 30;
	private final int e = 15;
	
	VertexArray Verts;
	
	
	public static void main(String[] args) {
		AStar3D AStar = new AStar3D();
		AStar.aStarSearch(0, 29);
	}
	
	AdjMatrixGraph AdjacencyM;
	
	public AStar3D() {
		
		AdjacencyM = new AdjMatrixGraph(v, e);
		StdOut.println(AdjacencyM);
		
		Verts = new VertexArray(v);
		
		for(int i = 0; i<v; i++) {
			double x = (double) StdRandom.uniform(minBounds, maxBounds);
			double y = (double) StdRandom.uniform(minBounds, maxBounds);
			double z = (double) StdRandom.uniform(minBounds, maxBounds);
			
			Verts.setVerts(i,new Vector3D(x, y, z));
		}
		
		StdOut.println(Verts);
	}
	
	//TODO: add an is valid check for both verts and edges
	
	public double movementCost(int a, int b) {
		if(AdjacencyM.contains(a, b)) {
			return AdjacencyM.getEdge(a, b);
		}
		return maxCost;
	}
	
	public double distanceCost(int a, int b) {//TODO: check that a and b are on the graph
		return Distance.Euclidian(Verts.getVerts(a), Verts.getVerts(b));
	}
	
	void aStarSearch(int s, int f) {//s = start, f = finish
		//TODO check if a and b are valid
		
		if(s == f) {
			//we are already at the finish!
			return;
		}
		
	    Boolean[] closedList = new Boolean[v]; 
	    
	    Iterator<Integer> it = AdjacencyM.adj(s).iterator();
	    List<Integer> openList = new ArrayList<Integer>();
		while(it.hasNext()) {
			openList.add(it.next());
		}
		
		
		
		StdOut.println(openList);
	}
	
}
