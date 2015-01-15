package com.g43riko.components;

import com.g43riko.core.GameObject;
import com.g43riko.core.Transform;

public abstract class GameComponent {
	private GameObject parent;
	
	public void input(float delta){};
	
	public void update(float delta){};
	
	public Transform getTransform(){
		return parent.getTransform();
	}
	
	public void setParent(GameObject parent){
		this.parent = parent;
	}
}
