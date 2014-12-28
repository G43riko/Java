package PathFinder_3;

public class Edge {
	private int distance;
	private Point target;
	
	public Edge(int distance, Point target) {
		this.distance = distance;
		this.target = target;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Point getTarget() {
		return target;
	}

	public void setTarget(Point target) {
		this.target = target;
	}
}
