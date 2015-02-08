package game.world;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import game.main.Loader;
import game.object.GameObject;
import game.rendering.material.Material;
import game.rendering.material.Texture2D;
import game.rendering.model.Model;
import game.util.Maths;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector3f;

public class BasicBlock extends GameObject{
	private int width = Block.WIDTH;
	private int height = Block.HEIGHT;
	private int depth = Block.DEPTH;
	private int type;
	
	private boolean active = true;
	
	public BasicBlock(GVector3f position, int blockType) {
		super(position, 8);
		this.type = blockType;
	}
	
	public GMatrix4f getTransformationMatrix(int i){
		Matrix4f trans = Maths.createTransformationMatrix(new Vector3f(getPosition().getX(),getPosition().getY(),getPosition().getZ()), 
				 getRotation().getX(), getRotation().getY(), getRotation().getZ(), getScale().getX());;
		switch(i){
			case 1:
				trans.rotate((float)Math.toRadians(90), new Vector3f(0,0,1));
				return Maths.MatrixToGMatrix(trans);
			case 2:
				trans.rotate((float)Math.toRadians(180), new Vector3f(0,0,1));
				return Maths.MatrixToGMatrix(trans);
			case 3:
				trans.rotate((float)Math.toRadians(270), new Vector3f(0,0,1));
				return Maths.MatrixToGMatrix(trans);
			case 4:
				trans.rotate((float)Math.toRadians(90), new Vector3f(1,0,0));
				return Maths.MatrixToGMatrix(trans);
			case 5:
				trans.rotate(-(float)Math.toRadians(90), new Vector3f(1,0,0));
				return Maths.MatrixToGMatrix(trans);
			default:
				return Maths.MatrixToGMatrix(trans);
		}
	}
	
	public static Model getSide(){
		float[] vertices = new float[]{-Block.WIDTH, Block.HEIGHT,  Block.DEPTH,
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
		
		return new Loader().loadToVAO(vertices, texture, normals, indices);
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
