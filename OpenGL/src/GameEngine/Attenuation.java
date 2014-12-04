package GameEngine;

public class Attenuation {
	private float constant;
	private float linear;
	private float exponent;
	public Attenuation(float c, float l, float e){
		linear = l;
		constant = c;
		exponent = e;
	}
	
	public float getContstant() {
		return constant;
	}
	public void setContstant(float contstant) {
		this.constant = contstant;
	}
	public float getLinear() {
		return linear;
	}
	public void setLinear(float linear) {
		this.linear = linear;
	}
	public float getExponent() {
		return exponent;
	}
	public void setExponent(float exponent) {
		this.exponent = exponent;
	}
}
