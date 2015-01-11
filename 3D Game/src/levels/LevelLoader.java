package levels;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

import renderEngine.Loader;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import entities.Entity;

public class LevelLoader {
	public static void loadLevel(String levelName, Loader loader,List scene,List terrains){
		List scena = new ArrayList<Entity>();
		
		FileReader fr = null;
		try {
			fr = new FileReader(new File("src/Levels/"+levelName+".txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Nemoûem naËÌtaù obj s˙bor: "+e);
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(fr);
		String line;
		try {
			RawModel model;
			TexturedModel staticModel;
			Terrain terrain;
			while((line = reader.readLine())!=null){
				if(line.startsWith("e ")){
					String[] currentLine = line.split(" ");
					
					model = OBJLoader.loadObjModel(currentLine[1], loader);
					staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture(currentLine[2])));
					Entity entity = new Entity(staticModel,	new Vector3f(Float.parseFloat(currentLine[3]),
																		 Float.parseFloat(currentLine[4]),
																		 Float.parseFloat(currentLine[5])),
																		 	Float.parseFloat(currentLine[6]),
																		 	Float.parseFloat(currentLine[7]),
																		 	Float.parseFloat(currentLine[8]),
																		 	Float.parseFloat(currentLine[9]));
					
					if(Float.parseFloat(currentLine[10])==1){
						entity.getModel().getTexture().setHasTransparency(true);
					}
					if(Float.parseFloat(currentLine[11])==1){
						entity.getModel().getTexture().setUseFakeLighting(true);
					}
					scene.add(entity);
				}
//				else if(line.startsWith("t ")){
//					String[] currentLine = line.split(" ");
//					terrain = new Terrain(Integer.valueOf(currentLine[1]),
//												  Integer.valueOf(currentLine[2]),loader,new ModelTexture(loader.loadTexture(currentLine[3])));
//					terrains.add(terrain);
//				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
