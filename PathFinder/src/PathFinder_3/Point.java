package PathFinder_3;

import java.util.ArrayList;

public class Point {
	private static ArrayList<Integer> ides = new ArrayList<Integer>();
	private int id;
	private ArrayList<Edge> edges;
	private int totalDist = 0;
	private int lastDist = 0;
	
	public Point(Edge...edges){
		this.id = makeId();
		this.edges = new ArrayList<Edge>();
		for(Edge e:edges){
			this.edges.add(e);
		}
	};
	
	public void setId(int id){
		this.id = id;
		ides.add(id);
		
	}
	
	public void add(Edge e){
		edges.add(e);
	};
	
	public void remove(Edge e){
		edges.remove(e);
	}
	
	private static int makeId(){
		//int maxIntValue = 2147483647;
		int id = (int)Math.floor(Math.random()* 100);
		while(ides.contains(id)){
			id = (int)Math.floor(Math.random()* 100);
		}
		ides.add(id);
		return(id);
	}
	
	public String toString(){
		String ret = "som "+id+" a mám "+edges.size()+" hrany:";
		for(Edge e:edges){
			ret += "   "+e.getDistance()+" s "+e.getTarget().getId();
		}
		return ret;
	}

	public boolean isConnect(Point p){
		for(Edge e:edges){
			if(e.getTarget().getId()==p.getId()){
				return true;
			}
		}
		return false;
	}
	
	public int getId() {
		return id;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public int getTotalDist() {
		return totalDist;
	}

	public void setTotalDist(int totalDist) {
		this.totalDist = totalDist;
	}
	
	public void addTotalDist(int totalDist){
		this.totalDist += totalDist;
	}

	public int getLastDist() {
		return lastDist;
	}

	public void setLastDist(int lastDist) {
		this.lastDist = lastDist;
	}
}
