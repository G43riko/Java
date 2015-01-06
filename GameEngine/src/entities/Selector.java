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
	
	public void input(Map mapa){
		int i = (int)((entity.x+Block.WIDTH)/Block.WIDTH/2);
		int k = (int)((entity.z+Block.DEPTH)/Block.DEPTH/2);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){
			mapa.add(i,k,1);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)){
			mapa.remove(i, k);
		}
	}

}
