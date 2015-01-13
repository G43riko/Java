package GameEngine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

import java.util.ArrayList;
import java.util.HashMap;

import GameEngine.components.BaseLight;
import GameEngine.components.Camera;
import GameEngine.core.GameObject;
import GameEngine.core.Vector3f;
import GameEngine.rendering.resourceManagement.MappedValues;

public class RenderingEngine extends MappedValues{
	private Camera mainCamera;
	private Shader forwardAmbient;
	
	private ArrayList<BaseLight> lights;
	private BaseLight activeLight;
	
	private HashMap<String, Integer> samplerMap;
//	private HashMap<String, Float> floatHashMap;
	
	public RenderingEngine(){
		super();
		lights = new ArrayList<BaseLight>();
		samplerMap = new HashMap<String, Integer>();
		samplerMap.put("diffuse", 0);
		samplerMap.put("normalMap", 1);
		glClearColor(0.0f,0.0f,0.0f,0.0f);
		
		forwardAmbient = new Shader("forward-ambient");
				
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		
		glEnable(GL_DEPTH_TEST);
		
		glEnable(GL_DEPTH_CLAMP);
		
		glEnable(GL_TEXTURE_2D);
		addVector3f("ambient", new Vector3f(0.2f,0.2f,0.2f));
	}
	
	public void render(GameObject object){
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
	
	public void addLight(BaseLight light){
		lights.add(light);
	}
	
	public static String getOpenGLVersion(){
		return glGetString(GL_VERSION);
	}
	
	public static void setClearColor(Vector3f color) {
		glClearColor(color.GetX(),color.GetY(),color.GetZ(),1.0f);
	}
	
	public Camera getMainCamera() {
		return mainCamera;
	}
	
	public BaseLight getActiveLight(){
		return activeLight;
	}

	public void addCamera(Camera camera) {
//		System.out.println("prindala sa kamera");
		mainCamera = camera;
	}

	public int getSamplerSlot(String samplerName) {
		return samplerMap.get(samplerName );
	}
}
