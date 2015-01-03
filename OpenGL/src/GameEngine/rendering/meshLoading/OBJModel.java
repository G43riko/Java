package GameEngine.rendering.meshLoading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import GameEngine.core.Util;
import GameEngine.core.Vector2f;
import GameEngine.core.Vector3f;

public class OBJModel {
	private ArrayList<Vector3f> positions;
	private ArrayList<Vector2f> texCoords;
	private ArrayList<Vector3f> normals;
	private ArrayList<OBJIndex> indices;
	
	private boolean hasTexCoords;
	private boolean hasNormals;
	
	
	public OBJModel(String fileName){
		positions = new ArrayList<Vector3f>();
		texCoords = new ArrayList<Vector2f>();
		normals = new ArrayList<Vector3f>();
		indices = new ArrayList<OBJIndex>();
		hasTexCoords = false;
		hasNormals = false;
		
		BufferedReader meshReader = null;
		try {
			meshReader = new BufferedReader(new FileReader("./res/models/"+fileName));
			String line ;
			while((line = meshReader.readLine())!=null){
				String[] tokens = line.split(" ");
				tokens = Util.removeEmptyString(tokens);
				if(tokens.length==0||tokens[0].equals("#")){
					continue;
				}
				else if(tokens[0].startsWith("v")){
					positions.add(new Vector3f(Float.valueOf(tokens[1]),
														 Float.valueOf(tokens[2]),
														 Float.valueOf(tokens[3])));
				}
				else if(tokens[0].startsWith("vt")){
					texCoords.add(new Vector2f(Float.valueOf(tokens[1]),Float.valueOf(tokens[2])));
				}
				else if(tokens[0].startsWith("vn")){
					normals.add(new Vector3f(Float.valueOf(tokens[1]),
											 Float.valueOf(tokens[2]),
											 Float.valueOf(tokens[3])));
				}
				else if(tokens[0].startsWith("f")){
					for(int i=0 ; i<tokens.length-3 ; i++){
						indices.add(parseOBJIndex(tokens[1]));
						indices.add(parseOBJIndex(tokens[2]));
						indices.add(parseOBJIndex(tokens[3]));
					}
				}
			}
			meshReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private OBJIndex parseOBJIndex(String token){
		String[] values = token.split("/");
		OBJIndex result = new OBJIndex();
		result.vertexIndex = Integer.parseInt(values[0])-1;
		
		if(values.length>1){
			hasTexCoords = true;
			result.textCoordIndex = Integer.parseInt(values[1])-1;
			if(values.length>2){
				hasNormals = true;
				result.normalIndex = Integer.parseInt(values[2])-1;
			}
		}
		return result;
	}
	
	public ArrayList<Vector3f> getPositions() {
		return positions;
	}

	public ArrayList<Vector2f> getTexCoords() {
		return texCoords;
	}

	public ArrayList<Vector3f> getNormalals() {
		return normals;
	}

	public ArrayList<OBJIndex> getIndices() {
		return indices;
	}
}
