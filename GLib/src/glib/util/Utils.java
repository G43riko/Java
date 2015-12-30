package glib.util;

import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;



public abstract class Utils {
	public static String getMyIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			GLog2.write("nepodarilo sa zÌskaù adresu localhostu");
		}
		return "Error";
	}
	
	public static void sleep(int ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			GLog2.write("nepodarilo sa uspaù vl·kno");
		}
	}
	
	public static<T> void drawArray(T[] array){
		for(T b : array)
			GLog2.write(b + ", ");

		GLog2.write("");
	}
	
	public static String GenerateString(final int length) {
	    Random r = new Random(); // perhaps make it a class variable so you don't make a new one every time
	    StringBuilder sb = new StringBuilder();
	    for(int i = 0; i < length; i++) 
	        sb.append((char)(r.nextInt((int)(Character.MAX_VALUE))));
	    
	    return sb.toString();
	}
	
	public static<T> T[] concatenateArray (T[] a, T[] b) {
	    int aLen = a.length;
	    int bLen = b.length;

	    @SuppressWarnings("unchecked")
	    T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
	    System.arraycopy(a, 0, c, 0, aLen);
	    System.arraycopy(b, 0, c, aLen, bLen);

	    return c;
	}
}
