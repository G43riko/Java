package game.rendering.material;

public class Material {
	private final static String DEFAULT_TEXTURE = "unknown.jpg";
	private Texture2D diffuse;
//	private Texture2D normal = null;
//	private Texture2D bump = null;
//	private Texture2D ambientOcclusion = null;
	
	private float specularIntensity;
	private float specularPower;
	
	public Material(){
		this(new Texture2D(DEFAULT_TEXTURE),8,1);
	}
	
	public Material(String name){
		this(new Texture2D(name),8,1);
	}
	
	public Material(Texture2D diffuse, int specularIntensity, int specularPower) {
		this.diffuse = diffuse;
		this.specularIntensity = specularIntensity;
		this.specularPower = specularPower;
	}

	public Texture2D getDiffuse() {return diffuse;}
	public float getSpecularIntensity() {return specularIntensity;}
	public float getSpecularPower() {return specularPower;}
}
