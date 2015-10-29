package org.engine.utils;

import glib.util.GDebug;
import glib.util.vector.GVector2f;

public class Utils {
	public static void sleep(int num){
		try {
			Thread.sleep(num);
		} catch (InterruptedException e) {
			e.printStackTrace();
			GDebug.logError("Nepodarilo sa uspaù vl·kno", "Utils", e);
		}
	}
	
	public static GVector2f invertHorizontali(GVector2f pos, GVector2f parentSize){
		return pos.addToY(-parentSize.getY()).abs();
	}
}
