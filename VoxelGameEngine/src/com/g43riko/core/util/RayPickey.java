package com.g43riko.core.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.Project.gluUnProject;;
public class RayPickey {

	
	public Vector3f getPickingRay(float cursorX, float cursorY){
		IntBuffer viewport = ByteBuffer.allocateDirect((Integer.SIZE/8)*16).order(ByteOrder.nativeOrder()).asIntBuffer();
		FloatBuffer modelview = ByteBuffer.allocateDirect((Float.SIZE/8)*16).order(ByteOrder.nativeOrder()).asFloatBuffer();
		FloatBuffer projection = ByteBuffer.allocateDirect((Float.SIZE/8)*16).order(ByteOrder.nativeOrder()).asFloatBuffer();
		FloatBuffer pickingRayBuffer = ByteBuffer.allocateDirect((Float.SIZE/8)*3).order(ByteOrder.nativeOrder()).asFloatBuffer();
		FloatBuffer zBuffer = ByteBuffer.allocateDirect((Float.SIZE/8)*1).order(ByteOrder.nativeOrder()).asFloatBuffer();
		glGetFloat(GL_MODELVIEW_MATRIX, modelview);
		glGetFloat(GL_PROJECTION_MATRIX, projection);
		glGetInteger(GL_VIEWPORT, viewport);
		
		float winX = (float) cursorX;
		float winY = (float) viewport.get(3) - (float) cursorY;
		
		gluUnProject(winX, winY, 0, modelview, projection, viewport, pickingRayBuffer);
		Vector3f nearVector = new Vector3f(pickingRayBuffer.get(0),pickingRayBuffer.get(1),pickingRayBuffer.get(2));
		
		pickingRayBuffer.rewind();
		
		gluUnProject(winX, winY, 1, modelview, projection, viewport, pickingRayBuffer);
		Vector3f farVector = new Vector3f(pickingRayBuffer.get(0),pickingRayBuffer.get(1),pickingRayBuffer.get(2));
		
		farVector = farVector.Sub(nearVector).Normalized();
		return farVector;
	}
}
