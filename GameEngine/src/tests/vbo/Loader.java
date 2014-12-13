package tests.vbo;

import java.io.File;
import java.io.FileInputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.TextureLoader;

public class Loader {
	/*
	 * posiela veci na vypo�et
	 * upravuje udaje aby ich bolo mo�ne spracova�
	 * 
	 */
	private ArrayList<Integer> vaos = new ArrayList<Integer>();
	private ArrayList<Integer> vbos = new ArrayList<Integer>();
	private ArrayList<Integer> textures = new ArrayList<Integer>();
	
//	public RawModel loadToVAO(float[] positions, float[] textureCoords, float[] normals, int[] indices){
//		int vaoID = createVAO();
//		bindIndicesBuffer(indices);
//		storeDataInAttributeList(0,3,positions);
//		storeDataInAttributeList(1,2,textureCoords);
//		storeDataInAttributeList(2,3,normals);
//		unbindVAO();
//		return new RawModel(vaoID,indices.length);
//	}
	
	
	public RawModel loadToVAO(float[] positions, float[] textureCoords, int[] indices){
		/*
		 * spracuje udaje a vytvor� raw model s udajami o bodoch, ind�ci�ch, norm�lach, a text�rach
		 */
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0,3,positions);
		storeDataInAttributeList(1,2,textureCoords);
		unbindVAO();
		return new RawModel(vaoID,indices.length);
	}

	
	public int loadTexture(String filename){
		/*
		 * na��ta texturu
		 * //prid� ju do zoznamu text�r
		 * nastav� jej mipmapping
		 * vr�ti jej id
		 */
		String[] splitArray = filename.split("\\.");
		String ext = splitArray[splitArray.length-1];
		int id = 0;
		try{
			id = TextureLoader.getTexture(ext, new FileInputStream(new File("res/textures/"+filename))).getTextureID();
			GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -0.4f);
		}
		catch(Exception e){
			System.exit(-1);
		}
		textures.add(id);
		return id;
	}
	
	
	public void cleanUp(){
		/*
		 * vyma�e v�etky ulo�en� veci v pam�ti
		 */
		for(int vao:vaos){
			GL30.glDeleteVertexArrays(vao);
		}
		for(int vbo:vbos){
			GL15.glDeleteBuffers(vbo);
		}
		for(int texture:textures){
			GL11.glDeleteTextures(texture);
		}
	}
	
	private int createVAO(){
		/*
		 * vytvor� miesto pre vao
		 */
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	private void storeDataInAttributeList(int AttributeNumber, int coordinateSize, float[] data){
		/*
		 * uklad� d�ta niekde 
		 */
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(AttributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0,0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	private void unbindVAO(){
		GL30.glBindVertexArray(0);
	}
	
	private void bindIndicesBuffer(int[] indices){
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	private IntBuffer storeDataInIntBuffer(int[] data){
		/*
		 * premen� pole na intBuffer ktor� je mo�ne posla� do gpu
		 */
		IntBuffer buffer =	BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data){
		/*
		 * premen� pole na floatBuffer ktor� je mo�ne posla� do gpu
		 */
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
}
