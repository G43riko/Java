package com.voxel.component.light;

import glib.util.vector.GVector3f;

import com.voxel.rendering.shader.Shader;


public class DirectionalLight extends BaseLight{
private GVector3f direction;
	
	public DirectionalLight(GVector3f color, float intensity, GVector3f direction){
		super(color,intensity);
		this.direction = direction.normalize();
		//setShader(new Shader("forward-directional-noNormals"));
		setShader(new Shader("forward-directional"));
	}

	public GVector3f getDirection() {
		return direction;
	}

	public void setDirection(GVector3f direction) {
		this.direction = direction;
	}
}
