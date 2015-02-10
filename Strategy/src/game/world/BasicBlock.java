package game.world;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import game.main.Loader;
import game.object.GameObject;
import game.rendering.Vertex;
import game.rendering.material.Material;
import game.rendering.material.Texture2D;
import game.rendering.model.Model;
import game.util.Maths;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class BasicBlock extends GameObject{
	private int width = Block.WIDTH;
	private int height = Block.HEIGHT;
	private int depth = Block.DEPTH;
	private int type;
	private GVector3f[][] points = new GVector3f[][]{ new GVector3f[]{new GVector3f( Block.WIDTH, Block.HEIGHT, Block.DEPTH),
																	  new GVector3f( Block.WIDTH, Block.HEIGHT,-Block.DEPTH),
																	  new GVector3f(-Block.WIDTH, Block.HEIGHT, Block.DEPTH)},
																	  
													  new GVector3f[]{new GVector3f(-Block.WIDTH, Block.HEIGHT, Block.DEPTH),
																	  new GVector3f(-Block.WIDTH, Block.HEIGHT,-Block.DEPTH),
																	  new GVector3f(-Block.WIDTH,-Block.HEIGHT, Block.DEPTH)},
																	  
													  new GVector3f[]{new GVector3f( Block.WIDTH,-Block.HEIGHT, Block.DEPTH),
																	  new GVector3f( Block.WIDTH,-Block.HEIGHT,-Block.DEPTH),
																	  new GVector3f(-Block.WIDTH,-Block.HEIGHT, Block.DEPTH)},
																	  
													  new GVector3f[]{new GVector3f( Block.WIDTH, Block.HEIGHT, Block.DEPTH),
																	  new GVector3f( Block.WIDTH, Block.HEIGHT,-Block.DEPTH),
																	  new GVector3f( Block.WIDTH,-Block.HEIGHT, Block.DEPTH)},
																	  
													  new GVector3f[]{new GVector3f( Block.WIDTH, Block.HEIGHT, Block.DEPTH),
																	  new GVector3f( Block.WIDTH,-Block.HEIGHT, Block.DEPTH),
																	  new GVector3f(-Block.WIDTH, Block.HEIGHT, Block.DEPTH)},
																	  
													  new GVector3f[]{new GVector3f( Block.WIDTH, Block.HEIGHT,-Block.DEPTH),
																	  new GVector3f( Block.WIDTH,-Block.HEIGHT,-Block.DEPTH),
																	  new GVector3f(-Block.WIDTH, Block.HEIGHT,-Block.DEPTH)}};
	
	
	private boolean active = true;
	
	public GVector3f getPoint(int i, int j){
		return points[i][j];
	}
	
	public BasicBlock(GVector3f position, int blockType) {
		super(position, 8);
		this.type = blockType;
	}
	
	public static Model getTop(){
		float[] position = new float[]{-Block.WIDTH, Block.HEIGHT,  Block.DEPTH,
									   -Block.WIDTH, Block.HEIGHT, -Block.DEPTH,
									    Block.WIDTH, Block.HEIGHT, -Block.DEPTH,
									    Block.WIDTH, Block.HEIGHT,  Block.DEPTH};
		
		int[] indices = new int[]{0,1,3,
								  3,1,2};
		
		float[] texture = new float[]{0,0,
									  0,Block.DEPTH,
									  Block.WIDTH,Block.DEPTH,
									  Block.WIDTH,0};
		
		float[] normals = {0.0000f,  1.0000f,  0.0000f,
				 		   0.0000f,  1.0000f,  0.0000f,
				 		   0.0000f,  1.0000f,  0.0000f,
				 		   0.0000f,  1.0000f,  0.0000f};
		return new Loader().loadToVAO(position, texture, normals, indices);
	}
	
	public static Model getLeft(){
		float[] position = new float[]{ Block.WIDTH,-Block.HEIGHT,  Block.DEPTH,
									    Block.WIDTH,-Block.HEIGHT, -Block.DEPTH,
									    Block.WIDTH, Block.HEIGHT, -Block.DEPTH,
									    Block.WIDTH, Block.HEIGHT,  Block.DEPTH};
		
		int[] indices = new int[]{3,1,0,
								  2,1,3};
		
		float[] texture = new float[]{0,0,
									  0,Block.DEPTH,
									  Block.WIDTH,Block.DEPTH,
									  Block.WIDTH,0};
		
		float[] normals = {1.0000f,  0.0000f,  0.0000f,
				 		   1.0000f,  0.0000f,  0.0000f,
				 		   1.0000f,  0.0000f,  0.0000f,
				 		   1.0000f,  0.0000f,  0.0000f};
		return new Loader().loadToVAO(position, texture, normals, indices);
	}
	
	public static Model getForward(){
		float[] position = new float[]{ Block.WIDTH,-Block.HEIGHT, -Block.DEPTH,
									   -Block.WIDTH,-Block.HEIGHT, -Block.DEPTH,
									   -Block.WIDTH, Block.HEIGHT, -Block.DEPTH,
									    Block.WIDTH, Block.HEIGHT, -Block.DEPTH};
		
		int[] indices = new int[]{3,1,0,
								  2,1,3};
		
		float[] texture = new float[]{0,0,
									  0,Block.DEPTH,
									  Block.WIDTH,Block.DEPTH,
									  Block.WIDTH,0};
		
		float[] normals = {0.0000f,  0.0000f,  -1.0000f,
				 		   0.0000f,  0.0000f,  -1.0000f,
				 		   0.0000f,  0.0000f,  -1.0000f,
				 		   0.0000f,  0.0000f,  -1.0000f};
		return new Loader().loadToVAO(position, texture, normals, indices);
	}
	
	public static Model getBack(){
		float[] position = new float[]{ Block.WIDTH,-Block.HEIGHT,  Block.DEPTH,
									   -Block.WIDTH,-Block.HEIGHT,  Block.DEPTH,
									   -Block.WIDTH, Block.HEIGHT,  Block.DEPTH,
									    Block.WIDTH, Block.HEIGHT,  Block.DEPTH};
		
		int[] indices = new int[]{0,1,3,
								  3,1,2};
		
		float[] texture = new float[]{0,0,
									  0,Block.DEPTH,
									  Block.WIDTH,Block.DEPTH,
									  Block.WIDTH,0};
		
		float[] normals = {0.0000f,  0.0000f,  1.0000f,
				 		   0.0000f,  0.0000f,  1.0000f,
				 		   0.0000f,  0.0000f,  1.0000f,
				 		   0.0000f,  0.0000f,  1.0000f};
		return new Loader().loadToVAO(position, texture, normals, indices);
	}
	
	public static Model getRight(){
		float[] position = new float[]{-Block.WIDTH,-Block.HEIGHT,  Block.DEPTH,
									   -Block.WIDTH,-Block.HEIGHT, -Block.DEPTH,
									   -Block.WIDTH, Block.HEIGHT, -Block.DEPTH,
									   -Block.WIDTH, Block.HEIGHT,  Block.DEPTH};
		
		int[] indices = new int[]{0,1,3,
								  3,1,2};
		
		float[] texture = new float[]{0,0,
									  0,Block.DEPTH,
									  Block.WIDTH,Block.DEPTH,
									  Block.WIDTH,0};
		
		float[] normals = {-1.0000f,  0.0000f,  0.0000f,
				 		   -1.0000f,  0.0000f,  0.0000f,
				 		   -1.0000f,  0.0000f,  0.0000f,
				 		   -1.0000f,  0.0000f,  0.0000f};
		return new Loader().loadToVAO(position, texture, normals, indices);
	}
	
	public static Model getBottom(){
		float[] position = new float[]{-Block.WIDTH, -Block.HEIGHT,  Block.DEPTH,
									   -Block.WIDTH, -Block.HEIGHT, -Block.DEPTH,
									    Block.WIDTH, -Block.HEIGHT, -Block.DEPTH,
									    Block.WIDTH, -Block.HEIGHT,  Block.DEPTH};
		
		int[] indices = new int[]{0,1,3,
								  3,1,2};
		
		float[] texture = new float[]{0,0,
									  0,Block.DEPTH,
									  Block.WIDTH,Block.DEPTH,
									  Block.WIDTH,0};
		
		float[] normals = {0.0000f, -1.0000f,  0.0000f,
				 		   0.0000f, -1.0000f,  0.0000f,
				 		   0.0000f, -1.0000f,  0.0000f,
				 		   0.0000f, -1.0000f,  0.0000f};
		return new Loader().loadToVAO(position, texture, normals, indices);
	}

	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Texture2D getDiffuse(){
		return ((Material) Block.blockDatas.get(type).get("mat")).getDiffuse();
	}
}
