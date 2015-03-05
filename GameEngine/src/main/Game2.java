package main;

import java.util.ArrayList;

import object.GameObject2;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import rendering.RenderingEngine;

public class Game2 {
	public final static Loader LOADER = new Loader();
	private RenderingEngine renderingEngine;
	private ArrayList<GameObject2> scene = new ArrayList<GameObject2>();
	
	public Game2(){
		renderingEngine = new RenderingEngine();
	}
	
	public void mainLoop(){
		while(!Display.isCloseRequested()&&!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			input();
			update();
			render();
		}
	}
	
	public void input(){
		for(GameObject2 g : scene){
			g.input();
		}
	}
	
	public void update(){
		for(GameObject2 g : scene){
			g.update();
		}
	}
	
	public void render(){
		for(GameObject2 g : scene){
			g.render(renderingEngine);
		}
	}
}
