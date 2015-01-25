package com.voxel.render;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class Mesh {
	private int vbo;
	private int ibo;
	private int size = 0;
	
	public void draw(){
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
		glEnableVertexAttribArray(3);
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(0, 3, GL_FLOAT, false,Vertex.SIZE * 4, 0);
		glVertexAttribPointer(1, 2, GL_FLOAT, false,Vertex.SIZE * 4, 12);
		glVertexAttribPointer(2, 3, GL_FLOAT, false,Vertex.SIZE * 4, 20);
		glVertexAttribPointer(3, 3, GL_FLOAT, false,Vertex.SIZE * 4, 32);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ibo);
		glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
		glDisableVertexAttribArray(3);
	}
	
//	public void draw(){
//		glEnableVertexAttribArray(0);
//		glEnableVertexAttribArray(1);
//		glEnableVertexAttribArray(2);
//		glEnableVertexAttribArray(3);
//		
//		glBindBuffer(GL_ARRAY_BUFFER, resource.getVbo());
//		glVertexAttribPointer(0, 3, GL_FLOAT, false,Vertex.SIZE * 4, 0);
//		glVertexAttribPointer(1, 2, GL_FLOAT, false,Vertex.SIZE * 4, 12);
//		glVertexAttribPointer(2, 3, GL_FLOAT, false,Vertex.SIZE * 4, 20);
//		glVertexAttribPointer(3, 3, GL_FLOAT, false,Vertex.SIZE * 4, 32);
//		//glDrawArrays(GL_TRIANGLES, 0, size);
//		
//		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,resource.getIbo());
//		glDrawElements(GL_TRIANGLES, resource.getSize(), GL_UNSIGNED_INT, 0);
//		
//		glDisableVertexAttribArray(0);
//		glDisableVertexAttribArray(1);
//		glDisableVertexAttribArray(2);
//		glDisableVertexAttribArray(3);
//	}
}
