package AStar;

public class Vector3D<X extends Comparable<X>, Y extends Comparable<Y>, Z extends Comparable<Z>> implements Comparable<Vector3D<X, Y, Z>> { 
	public X x; 
	public Y y; 
	public Z z;
	
	public Vector3D(X x, Y y, Z z) { 
		this.x = x; 
	    this.y = y;
	    this.z = z; 
	} 
	
	public Vector3D() { 
		    this.x = null; 
		    this.y = null;
		    this.z = null; 
	}
	  
	  @Override
	  public int hashCode() {
	      int hash = 7;
	      hash = 31 * hash + x.hashCode();
	      hash = 31 * hash + y.hashCode();
	      hash = 31 * hash + z.hashCode();
	      return hash;
	  }
	  
	  @SuppressWarnings("unchecked") //I don't see a great way around just suppressing this here.
	  @Override
	  public boolean equals(Object o) { //TODO: make this templated
		  if (o instanceof Vector3D) {
			  Vector3D<X,Y,Z> v = (Vector3D<X,Y,Z>) o;//TODO: can this be done without the unsafe cast?
		      if (this.hashCode() == v.hashCode()) return true;
		    }
		    return false;
	  }
	  
	  @Override
	  public String toString() {
		  return "[" + x.toString() + ", " + y.toString() + ", " + z.toString() + "]";
	  }

	@Override
	public int compareTo(Vector3D<X, Y, Z> other) {
		int res = this.x.compareTo(other.x);
        if (res == 0) {
        	res = this.y.compareTo(other.y);
        }
        if (res == 0) {
        	res = this.z.compareTo(other.z);
        }
            
		return res;
	}
	  
	} 
