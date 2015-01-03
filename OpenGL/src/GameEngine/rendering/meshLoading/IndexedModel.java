package GameEngine.rendering.meshLoading;

import java.util.ArrayList;

import GameEngine.core.Vector2f;
import GameEngine.core.Vector3f;

public class IndexedModel {
	private ArrayList<Vector3f> positions;
	private ArrayList<Vector2f> texCoords;
	private ArrayList<Vector3f> normalals;
	private ArrayList<Integer> indices;
	
	public IndexedModel(){
		positions = new ArrayList<Vector3f>();
		texCoords = new ArrayList<Vector2f>();
		normalals = new ArrayList<Vector3f>();
		indices = new ArrayList<Integer>();
	}

	public ArrayList<Vector3f> getPositions() {
		return positions;
	}

	public ArrayList<Vector2f> getTexCoords() {
		return texCoords;
	}

	public ArrayList<Vector3f> getNormalals() {
		return normalals;
	}

	public ArrayList<Integer> getIndices() {
		return indices;
	}
}
