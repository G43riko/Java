package terrains;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
 *  -void loadMap(File file)
 *  -String saveMap()
 *  
 */


public class Map {
	private Stlp[][] mapa;
	private int numX,numZ;
	private Block[][] terrain;
	
	public Map(int x,int y, int z,Loader loader){
		Block.init(loader);
		numX = x;
		numZ = z;
		mapa =  new Stlp[x][z];
		terrain = new Block[x][z];
	}
	
	public void initDefaultMap(){
		int numY = 4;
		int half = numY/2;
		int j;
		for(int i=0 ; i<numX ; i++){
			for(int k=0 ; k<numZ ; k++){
				int dist = half+((int)(Math.random()*numY/2)-numY/2/2);
				mapa[i][k] = new Stlp(i,k);
				for(j=0 ; j<dist ; j++){
					mapa[i][k].add(new Block(i,j, k,1));
				}
			}
		}
		createTerrain();
	}
	
	public void add(int x,int z,int type){
		mapa[x][z].add(type);
	}
	
	public void remove(int x, int z){
		mapa[x][z].removeTop();
	}
	
	private boolean exist(int x,int y,int z){
		if(x>0&&x<numX&&z>0&&z<numZ){
			return mapa[x][z].exist(y);
		}
		return false;
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
	
	public String saveMap(){
		return null;
	}

	public void loadMap(File file){}

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

//	public String saveMap(){
//		/*#S = Sizes
//		 *#B = Blocks
//		 * S numX numY numZ
//		 * 
//		 * B x y z type surX surY surZ
//		 * B x y z type surX surY surZ
//		 */
//		String file="#S = Sizes \n#B = Blocks \n\n";
//		file +="S "+numX+" "+numY+" "+numZ+"\n\n";
//		
//		for(int i=0 ; i<numX ; i++){
//			for(int j=0 ; j<numY ; j++){
//				for(int k=0 ; k<numZ ; k++){
//					file += "B "+mapa[i][j][k].getSurX()+" "+mapa[i][j][k].getSurY()+" "+mapa[i][j][k].getSurZ()+" "+mapa[i][j][k].getType()+"\n";
//				}
//			}
//		}
//		return file;
//	}
//	
//	public void loadMap(File file){
//		Game.isLoading = true;
//		BufferedReader reader=null;
//		try {
//			reader = new BufferedReader(new FileReader(file));
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		String line;
//		
//		try {
//			while((line = reader.readLine())!=null){
//				if(line.startsWith("S")){
//					numX = Integer.parseInt(line.split(" ")[1]);
//					numY = Integer.parseInt(line.split(" ")[2]);
//					numZ = Integer.parseInt(line.split(" ")[3]);
//					mapa = new Block[numX][numY][numZ];
//				}
//				if(line.startsWith("B")){
//					String[] l = line.split(" "); 
//					Block block = new Block(Integer.parseInt(l[1]),Integer.parseInt(l[2]),Integer.parseInt(l[3]),Integer.parseInt(l[4]));
//					mapa[Integer.parseInt(l[1])][Integer.parseInt(l[2])][Integer.parseInt(l[3])] = block;
//				}
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		createTerrain();
//		Game.isLoading = false;
//	}