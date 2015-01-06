package GameEngine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.util.ArrayList;
import java.util.HashMap;

import GameEngine.core.Util;
import GameEngine.core.Vector3f;
import GameEngine.rendering.meshLoading.IndexedModel;
import GameEngine.rendering.meshLoading.OBJModel;
import GameEngine.rendering.resourceManagement.MeshResource;

public class Mesh {
//	private int vbo;
//	private int ibo;
	private static HashMap<String,MeshResource> loadedModels = new HashMap<String,MeshResource>();
	private MeshResource resource;
	
	public Mesh(String fileName){
		
		MeshResource oldResource = loadedModels.get(fileName);
		if(oldResource != null){
			resource = oldResource;
		}
		else{
			resource = new MeshResource();
			loadMesh(fileName);
			loadedModels.put(fileName, resource);
		}
	}

	public Mesh(Vertex[] vertices, int[] indices){
		this(vertices,indices,false);
	}
	
	public Mesh(Vertex[] vertices, int[] indices, boolean calcNormal){
		resource = new MeshResource();
		addVertices(vertices, indices, calcNormal);
	}
	
	public void addVertices(Vertex[] vertices, int[] indices){
		addVertices(vertices, indices, false);
	}
	
	private Mesh loadMesh(String fileName){
		String[] splitArray = fileName.split("\\.");
		String ext = splitArray[splitArray.length-1];
		
		if(!ext.equals("obj")){
			System.err.println("format nieje podporovaný na prenos mesh dát!!!");
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		OBJModel test = new OBJModel("res/models/"+fileName);
		IndexedModel model = test.toIndexedModel();
		model.calcNormals();
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		
		for(int i=0 ; i<model.getPositions().size() ; i++){
			vertices.add(new Vertex(model.getPositions().get(i),
									model.getTexCoords().get(i),
									model.getNormals().get(i)));
		}
		
		Vertex[] vertexData = new Vertex[vertices.size()];
		vertices.toArray(vertexData);
		Integer[] IndexData = new Integer[model.getIndices().size()];
		model.getIndices().toArray(IndexData);
		
		addVertices(vertexData, Util.toIntArray(IndexData),false);
		
		return null;
	}
	
	public void addVertices(Vertex[] vertices, int[] indices, boolean calcNormal){
		if(calcNormal){
			calcNormals(vertices, indices);
		}
		resource.setSize(indices.length);
		
		glBindBuffer(GL_ARRAY_BUFFER, resource.getVbo());
		glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,resource.getIbo());
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
		
		glBindBuffer(GL_ARRAY_BUFFER, resource.getVbo());
		glVertexAttribPointer(0, 3, GL_FLOAT, false,Vertex.SIZE * 4, 0);
		glVertexAttribPointer(1, 2, GL_FLOAT, false,Vertex.SIZE * 4, 12);
		glVertexAttribPointer(2, 3, GL_FLOAT, false,Vertex.SIZE * 4, 20);
		//glDrawArrays(GL_TRIANGLES, 0, size);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,resource.getIbo());
		glDrawElements(GL_TRIANGLES, resource.getSize(), GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
	}
}
