package Helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import Main.Constants;

public class FileLoader {
	public static String getMeno(){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("./res/names.txt"));
			String line;
			int i = 0;
			int target = (int)Math.floor(Math.random()*Constants.MIEN_V_ZOZNAME);
			while((line = reader.readLine()) != null){
				i++;
				if(i==target){
					return line;
				}
			}
			reader.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}