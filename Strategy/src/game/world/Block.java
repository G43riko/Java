package game.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

import org.json.JSONObject;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import game.components.Player;
import game.rendering.RenderingEngine;
import game.rendering.material.Material;
import game.rendering.material.Texture2D;
import game.rendering.model.Model;
import game.util.Maths;
import glib.math.GMath;
import glib.util.vector.GMatrix4f;
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

//	private ArrayList<Block> dependOn = new ArrayList<Block>();
	private LinkedHashSet<Block> connection = new LinkedHashSet<Block>();
	private Block[] neightboars = new Block[6];
	public static int WIDTH = 1;
	public static int HEIGHT = 1;
	public static int DEPTH = 1;
	private World world;
	public static final HashMap<Integer,JSONObject> blockDatas;
	private static final Model[] model = new Model[6];
	private GVector3f direction = new GVector3f();
//	private boolean fall = false;
	private boolean clickable = true;
	private GVector3f relativePos = new GVector3f();
	
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
		direction = new GVector3f();
	}
	
	public void update(){
		if(!direction.isNull()){
			relativePos = relativePos.add(direction);
			if(direction.getY() > -Player.MAX_FALLING_SPEED);
				direction = direction.add(Player.GRAVITY);
			Block imp = world.getBlock(getPosition().add(relativePos).sub(new GVector3f(0,Block.HEIGHT,0)));
			if((imp) != null){
				world.blockInpact(this,imp);
			}
//			if(relativePos.getY()<-2*Block.HEIGHT){
////				setPosition(position)
//			}
		}
	}
	
	private void startFalling(){
		if(!direction.isNull())
			return;
		
//		world.remove(this,false);
		clickable = false;
		direction = new GVector3f(0,-0.001,0);
	}
	
//	private void stopFalling(){
//		direction = new GVector3f();
//	}
	
	public Block(JSONObject data){
		super(new GVector3f((float)data.getDouble("posX"),(float)data.getDouble("posY"),(float)data.getDouble("posZ")),data.getInt("typ"));
		direction = new GVector3f();
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
//		if(getBlockType()>0 && sides[0] && sides[1] && sides[2] && sides[3] && sides[4] && sides[5] && direction.isNull() && world != null && world.isRunning()){
//			startFalling();
//		}
//		else
//			stopFalling();
	}
	
	public String toString(){
		return "typ: "+blockDatas.get(type).getString("name");
	}
	
	public boolean checkFall(ArrayList<Block> b, int level){
		if(level<=0)
			return true;
		for(int i=0 ; i<6 ; i++){
			if(neightboars[i] != null){
				if(!b.contains(neightboars[i])){
					b.add(neightboars[i]);
					if(neightboars[i].checkFall(b, level-1))
						return true;
				}
			}
		}
		return false;
	}
	
	public boolean getSide(int side){
		return sides[side];
	}

	public boolean isClickable() {
		return clickable;
	}

	public GMatrix4f getTransformationMatrix(){
		Matrix4f trans = Maths.createTransformationMatrix(getPosition().add(relativePos),getRotation(), getScale());
		return Maths.MatrixToGMatrix(trans);
	}
	
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}

	public World getWorld() {
		return world;
	}

	public void setNeighboard(int i, Block val){
		neightboars[i] = val;
//		if(val==null)
//			return;
//		val.getConnection().addAll(connection);
//		connection.addAll(val.getConnection());
	}
	
	public void removeNeighboard(Block b){
		for(int i=0 ; i<6 ; i++){
			if(neightboars[i] == b){
				neightboars[i] = null;
				return;
			}
		}
	}
	
	public GVector3f getRelativePos() {
		return relativePos;
	}

	
//	public LinkedHashSet<Block> getConnection() {
//		return connection;
//	}

	public void remove(Chunk3D c) {
		for(int i=0 ; i<6 ; i++){
			Block b = neightboars[i];
			if(b!=null){
				b.removeNeighboard(this);
				if(b.checkFall())
					b.startFalling();
				ArrayList<Block> collizable = new ArrayList<Block>();
				if(!b.checkFall(collizable,30)){
					for(int j=0 ; j<collizable.size() ; j++){
						Block n = collizable.get(j);
						n.startFalling();
//						GVector3f pos = world.getPosFromBlock(n);
//						c.set(pos, null);
//						c.set(pos.add(new GVector3f(0,-1,0)), n);
//						world.set
					}
				}
			}
		}
	}

//	private boolean hasNeigboardExcept(Block ...b){
//		int size = b.length;
//		for(int i=0 ; i<6 ; i++){
//			if(neightboars[i]!=null){
//				for(int j=0 ; j<size ; j++){
//					if(neightboars[i] == b[j])
//						return true;
//				}
//			}	
//		}
//		return false;
//	}
	
	private boolean checkFall() {
		for(int i=0 ; i<6 ; i++){
			if(neightboars[i]!=null)
				return false;
			
		}
		return true;
	}
}
