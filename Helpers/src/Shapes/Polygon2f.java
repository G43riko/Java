package Shapes;

import java.util.ArrayList;

import Goniometry.Vector2f;

public class Polygon2f {
	private ArrayList<Vector2f> points = new ArrayList<Vector2f>();
	private Vector2f center;
	
	public Polygon2f(float... sur){
		int num = sur.length;
		float sumX = 0;
		float sumY = 0;
		for(int i=0 ; i<num ; i++){
			float x = sur[i]; 
			float y = sur[++i];
			sumX += x;
			sumY += y;
			points.add(new Vector2f(x,y));
		}
		this.center = new Vector2f(sumX/num/2,sumY/num/2);
	}
}
