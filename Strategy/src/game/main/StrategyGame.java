package game.main;

import game.core.CoreGame;
import game.object.Box;
import game.object.Camera;
import game.object.Entity;
import game.object.SkyBox;
import game.rendering.RenderingEngine;
import game.rendering.material.Texture2D;
import game.rendering.model.Model;
import glib.util.GLog;
import glib.util.vector.GVector3f;

public class StrategyGame extends CoreGame{
	private static final long serialVersionUID = 1L;
	
	public void init(){
		Camera camera = new Camera();
		
		setRenderingEngine(new RenderingEngine());
		setMainCamera(camera);
		setLoader(new Loader());
		setSkyBox(new SkyBox(camera));
		
		GLog.sleep(100);
		
//		Entity box = new Entity(getBox(1,1,1), new Texture2D("texture.png"));
//		box.setPosition(new GVector3f(0,1,0));
//		addToScene(box);
		Box box = new Box(new GVector3f(0,1,0),1);
		box.setPosition(new GVector3f(0,0,0));
		addToScene(box);
//		addToScene(new Entity(getPlane(), new Texture2D("texture.png")));
		
		
	}

	public Model getPlane(){
		float width = 50;
		float height = 50;
		float[] vertices = new float[]{-width, 0,  height,
									   -width, 0, -height,
									    width, 0, -height,
									    width, 0,  height};
		
		int[] indices = new int[]{0,1,3,
								  3,1,2};
		
		float[] texture = new float[]{0,0,
									  0,height,
									  width,height,
									  width,0};
		
		return getLoader().loadToVAO(vertices, texture, indices);
		
	}
	
	public Model getBox(int w, int h, int d){
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
				w,-h,d};
		
		float[] texture ={0,0,
				 		  0,1,
						  1,1,
						  1,0,			
						  0,0,
						  0,1,
						  1,1,
						  1,0,			
						  0,0,
						  0,1,
						  1,1,
						  1,0,
						  0,0,
						  0,1,
						  1,1,
						  1,0,
						  0,0,
						  0,1,
						  1,1,
						  1,0,
						  0,0,
						  0,1,
						  1,1,
						  1,0};
		
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
		
		return getLoader().loadToVAO(vertices, texture, indices);
	}
	
}
