package Main;

import Goniometry.Vector2f;
import Goniometry.Vector3f;

public class Collisions {
	
	private class Quad {
		public Vector2f center;
		public float r[];
		
		public Quad(final float width, final float height) {
			center = new Vector2f();
			r = new float[2];
			r[0] = width * 0.5f;
			r[1] = height * 0.5f;
		}
		public void update(final Vector2f position) {
			center.setX(position.getX());
			center.setY(position.getY());
		}
	}
	
	private class Box {
		public Vector3f center;
		public float r[];
		
		public Box(final float width, final float height, final float depth) {
			center = new Vector3f();
			r = new float[3];
			r[0] = width * 0.5f;
			r[1] = height * 0.5f;
			r[2] = depth * 0.5f;
		}
		public void update(final Vector3f position) {
			center.setX(position.getX());
			center.setY(position.getY());
			center.setZ(position.getZ());
		}
	}
	
	private class Circle {
		public Vector2f center;
		public float radius;
   
		public Circle(final float radius) {
			center = new Vector2f();
			this.radius = radius;
		}
   
		public void update(final Vector2f position) {
			center.setX(position.getX());
			center.setY(position.getY());
		}
	}
	
	public static boolean collisionRectRect(int ax,int ay, int awidth, int aheight, int bx,int by, int bwidth, int bheight){
		  if((bx+bwidth>ax)&&
		    (by+bheight>ay)&&
		    (bx<ax+awidth)&&
		    (by<ay+aheight)){
		    return true;
		  }  
		return false;
	};

	public static boolean collisionCircleCircle(double ax, double ay, int asize, double bx, double by, int bsize){
		double distX=ax-bx;
		double distY=ay-by;
		int rad_a=asize;
		int rad_b=bsize;
		double dist = Math.sqrt((distX * distX) + (distY * distY));
		if(dist <=(rad_a+rad_b)){
			return false;
		}
		return false;
	};
	
	public static boolean collisionPointRec(double ax, double ay, int awidth, int aheight, double bx,double by){
		if(bx>ax&&bx<ax+awidth&&by>ay&&by<ay+aheight){
			return true;
		}
		return false;
	};
	
//	public static boolean collisionPointCircle(double ax, double ay, double bx, double by, double bradius){
//		if(Distances.getDist2f(ax,ay,bx,by)<bradius){
//			return true;
//		}
//		return false;
//	};
	
	/*
	 *neotestované 
	 */
	
	public static boolean testQuadQuad(final Quad box1, final Quad box2) {
		if (Math.abs(box1.center.getX() - box2.center.getX()) > (box1.r[0] + box2.r[0]))
			return false;
		if (Math.abs(box1.center.getY() - box2.center.getY()) > (box1.r[1] + box2.r[1]))
			return false;
		return true;
	}
	
	public static boolean testBoxBox(final Box box1, final Box box2) {
		if (Math.abs(box1.center.getX() - box2.center.getX()) > (box1.r[0] + box2.r[0]))
			return false;
		if (Math.abs(box1.center.getY() - box2.center.getY()) > (box1.r[1] + box2.r[1]))
			return false;
		if (Math.abs(box1.center.getZ() - box2.center.getZ()) > (box1.r[2] + box2.r[2]))
			return false;
		return true;
	}
	
	public static boolean testCircleCircle(final Circle c1, final Circle c2) {
		final float distSQ = c1.center.distSQ(c2.center);
		final float radiusSum = c1.radius + c2.radius;
		      
		return distSQ <= radiusSum * radiusSum;
	}
	
	public static float sqDistPointQuad(final Vector2f p, final Quad aabb) {
		float sqDist = 0.0f;
		      
		float minX = aabb.center.getX() - aabb.r[0];
		float maxX = aabb.center.getX() + aabb.r[0];
		   
		float minY = aabb.center.getY() - aabb.r[1];
		float maxY = aabb.center.getY() + aabb.r[1];
		      
		float v = p.getX();
		      
		if (v < minX) sqDist += (minX - v) * (minX - v);
		if (v > maxX) sqDist += (v - maxX) * (v - maxX);
		      
		v = p.getY();
		      
		if (v < minY) sqDist += (minY - v) * (minY - v);
		if (v > maxY) sqDist += (v - maxY) * (v - maxY);
		      
		return sqDist;
	}
	
	public static float sqDistPointBox(final Vector3f p, final Box aabb) {
		float sqDist = 0.0f;
		      
	   	float minX = aabb.center.getX() - aabb.r[0];
		float maxX = aabb.center.getX() + aabb.r[0];
		   
		float minY = aabb.center.getY() - aabb.r[1];
		float maxY = aabb.center.getY() + aabb.r[1];
		      
		float minZ = aabb.center.getZ() - aabb.r[2];
		float maxZ = aabb.center.getZ() + aabb.r[2];
		      
		float v = p.getX();
		      
		if (v < minX) sqDist += (minX - v) * (minX - v);
		if (v > maxX) sqDist += (v - maxX) * (v - maxX);
		      
		v = p.getY();
		      
		if (v < minY) sqDist += (minY - v) * (minY - v);
		if (v > maxY) sqDist += (v - maxY) * (v - maxY);
		      
		v = p.getZ();
		      
		if (v < minZ) sqDist += (minZ - v) * (minZ - v);
		if (v > maxZ) sqDist += (v - maxZ) * (v - maxZ);
		return sqDist;
	}
	
	public static boolean testCircleAABB(final Circle circle, final Quad box) {
		float sqDist = sqDistPointQuad(circle.center, box);
		float r = circle.radius;
		      
		return sqDist <= r * r;
	}
}
