package maps;

import main.Utils;

public class Map {
	private Block[][][] mapa;
	public static int width = 2;
	public static int height = 1;
	public static int depth = 2;
	private int numWidth, numHeight, numDepth;
	
	public Map(int numWidth, int numHeight, int numDepth){
		this.numWidth = numWidth; //X
		this.numHeight = numHeight; //Y
		this.numDepth = numDepth; //Z
		this.mapa = new Block[numWidth][numDepth][numHeight];
	}
	
	public void createDefaultMap(){
		int half = numDepth/2;
		for(int i=0 ; i<numWidth ; i++){
			for(int k=0 ; k<numHeight ; k++){
				int j;
				for(j=0 ; j<half ; j++){
					mapa[i][j][k] = new Block(i,j,k,1);
				}
				for( ; j<numDepth ; j++){
					mapa[i][j][k] = new Block(i,j,k,0);
				}
			}
		}
		Utils.vypis("bola vytvorená mapa s " +numWidth*numHeight*numDepth+ " kockamy");
	}
	
	public Block getMapa(int x, int y, int z){
		return mapa[x][y][z];
	}
	
	public Block getMapa(String kde,Block od){
		switch(kde){
			case "up":
				return mapa[od.getSur()[0]][od.getSur()[1]+1][od.getSur()[2]];
			case "down":
				return mapa[od.getSur()[0]][od.getSur()[1]-1][od.getSur()[2]];
			case "left":
				return mapa[od.getSur()[0]-1][od.getSur()[1]][od.getSur()[2]];
			case "right":
				return mapa[od.getSur()[0]+1][od.getSur()[1]][od.getSur()[2]];
			case "back":
				return mapa[od.getSur()[0]][od.getSur()[1]][od.getSur()[2]-1];
			case "forward":
				return mapa[od.getSur()[0]][od.getSur()[1]][od.getSur()[2]+1];
			default:
				return null;
		}
	}
	
	public void draw(){
		for(Block[][] a:mapa){
			for(Block[] b:a){
				for(Block c:b){
					c.draw();
				}
			}
		}
	}
	
	public boolean isExists(int x, int y, int z){
		if(x>=0 && y>=0 && z>=0){
			if(x<numWidth && y<numHeight && z<numDepth){
				return true;
			}
		}
		return false;
	}
}
