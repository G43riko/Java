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

import org.engine.Config;
import org.engine.component.Camera;
import org.engine.component.light.DirectionalLight;
import org.engine.component.object.GameObject;
import org.engine.core.Screen;
import org.engine.gui.Hud;
import org.engine.rendering.material.Material;
import org.engine.rendering.material.Texture2D;
import org.engine.rendering.model.Model;
import org.engine.rendering.shader.GBasicShader;
import org.engine.rendering.shader.named.HudShader;
import org.engine.rendering.shader.named.ObjectShader;
import org.engine.rendering.shader.named.PostFXShader;
import org.engine.utils.Maths;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.tester.voxel.PointLightObject;
import org.tester.voxel.world.Block;
import org.tester.voxel.world.Blocks;

import glib.util.GLog;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;


public class RenderingEngine {
	public final static int MAX_LIGHTS = 8;
	
	private GVector3f ambient = new GVector3f();
	private GVector3f backgroundColor = new GVector3f();
	
	private int renderedPoligons = 0;
	private int renderedPoints = 0;
	
	private int typeOfView = 0;
	
	private DirectionalLight sun;
	private PointLightObject pointLight;
	
	private Camera mainCamera; 
	
	protected static HashMap<String, GBasicShader> shaders = new HashMap<String, GBasicShader>(); 
	private HashMap<String, Boolean> variables = new HashMap<String, Boolean>();
	
	/*************************BLOCK*PART***************************/
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
	
	public SelectBlock getSelectedBlock(){return selectBlock;}
	/*************************BLOCK*PART***************************/
	
	static{
		shaders.put("objectShader", new ObjectShader());
		shaders.put("hudShader", new HudShader());
		shaders.put("postFXShader", new PostFXShader());
	}
	
	//CONSTRUCTORS
	
	public RenderingEngine(Camera camera){
		mainCamera = camera;
		updateCamera();
		
		init3D();
		
		glClearColor(backgroundColor.getX(), backgroundColor.getY(), backgroundColor.getZ(), 1f);
		
		setAmbient(Config.ENGINE_DEFAULT_AMBIENT);
		
		setVariable("useLights", true);
		setVariable("useAmbient", true);
		setVariable("useTexture", true);
		setVariable("useSpecular", false);
		setVariable("useSpecularMap", false);
		setVariable("useCameraBlur", false);
		setVariable("useAntiAliasing", false);
		setVariable("useNormalMap", false);
		
		
		setVariable("useHud", true);
		
		setTypeOfView(0);
	}
	
	//RENDERERS
	
	public void renderObject(GameObject object) {
		if(mainCamera == null){
			GLog.write("nieje nastaven· hlavn· kamera");
			return;
		}
			
		getShader("objectShader").bind();

		getShader("objectShader").updateUniform("fakeLight", object.isUseFakeLight());
		getShader("objectShader").updateUniform("receiveLight", object.isReceiveLight());
		
		
		
		getShader("objectShader").updateUniform("transformationMatrix", object.getTransformationMatrix());
		setMaterial(object.getMaterial());
		prepareAndDraw(3, object.getModel());
		
		getShader("objectShader").updateUniform("fakeLight", false);
		getShader("objectShader").updateUniform("receiveLight", false);
		
		disableVertex(3);
	}
	
	public void renderHud(Hud hud) {
		if(!variables.containsKey("useHud") || !variables.get("useHud"))
			return;
		
		
		shaders.get("hudShader").bind();
		GL30.glBindVertexArray(hud.getModel().getVaoID());
		GL20.glEnableVertexAttribArray(0);
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		hud.getTexture().bind();
		
		shaders.get("hudShader").updateUniform("transformationMatrix", hud.getTransformationMatrix());
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, hud.getModel().getVertexCount());
		
//		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}

	public void renderScreen(Screen screen) {
		shaders.get("postFXShader").bind();
		GL30.glBindVertexArray(screen.getModel().getVaoID());
		GL20.glEnableVertexAttribArray(0);
		

		GL11.glDisable(GL11.GL_DEPTH_TEST);
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		screen.getTexture().bind();
		
		if(variables.get("useCameraBlur"))
			shaders.get("postFXShader").updateUniform("mouseMove", new GVector2f(Mouse.getDX(),Mouse.getDY()).div(16));
		
		shaders.get("postFXShader").updateUniform("transformationMatrix", screen.getTransformationMatrix());
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, screen.getModel().getVertexCount());
		
//		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
	
