package glib.util.time;

public class Timer {
	private boolean paused;
	private double time = 0;
	
	public Timer(){
		
	}
	
	public double getTime(){
		return time;
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public void pause() {
		paused = true;
	}
	
	public void reset() {
		time = 0;
	}
	
	public String toString() {
		return "Timer[Time=" + getTime() + ", Paused=" + paused + "]";
	}
}
