package terrains;

import object.GameObject2;
import main.Game2;
import models.RawModel;
import rendering.RenderingEngine;
import shapes.threeDimensional.Box;
import textures.ModelTexture;

public class Block2 extends GameObject2{
	public final static int DIRT = 1;
	public final static int GRASS = 2;
	public final static int WATER = 3;
	public final static int STONE = 3;
	
	public final static float WIDTH = 3;
	public final static float HEIGHT = 1f;
	public final static float DEPTH = 3f;
	private static RawModel[] models = new RawModel[]{Box.getTop(Game2.LOADER, WIDTH, HEIGHT, DEPTH, true),
													  Box.getRight(Game2.LOADER, WIDTH, HEIGHT, DEPTH, true),
													  Box.getBottom(Game2.LOADER, WIDTH, HEIGHT, DEPTH, true),
												      Box.getLeft(Game2.LOADER, WIDTH, HEIGHT, DEPTH, true),
												      Box.getBack(Game2.LOADER, WIDTH, HEIGHT, DEPTH, true),
												      Box.getFront(Game2.LOADER, WIDTH, HEIGHT, DEPTH, true)};
	private boolean[] sides = new boolean[]{true,true,true,true,true,true}; 
	
	private ModelTexture  texture;
	
	public Block2(){
		texture = new ModelTexture("grass.jpg");
	}
	
	public void render(RenderingEngine renderingEngine) {
		renderingEngine.renderBlock(this);
	}

	public ModelTexture getTexture() {
		return texture;
	}
	
	public boolean getSide(int i){
		return sides[i];
	}
	
	public RawModel getModel(int i){
		return models[i];
	}

}
