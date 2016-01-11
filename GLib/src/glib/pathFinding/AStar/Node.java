package glib.pathFinding.AStar;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private final List<Edge> adjacencies = new ArrayList<Edge>();
	private final double h_scores;
	private final String value;
	private double g_scores;
	private double f_scores = 0;
    
    private Node parent;

    //CONTRUCTORS
    
    public Node(String val, double hVal){
            value = val;
            h_scores = hVal;
    }
    
    //OTHERS

    public String toString(){
            return value;
    }
    
    //ADDERS
    
    public void addAdjacencies(Edge ... edges){
    	for(Edge edge : edges)
    		adjacencies.add(edge);
    }

    //GETTERS
    
	public List<Edge> getAdjacencies() {return adjacencies;}
	public double getH_scores() {return h_scores;}
	public double getG_scores() {return g_scores;}
	public double getF_scores() {return f_scores;}
	public String getValue() {return value;}
	public Node getParent() {return parent;}
	
	//SETTERS
	
	public void setG_scores(double g_scores) {this.g_scores = g_scores;}
	public void setF_scores(double f_scores) {this.f_scores = f_scores;}
	public void setParent(Node parent) {this.parent = parent;}

}
