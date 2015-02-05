package game.rendering;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;




import org.lwjgl.util.vector.Matrix4f;


import org.lwjgl.util.vector.Vector3f;












//import utils.Maths;


import game.object.Camera;
import game.object.Entity;
import game.rendering.shader.Shader;
import game.util.Maths;
import glib.util.GColor;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class RenderingEngine {
	public static final Shader defaultShader = new Shader("shader");
	public static final Shader entityShader = new Shader("entityShader");
	public static final Shader skyShader = new Shader("skyShader");
	private Camera mainCamera;
	private int view = 0;
	private boolean blur;
	private GVector3f ambient;
	private GVector2f mousePos;
	
	public RenderingEngine(){ 
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);
		
		glClearColor(0, 1.0f, 0.0f, 0.10f);
		
		setBlur(false);
		setAmbient(new GVector3f(1, 1, 1));
		mousePos = new GVector2f(Mouse.getX(),Mouse.getY());
	}
	
	public void clearScreen(){
		glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
	};
	
	public void renderEntity(Entity entity, Shader shader){
		if(mainCamera == null){
			return;
		}
		shader.bind();
		GL30.glBindVertexArray(entity.getModel().getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		
		if(view == 3){
			shader.updateUniform("color", entity.getTexture().getAverageColor());
		}
		if(blur){
			GVector2f actPos = new GVector2f(Mouse.getX(),Mouse.getY());
			shader.updateUniform("mouseDir", actPos.sub(mousePos).div(5));
			mousePos = actPos;
		}
		
		shader.updateUniform("viewMatrix", Maths.MatrixToGMatrix(Maths.createViewMatrix(mainCamera)));
		
		
		shader.updateUniform("transformationMatrix", entity.getTransformationMatrix());
		
		entity.getTexture().bind();
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, entity.getModel().getVertexCount(),GL11.GL_UNSIGNED_INT, 0);
		
		cleanUp();
	}
	
	public void renderSky(Entity entity, Shader shader){
		if(mainCamera == null){
			return;
		}
		shader.bind();
		GL30.glBindVertexArray(entity.getModel().getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		
		if(view == 3){
			shader.updateUniform("color", entity.getTexture().getAverageColor());
		}
		if(blur){
			shader.updateUniform("mouseDir", new GVector2f(Mouse.getDX(),Mouse.getDY()).div(5));
		}
		
		shader.updateUniform("viewMatrix", Maths.MatrixToGMatrix(Maths.createViewMatrix(mainCamera)));
		
		
		shader.updateUniform("transformationMatrix", entity.getTransformationMatrix());
		
		entity.getTexture().bind();
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, entity.getModel().getVertexCount(),GL11.GL_UNSIGNED_INT, 0);
		
		cleanUp();
	}
	
	public void cleanUp(){
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}

	public void setMainCamera(Camera mainCamera) {
		this.mainCamera = mainCamera;

		entityShader.bind();
		entityShader.updateUniform("projectionMatrix", mainCamera.getProjectionMatrix());
		entityShader.unbind();
		
		skyShader.bind();
		skyShader.updateUniform("projectionMatrix", mainCamera.getProjectionMatrix());
		skyShader.unbind();
	}

	public void setView(int view) {
		if(this.view == view)
			return;
		else{
			this.view = view;
			
			entityShader.bind();
			entityShader.updateUniform("view", view);
			entityShader.unbind();
			
			skyShader.bind();
			skyShader.updateUniform("view", view);
			skyShader.unbind();
			
		}
	}
	
	public void setBlur(boolean blur){
		if(this.blur == blur)
			return;
		this.blur = blur;
		entityShader.bind();
		entityShader.updateUniform("blur", blur);
		entityShader.unbind();
		
		skyShader.bind();
		skyShader.updateUniform("blur", blur);
		skyShader.unbind();
		
	}

	public void setAmbient(GVector3f ambient) {
		if(this.ambient == ambient){
			return ;
		}
		this.ambient = ambient;
		entityShader.bind();
		entityShader.updateUniform("ambient", ambient);
		entityShader.unbind();
		
		skyShader.bind();
		skyShader.updateUniform("ambient",ambient);
		skyShader.unbind();
	}
}
