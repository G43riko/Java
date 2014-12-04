package Main;

public class Mathematics {

	
	public static int choose(int... argc){
		return argc[(int)Math.floor(Math.random()*argc.length)];
	};
	
	public static double choose(double... argc){
		return argc[(int)Math.floor(Math.random()*argc.length)];
	};
	
	public static double average(double... argc){
		double sum=0;
		for(double co : argc){
			sum+=co;
		}
		return sum/argc.length;
	};
	
	public static int average(int... argc){
		int sum=0;
		for(int co : argc){
			sum+=co;
		}
		return sum/argc.length;
	};
}
