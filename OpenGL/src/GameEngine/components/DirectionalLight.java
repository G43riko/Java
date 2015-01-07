package GameEngine.components;

import GameEngine.core.Vector3f;
import GameEngine.rendering.RenderingEngine;
import GameEngine.rendering.Shader;

public class DirectionalLight extends BaseLight{
	private Vector3f direction;
	
	public DirectionalLight(Vector3f color, float intensity, Vector3f direction){
		super(color,intensity);
		this.direction = direction.Normalized();
		setShader(new Shader("forward-directional"));
	}

	public Vector3f getDirection() {
		return direction;
	}

	public void setDirection(Vector3f direction) {
		this.direction = direction;
	}
}
