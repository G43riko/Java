package PathFinder_3;

import java.util.ArrayList;

public class Point {
	private ArrayList<Edge> edges;
	
	public Point(Edge...edges){
		this.edges = new ArrayList<Edge>();
		for(Edge e:edges){
			this.edges.add(e);
		}
	};
	
	public void add(Edge e){
		edges.add(e);
	};
	
	public void remove(Edge e){
		edges.remove(e);
	}
}
