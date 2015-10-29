package org.tester.bomber.level;

import java.util.HashMap;

import org.engine.app.GameAble;
import org.engine.component.object.GameObject;
import org.engine.core.CoreEngine;
import org.engine.rendering.RenderingEngine;
import org.engine.rendering.material.Material;
import org.engine.rendering.model.Model;
import org.engine.utils.resource.OBJLoader;

import glib.util.vector.GVector3f;

public class Block extends GameObject{
	public final static HashMap<String, BlockType> blocks = new HashMap<String, BlockType>(); 

	private static HashMap<String, Model> models = new HashMap<String, Model>(); 
	
	public final static int WIDTH = 1;
	public final static int HEIGHT = 1;
	public final static int DEPTH = 1;
	
	public final static String NOTHING = "nothing";
	public final static String IRON = "iron";
	public final static String WOOD = "wood";
	
	private String type;
	
	static{
		blocks.put(NOTHING, new BlockType(null, 0));
		blocks.put(IRON, new BlockType(new Material("materials/block_iron.png"), 1));
		blocks.put(WOOD, new BlockType(new Material("materials/block_wood.png"), 5));
		
		models.put("top", getTop());
		models.put("left", getLeft());
		models.put("back", getBack());
		models.put("right", getRight());
		models.put("forward", getForward());
	}
	
	public Block(GameAble parent) {
		super(parent, new Material("materials/texture.png"), OBJLoader.loadObjModel("box"));
	}

	public Block(GameAble parent, int type, GVector3f position) {
		super(parent, blocks.get(getNameFromInt(type)).getMaterial(), null);
		this.type = getNameFromInt(type);
		setPosition(position);
	}

	@Override
	public void render(RenderingEngine renderingEngine) {
		if(type == NOTHING)
			return;
		renderingEngine.renderBlockBomber(this);
	}
	
	private static String getNameFromInt(int type){
		switch(type){
			case 1 :
				return WOOD;
			case 2 :
				return IRON;
			default :
				return NOTHING;
		}
	}
	
	public Model getModel(String name){
		return models.get(name);
	}
	
	private static Model getTop(){
		float[] position = new float[]{0, Block.HEIGHT,  Block.DEPTH,
									   0, Block.HEIGHT, 0,
									    Block.WIDTH, Block.HEIGHT, 0,
									    Block.WIDTH, Block.HEIGHT,  Block.DEPTH};
		
		int[] indices = new int[]{3,1,0,
								  2,1,3};
		
		float[] texture = new float[]{0,0,
									  Block.DEPTH,0,
									  Block.DEPTH,Block.WIDTH,
									  0, Block.WIDTH};
		
		float[] normals = {0.0000f,  1.0000f,  0.0000f,
				 		   0.0000f,  1.0000f,  0.0000f,
				 		   0.0000f,  1.0000f,  0.0000f,
				 		   0.0000f,  1.0000f,  0.0000f};
		
		return CoreEngine.getLoader().loadToVAO(position, texture, normals, indices);
	}
	
	private static Model getLeft(){
		float[] position = new float[]{ Block.WIDTH,0,  Block.DEPTH,
									    Block.WIDTH,0, 0,
									    Block.WIDTH, Block.HEIGHT, 0,
									    Block.WIDTH, Block.HEIGHT,  Block.DEPTH};
		
		int[] indices = new int[]{0,1,3,
								  3,1,2};
		
		float[] texture = new float[]{0,0,
									  Block.DEPTH,0,
									  Block.DEPTH,Block.WIDTH,
									  0, Block.WIDTH};
		
		float[] normals = {1.0000f,  0.0000f,  0.0000f,
				 		   1.0000f,  0.0000f,  0.0000f,
				 		   1.0000f,  0.0000f,  0.0000f,
				 		   1.0000f,  0.0000f,  0.0000f};
		return CoreEngine.getLoader().loadToVAO(position, texture, normals, indices);
	}
	
	private static Model getForward(){
		float[] position = new float[]{ Block.WIDTH,0, 0,
									   0,0, 0,
									   0, Block.HEIGHT, 0,
									    Block.WIDTH, Block.HEIGHT, 0};
		
		int[] indices = new int[]{0,1,3,
								  3,1,2};
		
		float[] texture = new float[]{0,0,
									  Block.DEPTH,0,
									  Block.DEPTH,Block.WIDTH,
									  0, Block.WIDTH};
		
		float[] normals = {0.0000f,  0.0000f,  -1.0000f,
				 		   0.0000f,  0.0000f,  -1.0000f,
				 		   0.0000f,  0.0000f,  -1.0000f,
				 		   0.0000f,  0.0000f,  -1.0000f};
		return CoreEngine.getLoader().loadToVAO(position, texture, normals, indices);
	}
	
	private static Model getBack(){
		float[] position = new float[]{ Block.WIDTH,0,  Block.DEPTH,
									   0,0,  Block.DEPTH,
									   0, Block.HEIGHT,  Block.DEPTH,
									    Block.WIDTH, Block.HEIGHT,  Block.DEPTH};
		
		int[] indices = new int[]{3,1,0,
								  2,1,3};
		
		float[] texture = new float[]{0,0,
									  Block.DEPTH,0,
									  Block.DEPTH,Block.WIDTH,
									  0, Block.WIDTH};	
		
		float[] normals = {0.0000f,  0.0000f,  1.0000f,
				 		   0.0000f,  0.0000f,  1.0000f,
				 		   0.0000f,  0.0000f,  1.0000f,
				 		   0.0000f,  0.0000f,  1.0000f};
		return CoreEngine.getLoader().loadToVAO(position, texture, normals, indices);
	}
	
	private static Model getRight(){
		float[] position = new float[]{0,0,  Block.DEPTH,
									   0,0, 0,
									   0, Block.HEIGHT, 0,
									   0, Block.HEIGHT,  Block.DEPTH};
		
		int[] indices = new int[]{3,1,0,
								  2,1,3};
		
		float[] texture = new float[]{0,0,
									  Block.DEPTH,0,
									  Block.DEPTH,Block.WIDTH,
									  0, Block.WIDTH};
		
		float[] normals = {-1.0000f,  0.0000f,  0.0000f,
				 		   -1.0000f,  0.0000f,  0.0000f,
				 		   -1.0000f,  0.0000f,  0.0000f,
				 		   -1.0000f,  0.0000f,  0.0000f};
		return CoreEngine.getLoader().loadToVAO(position, texture, normals, indices);
	}
	
	private static Model getBottom(){
		float[] position = new float[]{0, 0,  Block.DEPTH,
									   0, 0, 0,
									    Block.WIDTH, 0, 0,
									    Block.WIDTH, 0,  Block.DEPTH};
		
		int[] indices = new int[]{0,1,3,
								  3,1,2};
		
		float[] texture = new float[]{0,0,
									  Block.DEPTH,0,
									  Block.DEPTH,Block.WIDTH,
									  0, Block.WIDTH};
		
		float[] normals = {0.0000f, -1.0000f,  0.0000f,
				 		   0.0000f, -1.0000f,  0.0000f,
				 		   0.0000f, -1.0000f,  0.0000f,
				 		   0.0000f, -1.0000f,  0.0000f};
		return CoreEngine.getLoader().loadToVAO(position, texture, normals, indices);
	}

	public String getType() {
		return type;
	}
}
