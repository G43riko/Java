package Main;

public class Map {
	private Block[][] mapa;
	public static float blockX=1, blockY=1;
	private int numX, numY;
	
	public Map(int x, int y){
		blockX /= x/2;
		blockY /= y/2;
		numX = x;
		numY = y;
		mapa = new Block[x][y];
		//Arrays.fill(mapa, null);
		initMap();
	}
	
	private void initMap(){
		for(int i=0 ; i<numX ; i++){
			for(int j=0 ; j<numY ; j++){
				//if(Math.random()<0.5)
				if(j<numY/2||j>-1)
					mapa[i][j] = new Block(i,j,1);
				else
					mapa[i][j] = new Block(i,j,0);
			}	
		}
	}
	
	public void draw(){
		for(int i=0 ; i<numX ; i++){
			for(int j=0 ; j<numY ; j++){
				if(mapa[i][j]!=null){
					mapa[i][j].draw();
				}
			}
		}
	}
}
