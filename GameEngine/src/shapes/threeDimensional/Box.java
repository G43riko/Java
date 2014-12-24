package shapes.threeDimensional;

import main.Loader;
import models.RawModel;

public class Box {
	
	static float vertices[] = {
    -0.5000f, -0.5000f,  0.5000f,
     0.5000f, -0.5000f,  0.5000f,
    -0.5000f,  0.5000f,  0.5000f,
    -0.5000f,  0.5000f,  0.5000f,
     0.5000f, -0.5000f,  0.5000f,
     0.5000f,  0.5000f,  0.5000f, 

    -0.5000f, -0.5000f, -0.5000f,
    -0.5000f,  0.5000f, -0.5000f,
     0.5000f, -0.5000f, -0.5000f,
     0.5000f, -0.5000f, -0.5000f,
    -0.5000f,  0.5000f, -0.5000f,
     0.5000f,  0.5000f, -0.5000f,

     0.5000f, -0.5000f, -0.5000f, 
     0.5000f,  0.5000f, -0.5000f, 
     0.5000f, -0.5000f,  0.5000f, 
     0.5000f, -0.5000f,  0.5000f, 
     0.5000f,  0.5000f, -0.5000f, 
     0.5000f,  0.5000f,  0.5000f, 

    -0.5000f, -0.5000f, -0.5000f, 
    -0.5000f, -0.5000f,  0.5000f, 
    -0.5000f,  0.5000f, -0.5000f, 
    -0.5000f,  0.5000f, -0.5000f, 
    -0.5000f, -0.5000f,  0.5000f, 
    -0.5000f,  0.5000f,  0.5000f, 

    -0.5000f, -0.5000f, -0.5000f,
     0.5000f, -0.5000f, -0.5000f,
    -0.5000f, -0.5000f,  0.5000f,
    -0.5000f, -0.5000f,  0.5000f,
     0.5000f, -0.5000f, -0.5000f,
     0.5000f, -0.5000f,  0.5000f,

    -0.5000f,  0.5000f, -0.5000f,
    -0.5000f,  0.5000f,  0.5000f,
     0.5000f,  0.5000f, -0.5000f,
     0.5000f,  0.5000f, -0.5000f,
    -0.5000f,  0.5000f,  0.5000f,
     0.5000f,  0.5000f,  0.5000f
	};
	
	static int indices[] = {
		0,  1,  2, 
		3,  4,  5,

		18, 19, 20,
		21, 22, 23,

		12, 13, 14,
		15, 16, 17,

		6,  7,  8,
		9, 10, 11,

		30, 31, 32,
		33, 34, 35,

		24, 25, 26,
		27, 28, 29
	};
	
	static float normals[] ={
	    -1.0000f, -1.0000f,  1.0000f,
	     1.0000f, -1.0000f,  1.0000f,
	    -1.0000f,  1.0000f,  1.0000f,
	    -1.0000f,  1.0000f,  1.0000f,
	     1.0000f, -1.0000f,  1.0000f,
	     1.0000f,  1.0000f,  1.0000f
	};

	static float texcoords[] ={
	    1.0000f, 0.0000f, 0.0000f,
	    1.0000f, 1.0000f, 0.0000f,
	    0.0000f, 1.0000f, 0.0000f,
	    0.0000f, 0.0000f, 0.0000f
	};
	
	//dorobiù velkosti
	public static float[] getVertices(float width, float height, float depth){
		float[] vertices = {			
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,0.5f,-0.5f,		
				
				-0.5f,0.5f,0.5f,	
				-0.5f,-0.5f,0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				0.5f,0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				-0.5f,-0.5f,0.5f,	
				-0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				0.5f,0.5f,-0.5f,
				0.5f,0.5f,0.5f,
				
				-0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f
		};
		return vertices;
	}
	
	public static int[] getIndices(){
		int[] indices = {
				0,1,3,	
				3,1,2,	
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,	
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22

		};
		return indices;
	}
	
	public static float[] getTextures(){
		float[] textures = {
				
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0	
		};
		return textures;
	}
	
	public static RawModel getModel(Loader loader, float width,float height,float depth){
		//return loader.loadToVAO(getVertices(width,height,depth), getTextures(),getIndices());
		return loader.loadToVAO(vertices, texcoords, normals, indices);
	}
}
