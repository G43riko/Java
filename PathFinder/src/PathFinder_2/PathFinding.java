package PathFinder_2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public class PathFinding {
	public static Color getColor(int type){
		switch (type){
			 case 0://nothing
				 return Color.WHITE;
			 case 1://start
				 return Color.RED;
			 case 2://goal
				 return Color.BLUE;
			 case 3://block
				 return Color.BLACK;
			 case 4://history
				 return Color.PINK;
			 case 5://possibles
				 return Color.ORANGE;
			 case 6://the best
				 return Color.GRAY;
			 case 7://now position
				 return Color.DARK_GRAY;
			 case 8://path
				 return Color.MAGENTA;
		}
		return Color.WHITE;
	};
	
	public static void getDistFromGoal(int[] goal, int[] start) {
		List<float[]> act = new ArrayList<float[]>();
		act.add(new float[]{goal[0],goal[1],0,0});
		int level=0;
		while(act.size()!=0){
			for(int k=0 ; k<act.size() ; k++){
				if(act.get(k)[3]>level){
					continue;
				}
				for(int i=-1 ; i<=1 ; i++){
					for(int j=-1 ; j<=1 ; j++){
						float num;
						if(i==0 && j==0){
							continue;
						}
						else if((act.get(k)[0]+i)>=0&&act.get(k)[0]+i<Main.map.length&&(act.get(k)[1]+j)>=0&&
							act.get(k)[1]+j<Main.map[(int)act.get(k)[0]+i].length){
							num = act.get(k)[2]+1;
							if(i!=0&&j!=0){
								if(Main.map[(int)act.get(k)[0]][(int)act.get(k)[1]+j].type!=0||
								   Main.map[(int)act.get(k)[0]+i][(int)act.get(k)[1]].type!=0){
									//Main.map[(int)act.get(k)[0]+i][(int)act.get(k)[1]+j].distToGoal = -1;
									continue;
								}
								num += 0.5;
							}
							if((int)act.get(k)[0]+i == start[0]&&(int)act.get(k)[1]+j==start[1]){
								return;
							}
							if(Main.map[(int)act.get(k)[0]+i][(int)act.get(k)[1]+j].type==0){
								Main.map[(int)act.get(k)[0]+i][(int)act.get(k)[1]+j].type=5;
								Main.map[(int)act.get(k)[0]+i][(int)act.get(k)[1]+j].distToGoal = num;
								act.add(new float[]{act.get(k)[0]+i,act.get(k)[1]+j,(float)num,level+1});
							}
							else if(Main.map[(int)act.get(k)[0]+i][(int)act.get(k)[1]+j].type==5){
								if(num<Main.map[(int)act.get(k)[0]+i][(int)act.get(k)[1]+j].distToGoal){
									act.add(new float[]{act.get(k)[0]+i,act.get(k)[1]+j,(float)num,level+1});
									Main.map[(int)act.get(k)[0]+i][(int)act.get(k)[1]+j].distToGoal = num;
								}
							}
						}
					}
				}
				act.remove(k);
				k--;
			}
			level++;
		}
	}
	
	public static void findShortestDistance(int[] goal, int start[]){
		float[] max=null;
		if(goal[0]!=Main.start[0]||goal[1]!=Main.start[1]){
			Main.map[goal[0]][goal[1]].type = 6;
		}
		for(int i=-1 ; i<=1 ; i++){
			for(int j=-1 ; j<=1 ; j++){
				if(i==0&&j==0){
					continue;
				}
				if((goal[0]+i)>=0&&goal[0]+i<Main.map.length&&(goal[1]+j)>=0&&goal[1]+j<Main.map[(int)goal[0]+i].length){
					if(Main.map[goal[0]+i][goal[1]+j].type==5){
						if(max==null){
							max = new float[]{goal[0]+i,goal[1]+j,(float)Main.map[goal[0]+i][goal[1]+j].distToGoal};
						}
						if(max[2]>Main.map[goal[0]+i][goal[1]+j].distToGoal){
							max = new float[]{goal[0]+i,goal[1]+j,(float)Main.map[goal[0]+i][goal[1]+j].distToGoal};
						}
						if(max[2]==0){
							return;
						}
					}
					else if(Main.map[goal[0]+i][goal[1]+j].type==2){
						return;
					}
				}
			}
		}
		findShortestDistance(new int[]{(int)max[0],(int)max[1]},start);
	}
	
	public static double getDist(int ax, int ay, int bx, int by){
		double dist;
		dist=(ax-bx)*(ax-bx)+(ay-by)*(ay-by);
		dist=Math.sqrt(dist);
		return dist;
	};
	
	public static boolean isExist(int x, int y){
		if(x<0||y<0){
			return false;
		}
		if(x<Main.map.length&&y<Main.map[x].length){
			return true;
		}
		return false;
	}
}
