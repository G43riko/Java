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
		Main.map[goal[0]][goal[1]].type=5;
		float length = (float)Main.map[goal[0]][goal[1]].distToGoal;
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(goal[1]+1<Main.map[goal[0]].length){
			if(Main.map[goal[0]][goal[1]+1].type==0){
				Main.map[goal[0]][goal[1]+1].distToGoal = length+1;
				getDistFromGoal(new int[]{goal[0],goal[1]+1},start);
			}
		}
		
		else if(goal[0]+1<Main.map.length){
			if(Main.map[goal[0]+1][goal[1]].type==0){
				Main.map[goal[0]][goal[1]+1].distToGoal = length+1;
				getDistFromGoal(new int[]{goal[0]+1,goal[1]},start);
			}
		}
		
		else if(goal[1]-1>=0){
			if(Main.map[goal[0]][goal[1]-1].type==0){
				Main.map[goal[0]][goal[1]+1].distToGoal = length+1;
				getDistFromGoal(new int[]{goal[0],goal[1]-1},start);
			}
		}
		
		else if(goal[0]-1>=0){
			if(Main.map[goal[0]-1][goal[1]].type==0){
				Main.map[goal[0]][goal[1]+1].distToGoal = length+1;
				getDistFromGoal(new int[]{goal[0]-1,goal[1]},start);
			}
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
