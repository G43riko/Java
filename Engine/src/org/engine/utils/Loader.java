package org.engine.utils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.engine.rendering.model.Model;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Loader {
	
	private static ArrayList<Integer> vaos = new ArrayList<Integer>();
	private static ArrayList<Integer> vbos = new ArrayList<Integer>();
	private static ArrayList<Integer> textures = new ArrayList<Integer>();
	
	public static Model loadToVAO(float[] positions, float[] textureCoords, int[] indices){
		int vaoID = createVAO();
		
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0,3,positions);
		storeDataInAttributeList(1,2,textureCoords);
		
		unbindVAO();
		return new Model(vaoID, indices.length, positions.length/3);
	}
	
	public static Model loadToVAO(float[] positions, int[] indices){
		int vaoID = createVAO();
		
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0,3,positions);
		
		unbindVAO();
		return new Model(vaoID, indices.length, positions.length/3);
	}
	
	public static Model loadToVAO(float[] positions, float[] textureCoords, float[] normals, int[] indices){
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0,3,positions);
		storeDataInAttributeList(1,2,textureCoords);
		storeDataInAttributeList(2,3,normals);
		unbindVAO();
		return new Model(vaoID, indices.length, positions.length/3);
	}
	
	public static Model loadToVAO(float[] positions, float[] textureCoords, float[] normals, float[] tangent, int[] indices){
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0,3,positions);
		storeDataInAttributeList(1,2,textureCoords);
		storeDataInAttributeList(2,3,normals);
		storeDataInAttributeList(3,3,tangent);
		unbindVAO();
		return new Model(vaoID, indices.length, positions.length/3);
	}
	
	public static Model loadToVAO(float[] positions){
		int vaoID = createVAO();
		storeDataInAttributeList(0,2,positions);
		unbindVAO();
		return new Model(vaoID,positions.length/2, positions.length);
	}
	
	public static void cleanUp(){
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
	
	private static int createVAO(){
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	private static void storeDataInAttributeList(int AttributeNumber, int coordinateSize, float[] data){
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(AttributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0,0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	private static void unbindVAO(){
		GL30.glBindVertexArray(0);
	}
	
	private static void bindIndicesBuffer(int[] indices){
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	private static IntBuffer storeDataInIntBuffer(int[] data){
		IntBuffer buffer =	BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private static FloatBuffer storeDataInFloatBuffer(float[] data){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
}
