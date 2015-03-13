package glib.math;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class GMath {
	public static float dist(float ax, float ay, float bx, float by){
		return new GVector2f(ax,ay).dist(new GVector2f(bx,by));
	};
	
	public static float dist(float ax, float ay, float az, float bx, float by, float bz){
		return new GVector3f(ax,ay,az).dist(new GVector3f(bx,by,bz));
	};
	
	public static float choose(float... argc){
		return argc[(int)Math.floor(Math.random()*argc.length)];
	};
	
	public static float average(float... argc){
		float sum=0;
		for(float co : argc){
			sum+=co;
		}
		return sum/argc.length;
	};
	
	public static GVector2f center(GVector2f... vectors){
		float sumX = 0;
		float sumY = 0;
		for(GVector2f vec : vectors){
			sumX += vec.getX();
			sumY += vec.getY();
		}
		return new GVector2f(sumX/vectors.length, sumY/vectors.length);
	};
	
	public static GVector3f center(GVector3f... vectors){
		float sumX = 0;
		float sumY = 0;
		float sumZ = 0;
		for(GVector3f vec : vectors){
			sumX += vec.getX();
			sumY += vec.getY();
			sumZ += vec.getZ();
		}
		return new GVector3f(sumX/vectors.length, sumY/vectors.length, sumZ/vectors.length);
	};
	
	public static int fastfloor(double x) {
		return x>0 ? (int)x : (int)x-1;
	};
	
	public static float interpolateLinear(float scale, float startValue, float endValue) {
        if (startValue == endValue)
            return startValue;
        if (scale <= 0f)
            return startValue;
        if (scale >= 1f)
            return endValue;
        return (endValue - startValue)*scale + startValue;
    };
    
    public static float randomize(float value, float level){
    	return value + (float)(Math.random()*2*level - level);
    };
    
    public static float between(float value, float min, float max){
    	return (float)Math.max(min, Math.min(value, max));
    };

    public static float max(float... args){
    	float max = args[0];
    	int num = args.length;
    	for(int i=1 ; i<num ; i++)
    		if(args[i] > max)
    			max = args[i];
    	
    	return max;
    }
    
    public static float min(float... args){
    	float min = args[0];
    	int num = args.length;
    	for(int i=1 ; i<num ; i++)
    		if(args[i] < min)
    			min = args[i];
    	
    	return min;
    }

    public static int rand(int min, int max){
    	return (int)(Math.random()*(max-min)) + min;
    }
    
    public static float rand(float min, float max){
    	return (float)(Math.random()*(max-min)) + min;
    }
}
