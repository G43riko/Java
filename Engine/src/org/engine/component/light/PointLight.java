package org.engine.component.light;

import org.engine.app.GameAble;

import glib.util.vector.GVector3f;

public class PointLight extends BasicLight{
	private GVector3f attenuation;
	private float range;
	public final static float DEFAULT_RANGE = 400;
	//CONSTRUCTORS
	
	public PointLight(GameAble parent, GVector3f position) {
		this(parent, position, new GVector3f(1), DEFAULT_RANGE, 1);
	}
	
//	public PointLight(GameAble parent, GVector3f position, GVector3f color){
//		this(parent, position, color, new GVector3f(1,0,0));
//	}
	
	public PointLight(GameAble parent, GVector3f position, GVector3f color){
		this(parent, position, color, DEFAULT_RANGE, 1);
	}
	
	public PointLight(GameAble parent, GVector3f position, GVector3f color, float range, int intensity) {
		super(parent, position, color, intensity);
		this.attenuation = new GVector3f(1, 4.5/range, 75 / range);
		this.range = 20;//calcRange(attenuation);
	}
	
//	public PointLight(GameAble parent, GVector3f position, GVector3f color, GVector3f attenuation) {
//		super(parent, position, color);
//		this.attenuation = attenuation;
//		System.out.println(calcRange(attenuation));
//		this.range = 20;//calcRange(attenuation);
//	}

	//OTHERS
	
	private float calcRange(GVector3f a){
		return (float)(-a.getY() + Math.sqrt(a.getY() * a.getY() -4 * a.getX() * a.getZ())) / 2 * a.getX();
	}

	//GETTERS
	
	public GVector3f getColor() {
		return color;
	}

	public GVector3f getAttenuation() {
		return attenuation;
	}

	public float getRange() {
		return range;
	}
	
	//SETTERS

	public void setColor(GVector3f color) {
		setChange(true);
		this.color = color;
	}

	public void setAttenuation(GVector3f attenuation) {
		setChange(true);
		this.attenuation = attenuation;
		this.range = calcRange(attenuation);
	}
}
