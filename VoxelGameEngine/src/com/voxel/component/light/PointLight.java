package com.voxel.component.light;

import com.voxel.rendering.shader.Shader;
import com.voxel.core.util.GVector3f;


public class PointLight extends BaseLight{
	private static final int COLOR_DEPTH = 256;
	private GVector3f attenuation;
	private float range;
	
	public PointLight(GVector3f color, float intensity, GVector3f attenuation){
		super(color,intensity);
		this.attenuation = attenuation;
		float a = attenuation.getZ();
		float b = attenuation.getY();
		float c = attenuation.getX() - COLOR_DEPTH*getIntensity()*getColor().max();
		
		this.range = (float)(-b + Math.sqrt(b*b-4*a*c))/2*a;
		setShader(new Shader("forward-point"));
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

	public float getConstant() {
		return attenuation.getX();
	}

	public void setConstant(float constant) {
		attenuation.setX(constant);
	}

	public float getLinear() {
		return attenuation.getY();
	}

	public void setLinear(float linear) {
		attenuation.setY(linear);
	}

	public float getExponent() {
		return attenuation.getZ();
	}

	public void setExponent(float exponent) {
		attenuation.setZ(exponent);
	}
}
