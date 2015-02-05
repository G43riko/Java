package glib.util.time;

import java.util.HashMap;

public class Timer {
	private boolean paused;
	private double time = 0;
	private double startTime;
	private static HashMap<String,Long> datas = new HashMap<String,Long>();
	public Timer(){
		
	}
	
	public void start(){
		startTime = System.currentTimeMillis();
		paused = false;
	}
	
	public double getTime(){
		return time;
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public void pause() {
		time += System.currentTimeMillis()-startTime; 
		paused = true;
	}
	
	public void reset() {
		time = 0;
	}
	
	public String toString() {
		return "Timer[Time=" + getTime() + ", Paused=" + paused + "]";
	}
	
	public static void set(String name){
		datas.put(name, System.currentTimeMillis());
	}
	public static Long get(String name){
		return System.currentTimeMillis() - datas.get(name);
	}
	public static void reset(String name){
		datas.put(name, System.currentTimeMillis());
	}
	public static void remove(String name){
		datas.remove(name);
	}
}
