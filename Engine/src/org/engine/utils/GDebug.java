package org.engine.utils;

import java.util.ArrayList;

public class GDebug {
	private static ArrayList<GDebug> logs = new ArrayList<GDebug>();
	
	private String text;
	private String object;
	private long time;
	
	public GDebug(String text, String object) {
		this.text = text;
		this.object = object;
		time = System.currentTimeMillis();
	}

	public static void logError(String text, String object){
		logs.add(new GDebug(text, object));
	}
}
