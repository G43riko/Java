package GameEngine.components;

import GameEngine.core.Vector3f;
import GameEngine.rendering.ForwardSpot;

public class SpotLight extends PointLight {
	private Vector3f direction;
	private float cutoff;
	
	public SpotLight(Vector3f color, float intensity, float constant,float linear,float exponent, Vector3f direction, float cutoff) {
		super(color,intensity,new Vector3f(constant,linear,exponent));
		setShader(ForwardSpot.getInstance());
		this.direction = direction;
		this.cutoff = cutoff;
	}
	public Vector3f getDirection() {
		return direction;
	}
	public void setDirection(Vector3f direction) {
		this.direction = direction;
	}
	public float getCutoff() {
		return cutoff;
	}
	public void setCutoff(float cutoff) {
		this.cutoff = cutoff;
	}
}
