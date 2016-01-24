package glib.util.analytics;

import java.text.DecimalFormat;

import glib.util.Units;

public class FrameData {
	private final static DecimalFormat FORMAT = new DecimalFormat("0000.00000");
	private long initTime = System.nanoTime();
	private long lastTime = initTime;
	
	private long renderTime;
	private long updateTime;
	private long inputTime;
	private long totalTime;

	//CONSTRUCTORS
	
	public FrameData(){
		
	}
	
	public FrameData(long initTime, long lastTime, long renderTime, long updateTime, long inputTime, long totalTime) {
		this.renderTime = renderTime;
		this.updateTime = updateTime;
		this.inputTime  = inputTime;
		this.totalTime  = totalTime;
		this.initTime   = initTime;
		this.lastTime   = lastTime;
	}

	//ENDERS
	
	public void endRender(){
		renderTime = System.nanoTime() - lastTime;
		lastTime = System.nanoTime();
	}
	
	public void endUpdate(){
		updateTime = System.nanoTime() - lastTime;
		lastTime = System.nanoTime();
	}
	
	public void endInput(){
		inputTime = System.nanoTime() - lastTime;
		lastTime = System.nanoTime();
	}

	public void endLoop(){
		totalTime = System.nanoTime() - initTime;
		lastTime = System.nanoTime();
	}

	//OTHERS
	
	@Override
	public String toString() {
		return "[total:" 	  + FORMAT.format(totalTime  / Units.MS) + 
				"ms render: " + FORMAT.format(renderTime / Units.MS) + 
				"ms updade: " + FORMAT.format(updateTime / Units.MS) + 
				"ms input: "  + FORMAT.format(inputTime  / Units.MS) + 
				"ms length: " + FORMAT.format(getLengthMs()) + "ms]";
	}
	
	//GETTERS

	public long getRenderTimeMs() {return renderTime / Units.MS;}
	public long getUpdateTimeMs() {return updateTime / Units.MS;}
	public long getInputTimeMs() {return inputTime / Units.MS;}
	public long getTotalTimeMs() {return totalTime / Units.MS;}
	public long getLength(){return lastTime - initTime;}
	public long getLengthMs(){return getLength() / Units.MS;}
	public long getRenderTime() {return renderTime;}
	public long getUpdateTime() {return updateTime;}
	public long getInputTime() {return inputTime;}
	public long getTotalTime() {return totalTime;}
	public long getInitTime() {return initTime;}
	public long getLastTime() {return lastTime;}
}
