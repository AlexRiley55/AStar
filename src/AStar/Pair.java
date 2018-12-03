package AStar;

public class Pair<X extends Comparable<X>, Y extends Comparable<Y>> implements Comparable<Pair<X, Y>> { 
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
	public int compareTo(Pair<X, Y> other) {
		int res = this.x.compareTo(other.x);
        if (res == 0)
            res = this.y.compareTo(other.y);
		return res;
	}
	  
} 
