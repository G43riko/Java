package org.engine.rendering.material;

import java.beans.ConstructorProperties;

public class Material {
	private Texture2D diffuse;
	
	private float specularIntensity = 1;
	private float specularPower = 8;
	
//	private Texture2D normall;
//	private Texture2D bumb;
//	private Texture2D specular;
//	private Texture2D aplha;
	
	//CONSTRUCTORS
	
	public Material(String fileName){
		diffuse = new Texture2D(fileName);
	}
	
	public Material(Texture2D diffuse){
		this.diffuse = diffuse;
	}

	//GETTERS
	
	public Texture2D getDiffuse() {
		return diffuse;
	}

	public float getSpecularIntensity() {
		return specularIntensity;
	}

	public float getSpecularPower() {
		return specularPower;
	}
}
