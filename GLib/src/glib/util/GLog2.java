package glib.util;

import java.util.ArrayList;

public class GLog2 {
	public final static boolean EXCEPTION 		= false;
	public final static boolean DEBUG 	  		= false;
	public final static boolean CONSTRUCTORS	= false;
	
	private static ArrayList<GLog2> logs = new ArrayList<GLog2>();
	private String 		text; 
	private Exception 	exception;
	private long 		created = System.currentTimeMillis();;
	
	//CONTRUCTORS
	
	private GLog2(String text, Exception exception){
		this.text = text;
		this.exception = exception;
	}
	
	//OTHERS
	
	public static void write(String text){write(text, null, true);}
	public static void write(String text, boolean value){write(text, null, value);}
	public static void write(String text, Exception exception, boolean value){
		logs.add(new GLog2(text, exception));
		
		if(value){
			System.out.println(text);
			if(exception != null)
				exception.printStackTrace();
		}
	}
	
	public static void printLogs(){
		logs.stream()	
			.sorted((a, b) -> Long.compare(a.created, b.created))
			.map(a -> a.created + ": " + a.text + " = " + a.exception)
			.forEach(System.out::println);
	}
}
