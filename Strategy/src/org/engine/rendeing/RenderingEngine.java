package org.engine.rendeing;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.engine.component.GameComponent;
import org.engine.light.PointLight;
import org.engine.object.GameObject;
import org.engine.particles.Particle;
import org.engine.rendeing.material.Material;
import org.engine.rendeing.material.Texture2D;
import org.engine.rendeing.model.Model;
import org.engine.rendeing.shader.GBasicShader;
import org.engine.rendeing.shader.Shader;
import org.engine.util.Maths;
import org.engine.util.MousePicker;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import game.Line;
import game.component.CameraStrategy;
import game.component.SkyBox;
import game.entity.Bullet;
import game.entity.enemy.BasicEnemy;
import game.entity.player.Player;
import game.object.GameObjectWithLight;
import game.rendering.shader.EntityShader;
import game.rendering.shader.ParticleShader;
import game.rendering.shader.SkyShader;
import game.world.Block;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public final class RenderingEngine {
	public static final GBasicShader entityShader = new EntityShader();
	public static final GBasicShader skyShader = new SkyShader();
	public static final GBasicShader particleShader = new ParticleShader();
	private static HashMap<String, GBasicShader> shaders = new HashMap<String, GBasicShader>(); 
//	static{
//		shaders.put("entityShader", new EntityShader());
//		shaders.put("skyShader", new SkyShader());
//		shaders.put("particleShader", new ParticleShader());
//	}
	public final static int MAX_LIGHTS = 4;
	
	private CameraStrategy mainCamera;
	private int view = 0;
	private boolean specular;
	private boolean texture;
	private boolean light;
	private boolean fog;
	private HashMap<String, Boolean> variables = new HashMap<String, Boolean>(); 
	private GVector3f ambient;
	private GVector2f mousePos;
	private GVector2f mouseDir;
	private PointLight sun;
	private List<PointLight> lights = new ArrayList<PointLight>();
	private Texture2D normal = new Texture2D("normal.jpg");
	private SelectBlock selectBlock = new SelectBlock();
	private MousePicker mousePicker;
	private GVector3f backgroundColor = new GVector3f();
	
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
		setSpecular(false);
		setLight(true);
		setFog(false);
		setAmbient(new GVector3f(1));
		mousePos = new GVector2f(Mouse.getX(),Mouse.getY());
	}
	
	//RENDERERS
	
	public void renderSky(SkyBox sky){
		if(mainCamera == null || view == 4){
			return;
		}
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		skyShader.bind();
		
		
		if(view == 3){
			skyShader.updateUniform("color", sky.getTexture().getAverageColor());
		}
		
		skyShader.updateUniform("transformationMatrix", sky.getTransformationMatrix());
		
		sky.getTexture().bind();

		prepareAndDraw(2, sky.getModel());
		
		disableVertex(2);
	}
	
	public void renderBlock(Block block){
		if(mainCamera == null || !block.isActive()){
			return;
		}
		
		entityShader.bind();
		
		entityShader.updateUniform("transformationMatrix", block.getTransformationMatrix());
		
		if(view == 3 || selectBlock.block == block){
			entityShader.updateUniform("color", block.getMaterial().getDiffuse().getAverageColor());
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
		particleShader.bind();
		
		
		
		particleShader.updateUniform("color", particle.getColor());
		
		if(particle.isFadding())
			particleShader.updateUniform("alpha", particle.getAlpha());
		
		particleShader.updateUniform("transformationMatrix",particle.getTransformationMatrix(mainCamera.getPosition()));
		
		if(particle.getTexture() != null)
			particle.getTexture().bind();
		
		prepareAndDraw(2,particle.getModel());
		
		disableVertex(2);
	}
	
	public void renderLine(Line line) {
		entityShader.bind();
		entityShader.updateUniform("transformationMatrix", line.getTransformationMatrix());
		entityShader.updateUniform("color", line.getColor());
		entityShader.updateUniform("view", 3);
		
		GL30.glBindVertexArray(line.getModel().getVaoID());
		
		GL20.glEnableVertexAttribArray(0);
		
		GL11.glDrawElements(GL11.GL_LINE_STRIP, line.getModel().getVertexCount(),GL11.GL_UNSIGNED_INT, 0);
		
		GL20.glDisableVertexAttribArray(0);
		
		entityShader.updateUniform("view", view);
	}

	public void renderObjectWithLight(GameObjectWithLight object){
		if(mainCamera == null)
			return;
		
		
		entityShader.bind();
		
		entityShader.updateUniform("fakeLight", object.isFakeLight());
		
		entityShader.updateUniform("transformationMatrix", object.getTransformationMatrix());
		
		entityShader.updateUniform("color", new GVector3f(1,0,0));
		
		setMaterial(object.getMaterial());
		
		prepareAndDraw(3, object.getModel());
		
		disableVertex(3);
		
		entityShader.updateUniform("fakeLight", false);
	}
	
	public void renderObject(GameObject object){
		if(mainCamera == null)
			return;
		
		entityShader.bind();
		
		entityShader.updateUniform("transformationMatrix", object.getTransformationMatrix());
		
		setMaterial(object.getMaterial());
		
		prepareAndDraw(3, object.getModel());
		
		disableVertex(3);
		
	}
	
//	public void render(GameComponent component){
//		entityShader.bind();
//		entityShader.updateUniform("transformationMatrix", component.getTransformationMatrix());
//		setMaterial(component.getMaterial());
//		prepareAndDraw(3, component.getModel());
//		disableVertex(3);
//	}
	
	public void renderEnemy(BasicEnemy basicEnemy) {
		if(mainCamera == null){
			return;
		}
		
		entityShader.bind();
		
		entityShader.updateUniform("transformationMatrix", basicEnemy.getTransformationMatrix());
		
		entityShader.updateUniform("color", new GVector3f(1,0,0));
		
		prepareAndDraw(3, basicEnemy.getModel());
		
		disableVertex(3);
	}
	
	//OTHERS
	
	public void prepare() {
		glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
		calcMouseDir();
		setViewMatrix();
		setEyePos();
	}
	
	private void calcMouseDir(){
		GVector2f actPos = new GVector2f(Mouse.getX(),Mouse.getY());
		mouseDir =  actPos.sub(mousePos).div(5);
		mousePos = actPos;
	}
	
	private void prepareAndDraw(int i, Model model){
		GL30.glBindVertexArray(model.getVaoID());
		
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		if(i>2)
			GL20.glEnableVertexAttribArray(2);
		
//		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(),GL11.GL_UNSIGNED_INT, 0);
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
//		defaultShader.cleanUp();
//		hudShader.cleanUp();
		entityShader.cleanUp();
		skyShader.cleanUp();
		particleShader.cleanUp();
	}
	
	public void updateLight(PointLight light, int i){
		if(i<MAX_LIGHTS){
			entityShader.updateUniform("lightPosition"+i, light.getPosition());
			entityShader.updateUniform("lightColor"+i, light.getColor());
			entityShader.updateUniform("attenuation"+i, light.getAttenuation());
			entityShader.updateUniform("range"+i, light.getRange());
		}
	}
	
	public void addLight(PointLight light){
		lights.add(light);
		
		entityShader.bind();
		for(int i=0 ;i<MAX_LIGHTS ; i++){
			if(i < lights.size()){
				entityShader.updateUniform("lightPosition"+i, lights.get(i).getPosition());
				entityShader.updateUniform("lightColor"+i, lights.get(i).getColor());
				entityShader.updateUniform("attenuation"+i, lights.get(i).getAttenuation());
			}
			else{
				entityShader.updateUniform("lightPosition"+i, new GVector3f());
				entityShader.updateUniform("lightColor"+i, new GVector3f());
				entityShader.updateUniform("attenuation"+i, new GVector3f(1,0,0));
			}
		}
	}
	
	public void addLight(ArrayList<PointLight> lights){
		lights.addAll(lights);
		
		entityShader.bind();
		for(int i=0 ;i<MAX_LIGHTS ; i++){
			if(i < lights.size()){
				entityShader.updateUniform("lightPosition"+i, lights.get(i).getPosition());
				entityShader.updateUniform("lightColor"+i, lights.get(i).getColor());
				entityShader.updateUniform("attenuation"+i, lights.get(i).getAttenuation());
			}
			else{
				entityShader.updateUniform("lightPosition"+i, new GVector3f());
				entityShader.updateUniform("lightColor"+i, new GVector3f());
				entityShader.updateUniform("attenuation"+i, new GVector3f(1,0,0));
			}
		}
	}

	//GETTERS
	
	public static GBasicShader getEntityshader() {
		return entityShader;
	}

	public CameraStrategy getMainCamera() {
		return mainCamera;
	}
	
	public SelectBlock getSelectBlock() {
		return selectBlock;
	}

	//SETTERS

	public void setFog(boolean fog) {
		if(this.fog == fog)
			return;
		this.fog = fog;
		entityShader.bind();
		entityShader.updateUniform("fog", fog);
		
		skyShader.bind();
		skyShader.updateUniform("fog", fog);
	}
	
	public void setMainCamera(CameraStrategy mainCamera) {
		this.mainCamera = mainCamera;
		this.mousePicker = new MousePicker(mainCamera);
		mousePicker.update();
		
		entityShader.bind();
		entityShader.updateUniform("projectionMatrix", mainCamera.getProjectionMatrix());
		
		skyShader.bind();
		skyShader.updateUniform("projectionMatrix", mainCamera.getProjectionMatrix());
		
		particleShader.bind();
		particleShader.updateUniform("projectionMatrix", mainCamera.getProjectionMatrix());
	}

	public void setView(int view) {
		if(this.view == view)
			return;
		else{
			this.view = view;
			
			entityShader.bind();
			entityShader.updateUniform("view", view);
			
			skyShader.bind();
			skyShader.updateUniform("view", view);
			
			particleShader.bind();
			particleShader.updateUniform("view", view);
			
		}
	}
	
	public void setTexture(boolean texture){
		if(this.texture == texture)
			return;
		this.texture = texture;
		entityShader.bind();
		entityShader.updateUniform("texture", texture);
	}
	
	public void setSpecular(boolean specular){
		if(this.specular == specular)
			return;
		this.specular = specular;
		entityShader.bind();
		entityShader.updateUniform("specular", specular);
	}
	
	public void setLight(boolean light){
		if(this.light == light)
			return;
		this.light = light;
		entityShader.bind();
		entityShader.updateUniform("light", light);
	}

	public void setSun(PointLight sun){
		if(this.sun == sun)
			return;
		this.sun = sun;
		
		entityShader.bind();
		
		entityShader.updateUniform("lightPosition", sun.getPosition());
		entityShader.updateUniform("lightColor", sun.getColor());
	}
	
	public void setSelectBlock(SelectBlock selectBlock) {
		this.selectBlock = selectBlock;
	}

	public void setLights(List<PointLight> lights){
		if(this.lights == lights)
			return;
		this.lights = lights;
		
		entityShader.bind();
		for(int i=0 ;i<MAX_LIGHTS ; i++){
			if(i < lights.size()){
				entityShader.updateUniform("lightPosition"+i, lights.get(i).getPosition());
				entityShader.updateUniform("lightColor"+i, lights.get(i).getColor());
				entityShader.updateUniform("attenuation"+i, lights.get(i).getAttenuation());
			}
			else{
				entityShader.updateUniform("lightPosition"+i, new GVector3f());
				entityShader.updateUniform("lightColor"+i, new GVector3f());
				entityShader.updateUniform("attenuation"+i, new GVector3f(1,0,0));
			}
		}
	}
	
	public void setEyePos(){
		if(mainCamera == null){
			System.out.println("nieje nastavená hlavná kamera");
			return;
		}
		entityShader.bind();
		entityShader.updateUniform("eyePos", mainCamera.getPosition());
	}
	
	public void setMaterial(Material mat){
		entityShader.connectTextures();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		mat.getDiffuse().bind();
		
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		normal.bind();
		
		entityShader.updateUniform("shineDumper", mat.getSpecularPower());
		entityShader.updateUniform("reflectivity", mat.getSpecularIntensity());
	}
	
	public void setViewMatrix(){
		if(mainCamera == null){
			System.out.println("nieje nastavená hlavná kamera");
			return;
		}
		GMatrix4f mat = Maths.MatrixToGMatrix(Maths.createViewMatrix(mainCamera));
		entityShader.bind();
		entityShader.updateUniform("viewMatrix", mat);
		
		skyShader.bind();
		skyShader.updateUniform("viewMatrix", mat);
		
		particleShader.bind();
		particleShader.updateUniform("viewMatrix", mat);
		
	}
	
	public void setAmbient(GVector3f ambient) {
		if(this.ambient == ambient){
			return ;
		}
		this.ambient = ambient;
		
		entityShader.bind();
		entityShader.updateUniform("ambient", ambient);
		
		skyShader.bind();
		skyShader.updateUniform("ambient",ambient);
		
		particleShader.bind();
		particleShader.updateUniform("ambient",ambient);
	}

	public GBasicShader getShader(String name){
		return shaders.get(name);
	}
	
}
