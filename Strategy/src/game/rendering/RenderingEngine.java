package game.rendering;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import game.object.Camera;
import game.object.Entity;
import game.object.SkyBox;
import game.particle.Particle;
import game.rendering.shader.Shader;
import game.util.Maths;
import game.world.Block;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class RenderingEngine {
	public static final Shader defaultShader = new Shader("shader");
	public static final Shader guiShader = new Shader("guiShader");
	public static final Shader entityShader = new Shader("entityShader");
	public static final Shader skyShader = new Shader("skyShader");
	public static final Shader particleShader = new Shader("particleShader");
	
	private Camera mainCamera;
	private int view = 0;
	private boolean blur;
	private GVector3f ambient;
	private GVector2f mousePos;
	private GVector2f mouseDir;
	private Block select;
	
	public RenderingEngine(){ 
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
		
//		GL11.glEnable(GL11.GL_CULL_FACE);
//		GL11.glCullFace(GL11.GL_FRONT);
		
		glClearColor(0, 1.0f, 0.0f, 0.10f);
		
		setBlur(false);
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
		prepare(3,entity.getModel().getVaoID());
		
		if(view == 3){
			entityShader.updateUniform("color", entity.getTexture().getAverageColor());
		}
		if(blur){
			entityShader.updateUniform("mouseDir", mouseDir);
		}
		
		entityShader.updateUniform("transformationMatrix", entity.getTransformationMatrix());
		
		entity.getTexture().bind();
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, entity.getModel().getVertexCount(),GL11.GL_UNSIGNED_INT, 0);
		
		disableVertex(3);
	}
	
	public void renderSky(SkyBox sky){
		if(mainCamera == null){
			return;
		}
		skyShader.bind();
		
		prepare(2, sky.getModel().getVaoID());
		
		if(view == 3){
			skyShader.updateUniform("color", sky.getTexture().getAverageColor());
		}
		
		skyShader.updateUniform("transformationMatrix", sky.getTransformationMatrix());
		
		sky.getTexture().bind();
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, sky.getModel().getVertexCount(),GL11.GL_UNSIGNED_INT, 0);
		
		disableVertex(2);
	}
	
	public void renderBlock(Block block){
		if(mainCamera == null || !block.isActive()){
			return;
		}
		
		entityShader.bind();
		
		entityShader.updateUniform("transformationMatrix", block.getTransformationMatrix());
		
		if(view == 3 || select == block){
			entityShader.updateUniform("color", block.getDiffuse().getAverageColor());
		}
		if(blur){
			entityShader.updateUniform("mouseDir", mouseDir);
		}
		block.getDiffuse().bind();
		boolean search = (select == null || block.getPosition().dist(mainCamera.getPosition()) < select.getPosition().dist(mainCamera.getPosition())); 
		for(int i=0 ; i<6 ; i++){
			if(block.getSide(i)){
				if(search && mainCamera.intersect(block.getPosition().add(block.getPoint(i, 0)), 
										    	  block.getPosition().add(block.getPoint(i, 1)),  
										    	  block.getPosition().add(block.getPoint(i, 2))))
					select = block;
				prepare(3, block.getModel(i).getVaoID());
				GL11.glDrawElements(GL11.GL_TRIANGLES, block.getModel(i).getVertexCount(),GL11.GL_UNSIGNED_INT, 0);
			}
		}
		disableVertex(3);
	}

	public void renderParticle(Particle particle) {
		if(mainCamera == null){
			return;
		}
		particleShader.bind();
		
		prepare(2,particle.getModel().getVaoID());
		
		particleShader.updateUniform("color", particle.getColor());
		
		if(particle.isFadding())
			particleShader.updateUniform("alpha", particle.getAlpha());
		
		particleShader.updateUniform("transformationMatrix",particle.getTransformationMatrix(mainCamera.getPosition()));
		
		if(particle.getTexture() != null)
			particle.getTexture().bind();
		GL11.glDrawElements(GL11.GL_TRIANGLES, particle.getModel().getVertexCount(),GL11.GL_UNSIGNED_INT, 0);
		disableVertex(2);
	}

	public void calcMouseDir(){
		GVector2f actPos = new GVector2f(Mouse.getX(),Mouse.getY());
		mouseDir =  actPos.sub(mousePos).div(5);
		mousePos = actPos;
	}
	
	private void prepare(int i, int id){
		GL30.glBindVertexArray(id);
		
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		if(i>2)
			GL20.glEnableVertexAttribArray(2);
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
		guiShader.cleanUp();
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

	public void setEyePos(){
		entityShader.bind();
		entityShader.updateUniform("eyePos", mainCamera.getPosition());
	}
	
	public void setViewMatrix(){
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

	public Block getSelect() {
		return select;
	}
	
	public void setSelect(Block select) {
		this.select = select;
	}

	public Camera getMainCamera() {
		return mainCamera;
	}


}
