package terrains;


import main.Loader;
import models.RawModel;
import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import shapes.threeDimensional.Box;
import shapes.twoDimensional.Squad;
import textures.ModelTexture;
import utils.FileLoader;
import utils.OBJLoader;

public class Block extends Entity{
	public static float WIDTH = 3f;
	public static float HEIGHT = 1f;
	public static float DEPTH = 3f;
	private int type;
	private static RawModel model,top,bottom,left,right,front,back;
	private static ModelTexture[] textures = new ModelTexture[]{
		null,
		new ModelTexture(FileLoader.textureLoader("dirt.jpg")),
		new ModelTexture(FileLoader.textureLoader("stall.png"))
	};
	
	public Block(float x, float y, float z,int type){
		super(new TexturedModel(model,textures[type]), x, y, z, 0, 0, 0, 1);
		this.type = type;
	}
	

	public static void init(Loader loader){
//		model = OBJLoader.loadObjModel("box", loader);
		model = Box.getModel(loader, WIDTH, HEIGHT, DEPTH);
//		model = OBJLoader.loadObjModel("plane", loader);
	}
	
	public Block(float x, float y, float z) {
		this(x,y,z,0);
	}

	public int getType() {
		return type;
	}
}
