package com.g43riko.core;

import java.util.ArrayList;

import com.g43riko.components.GameComponent;


public class GameObject {
	private ArrayList<GameObject> children;
	private ArrayList<GameComponent> components;
	private Transform transform;
	
	public GameObject(){
		children = new ArrayList<GameObject>();
		components = new ArrayList<GameComponent>();
		transform = new Transform();
	}
	
	public void inputAll(float delta){
		input(delta);
		for(GameObject child:children){
			child.inputAll(delta);
		}
	}
	
	public void updateAll(float delta){
		update(delta);
		for(GameObject child:children){
			child.updateAll(delta);
		}
	}

	public void input(float delta){
		transform.update();
		for(GameComponent component:components){
			component.input(delta);
		}
	}
	
	public void update(float delta){
		for(GameComponent component:components){
			component.update(delta);
		}
	}
	
	public GameObject addComponent(GameComponent component){
		component.setParent(this);
		components.add(component);
		return this;
	}
	
	public void addChild(GameObject child){
		children.add(child);
//		child.setEngine(engine);
		child.getTransform().setParent(transform);
	}
	
	public Transform getTransform(){
		return transform;
	}
}
