package com.g43riko.voxel;

import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.glPolygonMode;

import org.lwjgl.input.Keyboard;

import com.g43riko.core.Game;
import com.g43riko.core.Window;

public class Tester extends Game{
	private int view = 0;
	public void render(){
		getRenderEngine().render();
		if(Keyboard.isKeyDown(Keyboard.KEY_V)){
			switchView();
		}
	};
	
	public void update(float delta){
		getRenderEngine().getCamera().input();
	};
	
	public void switchView(){
		if(view == 0){
			glPolygonMode(GL_FRONT_AND_BACK,GL_LINE);
			view=1;
		}
		else{
			glPolygonMode(GL_FRONT_AND_BACK,GL_FILL);
			view=0;
		}
	}
}
