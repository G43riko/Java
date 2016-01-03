package glib.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public final class Utils {
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
	    Random r = new Random();
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

	public static BufferedImage deepCopy(BufferedImage bi) {
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
	public static GColor calcAverageColor(BufferedImage bi, int x0, int y0, int w, int h) {
	    int x1 = x0 + w;
	    int y1 = y0 + h;
	    
	    GVector3f sum = new GVector3f();
	    
	    for (int x = x0; x < x1; x++) 
	        for (int y = y0; y < y1; y++) {
	            Color pixel = new Color(bi.getRGB(x, y));
	            sum = sum.add(new GVector3f(pixel.getRed(), pixel.getGreen(), pixel.getBlue()));
	        }
	    
	    sum = sum.div(w * h);
	    return new GColor(sum.getX(), sum.getY(), sum.getZ());
	}

	public static GVector2f invertHorizontali(GVector2f pos, GVector2f parentSize){
		return pos.addToY(-parentSize.getY()).abs();
	}

	public static int[] toIntArray(Integer[] data) {
		int[] result = new int[data.length];
		
		for(int i=0 ; i<data.length ; i++)
			result[i] = data[i].intValue();
		
		return result;
	}
}
