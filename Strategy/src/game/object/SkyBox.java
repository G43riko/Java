package game.object;

import game.rendering.RenderingEngine;
import game.rendering.material.Texture2D;
import game.rendering.model.Model;
import game.util.Loader;
import glib.util.vector.GVector3f;

public class SkyBox extends GameObject{
	private static int size = (int)(Math.sqrt(1000*1000/4));
	private Texture2D texture = new Texture2D("skyHD2.jpg");
	private Model model = getBox(1,1,1);
	private Camera camera;
	private float rotationSpeed = 0.001f;
	
	//CONSTRUCTORS
	
	public SkyBox(Camera camera) {
		super(GameObject.SKY_BOX);
		setScale(new GVector3f(size,size,size));
		this.camera = camera;
	}
	
	//OVERRIDES
	
	public void update(){
		rotate(new GVector3f(0,rotationSpeed,0));
		setPosition(camera.getPosition());
	}
	
	public void render(RenderingEngine renderer){
		renderer.renderSky(this);
	}
	
	//GETTERS
	
	public static Model getBox(int w, int h, int d){
		float[] vertices = new float[]{			
				-w,h,-d,	
				-w,-h,-d,	
				w,-h,-d,	
				w,h,-d,		
				
				-w,h,d,	
				-w,-h,d,	
				w,-h,d,	
				w,h,d,
				
				w,h,-d,	
				w,-h,-d,	
				w,-h,d,	
				w,h,d,
				
				-w,h,-d,	
				-w,-h,-d,	
				-w,-h,d,	
				-w,h,d,
				
				-w,h,d,
				-w,h,-d,
				w,h,-d,
				w,h,d,
				
				-w,-h,d,
				-w,-h,-d,
				w,-h,-d,
				w,-h,d
				};
		float t = 0.125f;  
		float[] texture ={0.25f,0.25f+t,
				 		  0.25f,0.50f+t,
				 		  0.50f,0.50f+t,
				 		  0.50f,0.25f+t,
				 		  
				 		  1.00f,0.25f+t,
				 		  1.00f,0.50f+t,
				 		  0.75f,0.50f+t,
				 		  0.75f,0.25f+t,
				 		  
				 		  0.50f,0.25f+t,
				 		  0.50f,0.50f+t,
				 		  0.75f,0.50f+t,
				 		  0.75f,0.25f+t,
				 		  
				 		  0.25f,0.25f+t,
				 		  0.25f,0.50f+t,
				 		  0.00f,0.50f+t,
				 		  0.00f,0.25f+t,
				 		  
				 		  0.25f,0.00f+t,
				 		  0.25f,0.25f+t,
				 		  0.50f,0.25f+t,
				 		  0.50f,0.00f+t,
				 		  
				 		  0.25f,0.75f+t,
				 		  0.25f,0.50f+t,
				 		  0.50f,0.50f+t,
				 		  0.50f,0.75f+t,};
		
		int[] indices ={3,1,0,	
						2,1,3,	
						
						4,5,7,
						7,5,6,
						
						11,9,8,
						10,9,11,
						
						12,13,15,
						15,13,14,
						
						19,17,16,
						18,17,19,
						
						20,21,23,
						23,21,22};
		
		return new Loader().loadToVAO(vertices, texture, indices);
	}

	public Texture2D getTexture() {
		return texture;
	}

	public Model getModel() {
		return model;
	}
}
