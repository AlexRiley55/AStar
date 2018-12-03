package AStar;

public class Pair<X, Y> implements Comparable { 
	  public final X x; 
	  public final Y y; 
	  public Pair(X x, Y y) { 
	    this.x = x; 
	    this.y = y; 
	  } 
	  public Pair() { 
		    this.x = null; 
		    this.y = null; 
	  }
	  
	  @Override
	  public int hashCode() {
	      int hash = 7;
	      hash = 31 * hash + x.hashCode();
	      hash = 31 * hash + y.hashCode();
	      return hash;
	  }
	  
	  @Override
	  public boolean equals(Object o) {
		  if (o instanceof Pair) {
			  Pair p = (Pair) o;
		      if (this.hashCode() == p.hashCode()) return true;
		    }
		    return false;
	  }
	  
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Pair<X,Y> p = (Pair<X,Y>) o;
		
		if(X.compareTo(p.x)) {
			
		}
		return 0;
	}
	  
	} 
