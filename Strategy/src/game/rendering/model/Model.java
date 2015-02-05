package game.rendering.model;

public class Model {
	private int vaoID;
	private int vertexCount;
	
	public Model(int vao, int vertex){
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
