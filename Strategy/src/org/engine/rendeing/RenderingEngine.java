package org.engine.rendeing;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.engine.component.SkyBox;
import org.engine.light.PointLight;
import org.engine.object.GameObject;
import org.engine.particles.Particle;
import org.engine.rendeing.material.Material;
import org.engine.rendeing.material.Texture2D;
import org.engine.rendeing.model.Model;
import org.engine.rendeing.shader.GBasicShader;
import org.engine.util.Maths;
import org.engine.util.MousePicker;
import org.engine.world.Line;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.strategy.component.CameraStrategy;
import org.strategy.entity.enemy.BasicEnemy;
import org.strategy.entity.player.Player;
import org.strategy.object.GameObjectWithLight;
import org.strategy.world.Block;

import game.rendering.shader.EntityShader;
import game.rendering.shader.ParticleShader;
import game.rendering.shader.SkyShader;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector3f;

public final class RenderingEngine {
	private static HashMap<String, GBasicShader> shaders = new HashMap<String, GBasicShader>(); 
	private HashMap<String, Boolean> variables = new HashMap<String, Boolean>();
	public final static int MAX_LIGHTS = 8;
	private CameraStrategy mainCamera;
	private GVector3f ambient;
	private GVector3f backgroundColor = new GVector3f();
	private MousePicker mousePicker;
	
	static{
		shaders.put("entityShader", new EntityShader());
		shaders.put("skyShader", new SkyShader());
		shaders.put("particleShader", new ParticleShader());
	}
	
	
	private int view = 0;
	private boolean texture;
	 
	private PointLight sun;
	private List<PointLight> lights = new ArrayList<PointLight>();
	private Texture2D normal = new Texture2D("normal.jpg");
	private SelectBlock selectBlock = new SelectBlock();
	
	
	public class SelectBlock{
		private Block block = null;
		private int side = -1;
		private float dist  = -1;
		
		public Block getBlock() {
			return block;
		}
		
		public int getSide() {
			return side;
		}
		
		public void reset(){
			block = null;
			dist = -1;
		}
	}
	
	//CONSTRUCTORS
	
	public RenderingEngine(){ 
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
		
		GL11.glEnable(GL11.GL_CULL_FACE);
//		GL11.glCullFace(GL11.GL_FRONT);
		GL11.glCullFace(GL11.GL_BACK);
		
		glClearColor(backgroundColor.getX(), backgroundColor.getY(), backgroundColor.getZ(), 1f);
		
		setTexture(true);
		setVariable("specular", false);
		setVariable("light", true);
		setVariable("fog", true);
		setAmbient(new GVector3f(1));
	}
	
	//RENDERERS

	public void renderSky(SkyBox sky){
		if(mainCamera == null || view == 4){
			return;
		}
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		getShader("skyShader").bind();
		
		
		if(view == 3){
			getShader("skyShader").updateUniform("color", sky.getTexture().getAverageColor());
		}
		
		getShader("skyShader").updateUniform("transformationMatrix", sky.getTransformationMatrix());
		
		sky.getTexture().bind();

		prepareAndDraw(2, sky.getModel());
		
		disableVertex(2);
	}
	
	public void renderBlock(Block block){
		if(mainCamera == null || !block.isActive()){
			return;
		}
		
		getShader("entityShader").bind();
		
		getShader("entityShader").updateUniform("transformationMatrix", block.getTransformationMatrix());
		
		if(view == 3 || selectBlock.block == block){
			getShader("entityShader").updateUniform("color", block.getMaterial().getDiffuse().getAverageColor());
		}
		
		setMaterial(block.getMaterial());
		
		float blockToCamDist = block.getPosition().dist(mainCamera.getPosition());
		boolean search = (block.isClickable() && blockToCamDist < Player.MAX_CLICK_DIST &&
						 (selectBlock.block == null || blockToCamDist < selectBlock.block.getPosition().dist(mainCamera.getPosition())) &&
						 blockToCamDist < block.getPosition().dist(mainCamera.getPosition().add(mainCamera.getForward())) && view!=4);
		for(int i=0 ; i<6 ; i++){
			if(block.getSide(i)){
//				if(search && mainCamera.intersect(block.getPosition().add(block.getPoint(i, 0)), 
//				    	  						  block.getPosition().add(block.getPoint(i, 1)),  
//				    	  						  block.getPosition().add(block.getPoint(i, 2)))){
				mousePicker.update();
				if(search && GVector3f.intersectRayWithSquare(mainCamera.getPosition(), 
															  mainCamera.getPosition().add(mousePicker.getCurrentRay().mul(1000)),
															  block.getPosition().add(block.getPoint(i, 0)), 
															  block.getPosition().add(block.getPoint(i, 1)),  
															  block.getPosition().add(block.getPoint(i, 2)))){
					float dist = block.getPosition().add(block.getPoint(i, 1).add(block.getPoint(i, 2)).div(2)).dist(mainCamera.getPosition());
					if(selectBlock.dist<0 || selectBlock.dist>dist){
						selectBlock.dist = dist;
						selectBlock.side = i;
					}
					selectBlock.block = block;
				}
				prepareAndDraw(3, block.getModel(i));
			}
		}
			
		disableVertex(3);
	}

	public void renderParticle(Particle particle) {
		if(mainCamera == null){
			return;
		}
		getShader("particleShader").bind();
		
		
		
		getShader("particleShader").updateUniform("color", particle.getColor());
		
		if(particle.isFadding())
			getShader("particleShader").updateUniform("alpha", particle.getAlpha());
		
		getShader("particleShader").updateUniform("transformationMatrix",particle.getTransformationMatrix(mainCamera.getPosition()));
		
		if(particle.getTexture() != null)
			particle.getTexture().bind();
		
		prepareAndDraw(2,particle.getModel());
		
		disableVertex(2);
	}
	
