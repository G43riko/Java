package glib.math;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class GColision {
	/*
	 * 2D COLLISIONS 
	 */
	
	/*
	 * Rectangle - Rectangle collision
	 */
	
	public static boolean rectRect(float ax, float ay, float aw, float ah, float bx, float by, float bw, float bh){
		return (bx + bw > ax) && (by + bh > ay) && (bx < ax + aw) && (by < ay + ah);
	};
	
	/*
	 * Circle - Circle collision
	 */
	
	public static boolean circleCircle(float ax, float ay, float aradius, float bx, float by, float beadius){
		return circleCircle(new GVector2f(ax,ay),aradius,new GVector2f(bx,by),beadius);
	};
	
	public static boolean circleCircle(GVector2f a, float aradius, GVector2f b, float bradius){
		double dist =a.dist(b);
		return dist <= aradius + bradius;
	}
	
	/*
	 * Point - Rectangle collision
	 */
	
	public static boolean pointRect(float ax, float ay, float awidth, float aheight, float bx,float by){
		return pointRect(new GVector2f(ax,ay), new GVector2f(awidth,aheight), new GVector2f(bx,by));
	};
	
	public static boolean pointRect(GVector2f aPos, GVector2f aSize, GVector2f bPos){
		return bPos.getX() > aPos.getX() && bPos.getX() < aPos.getX() + aSize.getX() && bPos.getY() > aPos.getY() && bPos.getY() < aPos.getY() + aSize.getY();
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
	
	/*
	 * Sphere - Sphere collision
	 */
	
	public static boolean sphereSphereCollision(float ax, float ay, float az, float aradius, float bx, float by, float bz, float bradius){
		return sphereSphereCollision(new GVector3f(ax, ay, az), aradius, new GVector3f(bx, by, bz), bradius);
	}
	
	public static boolean sphereSphereCollision(GVector3f posA, float radiusA, GVector3f posB, float radiusB){
		return posA.dist(posB) < radiusA + radiusB;
	}
	
	/*
	 * Point - Sphere collision
	 */
	
	public static boolean pointCircleCollision(float ax, float ay, float az, float bx, float by, float bz, float bradius){
		return pointCircleCollision(new GVector3f(ax, ay, az), new GVector3f(bx, by, bz), bradius);
	};
	
	public static boolean pointCircleCollision(GVector3f a, GVector3f b, float bradius){
		return a.dist(b)<bradius;
	};
	
	/*
	 * Point - Box collision
	 */
	
	public static boolean pointBoxCollision(float ax, float ay, float az, float awidth, float aheight,float adepth, float bx,float by, float bz){
		return pointBoxCollision(new GVector3f(ax, ay, az), new GVector3f(awidth, aheight, adepth), new GVector3f(bx, by, bz));
	}
	
	public static boolean pointBoxCollision(GVector3f  a, GVector3f asize, GVector3f  b){
		//neskontrolovane
		return a.getX() < b.getX() && a.getX() + asize.getX() > b.getX() &&
			   a.getY() < b.getY() && a.getY() + asize.getY() > b.getY() &&
			   a.getZ() < b.getZ() && a.getZ() + asize.getZ() > b.getZ();
	}

	/*
	 * Box - Box collision
	 */
}
