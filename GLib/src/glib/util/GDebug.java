package glib.util;

import java.util.ArrayList;

public class GDebug {
	private static ArrayList<GDebug> logs = new ArrayList<GDebug>();
	
	private String text;
	private String object;
	private long time;
	private Exception exception;
	
	public GDebug(String text, String object, Exception exception) {
		this.text = text;
		this.object = object;
		this.exception = exception;
		time = System.currentTimeMillis();
	}

	public static void logError(String text, String object){
		logs.add(new GDebug(text, object, null));
	}
	
	public static void logError(String text, String object, Exception exception){
		logs.add(new GDebug(text, object, exception));
	}
}
