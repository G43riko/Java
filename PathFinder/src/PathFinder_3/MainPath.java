package PathFinder_3;

import java.util.ArrayList;

public class MainPath {
	public static ArrayList<Point> points = new ArrayList<Point>();
	public static ArrayList<Point> pointsChecked = new ArrayList<Point>();
	public static ArrayList<Point> pointsChecking = new ArrayList<Point>(); 
	public static void main(String[] args) {
		points.addAll(createCompleteClasic(8));
		//vypis(points);
		int from=0, to = 63;
//		ArrayList<Point> hist = new ArrayList<Point>();
//		hist.add(points.get(from));
		boolean keepGoing = true;
		pointsChecking.add(points.get(from));
		while(keepGoing){
			for(int i=0 ; i<pointsChecking.size() ; i++){
				Point p=pointsChecking.get(i);
				for(Edge e:p.getEdges()){
					if(e.getTarget().getId()==to){
						System.out.println("našiel som cestu z "+p.getId()+" do "+to+" za "+(p.getTotalDist()+e.getDistance())+" krokov");
						return;
					}
					if(!pointsChecked.contains(e.getTarget())&&!pointsChecking.contains(e.getTarget())){
						System.out.println("do kontrolovaných sa z "+p.getId()+" pridalo: "+e.getTarget().getId()+" dist: "+e.getDistance());
						e.getTarget().addTotalDist(e.getDistance()+p.getTotalDist());
						pointsChecking.add(e.getTarget());
					}
				}

				System.out.println("do skontrolovaných sa pridalo: "+p.getId());
				pointsChecking.remove(p);
				pointsChecked.add(p);
				i--;
			}
		}
//		System.out.println("dist je: "+find(hist,to));
	} 
	
	private static int find(ArrayList<Point> hist, int goal){
		Point from = hist.get(hist.size()-1);
		for(Edge p:from.getEdges()){
			if(!hist.contains(p.getTarget())){
				if(p.getTarget().getId()==goal){
					return p.getDistance();
				}
				hist.add(p.getTarget());
				int res = find(hist,goal);
				if(res!=0){
					return res+p.getDistance();//add
				}
				hist.remove(p.getTarget());
			}
		}
		return 0;
	}
	
	private static ArrayList<Point> createCompleteClasic(int num) {
		ArrayList<Point> p = new ArrayList<Point>();
		int size = num*num;
		for(int i=0 ; i<size ; i++){
			p.add(new Point());
			p.get(i).setId(i);
		}
		for(Point pom:p){
			int id = p.indexOf(pom);
			
			if(id-(num+1)>=0&&id%num!=0){
				connectTwo(pom,p.get(id-(num+1)),3);
			}
			if(id-num>=0){
				connectTwo(pom,p.get(id-num),2);
			}
			if(id-(num-1)>=0&&id%num!=num-1){
				connectTwo(pom,p.get(id-(num-1)),3);
			}
			if(id-1>=0&&id%num!=0){
				connectTwo(pom,p.get(id-1),2);
			}
			if(id+1<size&&id%num!=num-1){
				connectTwo(pom,p.get(id+1),2);
			}
			if(id+(num-1)<size&&id%num!=0){
				connectTwo(pom,p.get(id+(num-1)),3);
			}
			if(id+num<size){
				connectTwo(pom,p.get(id+num),2);
			}
			if(id+(num+1)<size&&id%num!=num-1){
				connectTwo(pom,p.get(id+(num+1)),3);
			}
		}
		return p;
	}

	private static void vypis(ArrayList<Point> p) {
		for(Point a:p){
			System.out.println(a);
		}
		
	}
	
	private static ArrayList<Point> connectFour(Point a, Point b, Point c, Point d, int distance){
		ArrayList<Point> e = new ArrayList<Point>();
		e.add(a);
		e.add(b);
		e.add(c);
		e.add(d);
		for(Point pom1:e){
			for(Point pom2:e){
				if(pom2!=pom1){
					pom1.add(new Edge(distance,pom2));
				}
			}
		}
		return e;
	}
	
	private static ArrayList<Point> connectThree(Point a, Point b, Point c, int distance){
		ArrayList<Point> e = new ArrayList<Point>();
		e.add(a);
		e.add(b);
		e.add(c);
		for(Point pom1:e){
			for(Point pom2:e){
				if(pom2!=pom1){
					pom1.add(new Edge(distance,pom2));
				}
			}
		}
		return e;
	}
	
	private static ArrayList<Point> connectTwo(Point a, Point b, int distance){
		if(!a.isConnect(b))
			a.add(new Edge(distance,b));
		if(!b.isConnect(a))
		b.add(new Edge(distance,a));
		ArrayList<Point> e = new ArrayList<Point>();
		e.add(a);
		e.add(b);
		return e;
	}

}
