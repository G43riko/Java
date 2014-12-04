package Shapes;

import Goniometry.Vector2f;
import Main.Helpers;

public class Triangel2f {
	private Vector2f a,b,c, center;
//	private Line2f la, lb, lc;
	
	public Triangel2f(float ax, float ay, float bx, float by, float cx, float cy){
		this(new Vector2f(ax, ay), new Vector2f(bx, by), new Vector2f(cx, cy));
	}
	public Triangel2f(Vector2f a, Vector2f b, Vector2f c){
		this.a = a;
		this.b = b;
		this.c = c;
		this.center = Helpers.center(a,b,c);
//		this.la = new Line2f(b,c);
//		this.lb = new Line2f(a,c);
//		this.lc = new Line2f(b,a);
	}
	
	public Vector2f getCenter() {
		return center;
	}
	
	public void setCenter(Vector2f center) {
		this.center = center;
	}
}
