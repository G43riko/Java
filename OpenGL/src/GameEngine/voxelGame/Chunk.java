package GameEngine.voxelGame;

import GameEngine.core.GameObject;

public class Chunk extends GameObject{
	private static int numX = 16;
	private static int numY = 64;
	private static int numZ = 16;
	
	private Block[][][] blocks;
	
	public Chunk(){
		blocks = new Block[numX][numY][numZ];
	}
	
	public void initDefaultMap(){
		for(int i=0 ; i<numX ; i++){
			for(int j=0 ; j<numY ; j++){
				for(int k=0 ; k<numZ ; k++){
					Block newBlock = new Block(null, null, k);
					blocks[i][j][k] = newBlock;
					addComponent(newBlock);
				}
			}
		}
	}
}
