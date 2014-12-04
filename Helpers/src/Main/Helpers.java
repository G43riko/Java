package Main;

import Goniometry.Vector2f;

public class Helpers {
	
	public static double addRandomness2(double co, int percent){
		return co+Math.random()*(co/100*percent)*2-(co/100*percent);
		
	};
	
	public static double addRandomness(double hodnota,int percent){     
        return ((hodnota/100*(100-percent))+(hodnota/100*percent*2*Math.random()));
    }
	
	public static Vector2f center(Vector2f... vectors){
		float sumX = 0;
		float sumY = 0;
		for(Vector2f vec : vectors){
			sumX += vec.getX();
			sumY += vec.getY();
		}
		return new Vector2f(sumX/vectors.length, sumY/vectors.length);
	}
}

