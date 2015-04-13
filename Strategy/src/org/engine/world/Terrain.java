package org.engine.world;

import glib.math.GMath;
import glib.util.noise.PerlinNoise;
import glib.util.noise.ProceduralTerrain;
import glib.util.noise.SimplexNoise;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.engine.rendeing.model.Model;
import org.engine.util.Loader;
import org.lwjgl.util.vector.Vector3f;

public class Terrain {
	public final static int PERLIN_NOISE = 1;
	public final static int SIMPLEX_NOISE = 2;
	public final static int PROCEDURAL_TERRAIN = 3;
	
	private static final int SIZE = 800;
//	private static final int VERTEX_COUNT = 128;
	private static final float MAX_HEIGHT = 100;
	private static final float MAX_PIXEL_COLOR = 256 * 256 * 256;
	
	private float x;
	private float z;
	private Model model;
	
	public Terrain(float gridX, float gridZ, Loader loader, String heighMap){
		this.x = gridX * SIZE;
		this.z = gridZ * SIZE;
		this.model = generateTerrain(loader, heighMap);
	}
	
	public Terrain(float gridX, float gridZ, Loader loader, float[][] map){
		this.x = gridX * SIZE;
		this.z = gridZ * SIZE;
		this.model = generateTerrain(loader, map);
	}
	
	public Terrain(float gridX, float gridZ, Loader loader){
		this(gridX,gridZ,loader,255);
	}
	
	public Terrain(float gridX, float gridZ, Loader loader, int type){
		this.x = gridX * SIZE;
		this.z = gridZ * SIZE;
		switch(type){
			case PERLIN_NOISE:
				this.model = generateTerrain(loader,  PerlinNoise.GeneratePerlinNoise(PerlinNoise.generateWhiteNoise(SIZE, SIZE), 8, 0.7f, true));
				break;
			case SIMPLEX_NOISE:
				this.model = generateTerrain(loader, SimplexNoise.generateOctavedSimplexNoise(SIZE, SIZE, 6, 0.8f, 0.008f));
				break;
			case PROCEDURAL_TERRAIN:
				this.model = generateTerrain(loader, ProceduralTerrain.create(SIZE, SIZE));
				break;
			default:
				this.model = generateTerrain(loader, SimplexNoise.generateSimplexNoise(SIZE, SIZE));
		}
	}
	
