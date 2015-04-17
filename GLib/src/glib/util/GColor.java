package glib.util;

import glib.util.vector.GVector3f;

import java.awt.Color;

public class GColor extends Color{
	private static final long serialVersionUID = 1L;
	
	public GColor(int i){
		super(i);
	}
	
	public GColor(float r, float g, float b) {
		this(r,g,b,255);
	}
	
	public GColor(float r, float g, float b, float a) {
		super(normalize(r), normalize(g), normalize(b), normalize(a));
	}
	
	public GColor(GVector3f vec) {
		super((int)vec.getX(), (int)vec.getY(), (int)vec.getZ());
	}
	
	public static GColor average(GColor... colors){
		GVector3f average = new GVector3f();
		for(GColor c : colors){
			average = average.add(new GVector3f(c.getRed(), c.getGreen(), c.getBlue()));
		}
		average = average.div(colors.length);
		
		return new GColor(average);
	}
	
	public GColor getSimilarInstance(float value, boolean severally){
		if(severally){
			float r = (float)(Math.random()*2*value-value);
			float g = (float)(Math.random()*2*value-value);
			float b = (float)(Math.random()*2*value-value);
			return new GColor(getRed()+r, getGreen()+g, getBlue()+b);
		}
		else{
			float color = (float)(Math.random()*2*value-value);
			return new GColor(getRed()+color, getGreen()+color, getBlue()+color);
		}
	}
	
	public static int normalize(float color){
		return (int)Math.max(0, Math.min(255, color));
	}
	
	public GColor getInstance(){
		return new GColor(getRed(),getGreen(), getBlue(), getAlpha());
	}
}
