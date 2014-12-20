package terrains;


import main.Loader;
import models.RawModel;
import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import textures.ModelTexture;
import utils.FileLoader;
import utils.OBJLoader;

public class Block extends Entity{
	public static int WIDTH = 9;
	public static int HEIGHT = 3;
	public static int DEPTH = 9;
	private int type;
	private static RawModel model;
	private static ModelTexture[] textures = new ModelTexture[]{
		new ModelTexture(FileLoader.textureLoader("dirt.jpg")),
		new ModelTexture(FileLoader.textureLoader("stall.png"))
	};
	
	public Block(float x, float y, float z,int type){
		super(new TexturedModel(model,textures[type]), x, y, z, 0, 0, 0, 1);
	}
	

	public static void init(Loader loader){
		model = OBJLoader.loadObjModel("b", loader);
	}
	
	public Block(float x, float y, float z) {
		this(x,y,z,0);
	}

	public int getType() {
		return type;
	}
}
