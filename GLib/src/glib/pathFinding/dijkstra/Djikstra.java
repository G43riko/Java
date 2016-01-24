package glib.pathFinding.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Djikstra {
	public static void main(String[] args){
		Vertex<?> v0 = new Vertex<String>("Harrisburg");
		Vertex<?> v1 = new Vertex<String>("Baltimore");
		Vertex<?> v2 = new Vertex<String>("Washington");
		Vertex<?> v3 = new Vertex<String>("Philadelphia");
		Vertex<?> v4 = new Vertex<String>("Binghamton");
		Vertex<?> v5 = new Vertex<String>("Allentown");
		Vertex<?> v6 = new Vertex<String>("New York");
		v5.addAdjacencies(new Edge(v0, 81.77), new Edge(v3, 62.05), new Edge(v4, 134.47), new Edge(v6, 91.63));
		v3.addAdjacencies(new Edge(v1, 12.53), new Edge(v5, 61.44), new Edge(v6, 196.79));
		v1.addAdjacencies(new Edge(v0, 79.75), new Edge(v2, 39.42), new Edge(v3, 103.00));
		v6.addAdjacencies(new Edge(v3, 97.24), new Edge(v5, 87.94));
		v0.addAdjacencies(new Edge(v1, 79.83), new Edge(v5, 81.15));
		v4.addAdjacencies(new Edge(v5, 133.04));
		v2.addAdjacencies(new Edge(v1, 38.65));
		
		Vertex<?>[] vertices = { v0, v1, v2, v3, v4, v5, v6 };
		computePaths(v0, v1);
		for (Vertex<?> v : vertices){
			System.out.println("Distance to " + v + ": " + v.getMinDistance());
			List<Vertex<?>> path = getShortestPathTo(v);
			System.out.println("Path: " + path);
		}
	}
	
	public static void computePaths(Vertex<?> source){
		computePaths(source, null);
	}
	
	public static void computePaths(Vertex<?> source, Vertex<?> target){
		source.setMinDistance(0);
		PriorityQueue<Vertex<?>> vertexQueue = new PriorityQueue<Vertex<?>>();
		vertexQueue.add(source);
		 
		while (!vertexQueue.isEmpty()) {
			Vertex<?> u = vertexQueue.poll();
			if(u == target)
				return;
			for (Edge e : u.getAdjacencies()){
				Vertex<?> v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.getMinDistance() + weight;
				if (distanceThroughU < v.getMinDistance()) {
					vertexQueue.remove(v);
					v.setMinDistance(distanceThroughU);
					v.previous = u;
					vertexQueue.add(v);
				}
			}
			
		}
	}
	 
	public static List<Vertex<?>> getShortestPathTo(Vertex<?> target){
		 List<Vertex<?>> path = new ArrayList<Vertex<?>>();
		 
		 for (Vertex<?> vertex = target; vertex != null; vertex = vertex.previous)
			 path.add(vertex);
		 
		 Collections.reverse(path);
		 return path;
	}
}
