package org.engine.rendering.material;

public class Material {
	private Texture2D diffuse;
	
	private float specularIntensity = 1;
	private float specularPower = 8;
	
	private Texture2D normal;
	private Texture2D bump;
	private Texture2D specular;
	private Texture2D aplha;
	
	//CONSTRUCTORS
	
	public Material(String fileName){
		diffuse = new Texture2D(fileName);
	}
	
	public Material(Texture2D diffuse){
		this.diffuse = diffuse;
	}
	
	public Material(Texture2D diffuse, Texture2D specular){
		this.diffuse = diffuse;
		this.specular = specular;
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

	public Texture2D getSpecular() {
		return specular;
	}

	public Texture2D getNormal() {
		return normal;
	}

	public Texture2D getBump() {
		return bump;
	}

	public Texture2D getAplha() {
		return aplha;
	}
}
