package Textures;

public class ModelTexture {
	private int textureID;
	
	private float shineDamper = 1;
	private float refectivity = 0;
	
	private boolean hasTransparency = false;
	private boolean useFakeLighting = false;
	
	public ModelTexture(int id){
		textureID = id;
	}

	public int getTextureID() {
		return textureID;
	}

	public float getShineDamper() {
		return shineDamper;
	}

	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	}

	public float getRefectivity() {
		return refectivity;
	}

	public void setRefectivity(float refectivity) {
		this.refectivity = refectivity;
	}

	public boolean isHasTransparency() {
		return hasTransparency;
	}

	public void setHasTransparency(boolean hasTransparency) {
		this.hasTransparency = hasTransparency;
	}

	public boolean isUseFakeLighting() {
		return useFakeLighting;
	}

	public void setUseFakeLighting(boolean useFakeLighting) {
		this.useFakeLighting = useFakeLighting;
	}
}
