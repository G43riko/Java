package game.object.world;

import game.object.GameObject;
import game.rendering.material.Material;
import game.rendering.model.Model;
import game.util.Loader;
import glib.util.vector.GVector2f;

public class Plane extends GameObject{
	private static float size = 1000; 
	
	public Plane() {
		super(getTop(new GVector2f(size,size)), new Material("texture.png"));
	}
	
	private static Model getTop(GVector2f s){
		float[] position = new float[]{-s.getX(), 0,  s.getY(),
									   -s.getX(), 0, -s.getY(),
									    s.getX(), 0, -s.getY(),
									    s.getX(), 0,  s.getY()};
		
		int[] indices = new int[]{3,1,0,
								  2,1,3};
		
		float[] texture = new float[]{0,0,
									  s.getX(),0,
									  s.getX(),s.getY(),
									  0, s.getY()};
		
		float[] normals = {0.0000f,  1.0000f,  0.0000f,
				 		   0.0000f,  1.0000f,  0.0000f,
				 		   0.0000f,  1.0000f,  0.0000f,
				 		   0.0000f,  1.0000f,  0.0000f};
		
		return new Loader().loadToVAO(position, texture, normals, indices);
	}

	
	public Model getModel() {
		return model;
	}

	
	public Material getMaterial() {
		return material;
	}
}
