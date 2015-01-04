package terrains;

import java.util.ArrayList;

import main.Game;
import renderers.Renderer;
import shaders.StaticShader;

public class Stlp {
	private int numX;
	private int numZ;
	private ArrayList<Block> blocks;
	
	public Stlp(int x, int z){
		blocks = new ArrayList<Block>();
		this.numX = x;
		this.numZ = z;
	}
	
	public void add(Block b){
		blocks.add(b);
	}
	
	public void add(Block b,int y){
		blocks.set(y, b);
	}
	
//	public void add(int y,int type){
//		blocks.add(new Block(x,y,z,type));
//	}
	
	public void draw(Renderer renderer, StaticShader shader) {
		for(Block b:blocks){
			renderer.render(b, shader);
		}
	}
	
	
}
