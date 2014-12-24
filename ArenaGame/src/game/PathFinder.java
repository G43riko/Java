package game;

import java.util.ArrayList;
import java.util.List;

public class PathFinder {
	
	public static void getDirection(Block[][] mapa){
		int numX = mapa.length;
		int numY = mapa[0].length;
		
		for(int x=0 ; x<numX ;x++){
			for(int y=0 ; y<numY ;y++){
				float shortest[] = new float[]{x,y,-1,0};
				int direction = 0;
				if(mapa[x][y].getDir()!=0||mapa[x][y].getType()!=0){
					continue;
				}
				for(int i=-1 ; i<=1 ; i++){
					for(int j=-1 ; j<=1 ; j++){
						if(i==0&&j==0){
							continue;
						}
						direction++;
						if(Map.exist(x+i, y+j, mapa)&&(shortest[2]==-1||mapa[x+i][y+j].getDistToGoal()<shortest[2])){
							shortest = new float[]{x+i,y+j,mapa[x+i][y+j].getDistToGoal(),direction};
						}
					}
				}
				mapa[(int)shortest[0]][(int)shortest[1]].setDir((int)shortest[3]);
			}
		}
	}
	
	public static void clear(Block[][] mapa){
		int numX = mapa.length;
		int numY = mapa[0].length;
		
		for(int x=0 ; x<numX ;x++){
			for(int y=0 ; y<numY ;y++){
				if(mapa[x][y].getType()==0){
					mapa[x][y].setDistToGoal(-1);
					mapa[x][y].setDir(0);
				}
			}
		}
	}
	
	public static void getDist(Block[][] mapa, Vector2f start, Vector2f goal){
		//zoznam políèok ktoré treba skontrolova
		List<float[]> act = new ArrayList<float[]>();
		
		//pridá cielové políèko do zoznamu
		act.add(new float[]{goal.getX(),goal.getY(),0,0});
		int level=0;
		mapa[(int)goal.getX()][(int)goal.getY()].setDistToGoal(0);
		//ciklus kým su nejaké políèka na kontrolu
		while(act.size()!=0){
			for(int l=0 ; l<act.size() ; l++){//toto opravi
				float[] k = act.get(l);
				int direction=0;
				for(int i=-1 ; i<=1 ; i++){
					for(int j=-1 ; j<=1 ; j++){
						
						if(i==0&&j==0){
							continue;
						}
						direction++;
						float num;
						if(Map.exist(k[0]+i, k[1]+j, mapa)){
							if(mapa[(int)k[0]+i][(int)k[1]+j].getType()==0){
								num = k[2]+1;
								//kedy sa pridá o pol viacej
								if(i!=0&&j!=0){
									if(mapa[(int)k[0]][(int)k[1]+j].getType()!=0||mapa[(int)k[0]+i][(int)k[1]].getType()!=0){
										continue;
									}
									num += 0.5;
								}
								if((int)k[0]+i == start.getX()&&(int)k[1]+j==start.getY()){
									//return;
								}
							
								if(mapa[(int)k[0]+i][(int)k[1]+j].getDistToGoal()==-1){
									mapa[(int)k[0]+i][(int)k[1]+j].setDistToGoal(num);
									mapa[(int)k[0]+i][(int)k[1]+j].setDir(direction);
									act.add(new float[]{k[0]+i,k[1]+j,num,level+1});
								}
								else if(num<mapa[(int)k[0]+i][(int)k[1]+j].getDistToGoal()){
									mapa[(int)k[0]+i][(int)k[1]+j].setDistToGoal(num);
									mapa[(int)k[0]+i][(int)k[1]+j].setDir(direction);
									act.add(new float[]{k[0]+i,k[1]+j,num,level+1});
								}
							}
						}
						
					}
				}
				act.remove(k);
				l--;
			}
			level++;
		}
	}
}
