package glib.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;


import glib.util.GLog;
import glib.util.ResourceLoader;

public class SimpleStringData {
	public static final String DEFAUL_BIN_PATH = "bin/";
	
	private HashMap<String, String> data = new HashMap<String, String>();
	private String fileName;
	
	public SimpleStringData(String fileName){
		this.fileName = fileName;
		loadData();
	}
	
	private void loadData(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceLoader.load(fileName)));
		String line;
		
		try {
			while ((line = reader.readLine()) != null) {    
			    String datas[] = line.replace(" ", "").split("=");
			    data.put(datas[0], datas[1]);
			}
		} catch (IOException e) {
			GLog.write("nastala chyba pri naËÌtavanÌ profilu",e , true);
			e.printStackTrace();
		} 
	}
	
	public String get(String name){
		return data.get(name);
	}
	
	public String set(String name, String value){
		return data.put(name, value);
	}
	
	public boolean contains(String name){
		return data.containsKey(name);
	}
	
	public void saveData(){
		try {
			File file = new File(DEFAUL_BIN_PATH + fileName);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(Entry<String, String> line : data.entrySet()){
				bw.write(line.getKey() + "=" + line.getValue());
				bw.newLine();
			}
			fw.close();
            bw.close();
		} catch (IOException e) {
			GLog.write("nepodarilo sa uloûiù profil do s˙boru" + fileName, e, true);
		}
	}
}
