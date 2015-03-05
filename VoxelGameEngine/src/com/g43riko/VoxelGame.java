package com.g43riko;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.opengl.Display;

import com.g43riko.core.CoreGame;
import com.g43riko.object.Shape;
import com.g43riko.rendering.RenderingEngine;

public class VoxelGame extends CoreGame{
	public void init() {
		createWindow("VoxelGame", 800, 600, 60);
		
		setRenderingEngine(new RenderingEngine());
		addToScene(new Shape());
		
	}
	
}
