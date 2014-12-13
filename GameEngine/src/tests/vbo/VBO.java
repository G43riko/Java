package tests.vbo;

import org.lwjgl.opengl.Display;

import utils.FileLoader;

public class VBO {
	public static void main(String[] args){
		DisplayManager.createDisplay();
		
		Loader loader  = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
//		float[] vertices = {
//				//left bottom triangle
//				-0.5f,  0.5f,  0f,
//				-0.5f, -0.5f,  0f,
//				 0.5f, -0.5f,  0f,
//				 //Right top triangle
//				 0.5f, -0.5f,  0f,
//				 0.5f,  0.5f,  0f,
//				-0.5f,  0.5f,  0f
//		};
		float[] vertices = {
				-0.5f,  0.5f,  0f,//v1
				-0.5f, -0.5f,  0f,//v2
				 0.5f, -0.5f,  0f,//v3
				 0.5f,  0.5f,  0f //v4
		};
		
		int[] indices = {
			0,1,3,
			3,1,2
		};
		
		float[]textureCoords = {
				0,0,
				0,1,
				1,1,
				1,0
		};
		
		RawModel model = loader.loadToVAO(vertices, textureCoords,indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("dirt.jpg"));
		TexturedModel textureModel = new TexturedModel(model,texture);
		
		while(!Display.isCloseRequested()){
			renderer.prepare();
			
			shader.start();
			renderer.render(textureModel);
			shader.stop();
			
			DisplayManager.updateDisplay();
		}

		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
