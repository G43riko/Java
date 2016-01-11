package glib.pathFinding.AStar;

public class Edge {
	private final double cost;
    private final Node target;

    //CONTRUCTORS
    
	public Edge(Node targetNode, double costVal){
            target = targetNode;
            cost = costVal;
    }
	
	//GETTERS

    public double getCost() {return cost;}
	public Node getTarget() {return target;}
}
