package game.object;

import game.rendering.RenderingEngine;
import glib.util.vector.GVector3f;

public abstract class GameObject {
	private GVector3f position;
	private GVector3f rotation;
	private GVector3f scale;
	private int type;
	/*
	 * 1 - Camera
	 * 2 - Entity
	 * 3 - SkyBox
	 * 4 - DirectionalLight
	 * 5 - PointLight
	 * 6 - SpotLight
	 * 7 - Particle
	 */
	public GameObject(GVector3f position, GVector3f rotation, GVector3f scale, int type) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.type = type;
	}

	
	public void move(GVector3f vec){
		position = position.add(vec);
	}
	
	public void rotate(GVector3f vec){
		rotation = rotation.add(vec);
	}

	public GVector3f getPosition() {
		return position;
	}
	
	public void setPosition(GVector3f position) {
		this.position = position;
	}
	
	public GVector3f getRotation() {
		return rotation;
	}
	
	public void setRotation(GVector3f rotation) {
		this.rotation = rotation;
	}
	
	public GVector3f getScale() {
		return scale;
	}
	
	public void setScale(GVector3f scale) {
		this.scale = scale;
	}
	
	public void render(RenderingEngine renderingEngine) {}
	
	public void input() {}

	public void update() {}

	public int getType() {
		return type;
	}
	
}
