package game.main;

import org.json.JSONObject;

import game.Light;
import game.components.Player;
import game.core.CoreGame;
import game.object.Camera;
import game.object.Entity;
import game.object.SkyBox;
import game.particle.ParticleEmmiter;
import game.rendering.RenderingEngine;
import game.rendering.material.Texture2D;
import game.rendering.model.Model;
import game.world.Block;
import game.world.World;
import glib.util.GLog;
import glib.util.vector.GVector3f;

public class StrategyGame extends CoreGame{
	private static final long serialVersionUID = 1L;
	
	public void init(){
		GLog.sleep(100);
//		setWorld(new World());
		setWorld(new World("mainWorld.gw"));
		setPlayer(new Player(getWorld(), new Camera()));
		setRenderingEngine(new RenderingEngine());
		setMainCamera(getPlayer().getCamera());
		setLoader(new Loader());
		setSkyBox(new SkyBox(getPlayer().getCamera()));
		setSun(new Light(new GVector3f(100, 100, 100), new GVector3f(1)));
//		Entity box = new Entity(getBox(1,1,1), new Texture2D("texture.png"));
//		box.setPosition(new GVector3f(0,1,0));
//		addToScene(box);
		
//		Box box = new Box(new GVector3f(0,1,0),1);
//		addToScene(box);
		
		addToScene(new ParticleEmmiter(new GVector3f(0,1,-5)));
		
		
//		addToScene(new Block(new GVector3f(0,1,-5),1));
		
//		addToScene(new Entity(getPlane(), new Texture2D("wood_64.jpg")));
		
		
	}

//	public Model getPlane(){
//		float width = 50;
//		float height = 50;
//		float[] vertices = new float[]{-width, 0,  height,
//									   -width, 0, -height,
//									    width, 0, -height,
//									    width, 0,  height};
//		
//		int[] indices = new int[]{0,1,3,
//								  3,1,2};
//		
//		float[] texture = new float[]{0,0,
//									  0,height,
//									  width,height,
//									  width,0};
//		
//		return getLoader().loadToVAO(vertices, texture, indices);
//		
//	}
//	
//	public Model getBox(int w, int h, int d){
//		float[] vertices = new float[]{			
//				-w,h,-d,	
//				-w,-h,-d,	
//				w,-h,-d,	
//				w,h,-d,		
//				
//				-w,h,d,	
//				-w,-h,d,	
//				w,-h,d,	
//				w,h,d,
//				
//				w,h,-d,	
//				w,-h,-d,	
//				w,-h,d,	
//				w,h,d,
//				
//				-w,h,-d,	
//				-w,-h,-d,	
//				-w,-h,d,	
//				-w,h,d,
//				
//				-w,h,d,
//				-w,h,-d,
//				w,h,-d,
//				w,h,d,
//				
//				-w,-h,d,
//				-w,-h,-d,
//				w,-h,-d,
//				w,-h,d};
//		
//		float[] texture ={0,0,
//				 		  0,1,
//						  1,1,
//						  1,0,			
//						  0,0,
//						  0,1,
//						  1,1,
//						  1,0,			
//						  0,0,
//						  0,1,
//						  1,1,
//						  1,0,
//						  0,0,
//						  0,1,
//						  1,1,
//						  1,0,
//						  0,0,
//						  0,1,
//						  1,1,
//						  1,0,
//						  0,0,
//						  0,1,
//						  1,1,
//						  1,0};
//		
//		int[] indices ={0,1,3,	
//						3,1,2,	
//						4,5,7,
//						7,5,6,
//						8,9,11,
//						11,9,10,
//						12,13,15,
//						15,13,14,	
//						16,17,19,
//						19,17,18,
//						20,21,23,
//						23,21,22};
//		
//		return getLoader().loadToVAO(vertices, texture, indices);
//	}
//	
}
