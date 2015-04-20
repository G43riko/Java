package terrains;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

import renderers.Renderer;
import shaders.StaticShader;
import main.Loader;

public class Terrain {
	private int size=9;
	private int numX=256;
	private int numY=256;
	private int MAX_HEIGHT = 256;
	private int MIN_HEIGHT = 0;
	private BufferedImage image = null;
	private static final float MAX_PIXEL_COLOR = 256 * 256 * 256;
	
	public Terrain(String filename,Loader loader){
		try {
			image = ImageIO.read(new File("res/" +filename+ ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Renderer renderer, StaticShader shader) {
		int VERTEX_COUNT = image.getHeight();
		for(int i=0;i<VERTEX_COUNT;i++){
			for(int j=0;j<VERTEX_COUNT;j++){
				float height = new Color(image.getRGB(i, j)).getRed();
//				height += MAX_PIXEL_COLOR;
//				height /= MAX_PIXEL_COLOR/2f;
//				height *= MAX_HEIGHT;
//				height = 0;
//				if(j+1<VERTEX_COUNT&&i+1<VERTEX_COUNT){
//					GL11.glBegin(GL11.GL_TRIANGLES);
//						GL11.glVertex3f(i*size, height, i*size);
//						GL11.glVertex3f((i+1)*size,height, j*size);
//						GL11.glVertex3f((i+1)*size,height, (j+1)*size);
//					GL11.glEnd();
////					 maps.add(new float[]{i,j ,i+1,j, i+1,j+1});
//				 }
//				 if(i-1>=0&&j-1>=0){
//					 GL11.glBegin(GL11.GL_TRIANGLES);
//						GL11.glVertex3f(i*size, height, i*size);
//						GL11.glVertex3f(i*size,height, (j-1)*size);
//						GL11.glVertex3f((i+1)*size,height, j*size);
//					GL11.glEnd();
////					 maps.add(new float[]{i,j ,i,j-1, i+1,j});
//				 }
//				 if(i+1==VERTEX_COUNT&&j+1<VERTEX_COUNT){
//					 GL11.glBegin(GL11.GL_TRIANGLES);
//						GL11.glVertex3f(i*size, height, i*size);
//						GL11.glVertex3f((i+1)*size,height, j*size);
//						GL11.glVertex3f((i+1)*size,height, (j+1)*size);
//					GL11.glEnd();
////					 maps.add(new float[]{i,j ,i+1,j, i+1,j+1});
//				 }
//				 if(i==0){
//					 GL11.glBegin(GL11.GL_TRIANGLES);
//						GL11.glVertex3f(i*size, height, i*size);
//						GL11.glVertex3f(i*size,height, (j-1)*size);
//						GL11.glVertex3f((i+1)*size,height, j*size);
//					GL11.glEnd();
////					 maps.add(new float[]{i,j ,i,j-1, i+1,j});
//				 }
			}
		}
	}
}
