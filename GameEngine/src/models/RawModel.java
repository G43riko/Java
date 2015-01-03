package models;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

/*
 * Obsahuje iba udaje o tvare modelu
 */
public class RawModel {
	private int vaoID;
	private int vertexCount;
	
	public RawModel(int vao, int vertex){
		vaoID = vao;
		vertexCount = vertex;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}
}

