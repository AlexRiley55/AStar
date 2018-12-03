package AStar;

public class Distance {

	static double Euclidian(Vector3D<Double> p, Vector3D<Double> q) {
		return Math.sqrt(Math.pow((p.x - q.x), 2) + Math.pow((p.y - q.y), 2) + Math.pow((p.z - q.z), 2));
	}
	
	static double Manhattan(Vector3D<Double> p, Vector3D<Double> q) {
		return Math.abs(p.x - q.x) + Math.abs(p.y - q.y) + Math.abs(p.z - q.z);
	}
	
	static double Frenet(Vector3D<Double> p, Vector3D<Double> q) {//TODO: what is Frenet distance?
		return Math.sqrt(Math.pow((p.x - q.x), 2) + Math.pow((p.y - q.y), 2) + Math.pow((p.z - q.z), 2));
	}
	
}
