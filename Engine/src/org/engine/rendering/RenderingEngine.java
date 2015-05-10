package org.engine.rendering;

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

import java.util.HashMap;

import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector3f;

import org.engine.component.Camera;
import org.engine.object.GameObject;
import org.engine.rendering.material.Material;
import org.engine.rendering.model.Model;
import org.engine.rendering.shader.GBasicShader;
import org.engine.rendering.shader.named.ObjectShader;
import org.engine.utils.Maths;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class RenderingEngine {
	public final static int MAX_LIGHTS = 8;
	
	private GVector3f ambient = new GVector3f();
	private GVector3f backgroundColor = new GVector3f();
	
	private int renderedPoligons = 0;
	private int renderedPoints = 0;
	
	private Camera mainCamera;
	
	protected static HashMap<String, GBasicShader> shaders = new HashMap<String, GBasicShader>(); 
	private HashMap<String, Boolean> variables = new HashMap<String, Boolean>();
	
	static{
		shaders.put("objectShader", new ObjectShader());
	}
	
	//CONSTRUCTORS
	
	public RenderingEngine(){
		init3D();
		
		glClearColor(backgroundColor.getX(), backgroundColor.getY(), backgroundColor.getZ(), 1f);
		
		setAmbient(new GVector3f(1));
		
		setVariable("useLights", true);
		setVariable("useAmbient", true);
		setVariable("useTexture", true);
		setVariable("useSpecular", true);
		setVariable("useSpecularMap", true);
		
		
		shaders.get("objectShader").bind();
		
		shaders.get("objectShader").updateUniform("sunColor", new GVector3f(1));
		shaders.get("objectShader").updateUniform("sunDirection", new GVector3f(0.5f, 1, 0.5f));
		
	}
	
	//RENDERERS
	

	public void renderObject(GameObject object) {
		if(mainCamera == null)
			return;
		getShader("objectShader").bind();

		getShader("objectShader").updateUniform("fakeLight", object.isUseFakeLight());
		
		
		getShader("objectShader").updateUniform("transformationMatrix", object.getTransformationMatrix());
		setMaterial(object.getMaterial());
		prepareAndDraw(3, object.getModel());
		
		disableVertex(3);
	}
	
//	public void renderObject(List<GameObject> objects){
//		if(mainCamera == null)
//			return;
//		getShader("objectShader").bind();
//
//		objects.stream().forEach(object -> {
//			getShader("objectShader").updateUniform("transformationMatrix", object.getTransformationMatrix());
//			setMaterial(object.getMaterial());
//			prepareAndDraw(3, object.getModel());
//		});
//		
//		disableVertex(3);
//	}

	//OTHERS

	private void init3D(){
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	
	private void prepareAndDraw(int i, Model model) {
		GL30.glBindVertexArray(model.getVaoID());
		
		GL20.glEnableVertexAttribArray(0);
		
		if(i>1)
			GL20.glEnableVertexAttribArray(1);
		if(i>2)
			GL20.glEnableVertexAttribArray(2);
		
		renderedPoints += model.getPointCount();
		renderedPoligons += model.getVertexCount() / 3;
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(),GL11.GL_UNSIGNED_INT, 0);	
	}
	
	protected void disableVertex(int i){
		GL20.glDisableVertexAttribArray(0);
		
		if(i>1)
			GL20.glDisableVertexAttribArray(1);
		if(i>2)
			GL20.glDisableVertexAttribArray(2);
		
		GL30.glBindVertexArray(0);
	}

	public void prepare() {
		init3D();
		renderedPoligons = 0;
		renderedPoints = 0;
		glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
		setViewMatrix(Maths.MatrixToGMatrix(Maths.createViewMatrix(mainCamera)));
		setEyePos();
	}

	public void cleanUp() {
		shaders.forEach((key, value)->value.cleanUp());
	}

	public void updateCamera() {
		setProjectionMatrix(mainCamera.getProjectionMatrix());
	}

	//SETTERS

	public void setVariable(String name, boolean value){
		if(variables.containsKey(name) && variables.get(name) == value)
			return;
		variables.put(name, value);
		
		shaders.forEach((key,val) -> {
			if(val.hasUniform(name)){
				val.bind();
				val.updateUniform(name, value);
			}
		});
	}
	
	private void setMaterial(Material material) {
		getShader("objectShader").connectTextures();
		
		if(material.getDiffuse() != null && variables.get("useTexture")){
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			material.getDiffuse().bind();
		}
		
		if(material.getNormal() != null){
			GL13.glActiveTexture(GL13.GL_TEXTURE1);
			material.getNormal().bind();
		}
		
		if(material.getBump() != null){
			GL13.glActiveTexture(GL13.GL_TEXTURE2);
			material.getNormal().bind();
		}
		
		if(material.getSpecular() != null && variables.get("useSpecularMap")){
			GL13.glActiveTexture(GL13.GL_TEXTURE3);
			material.getSpecular().bind();
		}
		
		getShader("objectShader").updateUniform("specularPower", material.getSpecularPower());
		getShader("objectShader").updateUniform("specularIntensity", material.getSpecularIntensity());
	}
	
	private void setAmbient(GVector3f ambient) {
		if(ambient.equals(this.ambient)){
			return ;
		}
		this.ambient = ambient;
		
		shaders.forEach((key,val) -> {
			if(val.hasUniform("ambient")){
				val.bind();
				val.updateUniform("ambient", ambient);
			}
		});
		
	}
	
	private void setEyePos() {
		if(mainCamera == null){
			System.out.println("nieje nastavená hlavná kamera");
			return;
		}
		
		shaders.forEach((key,val) -> {
			if(val.hasUniform("eyePos")){
				val.bind();
				val.updateUniform("eyePos", mainCamera.getPosition());
			}
		});
		
	}

	private void setViewMatrix(GMatrix4f matrix) {
		if(mainCamera == null){
			System.out.println("nieje nastavená hlavná kamera");
			return;
		}
		
		shaders.forEach((key,val) -> {
			if(val.hasUniform("viewMatrix")){
				val.bind();
				val.updateUniform("viewMatrix", matrix);
			}
		});
		
	}

	private void setProjectionMatrix(GMatrix4f projectionMatrix) {
		shaders.forEach((key,val) -> {
			if(val.hasUniform("projectionMatrix")){
				val.bind();
				val.updateUniform("projectionMatrix", projectionMatrix);
			}
		});
		
	}

	public void setCamera(Camera camera) {
		mainCamera = camera;
		updateCamera();
	}

	public void setBackgroundColor(GVector3f backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	//GETTERS
	
	public static GBasicShader getShader(String name){
		return shaders.get(name);
	}

	public int getRenderedPoligons() {
		return renderedPoligons;
	}

	public int getRenderedPoints() {
		return renderedPoints;
	}


}
