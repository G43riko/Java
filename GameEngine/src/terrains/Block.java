package terrains;


import java.awt.Color;

import main.Loader;
import models.RawModel;
import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import shapes.threeDimensional.Box;
import shapes.twoDimensional.Rectangle;
import textures.ModelTexture;
import utils.FileLoader;
import utils.OBJLoader;


public class Block extends Entity{
	public static float WIDTH = 3f;
	public static float HEIGHT = 1f;
	public static float DEPTH = 3f;
	public static int maxTypes = 4;
	private int type,x,y,z;
	private boolean transparency;
	private Color color;
	private static RawModel model=null;
	private static TexturedModel[] models;
	private static Color[] colors = new Color[]{null,Color.ORANGE,Color.GREEN,Color.BLUE,Color.LIGHT_GRAY};
	
	public Block(int x, int y, int z,int type){
		super(models[type], x*Block.WIDTH*2,y*Block.HEIGHT*2, z*Block.DEPTH*2, 0, 0, 0, 1);
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = type;
		color = colors[type];
	}
	
	public static void init(Loader loader){
		if(model!=null){
			return;
		}
		model = Box.getModel(loader, WIDTH, HEIGHT, DEPTH);
		models = new TexturedModel[]{null,
			new TexturedModel(model,new ModelTexture("dirt.jpg")),
			new TexturedModel(model,new ModelTexture("grass.jpg")),
			new TexturedModel(model,new ModelTexture("water.jpg")),
			new TexturedModel(model,new ModelTexture("rock.jpg"))
		};
	}
	
	public Block(int x, int y, int z) {
		this(x,y,z,0);
	}

	public int getType() {
		return type;
	}
	
	public int getSurX(){
		return x;
	}
	
	public int getSurY(){
		return y;
	}
	
	public int getSurZ(){
		return z;
	}

	public void setType(int type) {
		this.type = type;
		texturedModel = models[type];
	}

	public Color getColor() {
		return color;
	}
}
