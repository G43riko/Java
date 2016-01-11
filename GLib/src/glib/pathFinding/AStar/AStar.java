package glib.pathFinding.AStar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class AStar {
	public static void main(String[] args){
        Node n1  = new Node("Arad",366);
        Node n2  = new Node("Zerind",374);
        Node n3  = new Node("Oradea",380);
        Node n4  = new Node("Sibiu",253);
        Node n5  = new Node("Fagaras",178);
        Node n6  = new Node("Rimnicu Vilcea",193);
        Node n7  = new Node("Pitesti",98);
        Node n8  = new Node("Timisoara",329);
        Node n9  = new Node("Lugoj",244);
        Node n10 = new Node("Mehadia",241);
        Node n11 = new Node("Drobeta",242);
        Node n12 = new Node("Craiova",160);
        Node n13 = new Node("Bucharest",0);
        Node n14 = new Node("Giurgiu",77);

        n4.addAdjacencies( new Edge(n1, 140), new Edge(n5, 99),  new Edge(n3, 151), new Edge(n6,80));
        n7.addAdjacencies( new Edge(n6, 97),  new Edge(n13,101), new Edge(n12,138));
        n12.addAdjacencies(new Edge(n11,120), new Edge(n6, 146), new Edge(n7, 138));
        n13.addAdjacencies(new Edge(n7, 101), new Edge(n14,90),  new Edge(n5, 211));
        n6.addAdjacencies( new Edge(n4, 80),  new Edge(n7, 97),  new Edge(n12,146));
        n1.addAdjacencies( new Edge(n2, 75),  new Edge(n4, 140), new Edge(n8, 118));
        n2.addAdjacencies( new Edge(n1, 75),  new Edge(n3, 71));
        n3.addAdjacencies( new Edge(n2, 71),  new Edge(n4, 151));
        n5.addAdjacencies( new Edge(n4, 99),  new Edge(n13,211));
        n8.addAdjacencies( new Edge(n1, 118), new Edge(n9, 111));
        n9.addAdjacencies( new Edge(n8, 111), new Edge(n10,70));
        n10.addAdjacencies(new Edge(n9, 70),  new Edge(n11,75));
        n11.addAdjacencies(new Edge(n10,75),  new Edge(n12,120));
        n14.addAdjacencies(new Edge(n13,90));

        AStarSearch(n1, n13);
        
        List<Node> path = printPath(n13);
        
        System.out.println("Path: " + path);

    }

    public static List<Node> printPath(Node target){
        List<Node> path = new ArrayList<Node>();
        
        for(Node node = target; node!=null; node = node.getParent())
        	path.add(node);

        Collections.reverse(path);

        return path;
    }

    public static void AStarSearch(Node source, Node goal){
    	Set<Node> explored = new HashSet<Node>();

    	PriorityQueue<Node> queue = new PriorityQueue<Node>(20, new Comparator<Node>(){
    		public int compare(Node i, Node j){
    			return (int)(i.getF_scores() - j.getF_scores());
    		}
    	});

    	source.setG_scores(0);
    	queue.add(source);

    	boolean found = false;

    	while(!queue.isEmpty() && !found){
    		Node current = queue.poll();

    		explored.add(current);

    		if(current.getValue().equals(goal.getValue()))
    			found = true;
    		
    		//check every child of current node
    		for(Edge e : current.getAdjacencies()){
    			Node child = e.getTarget();
    			double cost = e.getCost();
    			double temp_g_scores = current.getG_scores() + cost;
    			double temp_f_scores = temp_g_scores + child.getH_scores();

                //if child node has been evaluated and the newer f_score is higher, skip
    			if(explored.contains(child) && temp_f_scores >= child.getF_scores())
    				continue;

                //else if child node is not in queue or newer f_score is lower
    			else if(!queue.contains(child) || temp_f_scores < child.getF_scores()){
    				child.setParent(current);
    				child.setG_scores(temp_g_scores);
    				child.setF_scores(temp_f_scores);
    				
    				if(queue.contains(child))
    					queue.remove(child);
    				
    				queue.add(child);
    			}
    		}
    	}
    }
}

