package Shapes;

import Goniometry.Vector2f;
import Main.Distances;

public class Line2f {
	private Vector2f a,b,normalV,directionalV, center;
	float length;
	
	public Line2f(float ax, float ay, float bx, float by){
		this(new Vector2f(ax,ay),new Vector2f(bx,by));
	}
	
	public Line2f(Vector2f a, Vector2f b){
		this.a = a;
		this.b = b;
		this.length = Distances.getDist2f(a,b);
		this.center = new Vector2f((a.getX()+b.getX())/2,(a.getY()+b.getY())/2);
	}
	
}
