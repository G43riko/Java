package terrains;

public class Map {
	private Block[][][] mapa;
	private int numX,numY,numZ;
	
	public Map(int x, int y, int z){
		numX = x;
		numY = y;
		numZ = z;
		mapa = new Block[x][y][z];
	};
	
	public void initDefaultMap(){
		int half = numY/2;
		int i,j,k;
		for(i=0 ; i<numX ; i++){
			for(k=0 ; k<numZ ; k++){
				for(j=0 ; j<half ; j++){
					mapa[i][j][k] = new Block(i*Block.WIDTH*2,j*Block.HEIGHT*2, k*Block.DEPTH*2,1);
				}
				for( ; j<numY ; j++){
					mapa[i][j][k] = new Block(i*Block.WIDTH*2,j*Block.HEIGHT*2, k*Block.DEPTH*2);
				}
			}
		}
	}

	public void draw() {
		for(Block[][] a:mapa){
			for(Block[] b:a){
				for(Block c:b){
//					if(c.getType()!=0)
//						c.draw();
				}
			}
		}
	}
}
