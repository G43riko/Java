package shapes.threeDimensional;

import java.util.ArrayList;

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
				//back
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
				//back
				3,1,0,
				2,1,3,
				//front
				4,5,7,
				7,5,6,
				//right
				11,9,8,
				10,9,11,
				//left
				12,13,15,
				15,13,14,
				//top
				19,17,16,
				18,17,19,
				//bottom
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
			    0.0000f, -1.0000f,  0.0000f,
			    0.0000f, -1.0000f,  0.0000f
		};
		return normals;
	}
	
	public static RawModel getFront(Loader loader, float w,float h,float d, boolean repeat){
		float[] vertices = {
				//back
				-w,h,d,
				-w,-h,d,
				w,-h,d,
				w,h,d
		};
		int[] indices = {
				0,1,3,
				3,1,2
		};
		float[] textures = {
				0,0,
				0,1,
				1,1,
				1,0
		};
		if(repeat){
			textures = new float[]{0,0,
								   0,h,
								   w,h,
								   w,0};
		}
		float[] normals = {
				0.0000f,  0.0000f,  1.0000f,
				0.0000f,  0.0000f,  1.0000f,
				0.0000f,  0.0000f,  1.0000f,
				0.0000f,  0.0000f,  1.0000f
		};
		return loader.loadToVAO(vertices, textures, normals, indices);
	}
	public static RawModel getBack(Loader loader, float w,float h,float d, boolean repeat){
		float[] vertices = {
				//back
				-w,h,-d,
				-w,-h,-d,
				w,-h,-d,
				w,h,-d
		};
		int[] indices = {
				3,1,0,
				2,1,3
		};
		float[] textures = {
				0,0,
				0,1,
				1,1,
				1,0
		};
		if(repeat){
			textures = new float[]{0,0,
								   0,h,
								   w,h,
								   w,0};
		}
		float[] normals = {
				0.0000f,  0.0000f,  -1.0000f,
				0.0000f,  0.0000f,  -1.0000f,
				0.0000f,  0.0000f,  -1.0000f,
				0.0000f,  0.0000f,  -1.0000f
		};
		return loader.loadToVAO(vertices, textures, normals, indices);
	}
	
	public static RawModel getTop(Loader loader, float w,float h,float d, boolean repeat){
		float[] vertices = {
				//top
				-w,h,d,
				-w,h,-d,
				w,h,-d,
				w,h,d,
		};
		int[] indices = {
				3,1,0,
				2,1,3
		};
		float[] textures = {
				0,0,
				0,1,
				1,1,
				1,0
		};
		if(repeat){
			textures = new float[]{0,0,
								   0,d,
								   w,d,
								   w,0};
		}
			
		float[] normals = {
				 0.0000f,  1.0000f,  0.0000f,
				 0.0000f,  1.0000f,  0.0000f,
				 0.0000f,  1.0000f,  0.0000f,
				 0.0000f,  1.0000f,  0.0000f
		};
		return loader.loadToVAO(vertices, textures, normals, indices);
	}
	
	public static RawModel getBottom(Loader loader, float w,float h,float d,boolean repeat){
		float[] vertices = {
				//bottom
				-w,-h,d,
				-w,-h,-d,
				w,-h,-d,
				w,-h,d
		};
		int[] indices = {
				0,1,3,
				3,1,2
		};
		float[] textures = {
				0,0,
				0,1,
				1,1,
				1,0
		};
		if(repeat){
			textures = new float[]{0,0,
								   0,d,
								   w,d,
								   w,0};
		}
		float[] normals = {
				0.0000f, -1.0000f,  0.0000f,
			    0.0000f, -1.0000f,  0.0000f,
			    0.0000f, -1.0000f,  0.0000f,
			    0.0000f, -1.0000f,  0.0000f
		};
		return loader.loadToVAO(vertices, textures, normals, indices);
	}
	
	public static RawModel getLeft(Loader loader, float w,float h,float d, boolean repeat){
		float[] vertices = {
				//left
				-w,h,-d,
				-w,-h,-d,
				-w,-h,d,
				-w,h,d,
		};
		int[] indices = {
				0,1,3,
				3,1,2,
		};
		float[] textures = {
				0,0,
				0,1,
				1,1,
				1,0
		};
		if(repeat){
			textures = new float[]{0,0,
								   0,d,
								   h,d,
								   h,0};
		}
		float[] normals = {
				-1.0000f,  0.0000f,  0.0000f,
				-1.0000f,  0.0000f,  0.0000f,
				-1.0000f,  0.0000f,  0.0000f,
				-1.0000f,  0.0000f,  0.0000f
		};
		return loader.loadToVAO(vertices, textures, normals, indices);
	}
	public static RawModel getRight(Loader loader, float w,float h,float d, boolean repeat){
		float[] vertices = {
				//right
				w,h,-d,
				w,-h,-d,
				w,-h,d,
				w,h,d
		};
		int[] indices = {
				3,1,0,
				2,1,3
		};
		float[] textures = {
				0,0,
				0,1,
				1,1,
				1,0
		};
		if(repeat){
			textures = new float[]{0,0,
								   0,d,
								   h,d,
								   h,0};
		}
		float[] normals = {
				1.0000f,  0.0000f,  0.0000f,
			    1.0000f,  0.0000f,  0.0000f,
			    1.0000f,  0.0000f,  0.0000f,
			    1.0000f,  0.0000f,  0.0000f
		};
		return loader.loadToVAO(vertices, textures, normals, indices);
	}
	public static RawModel getModel(Loader loader, float width,float height,float depth){
		return loader.loadToVAO(getVertices(width,height,depth), getTextures(),getNormals(),getIndices());
		//return loader.loadToVAO(vertices, textureCoords, normals, indices);
	}
	
	

	public static RawModel getSelectedModel(Loader loader, float width,float height,float depth,boolean[] faces){
		/*
		 * 0   1  2
		 * 3   4  5
		 * 
		 * 6   7  8
		 * 9  10 11
		 * 
		 * 12 13 14
		 * 15 16 17
		 * 
		 * 18 19 20
		 * 21 22 23
		 * 
		 * 24 25 26
		 * 27 28 29
		 * 
		 * 30 31 32
		 * 33 34 35
		 */
		int[] indices = getIndices();
		ArrayList<Integer> indicesNew = new ArrayList<Integer>();
		for(int i=0 ; i<6 ; i++){
			if(faces[i]){
				for(int j=0 ; j<6 ; j++){
					indicesNew.add(indices[i*6+j]);
				}
			}
		}
		indices = new int[indicesNew.size()];
		for(int i=0 ; i<indicesNew.size() ; i++){
			indices[i] = indicesNew.get(i);
		}
		return loader.loadToVAO(getVertices(width,height,depth), getTextures(),getNormals(),indices);
	}
}
