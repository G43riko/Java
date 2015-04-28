package org.engine.rendering.model;

import java.beans.ConstructorProperties;

public class Model {
	private int vaoID;
	private int vertexCount;

	//CONSTRUCTORS
	
	@ConstructorProperties({"vao", "vertex"})
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
