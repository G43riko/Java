package org.engine.ai;

import org.engine.app.GameAble;
import org.engine.component.GameComponent;
import org.engine.component.object.GameObject;
import org.engine.core.CoreEngine;
import org.engine.rendering.material.Material;

import glib.shapes.threeDimensional.Box;
import glib.util.vector.GVector3f;

public class BasicEnemy extends GameObject{
	
	private Follower follower;
	
	
	public BasicEnemy(GameAble parent) {
		this(parent, new GVector3f());
	}
	
	public BasicEnemy(GameAble parent, GVector3f position) {
		super(parent, new Material("materials/texture.png"), CoreEngine.getLoader().loadToVAO(Box.getVertices(1, 1, 1), Box.getTextures(), Box.getNormals(), Box.getIndices()));
		setPosition(position);
		follower = new Follower(getParent(), this, null);
	}
	
	@Override
	public void update(float delta) {
		follower.update(delta);
	}
	
	public void setTarget(GameComponent target){
		follower.setTarget(target);
	}
	
	public void setMoveOnFloor(boolean value){
		follower.setOnGroung(value);
	}

	public float getHeight() {
		return getScale().getY() * 2;
	}

}
