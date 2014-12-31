package GameEngine.core;

public class Time 
{
	public static final long SECOND = 1000000000L;
	private static double delta;
	
	public static double GetTime()
	{
		return System.nanoTime();
	}
	public static void setDelta(double delta){
		Time.delta=delta;
	}
	
	public static double getDelta(){
		return Time.delta;
	}
}