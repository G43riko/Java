package game.object;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import game.main.Loader;
import game.rendering.RenderingEngine;
import game.rendering.material.Texture2D;
import game.rendering.model.Model;
import game.util.Maths;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector3f;

public class Box extends GameObject{
	public final static int WIDTH = 2;
	public final static int HEIGHT = 2;
	public final static int DEPTH = 2;
	
	public boolean active = true;
	public boolean[] sides;
	private Entity[] faces = new Entity[6];
	private Model model;
	private Texture2D texture;
	
	public Box(GVector3f position,int type) {
		super(position, new GVector3f(), new GVector3f(1,1,1), 8);
		model = getTop();
		texture = getTexture(type);
		sides = new boolean[]{true,true,true,true,true,true};
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
	public void update(){
		rotate(new GVector3f(1,1,1));
	}
	
	private Texture2D getTexture(int type) {
		return new Texture2D("texture.png");
	}

	public void render(RenderingEngine renderer){
		renderer.renderBox(this, RenderingEngine.entityShader);
	}
	
	public static Model getTop(){
		float[] vertices = new float[]{-WIDTH, WIDTH,  DEPTH,
									   -WIDTH, WIDTH, -DEPTH,
									    WIDTH, WIDTH, -DEPTH,
									    WIDTH, WIDTH,  DEPTH};
		
		int[] indices = new int[]{0,1,3,
								  3,1,2};
		
		float[] texture = new float[]{0,0,
									  0,DEPTH,
									  WIDTH,DEPTH,
									  WIDTH,0};
		
		float[] normals = {0.0000f,  1.0000f,  0.0000f,
				 		   0.0000f,  1.0000f,  0.0000f,
				 		   0.0000f,  1.0000f,  0.0000f,
				 		   0.0000f,  1.0000f,  0.0000f};
		
		return new Loader().loadToVAO(vertices, texture, normals, indices);
	}
	

	public Model getModel() {
		return model;
	}
	

	public Texture2D getTexture() {
		return texture;
	}
}
