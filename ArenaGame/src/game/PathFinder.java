package game;

import java.util.ArrayList;
import java.util.List;



public class PathFinder {
	
	public static void getDist(Block[][] mapa, Vector2f start, Vector2f goal){
		//zoznam pol��ok ktor� treba skontrolova�
		List<float[]> act = new ArrayList<float[]>();
		
		//prid� cielov� pol��ko do zoznamu
		act.add(new float[]{goal.getX(),goal.getY(),0,0});
		int level=0;
		
		//ciklus k�m su nejak� pol��ka na kontrolu
		while(act.size()!=0){
			for(int i=-1 ; i<=1 ; i++){
				for(int j=-1 ; j<=1 ; j++){
					
				}
			}
		}
	}
}
