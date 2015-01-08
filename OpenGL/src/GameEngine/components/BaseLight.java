package GameEngine.components;

import GameEngine.core.CoreEngine;
import GameEngine.core.Transform;
import GameEngine.core.Vector3f;
import GameEngine.rendering.RenderingEngine;
import GameEngine.rendering.Shader;

public class BaseLight extends GameComponent{
	private Transform transform;
	private Vector3f color;
	private float intensity;
	private Shader shader;
	
	public BaseLight(Vector3f color, float intensity){
		this.color= color;
		this.intensity = intensity;
	}

	public Vector3f getColor() {
		return color;
	}
	
	public Shader getShader() {
		return shader;
	}
	
	public void addToEngine(CoreEngine engine){
		engine.getRenderingEngine().addLight(this);
	};
	
	public void setShader(Shader shader){
		this.shader = shader;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

	public float getIntensity() {
		return intensity;
	}

	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}
}
