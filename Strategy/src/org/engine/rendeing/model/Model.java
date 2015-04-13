package org.engine.rendeing.model;

public class Model {
	private int vaoID;
	private int vertexCount;

	//CONSTRUCTORS
	
	public Model(int vao, int vertex){
		vaoID = vao;
		vertexCount = vertex;
	}

	//GETTERS
	
	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}

}
