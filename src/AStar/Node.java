package AStar;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {

	public Vector3D location;
	public ArrayList<Pair<Double,Node>> Edges;
	
	Node(Vector3D loc, ArrayList<Pair<Double,Node>> e){
		location = loc;
		Edges = e;
	}
	
	public void addEdge(Pair<Double,Node> p) {
		Edges.add(p);
		p.getY().Edges.add(new Pair<Double,Node>(p.getX(), this));
	}
	
	public void addEdge(Double cost, Node n) {
		Edges.add(new Pair<Double,Node>(cost, n));
		n.Edges.add(new Pair<Double,Node>(cost, this));
	}
	
	public double conected(Node n) {
		for(Pair<Double,Node> pair:Edges) {
			if (n == pair.getY()) {
				return pair.getX();
			}
		}
		
		return -1.0;
	}
	
	@Override
	  public int hashCode() {
	      int hash = 7;
	      hash = 31 * hash + location.hashCode();
	      //hash = 31 * hash + Edges.hashCode();
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
		  String res = location.toString() + " ";
		  for(Pair<Double,Node> p:Edges) {
			  res += "[ " + p.getX() + " " + p.getY().location.toString() + " ]";
		  }
		  return  res;
	  }
	  

	@Override
	public int compareTo(Node other) {
        return this.Edges.size() - other.Edges.size();
	}
}
