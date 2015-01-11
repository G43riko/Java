package GameEngine.rendering.meshLoading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import GameEngine.core.Util;
import GameEngine.core.Vector2f;
import GameEngine.core.Vector3f;

public class OBJModel {
//	private ArrayList<Vector3f> m_positions;
//	private ArrayList<Vector2f> m_texCoords;
//	private ArrayList<Vector3f> m_normals;
//	private ArrayList<OBJIndex> m_indices;
//	private boolean             m_hasTexCoords;
//	private boolean             m_hasNormals;
//
//	public OBJModel(String fileName)
//	{
//		m_positions = new ArrayList<Vector3f>();
//		m_texCoords = new ArrayList<Vector2f>();
//		m_normals = new ArrayList<Vector3f>();
//		m_indices = new ArrayList<OBJIndex>();
//		m_hasTexCoords = false;
//		m_hasNormals = false;
//
//		BufferedReader meshReader = null;
//
//		try
//		{
//			meshReader = new BufferedReader(new FileReader(fileName));
//			String line;
//
//			while((line = meshReader.readLine()) != null)
//			{
//				String[] tokens = line.split(" ");
//				tokens = Util.removeEmptyString(tokens);
//
//				if(tokens.length == 0 || tokens[0].equals("#"))
//					continue;
//				else if(tokens[0].equals("v"))
//				{
//					m_positions.add(new Vector3f(Float.valueOf(tokens[1]),
//							Float.valueOf(tokens[2]),
//							Float.valueOf(tokens[3])));
//				}
//				else if(tokens[0].equals("vt"))
//				{
//					m_texCoords.add(new Vector2f(Float.valueOf(tokens[1]),
//							1.0f - Float.valueOf(tokens[2])));
//				}
//				else if(tokens[0].equals("vn"))
//				{
//					m_normals.add(new Vector3f(Float.valueOf(tokens[1]),
//							Float.valueOf(tokens[2]),
//							Float.valueOf(tokens[3])));
//				}
//				else if(tokens[0].equals("f"))
//				{
//					for(int i = 0; i < tokens.length - 3; i++)
//					{
//						m_indices.add(ParseOBJIndex(tokens[1]));
//						m_indices.add(ParseOBJIndex(tokens[2 + i]));
//						m_indices.add(ParseOBJIndex(tokens[3 + i]));
//					}
//				}
//			}
//
//			meshReader.close();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			System.exit(1);
//		}
//	}
//
//	public IndexedModel toIndexedModel()
//	{
//		IndexedModel result = new IndexedModel();
//		IndexedModel normalModel = new IndexedModel();
//		HashMap<OBJIndex, Integer> resultIndexMap = new HashMap<OBJIndex, Integer>();
//		HashMap<Integer, Integer> normalIndexMap = new HashMap<Integer, Integer>();
//		HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
//
//		for(int i = 0; i < m_indices.size(); i++)
//		{
//			OBJIndex currentIndex = m_indices.get(i);
//
//			Vector3f currentPosition = m_positions.get(currentIndex.getVertexIndex());
//			Vector2f currentTexCoord;
//			Vector3f currentNormal;
//
//			if(m_hasTexCoords)
//				currentTexCoord = m_texCoords.get(currentIndex.getTextCoordIndex());
//			else
//				currentTexCoord = new Vector2f(0,0);
//
//			if(m_hasNormals)
//				currentNormal = m_normals.get(currentIndex.getNormalIndex());
//			else
//				currentNormal = new Vector3f(0,0,0);
//
//			Integer modelVertexIndex = resultIndexMap.get(currentIndex);
//
//			if(modelVertexIndex == null)
//			{
//				modelVertexIndex = result.getPositions().size();
//				resultIndexMap.put(currentIndex, modelVertexIndex);
//
//				result.getPositions().add(currentPosition);
//				result.getTexCoords().add(currentTexCoord);
//				if(m_hasNormals)
//					result.getNormals().add(currentNormal);
//			}
//
//			Integer normalModelIndex = normalIndexMap.get(currentIndex.getVertexIndex());
//
//			if(normalModelIndex == null)
//			{
//				normalModelIndex = normalModel.getPositions().size();
//				normalIndexMap.put(currentIndex.getVertexIndex(), normalModelIndex);
//
//				normalModel.getPositions().add(currentPosition);
//				normalModel.getTexCoords().add(currentTexCoord);
//				normalModel.getNormals().add(currentNormal);
//				normalModel.getTangents().add(new Vector3f(0,0,0));
//			}
//
//			result.getIndices().add(modelVertexIndex);
//			normalModel.getIndices().add(normalModelIndex);
//			indexMap.put(modelVertexIndex, normalModelIndex);
//		}
//
//		if(!m_hasNormals)
//		{
//			normalModel.calcNormals();
//
//			for(int i = 0; i < result.getPositions().size(); i++)
//				result.getNormals().add(normalModel.getNormals().get(indexMap.get(i)));
//		}
//
//		normalModel.calcTangents();
//
//		for(int i = 0; i < result.getPositions().size(); i++)
//			result.getTangents().add(normalModel.getTangents().get(indexMap.get(i)));
//
////		for(int i = 0; i < result.GetTexCoords().size(); i++)
////			result.GetTexCoords().Get(i).SetY(1.0f - result.GetTexCoords().Get(i).GetY());
//
//		return result;
//	}
//
//	private OBJIndex ParseOBJIndex(String token)
//	{
//		String[] values = token.split("/");
//
//		OBJIndex result = new OBJIndex();
//		result.setVertexIndex(Integer.parseInt(values[0]) - 1);
//
//		if(values.length > 1)
//		{
//			if(!values[1].isEmpty())
//			{
//				m_hasTexCoords = true;
//				result.setTextCoordIndex(Integer.parseInt(values[1]) - 1);
//			}
//
//			if(values.length > 2)
//			{
//				m_hasNormals = true;
//				result.setNormalIndex(Integer.parseInt(values[2]) - 1);
//			}
//		}
//
//		return result;
//	}
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
			meshReader = new BufferedReader(new FileReader(fileName));
			String line ;
			while((line = meshReader.readLine())!=null){
				String[] tokens = line.split(" ");
				tokens = Util.removeEmptyString(tokens);
				if(tokens.length==0||tokens[0].equals("#")){
					continue;
				}
				else if(tokens[0].equals("v")){
					//System.out.println(Float.valueOf(tokens[1])+" "+Float.valueOf(tokens[2])+" "+Float.valueOf(tokens[3]));
					//System.out.println(tokens.length+" "+tokens[0]);
					positions.add(new Vector3f(Float.valueOf(tokens[1]),
											   Float.valueOf(tokens[2]),
											   Float.valueOf(tokens[3])));
				}
				else if(tokens[0].equals("vt")){
					texCoords.add(new Vector2f(Float.valueOf(tokens[1]),Float.valueOf(tokens[2])));
				}
				else if(tokens[0].equals("vn")){
					normals.add(new Vector3f(Float.valueOf(tokens[1]),
											 Float.valueOf(tokens[2]),
											 Float.valueOf(tokens[3])));
				}
				else if(tokens[0].equals("f")){
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
	
	public IndexedModel toIndexedModel(){
		IndexedModel  result = new IndexedModel();
		IndexedModel  normalModel = new IndexedModel();
		HashMap<OBJIndex, Integer> resultIndexMap = new HashMap<OBJIndex, Integer>();
		HashMap<Integer, Integer> normalIndexMap = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> IndexMap = new HashMap<Integer, Integer>();
//		int currentVertexIndex = 0;
		for(int i=0 ; i<indices.size() ; i++){
			OBJIndex currentIndex = indices.get(i);
			Vector3f currentPosition = positions.get(currentIndex.vertexIndex);
			Vector2f currentTexCoord;
			Vector3f currentNormal;
			
			if(hasTexCoords){
				currentTexCoord = texCoords.get(currentIndex.textCoordIndex);
			}
			else{
				currentTexCoord = new Vector2f(0,0);
			}
			
			if(hasNormals){
				currentNormal = normals.get(currentIndex.normalIndex);
			}
			else{
				currentNormal = new Vector3f(0,0,0);
			}
			
			Integer modelVertexIndex = resultIndexMap.get(currentIndex);
			
			if(modelVertexIndex == null){
				resultIndexMap.put(currentIndex,result.getPositions().size());
				modelVertexIndex = result.getPositions().size();
				
				result.getPositions().add(currentPosition);
				result.getTexCoords().add(currentTexCoord);
				if(hasNormals)
					result.getNormals().add(currentNormal);
				
				result.getTangents().add(new Vector3f(0,0,0));
//				currentVertexIndex++;
			}
			
			Integer normalModelIndex = normalIndexMap.get(currentIndex.vertexIndex);
			
			if(normalModelIndex == null){
				normalIndexMap.put(currentIndex.vertexIndex,normalModel.getPositions().size());
				normalModelIndex = normalModel.getPositions().size();
				
				normalModel.getPositions().add(currentPosition);
				normalModel.getTexCoords().add(currentTexCoord);
				normalModel.getNormals().add(currentNormal);
				normalModel.getTangents().add(new Vector3f(0,0,0));
			}
			result.getIndices().add(modelVertexIndex);
			normalModel.getIndices().add(normalModelIndex);
			
			IndexMap.put(modelVertexIndex, normalModelIndex);
		}
		if(!hasNormals){
			normalModel.calcNormals();
			for(int i=0 ; i<result.getPositions().size() ; i++){
				result.getNormals().add(normalModel.getNormals().get(IndexMap.get(i)));
			}
		}
		
		normalModel.calcTangents();
		
		for(int i=0 ; i<result.getPositions().size() ; i++){
			result.getTangents().add(normalModel.getTangents().get(IndexMap.get(i)));
		}
		
		return result;
	}
	
}
