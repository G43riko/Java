package glib.util.time;

public class Timer {
	private boolean paused;
	private double time = 0;
	private double startTime;
	
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
}
