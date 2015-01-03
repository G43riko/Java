package Main;

import static org.lwjgl.opengl.GL11.GL_FRONT;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glPolygonMode;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

public class Terrain {
	public float[][][] mapa;
	public ArrayList<float[]> maps;
	private int numX = Display.getWidth();
	private int numY = Display.getHeight();
	
	public Terrain(int distance) {
		numX/=distance;
		numY/=distance;
		maps = new ArrayList<float[]>();
//		mapa = new float[numX][numY][2];
//		System.out.println(numX+" "+numY);
		for(int i=0 ; i<numX ; i++){
			for(int j=0 ; j<numY ; j++){
				 if(j+1<numY&&i+1<numX){
					 maps.add(new float[]{i,j ,i+1,j, i+1,j+1});
				 }
				 if(i-1>=0&&j-1>=0){
					 maps.add(new float[]{i,j ,i,j-1, i+1,j});
				 }
				 if(i+1==numX&&j+1<numY){
					 maps.add(new float[]{i,j ,i+1,j, i+1,j+1});
				 }
				 if(i==0){
					 maps.add(new float[]{i,j ,i,j-1, i+1,j});
				 }
			}
		}
		System.out.println(maps.size());
		glPolygonMode(GL_FRONT,GL_LINE);
	}
	
	public void draw(){
		Vector3f color = new Vector3f(1,0,0);
		for(float[] p:maps){
			glBegin(GL_TRIANGLES);
			{
				glColor3f(color.x,color.y,color.y);	glVertex3f(p[0]/numX*2-1, p[1]/numY*2-1,  1f);
				glColor3f(color.x,color.y,color.y);	glVertex3f(p[2]/numX*2-1, p[3]/numY*2-1,  1f);
				glColor3f(color.x,color.y,color.y);	glVertex3f(p[4]/numX*2-1, p[5]/numY*2-1,  1f );
			}
		}
	}

}
