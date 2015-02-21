package glib.math;

import glib.util.vector.GVector2f;

public class GColision {
	/*
	 * 2D COLLISIONS 
	 */
	
	public static boolean rectRect(float ax, float ay, float aw, float ah, float bx, float by, float bw, float bh){
		return (bx+bw>ax) && (by+bh>ay) && (bx<ax+aw) && (by<ay+ah);
	};
	
	/*
	 * Circle - Circle collision
	 */
	public static boolean circleCircle(float ax, float ay, float asize, float bx, float by, float bsize){
		return circleCircle(new GVector2f(ax,ay),asize,new GVector2f(bx,by),bsize);
	};
	
	public static boolean circleCircle(GVector2f a, float asize, GVector2f b, float bsize){
		double dist =a.dist(b);
		return dist <=(bsize+bsize);
	}
	
	/*
	 * Point - Rectangle collision
	 */
	public static boolean pointRect(float ax, float ay, float awidth, float aheight, float bx,float by){
		return bx > ax && bx < ax + awidth && by > ay && by < ay + aheight;
	};
	
	/*
	 * Point - Circle collision
	 */
	public static boolean pointCircle(float ax, float ay, float bx, float by, float bradius){
		return pointCircle(new GVector2f(ax,ay),new GVector2f(bx,by),bradius);
	};
	
	public static boolean pointCircle(GVector2f a, GVector2f b, float bradius){
		return a.dist(b)<bradius;
	};
	
	/*
	 * 3D COLLISIONS 
	 */
	
}
