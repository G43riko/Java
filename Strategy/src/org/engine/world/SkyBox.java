package org.engine.world;

import org.engine.component.Camera;
import org.engine.component.GameComponent;
import org.engine.core.CoreEngine;
import org.engine.rendeing.material.Texture2D;
import org.engine.rendeing.model.Model;
import org.strategy.rendering.RenderingEngineStrategy;

import glib.util.vector.GVector3f;

public class SkyBox extends GameComponent{
	private static int size = (int)(Math.sqrt(1000 * 1000 / 4));
	private Texture2D texture;
	private Model model = getBox(1,1,1);
	private Camera camera;
	private GVector3f rotationSpeed = new GVector3f(0, 0.001f, 0);
	
	//CONSTRUCTORS
	
	public SkyBox(Camera camera) {
		this(camera, "skyHD2.jpg");
	}
	
	public SkyBox(Camera camera, String fileName) {
		super(GameComponent.SKY_BOX);
		texture = new Texture2D(fileName);
		setScale(new GVector3f(size));
		this.camera = camera;
//		System.out.println(this);
	}
	
	//OVERRIDES
	
	public void update(){
		rotate(rotationSpeed);
		setPosition(camera.getPosition());
	}
	
	public void render(RenderingEngineStrategy renderer){
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
		
		int[] indices ={0,1,3,	
						3,1,2,	
						
						7,5,4,
						6,5,7,
						
						8,9,11,
						11,9,10,
						
						15,13,12,
						14,13,15,
						
						16,17,19,
						19,17,18,
						
						23,21,20,
						22,21,23};
		
		return CoreEngine.getLoader().loadToVAO(vertices, texture, indices);
	}

	public Texture2D getTexture() {
		return texture;
	}

	public Model getModel() {
		return model;
	}

	//SETTERS
	
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	
	public void setRotationSpeed(GVector3f rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}

	public void setTexture(Texture2D texture) {
		this.texture = texture;
	}
}
