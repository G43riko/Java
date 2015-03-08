package game.rendering;

import static org.lwjgl.opengl.GL11.*;

import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import game.Light;
import game.components.Player;
import game.object.Camera;
import game.object.Entity;
import game.object.SkyBox;
import game.particle.Particle;
import game.rendering.material.Material;
import game.rendering.material.Texture2D;
import game.rendering.model.Model;
import game.rendering.shader.Shader;
import game.util.Maths;
import game.world.Block;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class RenderingEngine {
	public static final Shader defaultShader = new Shader("shader");
	public static final Shader hudShader = new Shader("hudShader");
	public static final Shader entityShader = new Shader("entityShader");
	public static final Shader skyShader = new Shader("skyShader");
	public static final Shader particleShader = new Shader("particleShader");
	
	public final static int MAX_LIGHTS = 4;
	
	private Camera mainCamera;
	private int view = 0;
	private boolean blur;
	private boolean specular;
	private boolean texture;
	private boolean light;
	private GVector3f ambient;
	private GVector2f mousePos;
	private GVector2f mouseDir;
	private Light sun;
	private List<Light> lights;
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
	
	public RenderingEngine(){ 
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_FRONT);
		
		glClearColor(0, 1.0f, 0.0f, 0.10f);
		
		setBlur(false);
		setTexture(true);
		setSpecular(true);
		setLight(true);
		setAmbient(new GVector3f(1, 1, 1));
		mousePos = new GVector2f(Mouse.getX(),Mouse.getY());
	}
	
	public void prepare() {
		glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
		calcMouseDir();
		setViewMatrix();
		setEyePos();
	}
	
	public void renderEntity(Entity entity){
		if(mainCamera == null){
			return;
		}
		entityShader.bind();
		
		if(view == 3){
			entityShader.updateUniform("color", entity.getTexture().getAverageColor());
		}
		if(blur){
			entityShader.updateUniform("mouseDir", mouseDir);
		}
		
		entityShader.updateUniform("transformationMatrix", entity.getTransformationMatrix());
		
		entity.getTexture().bind();

		prepareAndDraw(3,entity.getModel());
		
		disableVertex(3);
	}
	
	public void renderSky(SkyBox sky){
		if(mainCamera == null){
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
		if(blur){
			entityShader.updateUniform("mouseDir", mouseDir);
		}
		
		setMaterial(block.getMaterial());
		
		float blockToCamDist = block.getPosition().dist(mainCamera.getPosition());
		boolean search = (block.isClickable() && blockToCamDist < Player.MAX_CLICK_DIST &&
						 (selectBlock.block == null || blockToCamDist < selectBlock.block.getPosition().dist(mainCamera.getPosition())) &&
						 blockToCamDist < block.getPosition().dist(mainCamera.getPosition().add(mainCamera.getForward())) && view!=4);
		for(int i=0 ; i<6 ; i++){
			if(block.getSide(i)){
				if(search && mainCamera.intersect(block.getPosition().add(block.getPoint(i, 0)), 
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
	
	public void calcMouseDir(){
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
		defaultShader.cleanUp();
		hudShader.cleanUp();
		entityShader.cleanUp();
		skyShader.cleanUp();
		particleShader.cleanUp();
	}
	
	public void setMainCamera(Camera mainCamera) {
		this.mainCamera = mainCamera;

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
	
	public void setBlur(boolean blur){
		if(this.blur == blur)
			return;
		this.blur = blur;
		entityShader.bind();
		entityShader.updateUniform("blur", blur);
		
		skyShader.bind();
		skyShader.updateUniform("blur", blur);
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

	public void setSun(Light sun){
		if(this.sun == sun)
			return;
		this.sun = sun;
		
		entityShader.bind();
		
		entityShader.updateUniform("lightPosition", sun.getPosition());
		entityShader.updateUniform("lightColor", sun.getColor());
	}
	
	public void updateLight(Light light, int i){
		if(i<MAX_LIGHTS){
			entityShader.updateUniform("lightPosition"+i, light.getPosition());
			entityShader.updateUniform("lightColor"+i, light.getColor());
			entityShader.updateUniform("attenuation"+i, light.getAttenuation());
			entityShader.updateUniform("range"+i, light.getRange());
		}
	}
	
	public void setLights(List<Light> lights){
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
			System.out.println("nieje nastaven� hlavn� kamera");
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
			System.out.println("nieje nastaven� hlavn� kamera");
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

	public static Shader getEntityshader() {
		return entityShader;
	}

	public Camera getMainCamera() {
		return mainCamera;
	}

	public SelectBlock getSelectBlock() {
		return selectBlock;
	}

	public void setSelectBlock(SelectBlock selectBlock) {
		this.selectBlock = selectBlock;
	}


}
