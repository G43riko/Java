package glib.util;

import java.util.HashMap;

public class GLog {
	private static HashMap<String,Boolean> data = new HashMap<String,Boolean>();
	private static boolean showAll = false;
	private static boolean hideAll = false;
	private boolean show = true;
	static{
		data.put("mainLoop",true);
		data.put("menu",true);
		data.put("updateUniforms",true);
		
	}
	public void log(String msg){
		if(show){
			System.out.println(msg);
		}
	}
	
	public static void write(String msg){
		if(hideAll)
			return;
		if(showAll)
			System.out.println(msg);
	}
	
	public static void write(String msg, String name){
		if(hideAll)
			return;
		if(data.get(name)||showAll){
			System.out.println(msg);
		}
	}
	
	public static void set(String name,boolean value){
		data.put(name, value);
	}
	
	public static void change(String name){
		if(data.get(name))
			data.put(name, false);
		else
			data.put(name, true);
	}

	public void setShow(boolean show) {
		this.show = show;
	}
}
