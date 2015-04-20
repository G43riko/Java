package main;

import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

public class Entita {

	private Model m=null;
	int objectDisplayList; 
	
	public Entita(String file) {
		objectDisplayList = glGenLists(1);
		glNewList(objectDisplayList, GL_COMPILE);
		{
			m = Utils.OBJLoader(file);
			glBegin(GL_TRIANGLES);
			{
				for (Face face : m.faces) {
	            	Vector3f n1 = m.normals.get((int)face.normal.x -1);
	            	glNormal3f(n1.x, n1.y, n1.z);
	                Vector3f v1 = m.vertices.get((int)face.vertex.x - 1);
	                glVertex3f(v1.x, v1.y, v1.z);
	                
	                Vector3f n2 = m.normals.get((int)face.normal.x -1);
	            	glNormal3f(n2.x, n2.y, n2.z);
	                Vector3f v2 = m.vertices.get((int)face.vertex.y - 1);
	                glVertex3f(v2.x, v2.y, v2.z);
	                
	                Vector3f n3 = m.normals.get((int)face.normal.x -1);
	            	glNormal3f(n3.x, n3.y, n3.z);
	                Vector3f v3 = m.vertices.get((int)face.vertex.z - 1);
	                glVertex3f(v3.x, v3.y, v3.z);
	            }
			}
            glEnd();
		}
		glEndList();
	}
	
	public void draw(){
		glCallList(objectDisplayList);
	}

}
