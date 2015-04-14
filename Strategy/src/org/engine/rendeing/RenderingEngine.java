package org.engine.rendeing;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import game.rendering.shader.EntityShader;
import game.rendering.shader.ParticleShader;
import game.rendering.shader.SkyShader;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.engine.component.Camera;
import org.engine.light.PointLight;
import org.engine.object.GameObject;
import org.engine.rendeing.material.Material;
import org.engine.rendeing.model.Model;
import org.engine.rendeing.shader.GBasicShader;
import org.engine.util.Maths;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.strategy.component.CameraStrategy;

public class RenderingEngine {
	public final static int MAX_LIGHTS = 8;
	
	private Camera mainCamera;
	private GVector3f ambient;
	private GVector3f backgroundColor = new GVector3f();
	
	protected List<PointLight> lights = new ArrayList<PointLight>();
	
	protected static HashMap<String, GBasicShader> shaders = new HashMap<String, GBasicShader>(); 
	private HashMap<String, Boolean> variables = new HashMap<String, Boolean>();
	
	static{
		shaders.put("entityShader", new EntityShader());
		shaders.put("skyShader", new SkyShader());
		shaders.put("particleShader", new ParticleShader());
	}
	
	//CONSTRUCTOR
	
	public RenderingEngine(){
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		
		glClearColor(backgroundColor.getX(), backgroundColor.getY(), backgroundColor.getZ(), 1f);
		
		setAmbient(new GVector3f(1));
	}
	
	//RENDERERS
	

	public void renderObject(GameObject object){
		if(getMainCamera() == null)
			return;
		
		getShader("entityShader").bind();
		
		getShader("entityShader").updateUniform("transformationMatrix", object.getTransformationMatrix());
		
		setMaterial(object.getMaterial());
		
		prepareAndDraw(3, object.getModel());
		
		disableVertex(3);
		
	}
	
	
	//OTHERS

	public void prepare() {
		glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
		setViewMatrix();
		setEyePos();
	}
	
	protected void prepareAndDraw(int i, Model model){
		GL30.glBindVertexArray(model.getVaoID());
		
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		if(i>2)
			GL20.glEnableVertexAttribArray(2);
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(),GL11.GL_UNSIGNED_INT, 0);
	}
	
	protected void disableVertex(int i){
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		if(i>2)
			GL20.glDisableVertexAttribArray(2);
		
		GL30.glBindVertexArray(0);
	}
	
	
	public void cleanUp(){
		for(Entry<String, GBasicShader> s : shaders.entrySet()){
			getShader(s.getKey()).cleanUp();
		}
	}
	
	//ADDERS
	
	public void addLight(PointLight light){
		lights.add(light);
		
		getShader("entityShader").bind();
		for(int i=0 ;i<MAX_LIGHTS ; i++){
			if(i < lights.size()){
				getShader("entityShader").updateUniform("lightPosition"+i, lights.get(i).getPosition());
				getShader("entityShader").updateUniform("lightColor"+i, lights.get(i).getColor());
				getShader("entityShader").updateUniform("attenuation"+i, lights.get(i).getAttenuation());
			}
			else{
				getShader("entityShader").updateUniform("lightPosition"+i, new GVector3f());
				getShader("entityShader").updateUniform("lightColor"+i, new GVector3f());
				getShader("entityShader").updateUniform("attenuation"+i, new GVector3f(1,0,0));
			}
		}
	}
	
	//SETTERS
	
	public void setVariable(String name, boolean value){
		if(variables.containsKey(name) && variables.get(name) == value)
			return;
		variables.put(name, value);
		
		for(Entry<String, GBasicShader> s : shaders.entrySet()){
			if(s.getValue().hasUniform(name)){
				getShader(s.getKey()).bind();
				getShader(s.getKey()).updateUniform(name, value);
			}
		}
	}
	
	public void setMainCamera(CameraStrategy mainCamera) {
		this.mainCamera = mainCamera;
		
		for(Entry<String, GBasicShader> s : shaders.entrySet()){
			getShader(s.getKey()).bind();
			getShader(s.getKey()).updateUniform("projectionMatrix", mainCamera.getProjectionMatrix());
		}
	}
	
	public void setEyePos(){
		if(mainCamera == null){
			System.out.println("nieje nastavená hlavná kamera");
			return;
		}
		getShader("entityShader").bind();
		getShader("entityShader").updateUniform("eyePos", mainCamera.getPosition());
	}
	
	public void setAmbient(GVector3f ambient) {
		if(this.ambient == ambient){
			return ;
		}
		this.ambient = ambient;
		
		for(Entry<String, GBasicShader> s : shaders.entrySet()){
			getShader(s.getKey()).bind();
			getShader(s.getKey()).updateUniform("ambient", ambient);
		}	
	}

	public void setViewMatrix(){
		if(mainCamera == null){
			System.out.println("nieje nastavená hlavná kamera");
			return;
		}
		GMatrix4f mat = Maths.MatrixToGMatrix(Maths.createViewMatrix(mainCamera));
		
		for(Entry<String, GBasicShader> s : shaders.entrySet()){
			getShader(s.getKey()).bind();
			getShader(s.getKey()).updateUniform("viewMatrix", mat);
		}	
	}

	public void setMaterial(Material mat){
		getShader("entityShader").connectTextures();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		mat.getDiffuse().bind();
		
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		mat.getNormal().bind();
		
		getShader("entityShader").updateUniform("shineDumper", mat.getSpecularPower());
		getShader("entityShader").updateUniform("reflectivity", mat.getSpecularIntensity());
	}

	
	//GETTERS
	
	public static GBasicShader getShader(String name){
		return shaders.get(name);
	}

	public Camera getMainCamera() {
		return mainCamera;
	}
	

}
