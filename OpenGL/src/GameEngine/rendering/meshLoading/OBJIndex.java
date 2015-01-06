package GameEngine.rendering.meshLoading;

public class OBJIndex {
	public int vertexIndex;
	public int textCoordIndex;
	public int normalIndex;
	
	@Override
	public boolean equals(Object obj){
		OBJIndex index  = (OBJIndex)obj;
		return vertexIndex == index.vertexIndex&& textCoordIndex == index.textCoordIndex && normalIndex == index.normalIndex;
	}
	
	@Override
	public int hashCode(){
		final int BASE = 17;
		final int MULTIPLIER = 31;
		
		int result = BASE;
		
		result = MULTIPLIER * result + vertexIndex;
		result = MULTIPLIER * result + textCoordIndex;
		result = MULTIPLIER * result + normalIndex;
		
		return result;
	}
}
