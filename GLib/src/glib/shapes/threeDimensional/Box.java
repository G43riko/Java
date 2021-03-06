package glib.shapes.threeDimensional;

public class Box {
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
//				0,1,3,
//				3,1,2,
				
				//front
				4,5,7,
				7,5,6,
//				7,5,4,
//				6,5,7,
				
				//right
				11,9,8,
				10,9,11,
//				8,9,11,
//				11,9,10,
				
				//left
				12,13,15,
				15,13,14,
//				15,13,12,
//				14,13,15,
				
				//top
				19,17,16,
				18,17,19,
//				16,17,19,
//				19,17,18,
				
				//bottom
				20,21,23,
				23,21,22
//				23,21,20,
//				22,21,23
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
				0.0000f,  0.0000f, -1.0000f,
			    0.0000f,  0.0000f, -1.0000f,
			    0.0000f,  0.0000f, -1.0000f,
			    0.0000f,  0.0000f, -1.0000f,
			    0.0000f,  0.0000f,  1.0000f,
			    0.0000f,  0.0000f,  1.0000f,
			    0.0000f,  0.0000f,  1.0000f,
			    0.0000f,  0.0000f,  1.0000f,
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

}
