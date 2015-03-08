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
	public final static int WOOD = 5;
	public final static int IRON = 6;
	public final static int BRICK = 7;
	public final static int LAVA = 8;
	public final static int CONCRETE = 9;
	public final static int TILE = 10;
	public final static int PATH = 11;
	
	public static int WIDTH = 1;
	public static int HEIGHT = 1;
	public static int DEPTH = 1;
	public static final HashMap<Integer,JSONObject> blockDatas;
	private static final Model[] model = new Model[6];
	private boolean clickable = true;
	
	private boolean[] sides = new boolean[]{true,true,true,true,true,true};
	static{
		blockDatas = new HashMap<Integer,JSONObject>();
		getBlockData();
		model[0] = getTop();
		model[1] = getRight();
		model[2] = getBottom();
		model[3] = getLeft();
		model[4] = getBack();
		model[5] = getForward();
	}
	
	public Block(GVector3f position, int type) {
		super(position, type);
	}
	
	public Block(JSONObject data){
		super(new GVector3f((float)data.getDouble("posX"),(float)data.getDouble("posY"),(float)data.getDouble("posZ")),data.getInt("typ"));
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
		adding.put("sp", 15);//specularPower
		adding.put("mat",new Material(new Texture2D(adding.getString("name").toLowerCase()+postFix), adding.getInt("si"), adding.getInt("sp")));
		blockDatas.put(GRASS, adding);
		
		adding = new JSONObject();
		adding.put("name", "Dirt");
		adding.put("rX", 1);//repeatY
		adding.put("rY", 1);//repeatX
		adding.put("rep", true);//repeat
		adding.put("t", false);//transparent
		adding.put("si", 0.2);//specularIntensity
		adding.put("sp", 10);//specularPower
		adding.put("mat",new Material(new Texture2D(adding.getString("name").toLowerCase()+postFix), adding.getInt("si"), adding.getInt("sp")));
		blockDatas.put(DIRT, adding);
		
		adding = new JSONObject();
		adding.put("name", "Rock");
		adding.put("rX", 1);//repeatY
		adding.put("rY", 1);//repeatX
		adding.put("rep", true);//repeat
		adding.put("t", false);//transparent
		adding.put("si", 0.5);//specularIntensity
		adding.put("sp", 8);//specularPower
		adding.put("mat",new Material(new Texture2D(adding.getString("name").toLowerCase()+postFix), adding.getInt("si"), adding.getInt("sp")));
		blockDatas.put(ROCK, adding);
		
		adding = new JSONObject();
		adding.put("name", "Water");
		adding.put("rX", 1);//repeatY
		adding.put("rY", 1);//repeatX
		adding.put("rep", true);//repeat
		adding.put("t", true);//transparent
		adding.put("si", 0.7);//specularIntensity
		adding.put("sp", 15);//specularPower
		adding.put("mat",new Material(new Texture2D(adding.getString("name").toLowerCase()+postFix), adding.getInt("si"), adding.getInt("sp")));
		blockDatas.put(WATER, adding);
		
		adding = new JSONObject();
		adding.put("name", "Wood");
		adding.put("rX", 1);//repeatY
		adding.put("rY", 1);//repeatX
		adding.put("rep", true);//repeat
		adding.put("t", false);//transparent
		adding.put("si", 0.1);//specularIntensity
		adding.put("sp", 8);//specularPower
		adding.put("mat",new Material(new Texture2D(adding.getString("name").toLowerCase()+postFix), adding.getInt("si"), adding.getInt("sp")));
		blockDatas.put(WOOD, adding);
		
		adding = new JSONObject();
		adding.put("name", "Iron");
		adding.put("rX", 1);//repeatY
		adding.put("rY", 1);//repeatX
		adding.put("rep", true);//repeat
		adding.put("t", false);//transparent
		adding.put("si", 1);//specularIntensity
		adding.put("sp", 50);//specularPower
		adding.put("mat",new Material(new Texture2D(adding.getString("name").toLowerCase()+postFix), adding.getInt("si"), adding.getInt("sp")));
		blockDatas.put(IRON, adding);
		
		adding = new JSONObject();
		adding.put("name", "Brick");
		adding.put("rX", 1);//repeatY
		adding.put("rY", 1);//repeatX
		adding.put("rep", true);//repeat
		adding.put("t", false);//transparent
		adding.put("si", 0.2);//specularIntensity
		adding.put("sp", 8);//specularPower
		adding.put("mat",new Material(new Texture2D(adding.getString("name").toLowerCase()+postFix), adding.getInt("si"), adding.getInt("sp")));
		blockDatas.put(BRICK, adding);
		
		adding = new JSONObject();
		adding.put("name", "Lava");
		adding.put("rX", 1);//repeatY
		adding.put("rY", 1);//repeatX
		adding.put("rep", true);//repeat
		adding.put("t", false);//transparent
		adding.put("si", 0.1);//specularIntensity
		adding.put("sp", 8);//specularPower
		adding.put("mat",new Material(new Texture2D(adding.getString("name").toLowerCase()+postFix), adding.getInt("si"), adding.getInt("sp")));
		blockDatas.put(LAVA, adding);

		adding = new JSONObject();
		adding.put("name", "Concrete");
		adding.put("rX", 1);//repeatY
		adding.put("rY", 1);//repeatX
		adding.put("rep", true);//repeat
		adding.put("t", false);//transparent
		adding.put("si", 0.5);//specularIntensity
		adding.put("sp", 8);//specularPower
		adding.put("mat",new Material(new Texture2D(adding.getString("name").toLowerCase()+postFix), adding.getInt("si"), adding.getInt("sp")));
		blockDatas.put(CONCRETE, adding);
		
		adding = new JSONObject();
		adding.put("name", "Tile");
		adding.put("rX", 1);//repeatY
		adding.put("rY", 1);//repeatX
		adding.put("rep", true);//repeat
		adding.put("t", false);//transparent
		adding.put("si", 1.001);//specularIntensity
		adding.put("sp", 80);//specularPower
		adding.put("mat",new Material(new Texture2D(adding.getString("name").toLowerCase()+postFix), adding.getInt("si"), adding.getInt("sp")));
		blockDatas.put(TILE, adding);

		adding = new JSONObject();
		adding.put("name", "Path");
		adding.put("rX", 1);//repeatY
		adding.put("rY", 1);//repeatX
		adding.put("rep", true);//repeat
		adding.put("t", false);//transparent
		adding.put("si", 0.2);//specularIntensity
		adding.put("sp", 8);//specularPower
		adding.put("mat",new Material(new Texture2D(adding.getString("name").toLowerCase()+postFix), adding.getInt("si"), adding.getInt("sp")));
		blockDatas.put(PATH, adding);
	}

	public Model getModel(int i) {
		return model[i];
	}

	public void setSide(int side, boolean value){
		sides[side] = value;
		active = sides[0] || sides[1] || sides[2] || sides[3] || sides[4] || sides[5] ;  
	}
	
	public String toString(){
		return "typ: "+blockDatas.get(type).getString("name");
	}
	
	public boolean getSide(int side){
		return sides[side];
	}

	public boolean isClickable() {
		return clickable;
	}

	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
}
