package renderers;

import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_CW;
import static org.lwjgl.opengl.GL11.GL_DEPTH;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glIsEnabled;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glFrontFace;
import static org.lwjgl.opengl.GL11.glGetString;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER_SRGB;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import shaders.StaticShader;
import textures.ModelTexture;
import utils.Maths;
import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import menus.RMenu;


public class Renderer {
	
	
	public static void clearScreen(RMenu rmenu){
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		//glLoadIdentity();
		
		GL11.glClearColor((float)rmenu.BGRed.getValue()/255,(float)rmenu.BGGreen.getValue()/255,(float)rmenu.BGBlue.getValue()/255, 1.0f);
	}
	
	public static void initGraphics(){
		glClearColor(0.0f,0.0f,0.0f,0.0f);
		
		glEnable(GL_DEPTH_TEST);
//		glFrontFace(GL_CW);
		glEnable(GL11.GL_CULL_FACE);
		glCullFace(GL11.GL_BACK);
		glEnable(GL_DEPTH);
		glEnable(GL_DEPTH_TEST);
//		glEnable(GL_FRAMEBUFFER_SRGB);
		
		glEnable(GL_TEXTURE_2D);
	}
	
	public static void change(int co){
		if(glIsEnabled(co)){
			glDisable(co);
		}
		else{
			glEnable(co);
		}
	}
	
	public void render(Entity entity,StaticShader shader){
		TexturedModel texturedModel = entity.getModel();
		RawModel model = texturedModel.getRawModel();
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);//pre x,y,z - pos. suradnice
		GL20.glEnableVertexAttribArray(1);//pre u,v - texturu
		GL20.glEnableVertexAttribArray(2);//x,y,z - pre normálu
		//GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
		
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(new Vector3f(entity.getX(),entity.getY(),entity.getZ()), 
																		 entity.getRx(), entity.getRy(), entity.getRz(), entity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
		
		ModelTexture texture = texturedModel.getTexture();
		shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getID());//pripojí textúru
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(),GL11.GL_UNSIGNED_INT, 0);//vykreslí model
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}
	
	public static String getOpenGLVersion(){
		return glGetString(GL_VERSION);
	}
}
