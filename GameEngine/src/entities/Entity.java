package entities;

import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;


public class Entity extends BasicEntity{
	private TexturedModel model;
	
	public Entity(TexturedModel model, Vector3f pos, Vector3f rot,float scale ){
		this(model, pos.x, pos.y, pos.z, rot.x, rot.y, rot.z, scale);
	}
	
	public Entity(TexturedModel model,float x, float y, float z, float rx, float ry, float rz, float scale) {
		super(x, y, z, rx, ry, rz, scale);
		this.model = model;
	}

	public TexturedModel getModel() {
		return model;
	}

}
