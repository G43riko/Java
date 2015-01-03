package GameEngine.components;

import GameEngine.core.Vector3f;
import GameEngine.rendering.ForwardSpot;

public class SpotLight extends PointLight {
	private float cutoff;
	
	public SpotLight(Vector3f color, float intensity, float constant,float linear,float exponent, float cutoff) {
		super(color,intensity,new Vector3f(constant,linear,exponent));
		setShader(ForwardSpot.getInstance());
		this.cutoff = cutoff;
	}
	public Vector3f getDirection() {
		return getTransform().getRotation().GetForward();
	}
	public float getCutoff() {
		return cutoff;
	}
	public void setCutoff(float cutoff) {
		this.cutoff = cutoff;
	}
}
