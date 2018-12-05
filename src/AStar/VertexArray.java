package AStar;

public class VertexArray {
	
	
	
	private Object[] Verts;	//this has to be an array of objects because Java doesn't allow generic typed arrays
	//warning this array will except all objects! Be sure to only use the provided setter / getter
	
	public void setVerts(int index, Vector3D<Double, Double, Double> data) {
		Verts[index] = data;
	}
	
	@SuppressWarnings("unchecked")//I don't see a great way around just suppressing this here.
	public Vector3D<Double, Double, Double> getVerts(int index) {
		return (Vector3D<Double, Double, Double>) Verts[index]; //TODO: is there a way to do this without the unsafe cast
	}

	public VertexArray(int v) {
		Verts = new Object[v];
	}
	
	@SuppressWarnings("unchecked")//I don't see a great way around just suppressing this here.
	@Override
	public String toString() {//TODO: make toString for VertexArray
		String result = "";
		for(Object o:Verts) {
			result += ((Vector3D<Double, Double, Double>) o).toString();//TODO: is there a way to do this without the unsafe cast
		}
		return result;
	}
}
