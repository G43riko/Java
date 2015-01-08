package GameEngine.voxelGame;

import GameEngine.core.GameObject;

public class World extends GameObject{
	private Chunk[][] chunks;
	private int numX,numY;
//	private static Mesh[] meshes = new meshes[]{}
	
	public World(int x, int y){
		numX = x;
		numY = y;
		chunks = new Chunk[x][y];
	}
	
	public void initDefaultWorld(){
		for(int i=0 ; i<numX ; i++){
			for(int j=0 ; j<numY ; j++){
				Chunk newChunk = new Chunk();
				chunks[i][j] = newChunk;
				addChild(newChunk);
			}
		}
	}
}
