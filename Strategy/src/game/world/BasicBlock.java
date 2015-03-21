package game.world;


import org.json.JSONObject;

import game.object.GameObject;
import game.rendering.material.Material;
import game.rendering.model.Model;
import game.util.Loader;
import glib.util.vector.GVector3f;

public class BasicBlock extends GameObject{
	protected int type;
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

	protected boolean active = true;
	
	//CONSTRUCTORS
	
	public BasicBlock(GVector3f position, int blockType) {
		super(position, GameObject.BOX);
		this.type = blockType;
	}
	
	//OTHERS

	public JSONObject toJSON(){
		JSONObject o = new JSONObject();
		o.put("posX", getPosition().getX());
		o.put("posY", getPosition().getY());
		o.put("posZ", getPosition().getZ());
		o.put("typ", type);
		return o;
	}
	
	//SETTERS
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	//GETTERS
	
	public GVector3f getPoint(int i, int j){
		return points[i][j];
	}
	
	public static Model getTop(){
		float[] position = new float[]{-Block.WIDTH, Block.HEIGHT,  Block.DEPTH,
									   -Block.WIDTH, Block.HEIGHT, -Block.DEPTH,
									    Block.WIDTH, Block.HEIGHT, -Block.DEPTH,
									    Block.WIDTH, Block.HEIGHT,  Block.DEPTH};
		
		int[] indices = new int[]{0,1,3,
								  3,1,2};
		
		float[] texture = new float[]{0,0,
									  Block.DEPTH,0,
									  Block.DEPTH,Block.WIDTH,
									  0, Block.WIDTH};
		
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
									  Block.DEPTH,0,
									  Block.DEPTH,Block.WIDTH,
									  0, Block.WIDTH};
		
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
									  Block.DEPTH,0,
									  Block.DEPTH,Block.WIDTH,
									  0, Block.WIDTH};
		
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
									  Block.DEPTH,0,
									  Block.DEPTH,Block.WIDTH,
									  0, Block.WIDTH};	
		
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
									  Block.DEPTH,0,
									  Block.DEPTH,Block.WIDTH,
									  0, Block.WIDTH};
		
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
		
		int[] indices = new int[]{3,1,0,
								  2,1,3};
		
		float[] texture = new float[]{0,0,
									  Block.DEPTH,0,
									  Block.DEPTH,Block.WIDTH,
									  0, Block.WIDTH};
		
		float[] normals = {0.0000f, -1.0000f,  0.0000f,
				 		   0.0000f, -1.0000f,  0.0000f,
				 		   0.0000f, -1.0000f,  0.0000f,
				 		   0.0000f, -1.0000f,  0.0000f};
		return new Loader().loadToVAO(position, texture, normals, indices);
	}

	public boolean isActive() {
		return active;
	}
	
	public Material getMaterial(){
		return (Material) Block.blockDatas.get(type).get("mat");
	}
	
	public int getBlockType() {
		return type;
	}
}
