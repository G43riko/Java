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

public class Model {
	private static InputStream loadData(String fileName){
		InputStream input = ResourceLoader.class.getResourceAsStream(fileName);	//pohlad� subor kde by sa mal nach�dza�
		if(input == null){	//ak ho nen�jde
			input = ResourceLoader.class.getResourceAsStream("/"+fileName);	//pohlad� ho v prie�inku kde by sa mal nach�dza�	
		}
		return input;	//vr�ti �o sa na�lo
	}
	
	public static HashMap<Byte, Integer> loadData(Village village){
		String line = null;
		HashMap<Byte, Integer> result = new HashMap<Byte, Integer>();
		
		try {
			BufferedReader reader =  new BufferedReader(new InputStreamReader(loadData("data.txt"), "UTF-8"));	//otvor� s�bor
			while((line = reader.readLine()) != null){	//pokial sa d� zo s�boru ��ta� a neobsahuje len pr�zdne riadky
				String[] currentLine = line.split(" ");	//rozdel�me riadok na podla medzier
				
				byte buildingType = Byte.parseByte(currentLine[0]);
				if(buildingType == Buildings.OBCHOD){	//ak su to informacie o tovare v obchode
					village.getMarket().addResource(Byte.valueOf(currentLine[1]), Integer.valueOf(currentLine[2]), true);	//pr�dame ich do obchodu
				}
				else	//in��
					village.getBuilding(buildingType).addResource(Byte.valueOf(currentLine[1]), Integer.valueOf(currentLine[2]));	//pr�dame suroviny budove
			}
			reader.close();	//zavrieme s�bor
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return result;
	}

	private static void saveData(String data){
		File fileToSave = new File("res/data.txt");
		PrintStream file = null;
		try {
			file = new PrintStream(fileToSave);
			file.print(data);
			
		} catch (FileNotFoundException e1) {
			System.out.println("lutujeme ale ulo�enie nebolo �spe�n�");
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
}
