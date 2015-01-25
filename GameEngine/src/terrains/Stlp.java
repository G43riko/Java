package terrains;

import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.HTMLReader.BlockAction;

import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector3f;

import entities.Camerka;
import main.Game;
import renderers.Renderer;
import shaders.StaticShader;

/*
 * +void add(Block b)
 * +void add(Block b,int y)
 * +void draw(Renderer renderer, Shader shader)
 * +Block getTop()
 * -void remove()
 */


public class Stlp {
	public final static int NORT = 0;
	public final static int SOUTH = 2;
	public final static int EAST = 1;
	public final static int WEST = 3;
	
	private int numX;
	private int numZ;
	private Stlp[] susedia = new Stlp[4];
	private ArrayList<Block> blocks;
	
	public Stlp(int x, int z){
		blocks = new ArrayList<Block>();
		this.numX = x;
		this.numZ = z;
	}
	
	public void add(Block b){
		blocks.add(b);
	}
	
	public void add(int type){
		blocks.add(new Block(numX,blocks.size(),numZ,type));
	}
	
	public void add(Block b,int y){
		blocks.set(y, b);
	}
	
	public int draw(Renderer renderer, StaticShader shader) {
		int res = 0;
		Vector3f forward = Map.camera.getForward();
		for(Block b:blocks){
			if(b.getType()!=0 && b.isActive()){
				Vector3f pos = new Vector3f(Map.camera.getPosition().x-b.getX(),
											Map.camera.getPosition().y-b.getY(),
											Map.camera.getPosition().z-b.getZ());
				if(Vector3f.angle(pos,forward )<Map.camera.getMaxangle()){
					float dist = Camerka.sqDist(new Vector3f(b.getX(),b.getY(),b.getZ()));
					if(dist<1){
						if(Map.selected.distanceFromVec > dist||Map.selected.distanceFromVec<0){
							Map.selected.distanceFromVec = dist;
							Map.selected.selected = b;
						}
					}
					renderer.render(b, shader);
					res++;
				}		
			}
		}
		return res;
	}
	
	public Block getTop(){
		return blocks.get(blocks.size()-1);
	}
	
	public boolean exist(int y){
		return blocks.contains(y);
	}
	
	public void removeTop(){
		if(blocks.size()>1)
			blocks.remove(blocks.size()-1);
	}
	
	public int getNum(){
		return blocks.size();
	}
	
	public int getHeight(){
		return blocks.size();
	}
	
	public void fix(){
		for(int i=0 ; i<blocks.size() ; i++){
			Block b = blocks.get(i);
			if(b.getType()==Block.WATER && i>0 && i<blocks.size()-1){
				b.setType(Block.WATER+Math.random()<0.5?-1:+1);
			}
			else if(b.getType()==Block.WATER){
				boolean maze = false;
				for(int j=0 ; j<4 ; j++){
					if(susedia[j]!=null){
						if(susedia[j].getHeight()<i+1){
							maze = true;
							break;
						}
					}
					else{
						maze = true;
						break;
					}
				}
				if(maze){
					b.setType(Block.WATER+Math.random()<0.5?-1:+1);
				}
			}
			if(i+1==blocks.size() && b.getType() != Block.GRASS && b.getType() != Block.WATER){
				b.setType(Block.GRASS);
			}
		}
	}
	
	public void addSuseda(int kde,Stlp kto){
		susedia[kde] = kto;
	}
	
	public String getSlpToSave(){
		String result = "";
		for(Block b:blocks){
			result += "B "+b.getSurX()+" "+b.getSurY()+" "+b.getSurZ()+" "+b.getType()+"\n";
		}
		return result;
	}

	public void setActivity() {
		for(int i=0 ; i<blocks.size() ; i++){
			Block b = blocks.get(i);
			boolean active = false;
			for(int j=0 ; j<4 ; j++){
				if(susedia[j]!=null){
					if(susedia[j].getHeight()<i+1){
						active = true;
						break;
					}
				}
				else{
					active = true;
					break;
				}
			}
			if(!active && i+1<blocks.size()){
				b.setActive(false);
			}
			else{
				b.setActive(true);
			}
		}
	}

}
