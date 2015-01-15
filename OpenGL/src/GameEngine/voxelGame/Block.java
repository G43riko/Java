package GameEngine.voxelGame;

import GameEngine.components.MeshRenderer;
import GameEngine.core.util.Vector2f;
import GameEngine.core.util.Vector3f;
import GameEngine.rendering.Material;
import GameEngine.rendering.Mesh;
import GameEngine.rendering.Vertex;

public class Block extends MeshRenderer{
	private int type;
	
	public Block(Mesh mesh, Material material,int type) {
		super(mesh, material);
		this.type = type;
	}
	
	public static Mesh getData(String type){
		float[] topDatas = new float[]{-1,  1, -1,
								   		1,  1, -1,
								   	   -1,  1,  1,
								   	   	1,  1, -1,
								   	   -1,  1,  1,
								   	    1,  1,  1};
		int size = 1;
		Vertex[] vertices = new Vertex[]{new Vertex(new Vector3f(-size   ,-1.0f ,-size  ), new Vector2f(0.0f, 0.0f)),
				 						 new Vertex(new Vector3f(-size   ,-1.0f , size*3), new Vector2f(0.0f, size)),
				 						 new Vertex(new Vector3f( size*3 ,-1.0f ,-size  ), new Vector2f(size, 0.0f)),
				 						 new Vertex(new Vector3f( size*3 ,-1.0f , size*3), new Vector2f(size, size))};

		int[] indices = new int[]{0,1,2,2,1,3};
		if(type.equals("top")){
			
		}
		else if(type.equals("bottom")){
			
		}
		else if(type.equals("left")){
			
		}
		else if(type.equals("right")){
			
		}
		else if(type.equals("back")){
			
		}
		else if(type.equals("forward")){
			
		}

		return null;
	}
	
}
