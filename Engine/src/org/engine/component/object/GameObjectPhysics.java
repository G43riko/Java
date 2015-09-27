package org.engine.component.object;

import org.engine.app.GameAble;
import org.engine.physics.BasicCollider;
import org.engine.rendering.material.Material;
import org.engine.rendering.model.Model;

import glib.util.vector.GVector3f;

public class GameObjectPhysics extends GameObject{
	private float bounce = 0.5f;
	private float bouncingLimit = 0.02f;
	
	private float weight = 1;
	private float friction = 1;
	
	private GVector3f velocity = new GVector3f();
	private GVector3f rotSpeed = new GVector3f();
	
	private BasicCollider collider; 
	
	public GameObjectPhysics(GameAble parent, Material material, Model model) {
		super(parent, material, model);
	}
	
	@Override
	public void update(float delta) {
		
	}
}
