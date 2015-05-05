package glib.shapes.threeDimensional;

public class Plane {
	public static float[] getVertices(float width, float height){
		float[] vertices = new float[]{-width, 0,  height,
									   -width, 0, -height,
									    width, 0, -height,
									    width, 0,  height};
		return vertices;
	}
	
	public static int[] getIndices(){
		return new int[]{3,1,0,
						 2,1,3};
	}
	
	public static float[] getTextures(float width, float height){
		float[] texture = new float[]{0,0,
									  0,height,
									  width,height,
									  width,0};
		
		return texture;
	}
	
	public static float[] getNormals(){
		float[] normals = new float[]{0.0f, 1,0f, 0.0f,
									  0.0f, 1,0f, 0.0f,
									  0.0f, 1,0f, 0.0f,
									  0.0f, 1,0f, 0.0f};
		return normals;
	}
}
