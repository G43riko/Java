package GameEngine.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import GameEngine.rendering.Mesh;
import GameEngine.rendering.Vertex;

public class ResourceLoader {
	public static Mesh loadMesh(String filename){
		String[] splitArray = filename.split("\\.");
		String ext = splitArray[splitArray.length-1];
		
		if(!ext.equals("obj")){
			System.out.println("formát "+ext+" nieje podporovany!");
			System.exit(1);
		}
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		BufferedReader meshReader = null;
		
		try{
			meshReader = new BufferedReader(new FileReader("./res/models/" + filename));
			String line;
			while((line = meshReader.readLine()) != null){
				String[] tokens = line.split(" ");
				tokens = Util.removeEmptyString(tokens);
				if(tokens.length==0||tokens[0].equals("#")){
					continue;
				}
				else if(tokens[0].equals("v")){
					vertices.add(new Vertex(new Vector3f(Float.valueOf(tokens[1]), 
														 Float.valueOf(tokens[2]), 
														 Float.valueOf(tokens[3]))));
				}
				else if(tokens[0].equals("f")){
					indices.add(Integer.parseInt(tokens[1].split("/")[0])-1);
					indices.add(Integer.parseInt(tokens[2].split("/")[0])-1);
					indices.add(Integer.parseInt(tokens[3].split("/")[0])-1);
					if(tokens.length>4){
						indices.add(Integer.parseInt(tokens[1].split("/")[0])-1);
						indices.add(Integer.parseInt(tokens[3].split("/")[0])-1);
						indices.add(Integer.parseInt(tokens[4].split("/")[0])-1);
					}
				}
			}
			meshReader.close();
		}
		catch(Exception e){
			System.out.println(e);
			System.exit(1);
		}
		Vertex[] vertexData = new Vertex[vertices.size()];
		vertices.toArray(vertexData);
		
		Integer[] indexData = new Integer[indices.size()];
		indices.toArray(indexData);
		return new Mesh(vertexData, Util.toIntArray(indexData));
	}
}
