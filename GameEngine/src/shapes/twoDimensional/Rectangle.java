	package shapes.twoDimensional;

import main.Loader;
import models.RawModel;

public class Rectangle {
	
	public static float[] getVertices(float width,float height){
		float[] vertices = {
				-width/2,  height/2,  0f,//v1
				-width/2, -height/2,  0f,//v2
				 width/2, -height/2,  0f,//v3
				 width/2,  height/2,  0f //v4
		};
		return vertices;
	}
	
	public static int[] getIndices(){
		int[] indices = {
			0,1,3,
			3,1,2
		};
		return indices;
	}
	
	public static float[] getTextures(){
		float[]textureCoords = {
				0,0,
				0,1,
				1,1,
				1,0
		};
		return textureCoords;
	}
	
	public static RawModel getModel(Loader loader, float width,float height){
		return loader.loadToVAO(getVertices(width,height), getTextures(),getIndices());
	}
}
