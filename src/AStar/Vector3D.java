package AStar;

public class Vector3D<T> {
	public final T x; 
	public final T y; 
	public final T z;
	
	public Vector3D(T x, T y, T z) { 
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
	  
	  @Override
	  public boolean equals(Object o) { //TODO: make this templated
		  if (o instanceof Vector3D) {
			  Vector3D v = (Vector3D) o;
		      if (this.hashCode() == v.hashCode()) return true;
		    }
		    return false;
	  }
	  
	  @Override
	  public String toString() {
		  return "[" + x.toString() + ", " + y.toString() + ", " + z.toString() + "]";
	  }
	  
	} 
