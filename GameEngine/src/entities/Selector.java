package entities;

import org.lwjgl.input.Keyboard;

import renderers.Renderer;
import shaders.StaticShader;
import terrains.Block;
import terrains.Map;

public class Selector{
	private static boolean stavalo = false;
	private Entity entity;
	
	public Selector(Entity entity){
		this.entity = entity;
	}
	
	public void draw(Renderer renderer, StaticShader shader) {
		renderer.render(entity, shader);
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public void input(Map mapa,float x, float z){
		int i = (int)((x+Block.WIDTH)/Block.WIDTH/2);
		int k = (int)((z+Block.DEPTH)/Block.DEPTH/2);
		
//		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){
//			for(int j=mapa.getStlp(i, k).size()-1 ; j>=0 ; j--){
//				if(mapa.getBlock(i,j, k).getType()==1){
//					mapa.set(i,j+1,k,new Block(i,j+1, k,1));
//					return;
//				}
//			}
//		}
//		if(Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)){
//			for(int j=mapa.getStlp(i, k).size()-1 ; j>=0 ; j--){
//				if(mapa.getBlock(i,j, k).getType()==1){
//					System.out.println("mažeee");
//					mapa.set(i,j,k,null);
//					return;
//				}
//			}
//		}
	}

}
