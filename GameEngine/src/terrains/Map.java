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
				for(j=0 ; j<half ; j++){
					mapa[i][j][k] = new Block(i*Block.WIDTH*2,j*Block.HEIGHT*2, k*Block.DEPTH*2,1);
				}
				for( ; j<numY ; j++){
					mapa[i][j][k] = new Block(i*Block.WIDTH*2,j*Block.HEIGHT*2, k*Block.DEPTH*2);
				}
			}
		}
		
		getTerrain();
		
		for(Block[] a:terrain){
			for(Block b:a){
				System.out.print(b.getType());
			}
			System.out.print("\n");
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
	
	public void getTerrain(){
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
	}
}
