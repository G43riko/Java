package com.voxel.rendering.material;

public class Material{

	private Texture texture;
	private float specularIntensity;
	private float specularPower;
	
	public Material(){
		this("diffuse", new Texture("unknown.jpg"),1,8);
	}
	
	public Material(String name,Texture texture){
		this(name,texture,8,1);
	}
	
	public Material(String name, Texture texture, float specularIntensity,float specularPower){
		this.texture = texture;
		this.specularIntensity = specularIntensity;
		this.specularPower = specularPower;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public float getSpecularIntensity() {
		return specularIntensity;
	}

	public void setSpecularIntensity(float specularIntensity) {
		this.specularIntensity = specularIntensity;
	}

	public float getSpecularPower() {
		return specularPower;
	}

	public void setSpecularPower(float specularPower) {
		this.specularPower = specularPower;
	}
}
