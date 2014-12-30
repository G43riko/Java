package shapes.threeDimensional;

import main.Loader;
import models.RawModel;

public class Box {
	public static float[] vertices = {
			//back
			-0.5f,0.5f,-0.5f,
			-0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,-0.5f,
			0.5f,0.5f,-0.5f,
			//front
			-0.5f,0.5f,0.5f,
			-0.5f,-0.5f,0.5f,
			0.5f,-0.5f,0.5f,
			0.5f,0.5f,0.5f,
			//right
			0.5f,0.5f,-0.5f,
			0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,0.5f,
			0.5f,0.5f,0.5f,
			//left
			-0.5f,0.5f,-0.5f,
			-0.5f,-0.5f,-0.5f,
			-0.5f,-0.5f,0.5f,
			-0.5f,0.5f,0.5f,
			//top
			-0.5f,0.5f,0.5f,
			-0.5f,0.5f,-0.5f,
			0.5f,0.5f,-0.5f,
			0.5f,0.5f,0.5f,
			//bottom
			-0.5f,-0.5f,0.5f,
			-0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,0.5f
			
	};
	
	public static float[] textureCoords = {
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
	
	public static int[] indices = {
			//0,1,3,
			//3,1,2,
			3,1,0,
			2,1,3,
			
			4,5,7,
			7,5,6,
			
			//8,9,11,
			//11,9,10,
			11,9,8,
			10,9,11,
			
			12,13,15,
			15,13,14,
			
			//16,17,19,
			//19,17,18,
			19,17,16,
			18,17,19,
			
			20,21,23,
			23,21,22
	};

	static float normals[] ={
//		0,0,0,
//		0,0,0,
//		0,0,0,
//		0,0,0,
//		0,0,0,
//		0,0,0
	     0.0000f,  0.0000f,  1.0000f,
	     0.0000f,  0.0000f,  1.0000f,
	     0.0000f,  0.0000f,  1.0000f,
	     0.0000f,  0.0000f,  1.0000f,

	     0.0000f,  0.0000f, -1.0000f,
	     0.0000f,  0.0000f, -1.0000f,
	     0.0000f,  0.0000f, -1.0000f,
	     0.0000f,  0.0000f, -1.0000f,
	     
	     1.0000f,  0.0000f,  0.0000f,
	     1.0000f,  0.0000f,  0.0000f,
	     1.0000f,  0.0000f,  0.0000f,
	     1.0000f,  0.0000f,  0.0000f,
	     
	    -1.0000f,  0.0000f,  0.0000f,
	    -1.0000f,  0.0000f,  0.0000f,
	    -1.0000f,  0.0000f,  0.0000f,
	    -1.0000f,  0.0000f,  0.0000f,
	    
	     0.0000f,  1.0000f,  0.0000f,
	     0.0000f,  1.0000f,  0.0000f,
	     0.0000f,  1.0000f,  0.0000f,
	     0.0000f,  1.0000f,  0.0000f,
	     
	     0.0000f, -1.0000f,  0.0000f,
	     0.0000f, -1.0000f,  0.0000f,
	     0.0000f, -1.0000f,  0.0000f
	};

	//dorobiù velkosti
	public static float[] getVertices(float w, float h, float d){
		float[] vertices = {	
				//w,h,d
				-w,h,-d,
				-w,-h,-d,
				w,-h,-d,
				w,h,-d,
				//front
				-w,h,d,
				-w,-h,d,
				w,-h,d,
				w,h,d,
				//right
				w,h,-d,
				w,-h,-d,
				w,-h,d,
				w,h,d,
				//left
				-w,h,-d,
				-w,-h,-d,
				-w,-h,d,
				-w,h,d,
				//top
				-w,h,d,
				-w,h,-d,
				w,h,-d,
				w,h,d,
				//bottom
				-w,-h,d,
				-w,-h,-d,
				w,-h,-d,
				w,-h,d
		};
		return vertices;
	}
	
	public static int[] getIndices(){
		int[] indices = {
				3,1,0,
				2,1,3,
				4,5,7,
				7,5,6,
				11,9,8,
				10,9,11,
				12,13,15,
				15,13,14,
				19,17,16,
				18,17,19,
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

	public static float[] getNormals(){
		float[] normals = {
				0.0000f,  0.0000f,  -1.0000f,
			    0.0000f,  0.0000f,  -1.0000f,
			    0.0000f,  0.0000f,  -1.0000f,
			    0.0000f,  0.0000f,  -1.0000f,
			    0.0000f,  0.0000f, 1.0000f,
			    0.0000f,  0.0000f, 1.0000f,
			    0.0000f,  0.0000f, 1.0000f,
			    0.0000f,  0.0000f, 1.0000f,
			    1.0000f,  0.0000f,  0.0000f,
			    1.0000f,  0.0000f,  0.0000f,
			    1.0000f,  0.0000f,  0.0000f,
			    1.0000f,  0.0000f,  0.0000f,
			   -1.0000f,  0.0000f,  0.0000f,
			   -1.0000f,  0.0000f,  0.0000f,
			   -1.0000f,  0.0000f,  0.0000f,
			   -1.0000f,  0.0000f,  0.0000f,
			    0.0000f,  1.0000f,  0.0000f,
			    0.0000f,  1.0000f,  0.0000f,
			    0.0000f,  1.0000f,  0.0000f,
			    0.0000f,  1.0000f,  0.0000f,
			    0.0000f, -1.0000f,  0.0000f,
			    0.0000f, -1.0000f,  0.0000f,
			    0.0000f, -1.0000f,  0.0000f
		};
		return normals;
	}
	
	public static RawModel getBack(Loader loader, float w,float h,float d){
		float[] tvertices = {
				//back
				-w,h,-d,
				-w,-h,-d,
				w,-h,-d,
				w,h,-d
		};
		int[] tindices = {
				3,1,0,
				2,1,3
		};
		float[] ttextures = {
				0,0,
				0,1,
				1,1,
				1,0
		};
		float[] tnormals = {
				0.0000f,  0.0000f,  -1.0000f,
				0.0000f,  0.0000f,  -1.0000f,
				0.0000f,  0.0000f,  -1.0000f,
				0.0000f,  0.0000f,  -1.0000f
		};
		return loader.loadToVAO(tvertices, ttextures, tnormals, tindices);
	}
	
	public static RawModel getModel(Loader loader, float width,float height,float depth){
		return loader.loadToVAO(getVertices(width,height,depth), getTextures(),getNormals(),getIndices());
		//return loader.loadToVAO(vertices, textureCoords, normals, indices);
	}
}
