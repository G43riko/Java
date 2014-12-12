package entities;


public class TexturedModel {
	RawModel rawModel;
	Texture texture;
	
	public TexturedModel(RawModel rawModel, Texture texture){
		this.rawModel = rawModel;
		this.texture = texture;
	}

	public RawModel getRawModel() {
		return rawModel;
	}

	public Texture getTexture() {
		return texture;
	}
}
