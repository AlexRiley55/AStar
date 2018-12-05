package AStar;

public class Pair<X extends Comparable<X>, Y extends Comparable<Y>> implements Comparable<Pair<X, Y>> { 
	  private  X x; 
	  private  Y y; 
	  public Pair(X x, Y y) { 
	    this.x = x; 
	    this.y = y; 
	  } 
	  public Pair() { 
		    this.x = null; 
		    this.y = null; 
	  }
	  
	  public X getX() {
		return x;
	}
	public void setX(X x) {
		this.x = x;
	}
	public Y getY() {
		return y;
	}
	public void setY(Y y) {
		this.y = y;
	}
	
	@Override
	  public int hashCode() {
	      int hash = 7;
	      hash = 31 * hash + x.hashCode();
	      hash = 31 * hash + y.hashCode();
	      return hash;
	  }
	  
	@SuppressWarnings("unchecked") //I don't see a great way around just suppressing this here.
	  @Override
	  public boolean equals(Object o) {
		  if (o instanceof Pair) {
			  Pair<X, Y> p = (Pair<X, Y>) o;//TODO: can this be done without the unsafe cast?
		      if (this.hashCode() == p.hashCode()) return true;//TODO: make this based on actual values of x and y. Check that X,Y are the same types as X,Y
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
