package terrains;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.lwjgl.util.vector.Vector2f;

import main.Game;
import main.Loader;
import renderers.Renderer;
import shaders.StaticShader;

/*
 *  +void initDefault()
 *  +boolean exist(int x,int y, int z)
 *  +void draw(Render render,Shader shader)
 *  +void createTerrain()
 *  +void loadMap()
 *  +void saveMap()
 *  +void add(Block b,int x,int z)
 *  +void remove(int x,int z)
 *  +int getNumBlock()
 *  +void loadMap(File file)
 *  +String saveMap()
 *  +addSesedov()
 *  +fixMap()
 */


public class Map {
	private Stlp[][] mapa;
	private int numX,numZ;
	private Block[][] terrain;
	
	public Map(int x, int z,Loader loader){
		Block.init(loader);
		numX = x;
		numZ = z;
		mapa =  new Stlp[x][z];
	}
	
	public void initDefaultMap(){
		initDefaultMap(numX,numZ);
	}
	
	public void fixMap(){
		for(int i=0 ; i<numX ; i++){
			for(int k=0 ; k<numZ ; k++){
				mapa[i][k].fix();
				
			}
		}
	}
	
	public void setActivity(){
		for(int i=0 ; i<numX ; i++){
			for(int k=0 ; k<numZ ; k++){
				mapa[i][k].setActivity();
			}
		}
	}
	
	public void initDefaultMap(int x, int z){
		numX = x;
		numZ = z;
		mapa =  new Stlp[x][z];
		terrain = new Block[x][z];
		int numY = 16;
		int half = numY/2;
		int j;
		for(int i=0 ; i<numX ; i++){
			for(int k=0 ; k<numZ ; k++){
				int dist = half+((int)(Math.random()*numY/2)-numY/2/2);
				mapa[i][k] = new Stlp(i,k);
				for(j=0 ; j<dist ; j++){
					if(j==0){
						mapa[i][k].add(new Block(i,j, k,Block.DIRT));
						continue;
					}
					mapa[i][k].add(new Block(i,j, k,(int)(Math.random()*Block.maxTypes+1)));
				}
			}
		}
		addSesedov();
		fixMap();
		createTerrain();
	}
	
	public void initMapFromHeighMap(String fileName){
		mapa =  new Stlp[numX][numZ];
		terrain = new Block[numX][numZ];
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("res/" +fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		int size = image.getHeight();
		size /=numX;
		for(int i=0;i<numX;i++){
			for(int k=0;k<numZ;k++){
				int height = new Color(image.getRGB(i*size, k*size)).getRed();
				height /=4;
				mapa[i][k] = new Stlp(i,k);
				for(int j=0 ; j<height ; j++){
					if(j==0){
						mapa[i][k].add(new Block(i,j, k,Block.DIRT));
						continue;
					}
					mapa[i][k].add(new Block(i,j, k,(int)(Math.random()*Block.maxTypes+1)));
				}
			}
		}
		addSesedov();
		fixMap();
		setActivity();
		createTerrain();
		System.out.println("Mapa obsahuje "+getNumBlock()+" kociek");
	}
	
	public void addSesedov(){
		for(int i=0 ; i<numX ; i++){
			for(int k=0 ; k<numZ ; k++){
				if(i>0){
					mapa[i][k].addSuseda(Stlp.WEST, mapa[i-1][k]);
				}
				if(k>0){
					mapa[i][k].addSuseda(Stlp.SOUTH, mapa[i][k-1]);
				}
				if(i<numX-1){
					mapa[i][k].addSuseda(Stlp.EAST, mapa[i+1][k]);
				}
				if(k<numZ-1){
					mapa[i][k].addSuseda(Stlp.NORT, mapa[i][k+1]);
				}
			}
		}
	}
	
	public void add(int x,int z,int type){
		mapa[x][z].add(type);
		updateTerrain(x, z);
	}
	
	public void remove(int x, int z){
		mapa[x][z].removeTop();
		updateTerrain(x, z);
	}
	
	private boolean exist(int x,int y,int z){
		if(x>0&&x<numX&&z>0&&z<numZ){
			return mapa[x][z].exist(y);
		}
		return false;
	}
	
	public Block getTop(Vector2f v){
		return mapa[(int)v.x][(int)v.y].getTop();
	}
	
	private boolean isHide(int x, int y, int z){
		if(exist(x+1,y,z)&&exist(x-1,y,z)&&exist(x,y,z+1)&&exist(x,y,z-1)&&exist(x,y+1,z)){
			return true;
		}
		return false;
	}
	
	public void draw(Renderer renderer, StaticShader shader) {
		for(int i=0 ; i<numX ; i++){
			for(int j=0 ; j<numZ ; j++){
				if(Game.isLoading)
					return;
				if(mapa[i][j]==null){
					return;
				}
				mapa[i][j].draw(renderer, shader);
			}
		}
	}
	
	public void createTerrain(){
		for(int i=0 ; i<numX ; i++){
			for(int j=0 ; j<numZ ; j++){
				terrain[i][j] = mapa[i][j].getTop();
			}
		}
	}
	
	public void updateTerrain(int i, int j){
		terrain[i][j] = mapa[i][j].getTop();
	}
	
	public String saveMap(){
		/*#S = Sizes
		 *#B = Blocks
		 * S numX numZ
		 * 
		 * B x y z type surX surY surZ
		 * B x y z type surX surY surZ
		 */
		String file="#S = Sizes \n#B = Blocks \n\n";
		file +="S "+numX+" "+numZ+"\n\n";
		for(int i=0 ; i<numX ; i++){
			for(int k=0 ; k<numZ ; k++){
				file += mapa[i][k].getSlpToSave();
			}
		}
		return file;
	}

	public void loadMap(File file){
		Game.isLoading = true;
		BufferedReader reader=null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String line;
		
		try {
			while((line = reader.readLine())!=null){
				if(line.startsWith("S")){
					numX = Integer.parseInt(line.split(" ")[1]);
					numZ = Integer.parseInt(line.split(" ")[2]);
					mapa = new Stlp[numX][numZ];
				}
				if(line.startsWith("B")){
					String[] l = line.split(" ");
					int x = Integer.parseInt(l[1]);
					int y = Integer.parseInt(l[2]);
					int z = Integer.parseInt(l[3]);
					int t = Integer.parseInt(l[4]);
					Block block = new Block(x,
											y,
											z,
											t);
					if(mapa[x][z] == null){
						mapa[x][z] = new Stlp(x,z);
					}
					mapa[x][z].add(block);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		createTerrain();
		Game.isLoading = false;
	}

	public Block[][] getTerrain() {
		return terrain;
	}
	
	public int getNumBlock(){
		int result = 0;
		for(int i=0 ; i<numX ; i++){
			for(int j=0 ; j<numZ ; j++){
				result += mapa[i][j].getNum();
			}
		}
		return result;
	}
}