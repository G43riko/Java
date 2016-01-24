package glib.util;

import java.util.ArrayList;
import java.util.List;

public class GLog {
	public final static boolean EXCEPTION 		= false;
	public final static boolean DEBUG 	  		= false;
	public final static boolean CONSTRUCTORS	= false;
	
	private static List<GLog> logs = new ArrayList<GLog>();
	private String 		text;
	private String 		methodName;
	private String 		className;
	private int			lineNumber;
	private Exception 	exception;
	private long 		created = System.currentTimeMillis();;
	
	//CONTRUCTORS
	
	private GLog(String text, Exception exception, int lineNumber, String methodName, String className){
		this.text = text;
		this.exception = exception;
		this.className = className;
		this.methodName = methodName;
		this.lineNumber = lineNumber;
	}
	
	//OTHERS
	
	public static void write(String text){
		write(text, null, true);
	}
	
	public static void write(String text, boolean value){
		write(text, null, value);
	}
	
	public static void write(String text,Exception exception){
		write(text, exception, true);
	}
	
	public static void write(String text, Exception exception, boolean value){
		StackTraceElement[] tracker = Thread.currentThread().getStackTrace();
		StackTraceElement t = tracker[tracker.length - 1];
		
		logs.add(new GLog(text, exception, t.getLineNumber(), t.getMethodName(), t.getClassName()));
		
		if(value){
			System.out.println(text);
			if(exception != null)
				exception.printStackTrace();
		}
	}
	
	public static void printLogs(){
		logs.stream()	
			.sorted((a, b) -> Long.compare(a.created, b.created))
			.forEach(System.out::println);
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(new GDate(created) + " - \"" + text + "\" [ ");
		result.append(className + " > " + methodName + " > " + lineNumber + " ] ");
		if(exception != null)
			result.append(exception);
		return result.toString();
	}
}
