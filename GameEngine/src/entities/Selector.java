package entities;

import menus.BMenu;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import renderers.Renderer;
import shaders.StaticShader;
import terrains.Block;
import terrains.Map;

public class Selector{
	private static boolean stavalo = false;
	private static boolean buralo = false;
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
	
	public void input(Map mapa,BMenu bmenu){
		int i = (int)((entity.x+Block.WIDTH)/Block.WIDTH/2);
		int k = (int)((entity.z+Block.DEPTH)/Block.DEPTH/2);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){
			if(stavalo){
				return;
			}
			mapa.add(i,k,bmenu.getSelectedType());
			stavalo = true;
			return;
		}
		stavalo = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)){
			if(buralo)
				return;
			mapa.remove(i, k);
			buralo = true;
			return;
		}
		buralo = false;
	}

	public Vector2f getSur(){
		return new Vector2f(((entity.x+Block.WIDTH)/Block.WIDTH/2),((entity.z+Block.DEPTH)/Block.DEPTH/2));
	}
}
