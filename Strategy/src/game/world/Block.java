package game.world;

import java.util.HashMap;

import org.json.JSONObject;

import game.rendering.RenderingEngine;
import game.rendering.material.Material;
import game.rendering.material.Texture2D;
import game.rendering.model.Model;
import glib.util.vector.GVector3f;

public class Block extends BasicBlock{
	public final static int VOID = 0;
	public final static int GRASS = 1;
	public final static int DIRT = 2;
	public final static int ROCK = 3;
	public final static int WATER = 4;
	
	public static final int WIDTH = 1;
	public static final int HEIGHT = 1;
	public static final int DEPTH = 1;
	public static final HashMap<Integer,JSONObject> blockDatas;
	private static final Model model;
	
	public boolean[] sides = new boolean[]{false,false,false,false,false,false};
//	public boolean[] sides = new boolean[]{true,true,true,true,true,true};
	static{
		blockDatas = new HashMap<Integer,JSONObject>();
		getBlockData();
		model = getSide();
	}
	
	public Block(GVector3f position, int type) {
		super(position, type);
	}
	
	public void render(RenderingEngine renderingEngine) {
		renderingEngine.renderBlock(this);
	}

	
	public static void getBlockData(){
		String postFix = "_64.jpg";
		JSONObject adding = new JSONObject();
		adding.put("name", "Grass");
		adding.put("rX", 1);//repeatY
		adding.put("rY", 1);//repeatX
		adding.put("rep", true);//repeat
		adding.put("t", false);//transparent
		adding.put("si", 0.1);//specularIntensity
		adding.put("sp", 8);//specularPower
		adding.put("mat",new Material(new Texture2D(adding.getString("name").toLowerCase()+postFix), adding.getInt("si"), adding.getInt("sp")));
		blockDatas.put(GRASS, adding);
		
//		adding = new JSONObject();
//		adding.put("name", "Dirt");
//		adding.put("rX", 1);//repeatY
//		adding.put("rY", 1);//repeatX
//		adding.put("rep", true);//repeat
//		adding.put("t", false);//transparent
//		adding.put("si", 0.2);//specularIntensity
//		adding.put("sp", 8);//specularPower
//		adding.put("mat",new Material(new Texture2D(adding.getString("name")+postFix), adding.getInt("si"), adding.getInt("sp")));
//		blockDatas.put(DIRT, adding);
//		
//		adding = new JSONObject();
//		adding.put("name", "Rock");
//		adding.put("rX", 1);//repeatY
//		adding.put("rY", 1);//repeatX
//		adding.put("rep", true);//repeat
//		adding.put("t", false);//transparent
//		adding.put("si", 0.5);//specularIntensity
//		adding.put("sp", 8);//specularPower
//		adding.put("mat",new Material(new Texture2D(adding.getString("name")+postFix), adding.getInt("si"), adding.getInt("sp")));
//		blockDatas.put(ROCK, adding);
//		
//		adding = new JSONObject();
//		adding.put("name", "Water");
//		adding.put("rX", 1);//repeatY
//		adding.put("rY", 1);//repeatX
//		adding.put("rep", true);//repeat
//		adding.put("t", true);//transparent
//		adding.put("si", 1);//specularIntensity
//		adding.put("sp", 8);//specularPower
//		adding.put("mat",new Material(new Texture2D(adding.getString("name")+postFix), adding.getInt("si"), adding.getInt("sp")));
//		blockDatas.put(WATER, adding);
	}

	
	public Model getModel() {
		return model;
	}
}
