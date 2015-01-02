package shapes.twoDimensional;

import main.Loader;
import models.RawModel;

public class Circle {
	private static float[] getVertices(float dist,int num,float x, float y){
		float[] vertices = new float[(num+1)*3];
		vertices[0] = x;
		vertices[1] = y;
		vertices[2] = 0;
		for(int i=3 ; i<=(num+1)*3 ; i+=3){
			vertices[i] = (float)Math.cos(Math.toRadians(360/num*i))*dist;
			vertices[i+1] = (float)Math.sin(Math.toRadians(360/num*i))*dist;
			vertices[i+2] = 0;
		}
		return vertices;
	}
	
	private static float[] getTextures(int num, float x, float y){
		float[] texts = new float[(num+1)*2];
		texts[0] = 0.5f;
		texts[1] = 0.5f;
		for(int i=2 ; i<(num+1)*2 ; i+=2){
			texts[i] = (float)Math.cos(Math.toRadians(360/num*i))/2+1;
			texts[i+1] = (float)Math.cos(Math.toRadians(360/num*i))/-2+1;
		}
		return texts;
	}
	
	private static int[] getIndices(int num){
		return null ;
	}
	
//	public static RawModel getModel(Loader loader, float dist,int num,float x, float y){
//		return loader.loadToVAO(getVertices(dist,num,x,y), getTextures(num,x,y),getIndices(num));
//	}
}

