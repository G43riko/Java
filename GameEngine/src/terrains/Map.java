package terrains;

import entities.Entity;
import renderers.Renderer;
import shaders.StaticShader;
import main.Loader;

public class Map {
	private Block[][][] mapa;
	private int numX,numY,numZ;
	private Block[][] terrain;
	
	public Map(int x, int y, int z){
		numX = x;
		numY = y;
		numZ = z;
		mapa = new Block[x][y][z];
		terrain = new Block[x][z];
	};
	
	public void initDefaultMap(Loader loader){
		Block.init(loader);
		int half = numY/2;
		int i,j,k;
		for(i=0 ; i<numX ; i++){
			for(k=0 ; k<numZ ; k++){
				boolean ground = false;
				int dist = half+((int)(Math.random()*2)-1);
				for(j=0 ; j<dist ; j++){
					mapa[i][j][k] = new Block(i*Block.WIDTH*2,j*Block.HEIGHT*2, k*Block.DEPTH*2,1);
				}
				for( ; j<numY ; j++){
					mapa[i][j][k] = new Block(i*Block.WIDTH*2,j*Block.HEIGHT*2, k*Block.DEPTH*2);
				}
			}
		}
	}

	public void draw(Renderer renderer, StaticShader shader) {
		for(Block[][] a:mapa){
			for(Block[] b:a){
				for(Block c:b){
					if(c.getType()!=0){
						renderer.render(c, shader);
					}
					
//					if(c.getType()!=0)
//						c.draw();
				}
			}
		}
	}
	
	public void createTerrain(){
		for(int i=0 ; i<numX ; i++){
			for(int j=0 ; j<numZ ; j++){
				for(int k=numY-1 ; k>=0 ; k-- ){
					if(mapa[i][k][j].getType()!=0){
						terrain[i][j] = mapa[i][k][j];
						break;
					}
				}
			}
		}
//		for(Block[] b:terrain){
//			for(int i=b.length-1 ; i>=0 ; i--){
//				System.out.print((int)(b[i].getY()/Block.HEIGHT/2)+"  ");
//			}
//			System.out.print("\n");
//		}
	}
	
	public Block[][] getTerrain(){
		return terrain;
	}
}
