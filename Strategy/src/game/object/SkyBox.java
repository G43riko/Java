package game.object;

import javax.swing.text.Position;

import game.main.Loader;
import game.rendering.RenderingEngine;
import game.rendering.material.Texture2D;
import game.rendering.model.Model;
import glib.util.vector.GVector3f;

public class SkyBox extends Entity{
	private static int size = (int)(Math.sqrt(1000*1000/4));
	private static Texture2D texture = new Texture2D("skyHD.jpg");
	private static Model model = getBox(1,1,1);
	private Camera camera;
	
	public SkyBox(Camera camera) {
		super(new GVector3f(), new GVector3f(), new GVector3f(size,size,size),model,texture);
		this.camera = camera;
	}
	
	public void update(){
		rotate(new GVector3f(0,0.1,0));
		if(camera.move)
			setPosition(camera.getPosition());
	}
	
	public void render(RenderingEngine renderer){
		renderer.renderSky(this,RenderingEngine.skyShader);
	}
	
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
						4,5,7,
						7,5,6,
						8,9,11,
						11,9,10,
						12,13,15,
						15,13,14,	
						16,17,19,
						19,17,18,
						20,21,23,
						23,21,22};
		
		return new Loader().loadToVAO(vertices, texture, indices);
	}
}
