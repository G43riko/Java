package com.voxel.world;

import glib.util.vector.GVector3f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import org.json.JSONException;
import org.json.JSONObject;

import com.voxel.rendering.material.Material;
import com.voxel.rendering.material.Texture;

public interface BlockInfo {
	public final static int WIDTH = 1;
	public final static int HEIGHT = 1;
	public final static int DEPTH = 1;
	
	public final static int VOID = 0;
	public final static int GRASS = 1;
	public final static int DIRT = 2;
	public final static int ROCK = 3;
	public final static int WATER = 4;
	
	public static JSONObject DATA = loadData();
	public final static BlockType[] BLOCKS = new BlockType[]{new BlockType(0,null,null,null,false),
															 makeBlockType(1,"Grass"),
															 makeBlockType(2,"Dirt"),
															 makeBlockType(3,"Rock"),
															 makeBlockType(4,"Water")};
	
	public static void saveData(){
		String basePath = (new File(".")).getAbsolutePath();
		File fileToSave = new File(basePath+basePath.substring(0, basePath.length()-1));
		try {
			PrintStream file = new PrintStream(fileToSave);
			file.println(DATA.toString());
			file.close();
		} catch (FileNotFoundException e1) {e1.printStackTrace(); }
	}
	
	public static JSONObject loadData(){
		String basePath = (new File(".")).getAbsolutePath();
		File fileToSave = new File(basePath.substring(0, basePath.length()-1)+"res\\datas\\blockInfos.txt");
		try {
			FileReader reader = new FileReader(fileToSave);
			JSONObject result = new JSONObject(new BufferedReader(reader).readLine());
			reader.close();
			return result;
		} catch (JSONException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();}
		System.out.println(basePath.substring(0, basePath.length()-1)+"res\\datas\\blockInfo.txt");
		return null;
	}
	
	public static double getBlockInfo(int type, String key){
		return DATA.getJSONObject(String.valueOf(type)).getDouble(key);
	}
	
	public static JSONObject getBlockInfo(int type){
		return DATA.getJSONObject(String.valueOf(type));
	}
	
	public static BlockType makeBlockType(int id, String name){
		JSONObject things =  DATA.getJSONObject(String.valueOf(1));
		GVector3f color = new GVector3f(things.getDouble("colorX"),things.getDouble("colorY"),things.getDouble("colorZ"));
		Material material = new Material("diffuse", new Texture(name.toLowerCase()+".jpg"));
		return new BlockType(1,name,color, material,things.getInt("transparent")==1);
	}
	
//	public static JSONObject loadAllBlockData(){
//		JSONObject result = new JSONObject();
//		
//		JSONObject grass = new JSONObject();
//		grass.put("repX", 1);
//		grass.put("repY", 1);
//		grass.put("repeat", 1);
//		grass.put("transparent", 0);
//		grass.put("colorX", 0);
//		grass.put("colorY", 255);
//		grass.put("colorZ", 0);
//		grass.put("specular", 0.1);
//		grass.put("exponent", 8);
//		
//		JSONObject dirt = new JSONObject();
//		dirt.put("repX", 1);
//		dirt.put("repY", 1);
//		dirt.put("repeat", 1);
//		dirt.put("transparent", 0);
//		dirt.put("colorX", 255);
//		dirt.put("colorY", 0);
//		dirt.put("colorZ", 0);
//		dirt.put("specular", 0.2);
//		dirt.put("exponent", 8);
//		
//		JSONObject rock = new JSONObject();
//		rock.put("repX", 1);
//		rock.put("repY", 1);
//		rock.put("repeat", 1);
//		rock.put("transparent", 0);
//		rock.put("colorX", 155);
//		rock.put("colorY", 155);
//		rock.put("colorZ", 155);
//		rock.put("specular", 0.5);
//		rock.put("exponent", 8);
//		
//		JSONObject water = new JSONObject();
//		water.put("repX", 1);
//		water.put("repY", 1);
//		water.put("repeat", 1);
//		water.put("transparent", 1);
//		water.put("colorX", 0);
//		water.put("colorY", 0);
//		water.put("colorZ", 255);
//		water.put("specular", 1);
//		water.put("exponent", 8);
//		
//		result.put(String.valueOf(GRASS), grass);
//		result.put(String.valueOf(DIRT), dirt);
//		result.put(String.valueOf(ROCK), rock);
//		result.put(String.valueOf(WATER), water);
//		
//		return result;
//	}
}
