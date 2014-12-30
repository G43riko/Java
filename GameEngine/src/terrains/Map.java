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
				int dist = half+((int)(Math.random()*numY/2)-numY/2/2);
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
//		int pocet = 0;
		for(int i=0 ; i<numX ; i++){
			for(int j=0 ; j<numY ; j++){
				for(int k=0 ; k<numZ ; k++){
					Block c = mapa[i][j][k];
					if(c.getType()!=0){
						if(isHide(i,j+1,k)&&isHide(i+1,j,k)&&isHide(i-1,j,k)&&isHide(i,j,k+1)&&isHide(i,j,k-1)){
							continue;
						}
						//u,d,l,r,f,b
						boolean u,d,l,r,f,b;
						u=d=l=r=f=b=false;
						if(isHide(i,j+1,k));
							u=true;
						if(isHide(i,j-1,k));
							d=true;
						if(isHide(i-1,j,k));
							l=true;
						if(isHide(i+1,j,k));
							r=true;
						if(isHide(i,j,k-1));
							f=true;
						if(isHide(i,j,k+1));
							b=true;
						renderer.render(c, shader);
//						pocet++;
					}
				}
			}
		}
//		System.out.println("vykreslilo to "+pocet+" krát");
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
	}
	
	private boolean exist(int x,int y,int z){
		if(x>0&&x<numX&&y>0&&y<numY&&z>0&&z<numZ){
			return true;
		}
		return false;
	}
	
	private boolean isHide(int x,int y,int z){
		if(exist(x,y,z)&&mapa[x][y][z].getType()!=0){
			return true;
		}
		return false;
	}
	
	public Block[][] getTerrain(){
		return terrain;
	}
}
