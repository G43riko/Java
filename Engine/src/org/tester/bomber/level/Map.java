package org.tester.bomber.level;

import java.lang.reflect.GenericArrayType;
import java.util.HashMap;

import org.engine.DefaultApp;
import org.engine.app.GameAble;
import org.engine.component.Camera;
import org.engine.core.Interactable;
import org.engine.rendering.RenderingEngine;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class Map implements Interactable{
	private HashMap<String, Block> blocks = new HashMap<String, Block>();
	GameAble parent;
	
	public Map(GameAble parent, GVector2f size){
		this.parent = parent;
		
		createMap(size);
	}

	private void createMap(GVector2f size) {
		GVector3f start = new GVector3f(-size.getX() / 2, 0, -size.getY() / 2);
		for(int i=0 ; i<size.getX() ; i++){
			for(int j=0 ; j<size.getY() ; j++){
				blocks.put(i + "_" + j, new Block(parent, (int)(Math.random() * 5), start.add(new GVector3f(i, 0, j))));
			}
		}
	}
	
	@Override
	public void render(RenderingEngine renderingEngine) {
		blocks.entrySet().stream()
						 .map(a -> a.getValue())
						 .filter(a -> a.getType() != Block.NOTHING)
						 .filter(a -> {
							 float tang = (float)Math.tan(Math.toRadians(Camera.getFOV()) * 0.5f);
							 float Hfar = 2 * tang * parent.getCamera().getPosition().getY();
							 float Wfar = Hfar * Camera.getASPECT_RATIO();
							 
							 GVector2f distances =  a.getPosition().sub(parent.getCamera().getPosition()).getXZ().abs();
							 
							 return distances.getX() < Wfar / (DefaultApp.getBoolean("canRotate") ? 1 : 2.5) &&
									distances.getY() < Hfar / (DefaultApp.getBoolean("canRotate") ? 1 : 2.5);
						 })
						 .forEach(a -> a.render(renderingEngine));
	}
}
