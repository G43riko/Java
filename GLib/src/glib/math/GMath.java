package glib.math;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class GMath {
	public static float distPointPoint(float ax, float ay, float bx, float by){
		return new GVector2f(ax, ay).dist(new GVector2f(bx, by));
	};
	
	public static float distPointPoint(float ax, float ay, float az, float bx, float by, float bz){
		return new GVector3f(ax, ay, az).dist(new GVector3f(bx, by, bz));
	};
	
	public static float distPointLine(GVector3f pointA, GVector3f pointB, GVector3f pointP){
		if(pointA.sub(pointB).dot(pointP.sub(pointB)) < 0)
			return pointP.dist(pointB);
		
		if(pointB.sub(pointA).dot(pointP.sub(pointA)) < 0)
			return pointP.dist(pointA);
		return distPointVector(pointA, pointB, pointP);
		
	}
	
	public static float distPointVector(GVector3f pointA, GVector3f pointB, GVector3f pointP){
		float u = ((pointP.getX() - pointA.getX()) * (pointB.getX() - pointA.getX()) +
				   (pointP.getY() - pointA.getY()) * (pointB.getY() - pointA.getY()) +
				   (pointP.getZ() - pointA.getZ()) * (pointB.getZ() - pointA.getZ())) / pointA.sub(pointB).getLength();
		
		return new GVector3f(pointA.getX() + u * (pointB.getX() - pointA.getX()),
							 pointA.getY() + u * (pointB.getY() - pointA.getY()),
							 pointA.getZ() + u * (pointB.getZ() - pointA.getZ())).sub(pointP).getLength();
	}
	
	public static GVector2f getClosestPointOnSegment(GVector2f ss, GVector2f se, GVector2f p){
		return getClosestPointOnSegment(ss.getX(), ss.getY(), se.getX(), se.getY(), p.getX(), p.getY());
	}
	
	public static GVector2f getClosestPointOnSegment(float sx1, float sy1, float sx2, float sy2, float px, float py){
		double xDelta = sx2 - sx1;
		double yDelta = sy2 - sy1;
	
		double u = ((px - sx1) * xDelta + (py - sy1) * yDelta) / (xDelta * xDelta + yDelta * yDelta);
	
		final GVector2f closestPoint;
		if (u < 0)
			closestPoint = new GVector2f(sx1, sy1);	
		else if (u > 1)
			closestPoint = new GVector2f(sx2, sy2);
		else
			closestPoint = new GVector2f(sx1 + u * xDelta, sy1 + u * yDelta);
		
		return closestPoint;
	}
	
	public static GVector3f getClosestPointOnSegment(GVector3f ss, GVector3f se, GVector3f p){
		return getClosestPointOnSegment(ss.getX(), ss.getY(), ss.getZ(), se.getX(), se.getY(), se.getZ(), p.getX(), p.getY(), p.getZ());
	}
	
	public static GVector3f getClosestPointOnSegment(float sx1, float sy1, float sz1, float sx2, float sy2, float sz2, float px, float py, float pz){
		double xDelta = sx2 - sx1;
		double yDelta = sy2 - sy1;
		double zDelta = sz2 - sz1;
	
		double u = ((px - sx1) * xDelta + (py - sy1) * yDelta + (pz - sz1) * zDelta) / (xDelta * xDelta + yDelta * yDelta + zDelta * zDelta);
	
		if (u < 0)
			return new GVector3f(sx1, sy1, sz1);	
		else if (u > 1)
			return new GVector3f(sx2, sy2, sz2);
	    else
	    	return new GVector3f(sx1 + u * xDelta, sy1 + u * yDelta, sz1 + u * zDelta);
	}
	
	public static float choose(float ... argc){
		return argc[(int)(Math.random() * argc.length)];
	};
	
	public static float average(float ... argc){
		float sum = 0;
		
		for(float co : argc)
			sum += co;
		
		return sum/argc.length;
	};
	
	public static GVector2f center(GVector2f... vectors){
		GVector2f sum = new GVector2f();
		for(GVector2f vec : vectors)
			sum = sum.add(vec);
		return sum.div(vectors.length);
	};
	
	public static GVector3f center(GVector3f... vectors){
		GVector3f sum = new GVector3f();
		for(GVector3f vec : vectors)
			sum = sum.add(vec);
		
		return sum.div(vectors.length);
	};
	
	public static int fastfloor(double x) {
		return x > 0 ? (int)x : (int)x-1;
	};
	
	public static float interpolateLinear(float minValue, float maxValue, float scale) {
	    return between((maxValue - minValue) * scale + minValue, minValue, maxValue);
	};
	
	public static float interpolateLinearSmooth(float a, float b, float scale) {
	    double theta = scale * Math.PI;
	    float f = (float)(1f - Math.cos(theta)) * 0.5f;
	    return a * (1f - f) + b * f;
	};
	
	public static float randomize(float value, float level){
		return value + (float)(Math.random() * 2 * level - level);
	};
	
	public static float between(float value, float min, float max){
		return Math.max(min, Math.min(value, max));
	};
	
	public static float max(float... args){
		float max = args[0];
		
		for(int i=1 ; i<args.length ; i++)
			max = Math.max(max, args[i]);
		
		return max;
	}
	
	public static float min(float... args){
		float min = args[0];
		
		for(int i=1 ; i<args.length ; i++)
				min = Math.min(min, args[i]);
		
		return min;
	}
	
	public static int rand(int min, int max){
		return (int)(Math.random() * (max - min)) + min;
	}
	
	public static float rand(float min, float max){
		return (float)(Math.random() * (max - min)) + min;
	}
}
