package Main;

public class Mathematics {

	
	public static int choose(int... argc){
		return argc[(int)Math.floor(Math.random()*argc.length)];
	};
	
	public static float choose(float... argc){
		return argc[(int)Math.floor(Math.random()*argc.length)];
	};
	
	
	public static int average(int... argc){
		int sum=0;
		for(int co : argc){
			sum+=co;
		}
		return sum/argc.length;
	};
	
	public static float average(float... argc){
		float sum=0;
		for(float co : argc){
			sum+=co;
		}
		return sum/argc.length;
	};
}
