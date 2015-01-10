package main;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

public class Ray {
	public static ArrayList<Ray> rays = new ArrayList<Ray>();
	private Vector3f a,b;
	
	public Ray(Vector3f a, Vector3f b){
		this.a = a;
		this.b = b;
	}
	
	public void draw(){
		//draw ray
	}
}
