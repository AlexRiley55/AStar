package AStar;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable {

	public Vector3D location;
	public ArrayList<Pair<Double,Node>> Edges;
	
	Node(Vector3D loc, ArrayList<Pair<Double,Node>> e){
		location = loc;
		Edges = e;
	}
	
	public void addEdge(Pair<Double,Node> p) {
		Edges.add(p);
		p.y.Edges.add(new Pair<Double,Node>(p.x, this));
	}
	
	public void addEdge(Double cost, Node n) {
		Edges.add(new Pair<Double,Node>(cost, n));
		n.Edges.add(new Pair<Double,Node>(cost, this));
	}
	
	public double conected(Node n) {
		for(Pair<Double,Node> pair:Edges) {
			if (n == pair.y) {
				return pair.x;
			}
		}
		
		return -1.0;
	}
	
	@Override
	  public int hashCode() {
	      int hash = 7;
	      hash = 31 * hash + location.hashCode();
	      hash = 31 * hash + Edges.hashCode();
	      return hash;
	  }
	  
	  @Override
	  public boolean equals(Object o) { //TODO: make this templated
		  if (o instanceof Node) {
			  Node n = (Node) o;
		      if (this.hashCode() == n.hashCode()) return true;
		    }
		    return false;
	  }
	  
	  @Override
	  public String toString() {
		  return location.toString() + " " + Edges.toString();
	  }
	  

	  @Override
	  public int compareTo(Object o) {
		  Node n = (Node) o;
		  //TODO: do this
		  return 0;
	  }
}
