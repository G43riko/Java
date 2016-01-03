package glib.pathFinding.dijkstra;

import java.util.ArrayList;
import java.util.List;

class Vertex<T> implements Comparable<Vertex<T>>{
	private double minDistance = Double.POSITIVE_INFINITY;
    private final T name;
    private List<Edge> adjacencies = new ArrayList<Edge>();
    public Vertex<?> previous;
    
    public Vertex(T name) { 
    	this.name = name; 
    }
    public String toString() { 
    	return name.toString(); 
    }
    
	@Override
	public int compareTo(Vertex<T> other){
	    return Double.compare(minDistance, other.minDistance);
	}
	
	//ADDERS
	
	public void addAdjacencies(Edge ... edges) {
		for(Edge edge : edges)
			adjacencies.add(edge);
	}
	
	//GETTERS
	
	public List<Edge> getAdjacencies() {return adjacencies;}
	public double getMinDistance() {return minDistance;}
	
	//SETTERS
	
	public void setMinDistance(double minDistance) {this.minDistance = minDistance;}
}
