package PathFinder_3;

import java.util.ArrayList;
import java.util.Collection;

public class MainPath {
	public static void main(String[] args) {
		ArrayList<Point> points = new ArrayList<Point>();
		//points.addAll(MainPath.createCompleteMap());
		points.addAll(createCompleteClasic(5));
		vypis(points);
	}

	private static ArrayList<Point> createCompleteMap() {
		ArrayList<Point> p = new ArrayList<Point>();
		Point a,b,c,d;
		
		a = new Point();
		b = new Point();
		p.addAll(connectTwo(a,b,1));
		
		a = new Point();
		b = new Point();
		c = new Point();
		p.addAll(connectThree(a,b,c,2));
		
		a = new Point();
		b = new Point();
		c = new Point();
		d = new Point();
		p.addAll(connectFour(a,b,c,d,3));
		
		return p;
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
