package terrains;

import java.util.ArrayList;
import java.util.List;

public class Map2 {
	private Stlp[][] mapa;
	private int numX,numZ;
	
	public Map2(int x,int y, int z){
		numX = x;
		numZ = z;
		mapa =  new Stlp[x][z];
	}
	
	public void initDefaultMap(int numY){
		int half = numY/2;
		int j;
		for(int i=0 ; i<numX ; i++){
			for(int k=0 ; k<numZ ; k++){
				int dist = half+((int)(Math.random()*numY/2)-numY/2/2);
				mapa[i][k] = new Stlp(i,k);
				for(j=0 ; j<dist ; j++){
					mapa[i][k].add(new Block(i,j, k,1));
				}
				for( ; j<numY ; j++){
					mapa[i][k].add(new Block(i,j, k));
				}
			}
		}
	}
	
	private boolean exist(int x,int y,int z){
//		if(x>0&&x<numX&&y>0&&y<numY&&z>0&&z<numZ){
//			return true;
//		}
		return false;
	}
}
