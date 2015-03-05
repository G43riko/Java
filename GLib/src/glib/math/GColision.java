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
		return pointRect(new GVector2f(ax,ay), new GVector2f(awidth,aheight), new GVector2f(bx,by));
	};
	
	public static boolean pointRect(GVector2f aPos, GVector2f aSize, GVector2f bPos){
		return bPos.getX() > aPos.getX() && bPos.getX() < aPos.getX() + aSize.getX() && bPos.getX() > aPos.getX() && bPos.getX() < aPos.getX() + aSize.getY();
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
