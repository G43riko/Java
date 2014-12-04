package GameEngine;

public class PointLight {
	private BaseLight baseLight;
	private Attenuation atten;
	private Vector3f position;
	
	public PointLight(BaseLight b, Attenuation a, Vector3f p){
		baseLight = b;
		atten = a;
		position = p;
		
	}

	public BaseLight getBaseLight() {
		return baseLight;
	}

	public void setBaseLight(BaseLight baseLight) {
		this.baseLight = baseLight;
	}

	public Attenuation getAttenuation() {
		return atten;
	}

	public void setAttenuation(Attenuation attenuation) {
		this.atten = attenuation;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}
}
