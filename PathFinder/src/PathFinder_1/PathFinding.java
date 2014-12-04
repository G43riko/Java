package PathFinder_1;
import java.awt.Color;


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
	
	public static void findPossibles(){
		for( int i=0 ; i<Main.map.length ; i++){
			for( int j=0 ; j<Main.map[i].length ; j++){
				if(Main.onlyGoal&&Main.map[i][j].type==2){
					Main.map[i][j].stepsFromGoal=0;
					selectAllPossibles(i,j,false);
					Main.onlyGoal=false;
				}
				else if(Main.map[i][j].type==7){
					selectAllPossibles(i,j,false);
				}
			}
		}
	};
	
	public static void findBestWay(int co){
		if(co==0){
			for( int i=0 ; i<Main.map.length ; i++){
				for( int j=0 ; j<Main.map[i].length ; j++){
					if(Main.map[i][j].type==5){
						Main.map[i][j].type=6;
					}
				}
			}
		}
	};
	
	public static void moveToPossibles(){
		for( int i=0 ; i<Main.map.length ; i++){
			for( int j=0 ; j<Main.map[i].length ; j++){
				if(Main.map[i][j].type==7){
					Main.map[i][j].type=4;
				}
			}
		}
		for( int i=0 ; i<Main.map.length ; i++){
			for( int j=0 ; j<Main.map[i].length ; j++){
				if(Main.map[i][j].type==6){
					Main.map[i][j].type=7;
				}
			}
		}
	};
	
	public static void canRepair(int x, int y,int level){
		
		/*
		if(Main.map[x+level-1][y+level-1].type==4){
			if(Main.map[x+level-1][y+level-1].distToGoal<Main.map[x][y].distToGoal){
				boolean vedla=true;
				for(int i=1 ; i<level ; i++){
					if(Main.map[x+i][y].type!=8||Main.map[x][y+i].type!=8){
						vedla=false;
						break;
					}
				}
				if(vedla){
					System.out.println("našla sa zmena");
					Main.map[x+level-1][y+level-1].type=8;
					Main.map[x][y].type=4;
				}
			}
		}
		*/
	};
	
	public static void repairPath(){
		boolean change=false;
		for( int i=0 ; i<Main.map.length ; i++){
			for( int j=0 ; j<Main.map[i].length ; j++){
				/*
				boolean changeInPole=false;
				for(double k=-1 ; k<=1 ; k+=2){
					for(double l=-1 ; l<=1 ; l+=2){
						if(Main.map[i][j].type==8){
							if(i+(int)k<0||j+(int)l<0||i+(int)k>=Main.map.length||j+(int)l>=Main.map[i+(int)k].length){
								continue;
							}
							if(Main.map[i+(int)k][j+(int)l].type==8){
								if(Main.map[i+(int)k][j].type==8){
									if(Main.map[i][j+(int)l].type==4){
										if(Main.map[i][j+(int)l].distToGoal<Main.map[i+(int)k][j].distToGoal){
											Main.map[i+(int)k][j].type=4;
											Main.map[i][j+(int)l].type=8;
											changeInPole=true;
										}
									}
								}
							}
							if(changeInPole){
								continue;
							}
							if(i-(int)k<0||j-(int)l<0||i-(int)k>=Main.map.length||j-(int)l>=Main.map[i-(int)k].length){
								continue;
							}
							if(Main.map[i-(int)k][j-(int)l].type==8){
								if(Main.map[i-(int)k][j].type==8){
									if(Main.map[i][j-(int)l].type==4){
										if(Main.map[i][j-(int)l].distToGoal<Main.map[i-(int)k][j].distToGoal){
											Main.map[i-(int)k][j].type=4;
											Main.map[i][j-(int)l].type=8;
											changeInPole=true;
										}
									}
								}
							}
						}
					};
				};
				*/
				if(Main.map[i][j].type==8){
					PathFinding.canRepair(i,j,2);
				}
			}
		}
	};
	
	public static void selectAllPossibles(int X, int Y,boolean diagonal){
		int stepsNow=Main.map[X][Y].stepsFromGoal;
		if(PathFinding.ifExist(X,(Y+1),1,stepsNow+1)){
			Main.map[X][Y+1].direction=1;
			Main.map[X][Y+1].stepsFromGoal=stepsNow+1;
			Main.map[X][Y+1].type=5;
		}
		if(PathFinding.ifExist(X, (Y-1),5,stepsNow+1)){
			Main.map[X][Y-1].direction=5;
			Main.map[X][Y-1].stepsFromGoal=stepsNow+1;
			Main.map[X][Y-1].type=5;
		}
		if(PathFinding.ifExist((X-1), Y,3,stepsNow+1)){
			Main.map[X-1][Y].direction=3;
			Main.map[X-1][Y].stepsFromGoal=stepsNow+1;
			Main.map[X-1][Y].type=5;
		}
		if(PathFinding.ifExist((X+1), Y,7,stepsNow+1)){
			Main.map[X+1][Y].direction=7;
			Main.map[X+1][Y].stepsFromGoal=stepsNow+1;
			Main.map[X+1][Y].type=5;
		}
		if(diagonal){
			if(PathFinding.ifExist((X-1), (Y+1),2,stepsNow+2)){
				Main.map[X-1][Y+1].direction=2;
				Main.map[X-1][Y+1].stepsFromGoal=stepsNow+2;
				Main.map[X-1][Y+1].type=5;
			}
			if(PathFinding.ifExist((X-1), (Y-1),4,stepsNow+2)){
				Main.map[X-1][Y-1].direction=4;
				Main.map[X-1][Y-1].stepsFromGoal=stepsNow+2;
				Main.map[X-1][Y-1].type=5;
			}
			if(PathFinding.ifExist((X+1), (Y-1),6,stepsNow+2)){
				Main.map[X+1][Y-1].direction=6;
				Main.map[X+1][Y-1].stepsFromGoal=stepsNow+2;
				Main.map[X+1][Y-1].type=5;
			}
			if(PathFinding.ifExist((X+1), (Y+1),8,stepsNow+2)){
				Main.map[X+1][Y+1].direction=8;
				Main.map[X+1][Y+1].stepsFromGoal=stepsNow+2;
				Main.map[X+1][Y+1].type=5;
			}
		}
	};
	
	public static void findPath(int type){
		 int[] start=new int[2],goal=new int[2];
		 for( int i=0 ; i<Main.map.length ; i++){
			for( int j=0 ; j<Main.map[i].length ; j++){
				if(Main.map[i][j].type==2){
					start[0]=i;
					start[1]=j;
				}
				else if(Main.map[i][j].type==1){
					goal[0]=i;
					goal[1]=j;
				}
			}
		};
		int num=0;
		while(Main.map[goal[0]][goal[1]].type!=2){
			if(num>Main.map.length*Main.map[0].length){
				System.exit(1);
			}
			num++;
			if(Main.map[goal[0]][goal[1]].type!=2){
				Main.map[goal[0]][goal[1]].type=8;
			}
			switch(Main.map[goal[0]][goal[1]].direction){
				case 1:
					goal[1]--;
					break;
				case 2:
					goal[1]--;
					goal[0]++;
					break;
				case 3:
					goal[0]++;
					break;
				case 4:
					goal[1]++;
					goal[0]++;
					break;
				case 5:
					goal[1]++;
					break;
				case 6:
					goal[1]++;
					goal[0]--;
					break;
				case 7:
					goal[0]--;
					break;
				case 8:
					goal[1]--;
					goal[0]--;
					break;	
			}
		}
	};
	
	public static boolean ifExist(int X,int Y,int dir,int dist){
		if(X<0||Y<0||X>=Main.map.length){
			return false;
		}
		if(Y>=Main.map[X].length){
			return false;
		}
		if(Main.map[X][Y].type==1){
			Main.map[X][Y].direction=dir;
			Main.map[X][Y].stepsFromGoal=dist;
			Main.findGoal=true;
		}
		if(Main.map[X][Y].type!=0){
			return false;
		}
		return true;
	}
	
	
	
	
	
	public static void getDistFromGoal(int x, int y) {
		 for( int i=0 ; i<Main.map.length ; i++){
			for( int j=0 ; j<Main.map[i].length ; j++){
				double distX=(x-i)*(x-i);
				double distY=(y-j)*(y-j);
				double result=Math.sqrt(distX+distY);
				Main.map[i][j].distToGoal=(double)Math.round(1000 * result) / 1000;
			}
		 }
	}
}
