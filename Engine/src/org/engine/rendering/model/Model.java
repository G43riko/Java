package org.engine.rendering.model;

public class Model {
	private int vaoID;
	private int vertexCount;
	private int pointCount;

	//CONSTRUCTORS
	
	public Model(int vao, int vertex, int pointCount){
		vaoID = vao;
		vertexCount = vertex;
		this.pointCount = pointCount;
	}

	//GETTERS
	
	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public int getPointCount() {
		return pointCount;
	}
}
