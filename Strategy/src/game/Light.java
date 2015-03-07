package game;

import glib.util.vector.GVector3f;

public class Light {
	private GVector3f position;
	private GVector3f color;
	private GVector3f attenuation;
	
	public Light(GVector3f position) {
		this(position, new GVector3f(1), new GVector3f(1,0,0));
	}
	
	public Light(GVector3f position, GVector3f color){
		this(position, color, new GVector3f(1,0,0));
	}
	
	public Light(GVector3f position, GVector3f color, GVector3f attenuation) {
		this.position = position;
		this.color = color;
		this.attenuation = attenuation;
	}

	public GVector3f getPosition() {
		return position;
	}

	public void setPosition(GVector3f position) {
		this.position = position;
	}

	public GVector3f getColor() {
		return color;
	}

	public void setColor(GVector3f color) {
		this.color = color;
	}

	public GVector3f getAttenuation() {
		return attenuation;
	}

	public void setAttenuation(GVector3f attenuation) {
		this.attenuation = attenuation;
	}
}
