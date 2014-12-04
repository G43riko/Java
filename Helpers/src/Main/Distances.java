package Main;

import Goniometry.Vector2f;

public class Distances {
	public static void getDist2f(float ax, float ay, float bx, float by){
		Distances.getDist2f(new Vector2f(ax,ay), new Vector2f(bx,by));
	};
	public static float getDist2f(Vector2f a, Vector2f b){
		float dist;
		dist=(a.getX()-b.getX())*(a.getX()-b.getX())+(a.getY()-b.getY())*(a.getY()-b.getY());
		dist=(float)Math.sqrt(dist);
		return dist;
	}
}
