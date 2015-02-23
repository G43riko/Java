package glib.util;

import java.io.File;

public class Loader {
	public static File loadFile(String adrress){
		File file = new File(adrress);
		
		if(!file.exists()){
			System.out.println("neotvorilo sa : "+adrress);
			file = new File(adrress.replaceAll("res/", ""));
			if(!file.exists())
				System.out.println("neotvorilo sa : "+adrress);
			else 
				System.out.println("otvorilo sa na druhý krát: "+adrress);
		}
		else 
			System.out.println("otvorilo sa : "+adrress);
		return file;
	}
}
