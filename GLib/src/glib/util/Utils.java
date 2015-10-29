package glib.util;

import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.UnknownHostException;



public abstract class Utils {
	public static String getMyIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			GLog.write("nepodarilo sa zÌskaù adresu localhostu");
		}
		return "Error";
	}
	
	public static void sleep(int ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			GLog.write("nepodarilo sa uspaù vl·kno");
		}
	}
	
	public static<T> void drawArray(T[] array){
		for(T b : array)
			System.out.print(b + ", ");
		
		System.out.println("");
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
