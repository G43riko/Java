package org.engine.rendeing.material;

public class Material {
	private final static String DEFAULT_DIFFUSE = "unknown.jpg";
	private final static String DEFAULT_NORMAL = "normal.jpg";
	private Texture2D diffuse;
	private Texture2D normal;
//	private Texture2D bump = null;
//	private Texture2D dudv = null;
//	private Texture2D ambientOcclusion = null;
	
	private float specularIntensity;
	private float specularPower;
	
	//CONSTRUCTORS
	public Material(Texture2D texture) {
		this(texture, null, 1, 8);
	}
	
	public Material(){
		this(new Texture2D(DEFAULT_DIFFUSE),new Texture2D(DEFAULT_NORMAL),1,8);
	}
	
	public Material(String name){
		this(new Texture2D(name), null, 1, 8);
	}
	public Material(String name, String normal){
		this(new Texture2D(name), null, 1, 8);
	}
	public Material(Texture2D diffuse, int specularIntensity, int specularPower) {
		this(diffuse, null, specularIntensity, specularPower);
	}
	
	public Material(Texture2D diffuse, Texture2D normal, int specularIntensity, int specularPower) {
		this.diffuse = diffuse;
		this.normal = normal;
		this.specularIntensity = specularIntensity;
		this.specularPower = specularPower;
	}

	//GETTERS

	public Texture2D getDiffuse() {return diffuse;}
	public Texture2D getNormal() {return normal;}
	
	//SETTERS
	
	public float getSpecularIntensity() {return specularIntensity;}
	public float getSpecularPower() {return specularPower;}
}
