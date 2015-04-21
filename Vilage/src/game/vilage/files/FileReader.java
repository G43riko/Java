package game.vilage.files;

import game.vilage.Village;
import game.vilage.buldings.BasicBuilding;
import game.vilage.buldings.Buildings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map.Entry;

import util.ResourceLoader;

public class FileReader {
	private static HashMap<Byte, String> loadedBuildings = loadBuildingDatas();
	
	private static InputStream loadData(String fileName){
		InputStream input = ResourceLoader.class.getResourceAsStream(fileName);	//pohlad· subor kde by sa mal nach·dzaù
		if(input == null){	//ak ho nen·jde
			input = ResourceLoader.class.getResourceAsStream("/"+fileName);	//pohlad· ho v prieËinku kde by sa mal nach·dzaù	
		}
		return input;	//vr·ti Ëo sa naölo
	}
	
	public static HashMap<Byte, Integer> loadData(Village village){
		String line = null;
		HashMap<Byte, Integer> result = new HashMap<Byte, Integer>();
		
		try {
			BufferedReader reader =  new BufferedReader(new InputStreamReader(loadData("data.vlg"), "UTF-8"));	//otvor˝ s˙bor
			while((line = reader.readLine()) != null){	//pokial sa d· zo s˙boru ËÌtaù a neobsahuje len pr·zdne riadky
				String[] currentLine = line.split(" ");	//rozdelÌme riadok na podla medzier
				
				byte buildingType = Byte.parseByte(currentLine[0]);
				if(buildingType == Buildings.OBCHOD){	//ak su to informacie o tovare v obchode
					village.getMarket().addResource(Byte.valueOf(currentLine[1]), Integer.valueOf(currentLine[2]), true);	//prÌdame ich do obchodu
				}
				else	//in·Ë
					village.getBuilding(buildingType).addResource(Byte.valueOf(currentLine[1]), Integer.valueOf(currentLine[2]));	//prÌdame suroviny budove
			}
			reader.close();	//zavrieme s˙bor
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return result;
	}

	private static void saveData(String data){
		File fileToSave = new File("res/data.vlg");
		PrintStream file = null;
		try {
			file = new PrintStream(fileToSave);
			file.print(data);
			
		} catch (FileNotFoundException e1) {
			System.out.println("lutujeme ale uloûenie nebolo ˙speönÈ");
			e1.printStackTrace();
		}
		file.close();
	}
	
	public static void saveData(Village village) {
		String data = "";
		for(Entry<Byte, BasicBuilding> e : village.getBuildings().entrySet()){
			data += e.getValue().toFile();
		}
		data += village.getMarket().toFile();
		saveData(data);
	}

	public static void getResourceDatas(HashMap<Byte, String> names, HashMap<Byte, Byte> producers){
		String line = null;
		try {
			BufferedReader reader =  new BufferedReader(new InputStreamReader(loadData("resources.vlg"), "UTF-8"));	//otvor˝ s˙bor
			while((line = reader.readLine()) != null){	//pokial sa d· zo s˙boru ËÌtaù a neobsahuje len pr·zdne riadky
				String[] currentLine = line.split("-");	//rozdelÌme riadok na podla medzier
				byte resource = Byte.parseByte(currentLine[0].trim());
				names.put(resource, String.valueOf(currentLine[1].trim()));
				producers.put(resource, Byte.parseByte(currentLine[2].trim()));
				
			}
			reader.close();	//zavrieme s˙bor
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private static HashMap<Byte, String> loadBuildingDatas(){
		HashMap<Byte, String> res = new HashMap<Byte, String>();
		String line = null;
		try {
			BufferedReader reader =  new BufferedReader(new InputStreamReader(loadData("buildings.vlg"), "UTF-8"));	//otvor˝ s˙bor
			while((line = reader.readLine()) != null){	//pokial sa d· zo s˙boru ËÌtaù a neobsahuje len pr·zdne riadky
				String[] currentLine = line.split("-");	//rozdelÌme riadok na podla medzier
				byte building = Byte.parseByte(currentLine[0].trim());
				res.put(building, line);
			}
			reader.close();	//zavrieme s˙bor
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return res;
	}

	public static void getBuildingData(byte type, String name, HashMap<Byte, Integer> requiered, HashMap<Byte, Integer> produced){
		try{
		String line = loadedBuildings.get(type);
		String[] currentLine = line.split("-");	//rozdelÌme riadok na podla medzier
		name = String.valueOf(currentLine[1].trim());
		
		String[] subpart = currentLine[2].split(":");
		produced.put(Byte.parseByte(subpart[0].trim()), Integer.parseInt(subpart[1].trim()));
		subpart = currentLine[3].split(",");
		for(String produce : subpart){
			String[] need = produce.split(":");
			requiered.put(Byte.parseByte(need[0].trim()), Integer.parseInt(need[1].trim()));
		}
		}
		catch(NumberFormatException e){
			return;
		}
	}
}
