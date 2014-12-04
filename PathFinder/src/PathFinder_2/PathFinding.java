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
	
	public static void getDistFromGoal() {
		int[] start=new int[2];
		int[] goal=new int[2];
		for(int i=0 ; i<Main.map.length ; i++){
			for(int j=0 ; j<Main.map[i].length ; j++){
				if(Main.map[i][j].type==1){
					start[0]=i;
					start[1]=j;
				}
				else if(Main.map[i][j].type==2){
					goal[0]=i;
					goal[1]=j;
					Main.map[i][j].distToGoal=0;
				}
			}
		}
		boolean keepGoing=true;
		List<double[]> zoznam=new ArrayList<double[]>();
		List<double[]> helper=new ArrayList<double[]>();
		zoznam.add(new double[]{goal[0],goal[1],0});
		int q=0;
		while(keepGoing&&q<100){
			int n=0;
			while(isExist(goal[0] + n, goal[1])){
				Main.map[goal[0]+n][goal[1]].distToGoal=n;
				for(int d=n ; d>0 ; d--){
					if(isExist(goal[0]+n,goal[1]+d)){
						Main.map[goal[0]+n][goal[1]+d].distToGoal=-(d*1.2+n-1);
					}
					if(isExist(goal[0]+n,goal[1]-d)){
						Main.map[goal[0]+n][goal[1]-d].distToGoal=(d*1.2+n-1);
					}
				}
				n++;
			}
			n=0;
			while(isExist(goal[0]-n,goal[1])){
				Main.map[goal[0]-n][goal[1]].distToGoal=n;
				n++;
			}
			n=0;
			while(isExist(goal[0],goal[1]+n)){
				Main.map[goal[0]][goal[1]+n].distToGoal=n;
				n++;
			}
			n=0;
			while(isExist(goal[0],goal[1]-n)){
				Main.map[goal[0]][goal[1]-n].distToGoal=n;
				n++;
			}
			//pre všetky kontrolované
				//prejde všetky okolo ktoré nemaju goaldistance
					//každy jeden prejde okolie a najde políèko s najmenšou goalDistance
						//pripoèítasa goalDistance a zapíše a povodne políèko sa zmaže 
			/*
			if(zoznam==null){
				zoznam=new ArrayList<int[]>();
				for(double i=-1 ; i<=1 ; i++){
					for(double j=-1 ; j<=1 ; j++){
						int x=(int)((double)goal[0]+i);
						int y=(int)((double)goal[1]+j);
						if(PathFinding.isExist(x,y)){
							Main.map[x][y].distToGoal=PathFinding.getDist(x, y, goal[0], goal[1]);
							if(i!=0||j!=0){
								Main.map[x][y].type=4;
								zoznam.add(new int[]{x,y});
							}
						}
					}
				}
			}
			else{
				for(int k=0 ; k<zoznam.size() ; k++){
					//zoznam.get(i)
					double shortestDist=-1;
					for(double i=-1 ; i<=1 ; i++){
						for(double j=-1 ; j<=1 ; j++){
							if(Main.map[zoznam.get(k)[0]][zoznam.get(k)[1]].type==4){
								int x=(int)((double)zoznam.get(k)[0]+i);
								int y=(int)((double)zoznam.get(k)[1]+j);
								if(PathFinding.isExist(x,y)){
									if(Main.map[x][y].distToGoal>0){
										if(shortestDist==-1){
											shortestDist=Main.map[x][y].distToGoal=PathFinding.getDist(x, y, goal[0], goal[1]);
										}
										else{
											if(shortestDist>PathFinding.getDist(x, y, goal[0], goal[1])){
												shortestDist=PathFinding.getDist(x, y, goal[0], goal[1]);
											}
										}
									}
									
								}
							}
						}
					}
					Main.map[zoznam.get(k)[0]][zoznam.get(k)[1]].distToGoal=shortestDist;
					zoznam.remove(k);
					k--;
				}
			}
			*/
			q++;
		}
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