	public void renderLine(Line line) {
		getShader("entityShader").bind();
		getShader("entityShader").updateUniform("transformationMatrix", line.getTransformationMatrix());
		getShader("entityShader").updateUniform("color", line.getColor());
		getShader("entityShader").updateUniform("view", 3);
		
		GL30.glBindVertexArray(line.getModel().getVaoID());
		
		GL20.glEnableVertexAttribArray(0);
		
		GL11.glDrawElements(GL11.GL_LINE_STRIP, line.getModel().getVertexCount(),GL11.GL_UNSIGNED_INT, 0);
		
		GL20.glDisableVertexAttribArray(0);
		
		getShader("entityShader").updateUniform("view", view);
	}

	public void renderObjectWithLight(GameObjectWithLight object){
		if(mainCamera == null)
			return;
		
		
		getShader("entityShader").bind();
		
		getShader("entityShader").updateUniform("fakeLight", object.isFakeLight());
		
		getShader("entityShader").updateUniform("transformationMatrix", object.getTransformationMatrix());
		
		getShader("entityShader").updateUniform("color", new GVector3f(1,0,0));
		
		setMaterial(object.getMaterial());
		
		prepareAndDraw(3, object.getModel());
		
		disableVertex(3);
		
		getShader("entityShader").updateUniform("fakeLight", false);
	}
	
	public void renderObject(GameObject object){
		if(mainCamera == null)
			return;
		
		getShader("entityShader").bind();
		
		getShader("entityShader").updateUniform("transformationMatrix", object.getTransformationMatrix());
		
		setMaterial(object.getMaterial());
		
		prepareAndDraw(3, object.getModel());
		
		disableVertex(3);
		
	}
	
//	public void render(GameComponent component){
//		getShader("entityShader").bind();
//		getShader("entityShader").updateUniform("transformationMatrix", component.getTransformationMatrix());
//		setMaterial(component.getMaterial());
//		prepareAndDraw(3, component.getModel());
//		disableVertex(3);
//	}
	
	public void renderEnemy(BasicEnemy basicEnemy) {
		if(mainCamera == null){
			return;
		}
		
		getShader("entityShader").bind();
		getShader("entityShader").updateUniform("transformationMatrix", basicEnemy.getTransformationMatrix());
		getShader("entityShader").updateUniform("color", new GVector3f(1,0,0));
		
		prepareAndDraw(3, basicEnemy.getModel());
		
		disableVertex(3);
	}
	
	//OTHERS
	
	public void prepare() {
		glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
		setViewMatrix();
		setEyePos();
	}
	
	private void prepareAndDraw(int i, Model model){
		GL30.glBindVertexArray(model.getVaoID());
		
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		if(i>2)
			GL20.glEnableVertexAttribArray(2);
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(),GL11.GL_UNSIGNED_INT, 0);
	}
	
	private void disableVertex(int i){
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
	
	public void updateLight(PointLight light, int i){
		if(i<MAX_LIGHTS){
			getShader("entityShader").updateUniform("lightPosition"+i, light.getPosition());
			getShader("entityShader").updateUniform("lightColor"+i, light.getColor());
			getShader("entityShader").updateUniform("attenuation"+i, light.getAttenuation());
			getShader("entityShader").updateUniform("range"+i, light.getRange());
		}
	}
	
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
	
	public void addLight(ArrayList<PointLight> lights){
		lights.addAll(lights);
		
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

	//GETTERS
	
	public CameraStrategy getMainCamera() {
		return mainCamera;
	}
	
	public SelectBlock getSelectBlock() {
		return selectBlock;
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
		this.mousePicker = new MousePicker(mainCamera);
		mousePicker.update();
		
		for(Entry<String, GBasicShader> s : shaders.entrySet()){
			getShader(s.getKey()).bind();
			getShader(s.getKey()).updateUniform("projectionMatrix", mainCamera.getProjectionMatrix());
		}
	}

	public void setView(int view) {
		if(this.view == view)
			return;
		
		this.view = view;
			
		for(Entry<String, GBasicShader> s : shaders.entrySet()){
			getShader(s.getKey()).bind();
			getShader(s.getKey()).updateUniform("view", view);
		}
			
		
	}
	
	public void setTexture(boolean texture){
		if(this.texture == texture)
			return;
		this.texture = texture;
		getShader("entityShader").bind();
		getShader("entityShader").updateUniform("texture", texture);
	}
	
	public void setSun(PointLight sun){
		if(this.sun == sun)
			return;
		this.sun = sun;
		
		getShader("entityShader").bind();
		
		getShader("entityShader").updateUniform("lightPosition", sun.getPosition());
		getShader("entityShader").updateUniform("lightColor", sun.getColor());
	}
	
	public void setSelectBlock(SelectBlock selectBlock) {
		this.selectBlock = selectBlock;
	}

	public void setLights(List<PointLight> lights){
		if(this.lights == lights)
			return;
		this.lights = lights;
		
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
	
	public void setEyePos(){
		if(mainCamera == null){
			System.out.println("nieje nastavená hlavná kamera");
			return;
		}
		getShader("entityShader").bind();
		getShader("entityShader").updateUniform("eyePos", mainCamera.getPosition());
	}
	
	public void setMaterial(Material mat){
		getShader("entityShader").connectTextures();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		mat.getDiffuse().bind();
		
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		normal.bind();
		
		getShader("entityShader").updateUniform("shineDumper", mat.getSpecularPower());
		getShader("entityShader").updateUniform("reflectivity", mat.getSpecularIntensity());
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

	public static GBasicShader getShader(String name){
		return shaders.get(name);
	}
	
}
