package renderers;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER_SRGB;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import shaders.StaticShader;
import terrains.Map;
import textures.ModelTexture;
import utils.Maths;
import entities.BasicEntity;
import entities.Camera;
import entities.Camerka;
import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import menus.RMenu;


public class Renderer {
	
	
	public static void clearScreen(RMenu rmenu){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor((float)rmenu.BGRed.getValue()/255,(float)rmenu.BGGreen.getValue()/255,(float)rmenu.BGBlue.getValue()/255, 1.0f);
	}
	
	public static void initGraphics(){
		glClearColor(0.0f,0.0f,0.0f,0.0f);
		
		//glFrontFace(GL_CW);
		glEnable(GL11.GL_CULL_FACE);
		glCullFace(GL11.GL_BACK);
//		glEnable(GL_DEPTH);
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
	
	public void render(BasicEntity entity,StaticShader shader){
		if(entity==null)
			return;
		
		TexturedModel texturedModel = ((Entity)entity).getModel();
		RawModel model = texturedModel.getRawModel();
		
		//bind cube
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);//pre x,y,z - pos. suradnice
		GL20.glEnableVertexAttribArray(1);//pre u,v - texturu
		GL20.glEnableVertexAttribArray(2);//x,y,z - pre normálu
		
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(new Vector3f(entity.getX(),entity.getY(),entity.getZ()), 
																		 entity.getRx(), entity.getRy(), entity.getRz(), entity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
		
		ModelTexture texture = texturedModel.getTexture();
		
		//load shine variables
		//shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
		
		//bind texture
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getID());//pripojí textúru
			GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(),GL11.GL_UNSIGNED_INT, 0);//vykreslí model
		
		//unbind cube	
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
		
	}
	
	public static String getOpenGLVersion(){
		return glGetString(GL_VERSION);
	}
}
