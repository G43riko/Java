package org.engine.utils.resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.engine.core.CoreEngine;
import org.engine.rendering.model.Model;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import glib.util.GLog;


public class OBJLoader {
	private static HashMap<String, Model> loadedModels = new HashMap<String, Model>(); 
	public static Model loadObjModel(String fileName){
		if(loadedModels.containsKey(fileName)){
			return loadedModels.get(fileName);
		}
		String line = "";
		
		FileReader fr = null;
		try {
			fr = new FileReader(new File("res/models/"+fileName+".obj"));
		} catch (FileNotFoundException e) {
			GLog.write("nieje mo�n� na��ta� s�bor: " + fileName);
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(fr);
		
		List<Vector3f> vertices = new ArrayList<Vector3f>();
		List<Vector2f> textures = new ArrayList<Vector2f>();
		List<Vector3f> normals = new ArrayList<Vector3f>();
		List<Integer> indices = new ArrayList<Integer>();
		
		float[] verticesArray = null;
		float[] normalsArray = null;
		float[] textureArray = null;
		int[] indicesArray = null;
		try{
			while(true){
				line = reader.readLine().replace("  ", " ");
				if(line.isEmpty())
					continue;
				String[] currentLine = line.split(" ");
				
				for(String s : currentLine)
					s = s.trim();
				
				if(line.startsWith("v ")){
					Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1]), 
												   Float.parseFloat(currentLine[2]), 
												   Float.parseFloat(currentLine[3]));
					vertices.add(vertex);
				}
				else if(line.startsWith("vt ")){
					Vector2f texture = new Vector2f(Float.parseFloat(currentLine[1]),Float.parseFloat(currentLine[2]));
					textures.add(texture);
				}
				else if(line.startsWith("vn ")){
					Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]), 
							   					   Float.parseFloat(currentLine[2]), 
							   					   Float.parseFloat(currentLine[3]));
					normals.add(normal);
				}
				else if(line.startsWith("f ")){
					textureArray = new float[vertices.size()*2];
					normalsArray = new float[vertices.size()*3];
					break;
				}
			}
			while(line!=null){
				
				if(line.isEmpty())
					break;
				if(!line.startsWith("f ")){
					line = reader.readLine().replace("  ", " ");
					continue;	
				}
				String[] currentLine  = line.split(" ");
				
				for(String s : currentLine)
					s = s.trim();
				
				String[] vertex1 = currentLine[1].split("/");
				String[] vertex2 = currentLine[2].split("/");
				String[] vertex3 = currentLine[3].split("/");
				
				processVertex(vertex1,indices,textures,normals,textureArray,normalsArray);
				processVertex(vertex2,indices,textures,normals,textureArray,normalsArray);
				processVertex(vertex3,indices,textures,normals,textureArray,normalsArray);
				line = reader.readLine();
			}
			reader.close();
		}catch(Exception e){
			GLog.write("Nastala chyba pri ��tan� zo s�boru v " + fileName + " na riadku " + line);
		}
		verticesArray = new float[vertices.size()*3];
		indicesArray = new int[indices.size()];
		int vertexPointer = 0;
		for(Vector3f vertex: vertices){
			verticesArray[vertexPointer++] = vertex.x;
			verticesArray[vertexPointer++] = vertex.y;
			verticesArray[vertexPointer++] = vertex.z;
		}
		for(int i=0 ; i<indices.size() ; i++){
			indicesArray[i]  = indices.get(i);
		}
		loadedModels.put(fileName, CoreEngine.getLoader().loadToVAO(verticesArray, textureArray, normalsArray, indicesArray));
		return loadedModels.get(fileName);
	}
	
	private static void processVertex(String[] vertexData, List<Integer> indices, 
			List<Vector2f> textures, List<Vector3f> normals,float[] textureArray,float[] normalsArray){
		int currentVertexPointer = Math.abs(Integer.parseInt(vertexData[0]))-1;
		indices.add(currentVertexPointer);
		
		Vector2f currentTex = textures.get(Math.abs(Integer.parseInt(vertexData[1]))-1);
		textureArray[currentVertexPointer*2] = currentTex.x;	
		textureArray[currentVertexPointer*2+1] = 1 - currentTex.y;
		
		Vector3f currentNorm = normals.get(Math.abs(Integer.parseInt(vertexData[2]))-1);
		normalsArray[currentVertexPointer*3] = currentNorm.x;
		normalsArray[currentVertexPointer*3+1] = currentNorm.y;
		normalsArray[currentVertexPointer*3+2] = currentNorm.z;
		
	}
}
