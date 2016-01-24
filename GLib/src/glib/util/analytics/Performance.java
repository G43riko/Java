package glib.util.analytics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Performance {
	private List<SecondData> datas = new ArrayList<SecondData>();
	private SecondData 	actualSecond;
	private FrameData 	actualFrame;
	private Runtime 	runtime = Runtime.getRuntime(); 
	
	//OTHERS
	
	public void start(){
		actualSecond = new SecondData();
		actualFrame  = new FrameData();
	}

	public void addToTimer(String name, double value){
		actualSecond.addToTimer(name, value);
	}
	
	public void increaseCounter(String name){
		actualSecond.increaseCounter(name);
	}
	
	//ENDERS
	
	public void endRender(){
		actualFrame.endRender();
	}
	
	public void endUpdate(){
		actualFrame.endUpdate();
	}
	
	public void endInput(){
		actualFrame.endInput();
	}

	public void endLoop(){
		actualFrame.endLoop();
		actualSecond.addFrame(actualFrame);
		actualFrame = new FrameData();
	}
	
	public void endSecond(){
		actualSecond.endSecond();
		actualSecond.addMemoryData(runtime.maxMemory(), runtime.freeMemory(), runtime.totalMemory());
//		System.out.println(actualSecond);
		datas.add(actualSecond);

		actualSecond = new SecondData();
	}
	
	//GETTERS
	
	public List<SecondData> getLastDatas(int num) {return datas.stream().skip(Math.max(0, datas.size() - num)).collect(Collectors.toList());}
	public SecondData getLastSecondData(){return datas.isEmpty() ? null : datas.get(datas.size() - 1);}
	public float getTimerFloat(String name){return actualSecond.getTimerFloat(name);}
	public long getTimerLong(String name){return actualSecond.getTimerLong(name);}
	public int getTimerInt(String name){return actualSecond.getCounter(name);}
	public int getCounter(String name){return actualSecond.getCounter(name);}
	public double getTimer(String name){return actualSecond.getTimer(name);}
	public List<SecondData> getDatas() {return datas;}
}
