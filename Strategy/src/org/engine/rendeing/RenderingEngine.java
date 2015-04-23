package org.engine.rendeing;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.engine.component.Camera;
import org.engine.gui.Hud;
import org.engine.light.PointLight;
import org.engine.object.GameObject;
import org.engine.particles.Particle;
import org.engine.particles.ParticleEmmiter;
import org.engine.rendeing.material.Material;
import org.engine.rendeing.model.Model;
import org.engine.rendeing.shader.GBasicShader;
import org.engine.rendeing.shader.named.EntityShader;
import org.engine.rendeing.shader.named.HudShader;
import org.engine.rendeing.shader.named.ParticleShader;
import org.engine.rendeing.shader.named.ShadowMapShader;
import org.engine.rendeing.shader.named.SkyShader;
import org.engine.rendeing.shader.named.WaterShader;
import org.engine.util.Maths;
import org.engine.water.Water;
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
		shaders.put("guiShader", new HudShader());
		shaders.put("waterShader", new WaterShader());
		shaders.put("shadowMapShader", new ShadowMapShader());
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
		
		setTexture(true);
		
		setVariable("specular", false);
		setVariable("light", true);
		setVariable("fog", false);
		setVariable("hud", true);
	}
	
	//RENDERERS
	
	public void renderParticle(Particle particle) {
		if(getMainCamera() == null){
			return;
		}
		getShader("particleShader").bind();
		
		getShader("particleShader").updateUniform("color", particle.getColor());
		
		if(particle.isFadding())
			getShader("particleShader").updateUniform("alpha", particle.getAlpha());
		
		getShader("particleShader").updateUniform("transformationMatrix",particle.getTransformationMatrix(getMainCamera().getPosition()));
		
		if(particle.getTexture() != null){
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			particle.getTexture().bind();
		}
		
		
		prepareAndDraw(2,particle.getModel());
		
		disableVertex(2);
	}
	
	public void renderParticle(ArrayList<ParticleEmmiter> particles) {
		if(getMainCamera() == null){
			return;
		}
		getShader("particleShader").bind();

		GL13.glActiveTexture(GL13.GL_TEXTURE0);

		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		
		glDisable(GL_DEPTH_TEST);
		
		for(ParticleEmmiter pe : particles){
			for(Particle p : pe.getParticles()){
				
				getShader("particleShader").updateUniform("color", p.getColor());
				
				if(p.isFadding())
					getShader("particleShader").updateUniform("alpha", p.getAlpha());
				
				getShader("particleShader").updateUniform("transformationMatrix",p.getTransformationMatrix(getMainCamera().getPosition()));
				
				if(p.getTexture() != null)
					p.getTexture().bind();
				
				
				GL30.glBindVertexArray(p.getModel().getVaoID());
				
				
				GL11.glDrawElements(GL11.GL_TRIANGLES, p.getModel().getVertexCount(),GL11.GL_UNSIGNED_INT, 0);
			}
		}
		
		glEnable(GL_DEPTH_TEST);
		
		disableVertex(2);
	}
	
	public void renderObject(GameObject object){
		if(getMainCamera() == null)
			return;
		
		getShader("entityShader").bind();
		
		getShader("entityShader").updateUniform("transformationMatrix", object.getTransformationMatrix());
		
		setMaterial(object.getMaterial());
		
		prepareAndDraw(3, object.getModel());
		
		disableVertex(3);
	}
	
	public void renderObject(ArrayList<GameObject> objects){
		if(getMainCamera() == null)
			return;
		getShader("entityShader").bind();

		for(GameObject o : objects){
			getShader("entityShader").updateUniform("transformationMatrix", o.getTransformationMatrix());
			setMaterial(o.getMaterial());
			prepareAndDraw(3, o.getModel());
		}
		
		disableVertex(3);
	}
	
	public void renderWater(Water water){
		getShader("waterShader").bind();
		
		GL30.glBindVertexArray(water.getModel().getVaoID());
		
		GL20.glEnableVertexAttribArray(0);
		
		GMatrix4f modelMatrix = water.getTransformationMatrix();
		getShader("waterShader").updateUniform("modelMatrix",modelMatrix);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, water.getModel().getVertexCount());
        
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
	}
	
	public void renderWater(ArrayList<Water> waters){
		getShader("waterShader").bind();
		GL30.glBindVertexArray(Water.getModel().getVaoID());
		GL20.glEnableVertexAttribArray(0);
		
		for(Water water : waters){
			GMatrix4f modelMatrix = water.getTransformationMatrix();
			getShader("waterShader").updateUniform("modelMatrix",modelMatrix);
	        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, Water.getModel().getVertexCount());
		}
        
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
	}
	
	public void renderHud(Hud hud){
		if(!variables.containsKey("hud") || !variables.get("hud"))
			return;
		
		shaders.get("guiShader").bind();
		GL30.glBindVertexArray(hud.getModel().getVaoID());
		GL20.glEnableVertexAttribArray(0);
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		//render
		
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			hud.getTexture().bind();
			
			shaders.get("guiShader").updateUniform("transformationMatrix", hud.getTransformationMatrix());
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, hud.getModel().getVertexCount());
		
