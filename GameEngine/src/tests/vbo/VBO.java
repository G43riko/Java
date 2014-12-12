package tests.vbo;

import org.lwjgl.opengl.Display;

public class VBO {
	public static void main(String[] args){
		DisplayManager.createDisplay();
		
		Loader loader  = new Loader();
		Renderer renderer = new Renderer();
		
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
				-0.5f,  0.5f,  0f,
				-0.5f, -0.5f,  0f,
				 0.5f, -0.5f,  0f,
				 0.5f,  0.5f,  0f
		};
		
		int[] indices = {
			0,1,3,
			3,1,2
		};
		
		RawModel model = loader.loadToVAO(vertices,indices);
		
		while(!Display.isCloseRequested()){
			renderer.prepare();
			
			renderer.render(model);
			
			DisplayManager.updateDisplay();
		}
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
