package org.tester.voxel.world;

import org.engine.core.CoreEngine;
import org.engine.rendering.material.Material;
import org.engine.rendering.model.Model;

public class Blocks {
	private static Material material = new Material("materials/texture.png");
	private static Model[] models = new Model[6];
	
	static{
		models[0] = getTop();
		models[1] = getLeft();
		models[2] = getBottom();
		models[3] = getRight();
		models[4] = getBack();
		models[5] = getForward();
	}
	
	public static Model getModel(int side){
		return models[side];
	}
	
	public static Material getMaterial(int type){
		return material;
	}
	
	public static int getRandomType(){
		return (int)(Math.random() * 2);
	}
	
	private static Model getTop(){
		float[] position = new float[]{-Block.WIDTH, Block.HEIGHT,  Block.DEPTH,
									   -Block.WIDTH, Block.HEIGHT, -Block.DEPTH,
									    Block.WIDTH, Block.HEIGHT, -Block.DEPTH,
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
		float[] position = new float[]{ Block.WIDTH,-Block.HEIGHT,  Block.DEPTH,
									    Block.WIDTH,-Block.HEIGHT, -Block.DEPTH,
									    Block.WIDTH, Block.HEIGHT, -Block.DEPTH,
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
		float[] position = new float[]{ Block.WIDTH,-Block.HEIGHT, -Block.DEPTH,
									   -Block.WIDTH,-Block.HEIGHT, -Block.DEPTH,
									   -Block.WIDTH, Block.HEIGHT, -Block.DEPTH,
									    Block.WIDTH, Block.HEIGHT, -Block.DEPTH};
		
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
		float[] position = new float[]{ Block.WIDTH,-Block.HEIGHT,  Block.DEPTH,
									   -Block.WIDTH,-Block.HEIGHT,  Block.DEPTH,
									   -Block.WIDTH, Block.HEIGHT,  Block.DEPTH,
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
		float[] position = new float[]{-Block.WIDTH,-Block.HEIGHT,  Block.DEPTH,
									   -Block.WIDTH,-Block.HEIGHT, -Block.DEPTH,
									   -Block.WIDTH, Block.HEIGHT, -Block.DEPTH,
									   -Block.WIDTH, Block.HEIGHT,  Block.DEPTH};
		
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
		float[] position = new float[]{-Block.WIDTH, -Block.HEIGHT,  Block.DEPTH,
									   -Block.WIDTH, -Block.HEIGHT, -Block.DEPTH,
									    Block.WIDTH, -Block.HEIGHT, -Block.DEPTH,
									    Block.WIDTH, -Block.HEIGHT,  Block.DEPTH};
		
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

}
