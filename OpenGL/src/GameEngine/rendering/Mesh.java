package GameEngine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import GameEngine.core.Util;
import GameEngine.core.Vector3f;
public class Mesh {
	private int vbo;
	private int ibo;
	private int size;
	
	public Mesh(){
		vbo = glGenBuffers();
		ibo = glGenBuffers();
		size = 0;
	}
	
	public void addVertices(Vertex[] vertices, int[] indices){
		addVertices(vertices, indices, false);
	}
	
	public void addVertices(Vertex[] vertices, int[] indices, boolean calcNormal){
		if(calcNormal){
			calcNormals(vertices, indices);
		}
		//this.size = vertices.length * Vertex.SIZE;
		size = indices.length;
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);
	}
	
	private void calcNormals(Vertex[] vertices, int[] indices){
		for(int i=0 ; i< vertices.length ; i+=3){
			int i0 = indices[i];
			int i1 = indices[i+1];
			int i2 = indices[i+2];
			
			Vector3f v1 = vertices[i1].getPos().Sub(vertices[i0].getPos());
			Vector3f v2 = vertices[i2].getPos().Sub(vertices[i0].getPos());
			
			Vector3f normal  = v1.Cross(v2).Normalized();
			
			vertices[i0].setNormal(vertices[i0].getNormal().Add(normal));
			vertices[i1].setNormal(vertices[i1].getNormal().Add(normal));
			vertices[i2].setNormal(vertices[i2].getNormal().Add(normal));
		}
		
		for(int i=0 ; i<vertices.length ; i++){
			vertices[i].setNormal(vertices[i].getNormal().Normalized());
		}
	}
	
	public void draw(){
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(0, 3, GL_FLOAT, false,Vertex.SIZE * 4, 0);
		glVertexAttribPointer(1, 2, GL_FLOAT, false,Vertex.SIZE * 4, 12);
		glVertexAttribPointer(2, 3, GL_FLOAT, false,Vertex.SIZE * 4, 20);
		//glDrawArrays(GL_TRIANGLES, 0, size);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ibo);
		glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
	}
}