//		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
	
	public void renderHud(ArrayList<Hud> hud, int texture){
		if(!variables.containsKey("hud") || !variables.get("hud"))
			return;
		shaders.get("guiShader").bind();

		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		
		for(Hud h : hud){
			GL30.glBindVertexArray(h.getModel().getVaoID());
			GL20.glEnableVertexAttribArray(0);
//			h.getTexture().bind();
			glBindTexture(GL_TEXTURE_2D, texture);
			shaders.get("guiShader").updateUniform("transformationMatrix", h.getTransformationMatrix());
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, h.getModel().getVertexCount());
		}
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
	
	public void renderHud(ArrayList<Hud> hud){
		if(!variables.containsKey("hud") || !variables.get("hud"))
			return;
		

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		shaders.get("guiShader").bind();
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		for(Hud h : hud){
			GL30.glBindVertexArray(h.getModel().getVaoID());
			GL20.glEnableVertexAttribArray(0);
			h.getTexture().bind();
			shaders.get("guiShader").updateUniform("transformationMatrix", h.getTransformationMatrix());
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, h.getModel().getVertexCount());
		}
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
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
	
	public void setTexture(boolean texture){
		if(variables.containsKey("texture") && variables.get("texture") == texture)
			return;
		variables.put("texture", texture);
		getShader("entityShader").bind();
		getShader("entityShader").updateUniform("texture", texture);
	}
	
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
		setProjectionMatrix(mainCamera.getProjectionMatrix());
	}
	
	public void setProjectionMatrix(GMatrix4f matrix){
		for(Entry<String, GBasicShader> s : shaders.entrySet()){
			if(s.getValue().hasUniform("projectionMatrix")){
				getShader(s.getKey()).bind();
				getShader(s.getKey()).updateUniform("projectionMatrix", matrix);
			}
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
			if(s.getValue().hasUniform("ambient")){
				getShader(s.getKey()).bind();
				getShader(s.getKey()).updateUniform("ambient", ambient);
			}
		}
	}

	public void setViewMatrix(){
		if(mainCamera == null){
			System.out.println("nieje nastavená hlavná kamera");
			return;
		}
		GMatrix4f mat = Maths.MatrixToGMatrix(Maths.createViewMatrix(mainCamera));
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		
		for(Entry<String, GBasicShader> s : shaders.entrySet()){
			if(s.getValue().hasUniform("viewMatrix")){
				getShader(s.getKey()).bind();
				getShader(s.getKey()).updateUniform("viewMatrix", mat);
			}
		}
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND);
	}

	public void setMaterial(Material mat){
		getShader("entityShader").connectTextures();
		
		if(mat.getDiffuse() != null){
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			mat.getDiffuse().bind();
		}
		
		if(mat.getNormal() != null){
			GL13.glActiveTexture(GL13.GL_TEXTURE1);
			mat.getNormal().bind();
		}
		
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
