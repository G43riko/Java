package glib.util;

import java.io.File;

public class Loader {
	public static File loadFile(String adrress){
		File file = new File(adrress);
		
		if(!file.exists()){
			GLog2.write("neotvorilo sa : " + adrress);
			file = new File(adrress.replaceAll("res/", ""));
			if(!file.exists())
				GLog2.write("neotvorilo sa : " + adrress);
			else
				GLog2.write("otvorilo sa na druhý krát: " + adrress);
		}
		else 
			GLog2.write("otvorilo sa : "+adrress);
		return file;
	}
}
