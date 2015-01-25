package com.voxel.component.light;

import com.voxel.render.shader.Shader;

import glib.util.vector.GVector3f;

public class SpotLight extends PointLight{
	private float cutoff;
	
	public SpotLight(GVector3f color, float intensity, float constant,float linear,float exponent, float cutoff) {
		super(color,intensity,new GVector3f(constant,linear,exponent));
		setShader(new Shader("forward-spot"));
		this.cutoff = cutoff;
	}
	
	public GVector3f getDirection() {
		return getTransform().getRotation().getForward();
	}
	
	public float getCutoff() {
		return cutoff;
	}
	
	public void setCutoff(float cutoff) {
		this.cutoff = cutoff;
	}
}
