package com.g43riko.voxel;

import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_CW;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glFrontFace;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER_SRGB;

import org.lwjgl.opengl.Display;

import com.g43riko.voxel.world.Chunk;
import com.g43riko.voxel.world.World;

public class RenderEngine {
	private Camera camera = null;
	//lights, cameras, meshes...
	private World world = new World();
	
	public RenderEngine(){
		init();
	}
	
	public void render(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		camera.useView();
		world.draw();
	}
	
	public void init(){
		glClearColor(1.0f,0.0f,0.0f,0.0f);
//		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_FRAMEBUFFER_SRGB);
	}
	

	public Camera getCamera() {
		if(camera == null){
			camera = new Camera(70,Display.getWidth()/Display.getHeight(),0.3f,1000);
		}
		return camera;
	}
}
