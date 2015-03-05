package rendering;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import entities.BasicEntity;
import entities.Entity;
import shaders.StaticShader;
import terrains.Block2;
import textures.ModelTexture;
import utils.Maths;

public class RenderingEngine {
	private final static StaticShader SHADER = new StaticShader();
	public void renderBlock(Block2 block) {
		
		
		Matrix4f transformationMatrix = block.getTransformationMatrix();
		SHADER.loadTransformationMatrix(transformationMatrix);
		
		ModelTexture texture = block.getTexture();
		
		//load shine variables
		//shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
		//bind texture
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getID());//pripojí textúru
		
		for(int i=0 ; i<6 ; i++){
			if(block.getSide(i)){
			RawModel model = block.getModel(i);
				GL30.glBindVertexArray(model.getVaoID());
				GL20.glEnableVertexAttribArray(0);//pre x,y,z - pos. suradnice
				GL20.glEnableVertexAttribArray(1);//pre u,v - texturu
				GL20.glEnableVertexAttribArray(2);//x,y,z - pre normálu
				
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(),GL11.GL_UNSIGNED_INT, 0);//vykreslí model
				//unbind cube	
				GL20.glDisableVertexAttribArray(0);
				GL20.glDisableVertexAttribArray(1);
				GL20.glDisableVertexAttribArray(2);
				GL30.glBindVertexArray(0);
			}
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

}