	private Model generateTerrain(Loader loader, String heightMap){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("res/textures/" +heightMap+ ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int VERTEX_COUNT = image.getHeight();
		
		int count = VERTEX_COUNT * VERTEX_COUNT;
		float[] vertices = new float[count * 3];
		float[] normals = new float[count * 3];
		float[] textureCoords = new float[count*2];
		int[] indices = new int[6*(VERTEX_COUNT-1)*(VERTEX_COUNT*1)];
		int vertexPointer = 0;
		for(int i=0;i<VERTEX_COUNT;i++){
			for(int j=0;j<VERTEX_COUNT;j++){
				vertices[vertexPointer*3] = (float)j/((float)VERTEX_COUNT - 1) * SIZE;
				vertices[vertexPointer*3+1] = getHeight(j,i,image);
				vertices[vertexPointer*3+2] = (float)i/((float)VERTEX_COUNT - 1) * SIZE;
				Vector3f normal = calculateNormal(j,i,image); 
				normals[vertexPointer*3] = normal.x;
				normals[vertexPointer*3+1] = normal.y;
				normals[vertexPointer*3+2] = normal.z;
				textureCoords[vertexPointer*2] = (float)j/((float)VERTEX_COUNT - 1);
				textureCoords[vertexPointer*2+1] = (float)i/((float)VERTEX_COUNT - 1);
				vertexPointer++;
			}
		}
		int pointer = 0;
		for(int gz=0;gz<VERTEX_COUNT-1;gz++){
			for(int gx=0;gx<VERTEX_COUNT-1;gx++){
				int topLeft = (gz*VERTEX_COUNT)+gx;
				int topRight = topLeft + 1;
				int bottomLeft = ((gz+1)*VERTEX_COUNT)+gx;
				int bottomRight = bottomLeft + 1;
				indices[pointer++] = topLeft;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = topRight;
				indices[pointer++] = topRight;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = bottomRight;
			}
		}
		return loader.loadToVAO(vertices, textureCoords, normals, indices);
	}
	
	private Model generateTerrain(Loader loader, float[][] map){
		int VERTEX_COUNT = map.length;
		
		int count = VERTEX_COUNT * VERTEX_COUNT;
		float[] vertices = new float[count * 3];
		float[] normals = new float[count * 3];
		float[] textureCoords = new float[count*2];
		int[] indices = new int[6*(VERTEX_COUNT-1)*(VERTEX_COUNT*1)];
		int vertexPointer = 0;
		for(int i=0;i<VERTEX_COUNT;i++){
			for(int j=0;j<VERTEX_COUNT;j++){
				vertices[vertexPointer*3] = (float)j/((float)VERTEX_COUNT - 1) * SIZE;
				vertices[vertexPointer*3+1] = getHeight(j,i,map);
				vertices[vertexPointer*3+2] = (float)i/((float)VERTEX_COUNT - 1) * SIZE;
				Vector3f normal = calculateNormal(j,i,map); 
				normals[vertexPointer*3] = normal.x;
				normals[vertexPointer*3+1] = normal.y;
				normals[vertexPointer*3+2] = normal.z;
				textureCoords[vertexPointer*2] = (float)j/((float)VERTEX_COUNT - 1);
				textureCoords[vertexPointer*2+1] = (float)i/((float)VERTEX_COUNT - 1);
				vertexPointer++;
			}
		}
		int pointer = 0;
		for(int gz=0;gz<VERTEX_COUNT-1;gz++){
			for(int gx=0;gx<VERTEX_COUNT-1;gx++){
				int topLeft = (gz*VERTEX_COUNT)+gx;
				int topRight = topLeft + 1;
				int bottomLeft = ((gz+1)*VERTEX_COUNT)+gx;
				int bottomRight = bottomLeft + 1;
				indices[pointer++] = topLeft;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = topRight;
				indices[pointer++] = topRight;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = bottomRight;
			}
		}
		return loader.loadToVAO(vertices, textureCoords, normals, indices);
	}
	
	private Vector3f calculateNormal(int x, int z, BufferedImage image){
		float heightL = getHeight(x-1, z, image);
		float heightR = getHeight(x+1, z, image);
		float heightD = getHeight(x, z-1, image);
		float heightU = getHeight(x, z+1, image);
		Vector3f normal = new Vector3f(heightL-heightR,2f,heightD-heightU);
		normal.normalise();
		return normal;
	}
	
	private Vector3f calculateNormal(int x, int z, float[][] map){
		float heightL = getHeight(x-1, z, map);
		float heightR = getHeight(x+1, z, map);
		float heightD = getHeight(x, z-1, map);
		float heightU = getHeight(x, z+1, map);
		Vector3f normal = new Vector3f(heightL-heightR,2f,heightD-heightU);
		normal.normalise();
		return normal;
	}
	
	private float getHeight(int x, int z, float[][] map){
		if(x<0 || z<0 || x>=map.length || z>=map[x].length){
			return 0;
		}
		float height = map[x][z] * MAX_HEIGHT;
		return height;
	}
	
	private float getHeight(int x, int z, BufferedImage image){
		if(x<0||x>=image.getHeight()||z<0||z>=image.getHeight()){
			return 0;
		}
		float height = image.getRGB(x, z);
		height += MAX_PIXEL_COLOR/8*5.5;
		height /= MAX_PIXEL_COLOR/2f;
		height *= MAX_HEIGHT;
		return height;
	}

	public float getX() {
		return x;
	}

	public float getZ() {
		return z;
	}

	public Model getModel() {
		return model;
	}
}
