package GameEngine.components;

import GameEngine.core.Vector3f;
import GameEngine.rendering.ForwardPoint;
import GameEngine.rendering.RenderingEngine;

public class PointLight extends BaseLight{
	private static final int COLOR_DEPTH = 256;
	private float constant,linear, exponent;
	private Vector3f attenuation;
	private float range;
	
	public PointLight(Vector3f color, float intensity, Vector3f attenuation){
		super(color,intensity);
		this.attenuation = attenuation;
		float a = attenuation.GetZ();
		float b = attenuation.GetY();
		float c = attenuation.GetX() - COLOR_DEPTH*getIntensity()*getColor().Max();
		
		this.range = (float)(-b + Math.sqrt(b*b-4*a*c))/2*a;
		setShader(ForwardPoint.getInstance());
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

	public float getConstant() {
		return attenuation.GetX();
	}

	public void setConstant(float constant) {
		attenuation.SetX(constant);
	}

	public float getLinear() {
		return attenuation.GetY();
	}

	public void setLinear(float linear) {
		attenuation.SetY(linear);
	}

	public float getExponent() {
		return attenuation.GetZ();
	}

	public void setExponent(float exponent) {
		attenuation.SetZ(exponent);
	}
}
