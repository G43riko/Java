package terrains;

import org.lwjgl.util.vector.Vector3f;

public class Block {
	public static int WIDTH = 3;
	public static int HEIGHT = 1;
	public static int DEPTH = 3;
	
	private int x, y, z;
	private int type;
	
	public Block(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
		type = 0;
	}
	
	public Block(int x, int y, int z,int type){
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = type;
	}
}
