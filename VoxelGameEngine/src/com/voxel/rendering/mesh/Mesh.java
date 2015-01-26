package com.voxel.rendering.mesh;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

import java.util.ArrayList;
import java.util.HashMap;

import com.voxel.core.Util;
import com.voxel.core.util.GVector3f;
import com.voxel.rendering.Vertex;
import com.voxel.rendering.mesh.meshLoading.IndexedModel;
import com.voxel.rendering.mesh.meshLoading.OBJModel;

public class Mesh {
	private static HashMap<String,MeshResource> loadedModels = new HashMap<String,MeshResource>();
	private MeshResource resource;
	private String fileName;
	
	public Mesh(String fileName){
		this.fileName = fileName;
		MeshResource oldResource = loadedModels.get(fileName);
		if(oldResource != null){
			resource = oldResource;
			resource.addReference();
		}
		else{
			loadMesh(fileName);
			loadedModels.put(fileName, resource);
		}
	}

	public Mesh(Vertex[] vertices, int[] indices){
		this(vertices,indices,false);
	}
	
	public Mesh(Vertex[] vertices, int[] indices, boolean calcNormal){
		fileName = "";
		addVertices(vertices, indices, calcNormal);
	}
	
	public void addVertices(Vertex[] vertices, int[] indices){
		addVertices(vertices, indices, false);
	}
	
	private void loadMesh(String fileName){
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
	}
	
	@Override
	protected void finalize(){
		if(resource.removeReference()&&!fileName.isEmpty()){
			loadedModels.remove(fileName);
		}
	}
	
	public void addVertices(Vertex[] vertices, int[] indices, boolean calcNormal){
		if(calcNormal){
			calcNormals(vertices, indices);
		}
		resource = new MeshResource(indices.length);
		
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
			
			GVector3f v1 = vertices[i1].getPos().sub(vertices[i0].getPos());
			GVector3f v2 = vertices[i2].getPos().sub(vertices[i0].getPos());
			
			GVector3f normal  = v1.cross(v2).normalize();
			
			vertices[i0].setNormal(vertices[i0].getNormal().add(normal));
			vertices[i1].setNormal(vertices[i1].getNormal().add(normal));
			vertices[i2].setNormal(vertices[i2].getNormal().add(normal));
		}
		
		for(int i=0 ; i<vertices.length ; i++){
			vertices[i].setNormal(vertices[i].getNormal().normalize());
		}
	}
	
	public void draw(){
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
//		glEnableVertexAttribArray(3);
		
		glBindBuffer(GL_ARRAY_BUFFER, resource.getVbo());
		glVertexAttribPointer(0, 3, GL_FLOAT, false,Vertex.SIZE * 4, 0);
		glVertexAttribPointer(1, 2, GL_FLOAT, false,Vertex.SIZE * 4, 12);
		glVertexAttribPointer(2, 3, GL_FLOAT, false,Vertex.SIZE * 4, 20);
//		glVertexAttribPointer(3, 3, GL_FLOAT, false,Vertex.SIZE * 4, 32);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,resource.getIbo());
		glDrawElements(GL_TRIANGLES, resource.getSize(), GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
//		glDisableVertexAttribArray(3);
	}
}
