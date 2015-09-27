package org.engine.utils;

public class GLog {
	public final static boolean	INIT		= true;
	public final static boolean	PERFORMANCE	= true;
	
	public static void write(boolean write, String text){
		if(write)
			System.out.println(text);
	}
}
