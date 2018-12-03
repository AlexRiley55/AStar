package AStar;

public class VertexArray {
	
	
	
	private Object[] Verts;	//this has to be an array of objects because Java doesn't allow generic typed arrays
	//warning this array will except all objects! Be sure to only use the provided setter / getter
	
	public void setVerts(int index, Vector3D<Double> data) {
		Verts[index] = data;
	}
	
	public Vector3D<Double> getVerts(int index) {
		return (Vector3D<Double>) Verts[index];
	}

	public VertexArray(int v) {
		Verts = new Object[v];
	}
	
	@Override
	public String toString() {//TODO: make toString for VertexArray
		String result = "";
		for(Object o:Verts) {
			result += ((Vector3D<Double>) o).toString();
		}
		return result;
	}
}