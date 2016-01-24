package glib.util.analytics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import glib.util.Units;

public class SecondData {
	private List<FrameData> 		frames 	= new ArrayList<FrameData>();
	private Map<String, Integer>	counter = new HashMap<String, Integer>();
	private Map<String, Double>		timers 	= new HashMap<String, Double>();
	
	private long updateTime = 0;
	private long renderTime = 0;
	private long inputTime = 0;
	private long totalTime = 0;
	
	private long maxMem;
	private long freeMem;
	private long totalMem;
	private FrameData avgFrame;
	
	//ADDERS
	
	public void addFrame(FrameData frame){
		frames.add(frame);
		
		updateTime += frame.getUpdateTime();
		renderTime += frame.getRenderTime();
		totalTime  += frame.getTotalTime();
		inputTime  += frame.getInputTime();
	}
	
	public void addMemoryData(long maxMem, long freeMem, long totalMem){
		this.totalMem = totalMem;
		this.freeMem  = freeMem;
		this.maxMem   = maxMem;
	}
	
	public void addToTimer(String name, double value){
		timers.put(name, getTimer(name) + value);
	}

	//OTHERS
	
	@Override
	public String toString() {
		return "free memory: " + getFreeMemMB() + "/" + getTotalMemMB() + " MB, Max memory: " + getMaxMemMB() + " MB, Average: " + avgFrame;
	}
	
	public void increaseCounter(String name){
		counter.put(name, getCounter(name) + 1);
	}
	
	//ENDERS
	
	public void endSecond(){
		long initTime = frames.get(0).getInitTime();
		long lastTime = frames.get(frames.size() - 1).getLastTime();
		
		updateTime /= frames.size();
		renderTime /= frames.size();
		totalTime  /= frames.size();
		inputTime  /= frames.size();
		
		avgFrame = new FrameData(initTime, lastTime, renderTime, updateTime, inputTime, totalTime);
	}
	
	//GETTERS
	
	public float getTimerFloat(String name){return timers.containsKey(name) ? Float.valueOf(timers.get(name).toString()) : 0;}
	public int getTimerInt(String name){return timers.containsKey(name) ? Integer.valueOf(timers.get(name).toString()) : 0;}
	public long getTimerLong(String name){return timers.containsKey(name) ? Long.valueOf(timers.get(name).toString()) : 0;}
	public int getCounter(String name){return counter.containsKey(name) ? counter.get(name) : 0;}
	public double getTimer(String name){return timers.containsKey(name) ? timers.get(name) : 0;}
	public FrameData getAverageFrame(){return avgFrame;}
	public int getNumberOfFrames(){return frames.size();}
	public int getTotalMemMB() {return (int)(totalMem / Units.MB);}
	public int getFreeMemMB() {return (int)(freeMem / Units.MB);}
	public int getMaxMemMB() {return (int)(maxMem / Units.MB);}
}
