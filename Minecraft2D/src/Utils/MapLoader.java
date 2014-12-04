package Utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Components.Block;
import Main.Main;


public class MapLoader {
	private File file;
	private BufferedReader reader;
	private BufferedWriter writer;
	public static  String INFO;
	
	public MapLoader(String adresa){
		this.file=new File(adresa);
	}
	
	private void loadReader(){
		try {
			this.reader = new BufferedReader(new FileReader(this.file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void loadWritter(){
		try {
			this.writer = new BufferedWriter( new FileWriter( this.file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Block> loadMap(){
		ArrayList<Block> vys = new ArrayList<Block>();
		this.loadReader();
		try {
			MapLoader.INFO = this.reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] riadok = INFO.split(" ");
		boolean isThereLine=true;
		System.out.println("sirka: "+riadok[0]+"\nvyska: "+riadok[1]+"\nvelkost: "+riadok[2]);
		int i=1,max=Integer.parseInt(riadok[0])*Integer.parseInt(riadok[1]);
		while(isThereLine){
			try {
				MapLoader.INFO = this.reader.readLine();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			String[] line = MapLoader.INFO.split(" ");
			Block test = new Block(Integer.parseInt(line[2]),Integer.parseInt(line[0]),Integer.parseInt(line[1]));
			test.setLightnes(Integer.parseInt(line[4]));
			test.setHealt(Integer.parseInt(line[3]));
			vys.add(test);
			i++;
			if(i==max){
				break;
			}
		}
		return vys;
	}

	public void saveMap() {
		this.loadWritter();
		String save = Main.mapa.Mapa.length+" "+Main.mapa.Mapa[0].length+" "+Block.size+"\n\r";
		try {
			this.writer.write(save);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int num=0;
		for(int i=0 ; i<Main.mapa.Mapa.length ; i++){
			for(int j=0 ; j<Main.mapa.Mapa[i].length ; j++){
				String string=i+" "+j+" "+Main.mapa.Mapa[i][j].type+" "+Main.mapa.Mapa[i][j].getHealt()+" ";
				string+=Main.mapa.Mapa[i][j].lightnes+" "+Main.mapa.Mapa[i][j].name;
				try {
					this.writer.write(num+"="+string+"\n");
					num++;
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		System.out.println("bolo zapísanych "+num+" položiek");
	}
}
/*

WIDTH HEIGHT BLOCKSIZE \n\r
X Y TYPE HEALT LIGHTNESS \n\r
//X Y TYPE R G B A HEALT LIGHTNESS NAME DESTRUCTION COLLISION FALLING\n\r
 
 
 */