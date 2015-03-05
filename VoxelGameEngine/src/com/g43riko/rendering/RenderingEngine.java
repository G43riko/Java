package com.g43riko.rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;


import com.g43riko.object.Shape;
import com.g43riko.rendering.shader.DefaultShader;

public class RenderingEngine {
	
	public final static DefaultShader DEFAULT_SHADER = new DefaultShader(); 
	
	public void renderShape(Shape shape) {
		DEFAULT_SHADER.bind();
		
		RawModel rawModel = shape.getModel();
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, rawModel.getVertexCount(), GL11.GL_UNSIGNED_INT,0);
		
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
		
	}

}
