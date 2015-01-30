package com.voxel.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;
import glib.util.GLog;
import glib.util.vector.GVector3f;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.opengl.Display;

import com.voxel.component.light.BaseLight;
import com.voxel.component.viewAndMovement.Camera;
import com.voxel.core.GameObject;
import com.voxel.rendering.shader.Shader;

public class RenderingEngine extends MappedValues{
	
	public static int numOfTriangels;
	public static int numOfRenderedBoxSides;
	private Camera mainCamera;
	private Shader forwardAmbient;
	
	private HashMap<String, Integer> samplerMap;
	
	private ArrayList<BaseLight> lights;
	private BaseLight activeLight;
	
	public RenderingEngine(){
		lights = new ArrayList<BaseLight>();
		samplerMap = new HashMap<String, Integer>();
		samplerMap.put("diffuse", 0);
		
		glClearColor(0.0f,0.0f,0.0f,0.0f);
		init3D();
		
		forwardAmbient = new Shader("forward-ambient");
		
		addGVector3f("ambient", new GVector3f(0.4f,0.4f,0.4f));
	}
	
	public void init2D(){
		glEnable(GL_TEXTURE_2D);
        glShadeModel(GL_SMOOTH);        
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_LIGHTING);                    
  
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
        glClearDepth(1);                                       
  
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glViewport(0,0,Display.getWidth(),Display.getHeight());
        glMatrixMode(GL_MODELVIEW);
  
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
	}
	
	public void init3D(){
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_DEPTH_CLAMP);
		
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);  
		
		glDisable(GL_BLEND);
	}
	
	public void render(GameObject object) {
//		init3D();
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		object.renderAll(forwardAmbient,this);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE,GL_ONE);
		glDepthMask(false);
		glDepthFunc(GL_EQUAL);
		
		for(BaseLight light:lights){
			activeLight = light;
			object.renderAll(light.getShader(),this);
		}
		
		glDepthFunc(GL_LESS);
		glDepthMask(true);
		glDisable(GL_BLEND);
	}

	public void addCamera(Camera camera) {
		GLog.write("pridala sa kamera");
		mainCamera = camera;
	}

	public Camera getMainCamera() {
		return mainCamera;
	}

	public int getSamplerSlot(String samplerName) {
		return samplerMap.get(samplerName);
	}

	public void addLight(BaseLight light){
		lights.add(light);
	}

	public BaseLight getActiveLight(){
		return activeLight;
	}
	
}