	public void renderBlock(Block block){
		if(mainCamera == null){
			GLog.write("nieje nastaven· hlavn· kamera");
			return;
		}
		
		getShader("objectShader").bind();
		getShader("objectShader").updateUniform("fakeLight", false);
		getShader("objectShader").updateUniform("receiveLight", true);
		getShader("objectShader").updateUniform("transformationMatrix", block.getTransformationMatrix());
		setMaterial(block.getMaterial());
		
		float blockToCamDist = block.getPosition().dist(getMainCamera().getPosition());
		boolean search = (block.isSelectable() && 
						  blockToCamDist < Config.PLAYER_MAX_CLICK_DIST &&
				 		  blockToCamDist < block.getPosition().dist(getMainCamera().getPosition().add(getMainCamera().getForward()))) &&
							(selectBlock.block == null || 
							blockToCamDist < selectBlock.block.getPosition().dist(getMainCamera().getPosition()) && false);
		
		mainCamera.getMousePicker().getCurrentRay();
		for(int i=0 ; i<6 ; i++)
			if(block.getSide(i)){
				if(search && GVector3f.intersectRayWithSquare(getMainCamera().getPosition(), 
						getMainCamera().getPosition().add(getMainCamera().getMousePicker().getCurrentRay().mul(1000)),
															  block.getPosition().add(block.getPoint(i, 0)), 
															  block.getPosition().add(block.getPoint(i, 1)),  
															  block.getPosition().add(block.getPoint(i, 2)))){
					float dist = block.getPosition().add(block.getPoint(i, 1).add(block.getPoint(i, 2)).div(2)).dist(getMainCamera().getPosition());
					if(selectBlock.dist<0 || selectBlock.dist>dist){
						selectBlock.dist = dist;
						selectBlock.side = i;
					}
					selectBlock.block = block;
				}
				prepareAndDraw(3, Blocks.getModel(i));
			}
		
		getShader("objectShader").updateUniform("receiveLight", false);
		disableVertex(3);
	}
	
	public void renderBlockBomber(org.tester.bomber.level.Block block) {
		if(mainCamera == null){
			GLog.write("nieje nastaven· hlavn· kamera");
			return;
		}
		
		getShader("objectShader").bind();
		getShader("objectShader").updateUniform("fakeLight", false);
		getShader("objectShader").updateUniform("receiveLight", true);
		getShader("objectShader").updateUniform("transformationMatrix", block.getTransformationMatrix());
		setMaterial(block.getMaterial());
		
		prepareAndDraw(3, block.getModel("top"));
		
		//a eöte netreba renderovaù ak su kocky vedla seba
		if(mainCamera.getPosition().getX() < block.getPosition().getX())
			prepareAndDraw(3, block.getModel("right"));
		else
			prepareAndDraw(3, block.getModel("left"));
		
		if(mainCamera.getPosition().getZ() < block.getPosition().getZ())
			prepareAndDraw(3, block.getModel("forward"));
		else
			prepareAndDraw(3, block.getModel("back"));
		
		
		getShader("objectShader").updateUniform("receiveLight", false);
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
		if(model == null){
			GLog.write("chce sa vykresliù model ktor˝ je null");
			return;
		}
			
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

	public void toogleVariable(String name){
		setVariable(name, !variables.get(name));
	}
	
	public void prepare() {
		init3D();
		renderedPoligons = 0;
		renderedPoints = 0;
		glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
		setViewMatrix(Maths.MatrixToGMatrix(Maths.createViewMatrix(mainCamera)));
		setEyePos();
		
		if(sun != null)
			setSun(sun);
		if(pointLight != null)
			setPointLight(pointLight);
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
		
		shaders.entrySet().stream()
						  .map(a -> a.getValue())
						  .filter(a -> a.hasUniform(name))
						  .forEach(a -> {
			a.bind();
			a.updateUniform(name, value);
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
			GLog.write("nieje nastaven· hlavn· kamera");
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
			GLog.write("nieje nastaven· hlavn· kamera");
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

	public void setBackgroundColor(GVector3f backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setTypeOfView(int value){
		if(typeOfView == value)
			return;
		
		typeOfView = value; 
		
		shaders.forEach((key,val) -> {
			if(val.hasUniform("typeOfView")){
				val.bind();
				val.updateUniform("typeOfView", typeOfView);
			}
		});
	}
	
	public void setSun(DirectionalLight sun){
		if(this.sun == sun && !sun.isChange())
			return;
		
		this.sun = sun;
		
		sun.setChange(false);
		shaders.get("objectShader").bind();
		((ObjectShader)shaders.get("objectShader")).updateUniform("sun", sun);
	}

	public void setPointLight(PointLightObject pointLight) {
		if(this.pointLight == pointLight && !pointLight.getPointLight().isChange())
			return;
		
		this.pointLight = pointLight;
		
		pointLight.setChange(false);
		
		pointLight.setMaterial(new Material(new Texture2D(pointLight.getPointLight().getColor(),new GVector2f(64,64))));
		
		shaders.get("objectShader").bind();
		((ObjectShader)shaders.get("objectShader")).updateUniform("pointLight", pointLight.getPointLight());
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

	public HashMap<String, Boolean> getVariables() {
		return new HashMap<String, Boolean>(variables);
	}

	
	public DirectionalLight getSun() {
		return sun;
	}

	
	public PointLightObject getPointLight() {
		return pointLight;
	}

	public Camera getMainCamera() {
		return mainCamera;
	}
	
	
}
