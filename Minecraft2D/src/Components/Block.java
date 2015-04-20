package Components;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Arrays;

import javax.swing.ImageIcon;

import Main.Main;
import Utils.Helpers;


public class Block {
	public static int size=16;
	public static int parts=2;
	public static Image[] images = new Image[]{null,
											   new ImageIcon(ClassLoader.getSystemResource(".\\Images\\Blocks\\T-Ground.png")).getImage(),
											   new ImageIcon(ClassLoader.getSystemResource(".\\Images\\Blocks\\T-Stone.png")).getImage()};
	public int type,x,y,lightnes,soif=18;
	private int num;
	private double healt;
	private boolean isDestructible,isCollisable,isFalling;
	private Color color,originalColor=null;
	public String name,skosenie;
	private Image image=null;
	private int[] sur=new int[2];
	private boolean[] borders = new boolean[8];
	
	public Block(int type,int x,int y){
		this.num = (int)Math.floor(Math.random()*3);
		this.lightnes=255;
		this.setType(type);
		this.x=x;
		this.y=y;
		Arrays.fill(this.borders, false);
		this.sur[0]=18*num+18;
		this.sur[1]=18;
	}
	
	public void setType(int typ){
		this.type=typ;
		this.setColor();
		this.setCollionable();
		this.autoSetHealt();
		this.setDestructibility();
	}

	public void setSurB(String typ){
		switch(typ){
			case"A":
				this.sur[0]=num*soif+soif;
				this.sur[1]=0;
				break;
			case"A1":
				this.sur[0]=num*soif+8*soif;
				this.sur[1]=6*soif;
				break;
			case"B":
				this.sur[0]=4*soif;
				this.sur[1]=num*soif;
				break;
			case"B1":
				this.sur[0]=8*soif;
				this.sur[1]=num*soif+7*soif;
				break;
			case"C":
				this.sur[0]=num*soif+soif;
				this.sur[1]=2*soif;
				break;
			case"C1":
				this.sur[0]=num*soif+8*soif;
				this.sur[1]=5*soif;
				break;
			case"D":
				this.sur[0]=0;
				this.sur[1]=num*soif;
				break;
			case"D1":
				this.sur[0]=9*soif;
				this.sur[1]=num*soif+7*soif;
				break;
			case"E":
				this.sur[0]=num*soif*2+soif;
				this.sur[1]=3*soif;
				break;
			case"E1":
				this.sur[0]=3*soif;
				this.sur[1]=num*soif*2+5*soif;
				break;
			case"F":
				this.sur[0]=num*soif*2+soif;
				this.sur[1]=4*soif;
				break;
			case"F1":
				this.sur[0]=3*soif;
				this.sur[1]=num*soif*2+6*soif;
				break;
			case"G":
				this.sur[0]=num*soif*2;
				this.sur[1]=4*soif;
				break;
			case"G1":
				this.sur[0]=2*soif;
				this.sur[1]=num*soif*2+6*soif;
				break;
			case"H":
				this.sur[0]=num*soif*2;
				this.sur[1]=3*soif;
				break;
			case"H1":
				this.sur[0]=2*soif;
				this.sur[1]=num*soif*2+5*soif;
				break;
			case"I":
				this.sur[0]=12*soif;
				this.sur[1]=num*soif;
				break;
			case"I1":
				this.sur[0]=12*soif;
				this.sur[1]=num*soif+8*soif;
				break;
			case"J":
				this.sur[0]=num*soif+6*soif;
				this.sur[1]=3*soif;
				break;
			case"J1":
				this.sur[0]=11*soif;
				this.sur[1]=num*soif+8*soif;
				break;
			case"K":
				this.sur[0]=9*soif;
				this.sur[1]=num*soif;
				break;
			case"K1":
				break;
			case"L":
				this.sur[0]=num*soif+6*soif;
				this.sur[1]=0;
				break;
			case"L1":
				this.sur[0]=11*soif;
				this.sur[1]=5*soif+num*soif;
				break;
			case"M":
				this.sur[0]=5*soif;
				this.sur[1]=num*soif;
				break;
			case"M1":
				this.sur[0]=13*soif+num*soif;
				this.sur[1]=2*soif;
				break;
			case"M2":
				this.sur[0]=13*soif+num*soif;
				this.sur[1]=3*soif;
				break;
			case"M3":
				this.sur[0]=9*soif;
				this.sur[1]=num*soif+8*soif;
				break;
			case"N":
				this.sur[0]=6*soif+num*soif;
				this.sur[1]=4*soif;
				break;
			case"N1":
				this.sur[0]=13*soif+num*soif;
				this.sur[1]=soif;
				break;
			case"N2":
				this.sur[0]=13*soif+num*soif;
				this.sur[1]=2*soif;
				break;
			case"N3":
				this.sur[0]=num*soif+8*soif;
				this.sur[1]=10*soif;
				break;
			case"O":
				this.sur[0]=num*soif+9*soif;
				this.sur[1]=3*18;
				break;
			case"O1":
				this.sur[0]=num*soif+6*soif;
				this.sur[1]=11*soif;
				break;
			case"O2":
				this.sur[0]=num*soif+9*soif;
				this.sur[1]=11*soif;
				break;
			case"O3":
				this.sur[0]=6*soif;
				this.sur[1]=num*soif+12*soif;
				break;
			case"O4":
				this.sur[0]=num*soif;
				this.sur[1]=13*soif;
				break;
			case"O5":
				this.sur[0]=num*soif+3*soif;
				this.sur[1]=13*soif;
				break;
			default:
				this.sur[0]=soif*num+soif;
				this.sur[1]=soif;
		}
	}
	
	public void setSurA(String typ){
		int what=0;
		if(this.type==1){
			if((this.getNeighbort("S")==null||this.getNeighbort("S").type!=what)&&
			   (this.getNeighbort("J")==null||this.getNeighbort("J").type!=what)&&
			   (this.getNeighbort("V")==null||this.getNeighbort("V").type!=what)&&
			   (this.getNeighbort("Z")==null||this.getNeighbort("Z").type!=what)&&(typ!="")){
				typ="";
			}
		}
		switch(typ){
			case "A"://OKEY
				if(this.getNeighbort("S").type!=what){
					setSurB("A1");
				}
				else{
					setSurB("A");
				}
				break;
			case "B"://OKEY
				if(this.getNeighbort("V").type!=what){
					setSurB("B1");
				}
				else{
					setSurB("B");
				}
				break;
			case "C"://OKEY
				if(this.getNeighbort("J").type!=what){
					setSurB("C1");
				}
				else{
					setSurB("C");
				}
				break;
			case "D"://OKEY
				if(this.getNeighbort("Z").type!=what){
					setSurB("D1");
				}
				else{
					setSurB("D");
				}
				break;
			case "E":
				if(this.getNeighbort("S").type!=what&&this.getNeighbort("V").type!=what){
					setSurB("E1");
				}
				else{
					setSurB("E");
				}
				break;
			case "F":
				if(this.getNeighbort("J").type!=what&&this.getNeighbort("V").type!=what){
					setSurB("F1");
				}
				else{
					setSurB("F");
				}
				break;
			case "G":
				if(this.getNeighbort("J").type!=what&&this.getNeighbort("Z").type!=what){
					setSurB("G1");
				}
				else{
					setSurB("G");
				}
				break;
			case "H":
				if(this.getNeighbort("S").type!=what&&this.getNeighbort("Z").type!=what){
					setSurB("H1");
				}
				else{
					setSurB("H");
				}
				break;
			case "I":
				setSurB("I");
				break;
			case "J":
				setSurB("J");
				break;
			case "K":
				setSurB("K");
				break;
			case "L":
				if(this.getNeighbort("Z").type!=what&&this.getNeighbort("S").type!=what&&this.getNeighbort("V").type!=what){
					setSurB("L1");
				}
				else{
					setSurB("L");
				}
				break;
			case "M":
				if(this.getNeighbort("V").type!=what&&this.getNeighbort("Z").type!=what){
					this.sur[0]=10*soif;
					this.sur[1]=num*soif+7*soif;
				}
				else if(this.getNeighbort("V").type==what&&this.getNeighbort("Z").type!=what){
					this.sur[0]=13*soif+num*soif;
					this.sur[1]=3*soif;
				}
				else if(this.getNeighbort("V").type!=what&&this.getNeighbort("Z").type==what){
					this.sur[0]=13*soif+num*soif;
					this.sur[1]=3*soif;
				}
				else{
					this.sur[0]=5*soif;
					this.sur[1]=num*soif;
				}
				break;
			case "N":
				if(this.getNeighbort("S").type!=what&&this.getNeighbort("J").type!=what){
					this.sur[0]=num*soif+8*soif;
					this.sur[1]=10*soif;
				}
				else if(this.getNeighbort("S").type==what&&this.getNeighbort("J").type!=what){
					this.sur[0]=13*soif+num*soif;
					this.sur[1]=0*soif;
				}
				else if(this.getNeighbort("S").type!=what&&this.getNeighbort("J").type==what){
					this.sur[0]=13*soif+num*soif;
					this.sur[1]=1*soif;
				}
				else{
					this.sur[0]=num*soif+6*soif;
					this.sur[1]=4*soif;
				}
				
				break;
			case "O":
				setSurB("O");
				break;
			default://OKEY
				setSurB("");
		}
	}
	
	public void setBorders(){
		int nerovne = this.type;
		if(this.getNeighbort("S")!=null&&this.getNeighbort("S").type!=nerovne){
			this.borders[0]=true;
			this.skosenie="A";
		}
		if(this.getNeighbort("V")!=null&&this.getNeighbort("V").type!=nerovne){
			this.borders[2]=true;
			this.skosenie="B";
		}
		if(this.getNeighbort("J")!=null&&this.getNeighbort("J").type!=nerovne){
			this.borders[4]=true;
			this.skosenie="C";
		}
		if(this.getNeighbort("Z")!=null&&this.getNeighbort("Z").type!=nerovne){
			this.borders[6]=true;
			this.skosenie="D";
		}
		
		if(this.borders[0]&&this.borders[2]&&this.getNeighbort("SV").type!=nerovne){
			this.borders[1]=true;
			this.skosenie="E";
		}
		if(this.borders[4]&&this.borders[2]&&this.getNeighbort("JV").type!=nerovne){
			this.borders[3]=true;
			this.skosenie="F";
		}
		if(this.borders[4]&&this.borders[6]&&this.getNeighbort("JZ").type!=nerovne){
			this.borders[5]=true;
			this.skosenie="G";
		}
		if(this.borders[0]&&this.borders[6]&&this.getNeighbort("SZ").type!=nerovne){
			this.borders[7]=true;
			this.skosenie="H";
		}
		
		/**/

		// ^
		// |
		if(this.borders[6]&&this.borders[0]&&this.borders[2]){
			this.skosenie="L";
		}
		//->
		if(this.borders[0]&&this.borders[2]&&this.borders[4]){
			this.skosenie="I";
		}
		// |
		// V
		if(this.borders[2]&&this.borders[4]&&this.borders[6]){
			this.skosenie="J";
		}
		//<-
		if(this.borders[0]&&this.borders[4]&&this.borders[6]){
			this.skosenie="K";
		}
		
		/**/
		
		//<->
		if(this.borders[0]&&!this.borders[2]&&this.borders[4]&&!this.borders[6]){
			this.skosenie="N";
		}
		// ^
		// |
		// v
		
		if(!this.borders[0]&&this.borders[2]&&!this.borders[4]&&this.borders[6]){
			this.skosenie="M";
		}
		
		/**/
		
		//  ^
		// <+>
		//  v
		if(this.borders[0]&&this.borders[2]&&this.borders[4]&&this.borders[6]){
			this.skosenie="O";
		}
		if(this.skosenie==null){
			this.skosenie="";
		}
		this.setSurA(this.skosenie);
	}

	public void addDemage(double demage){
		if(!this.isDestructible){
			return;
		}
		this.setHealt(this.getHealt() - demage);
		if(this.getHealt()<=0){
			this.setType(0);
		}
	}
	
	public void setHealt(double h){
		this.healt = h;
	}
	
	private void autoSetHealt(){
		switch(this.type){
			case 0 :
				this.healt = 0;
				break;
			case 1 :
				this.healt = 2;
				break;
			case 2 :
				this.healt = 4;
				break;
			case 3 :
				this.healt = 0;;
				break;
			case 4 :
				this.healt = 3;
				break;
			case 5 :
				this.healt = 1;
				break;
		}
	}
	
	//set image and color
	public void setColor(){
		switch(this.type){
			case 0:
				this.color=originalColor=Color.BLACK;
				this.name="niè";
				break;
			case 1:
				this.color=originalColor=Color.RED;
				if(Main.Images){
					this.image = Block.images[type];
				}
				this.name="Grass";
				break;
			case 2:
				this.color=originalColor=Color.BLUE;
				if(Main.Images){
					this.image = Block.images[type];
				}
				this.name="clay";
				break;
			case 3:
				this.color=originalColor=Color.DARK_GRAY;
				this.name="unbreakable";
				break;
			case 4:
				this.color=originalColor=Color.MAGENTA;
				this.name="kmen";
				break;
			case 5:
				this.color=originalColor=Color.GREEN;
				this.name="koruna";
				break;
		}
		this.addLightnessToColor();
	}
	
	//change color and add the lightness in color
	private void addLightnessToColor() {
		//this.color=new Color(this.color.getRed(),this.color.getGreen(),this.color.getBlue(),this.lightnes);
		int red = this.originalColor.getRed()-(255-this.lightnes);
		int green = this.originalColor.getGreen()-(255-this.lightnes);
		int blue = this.originalColor.getBlue()-(255-this.lightnes);
		if(red<0)red = 0;
		if(green<0)green = 0;
		if(blue<0)blue = 0;
		this.color=new Color(red,green,blue,255);
	}
	
	//Returns a block which is a neighbor with this block
	public Block getNeighbort(String where){
		Block helper = null;
		int i=0,j=0;
		switch(where){
			case "S":
				j=-1;
				break;
			case "J":
				j=1;
				break;
			case "V":
				i=+1;
				break;
			case "Z":
				i=-1;
				break;
			case "SV":
				i=1;
				j=-1;
				break;
			case "SZ":
				i=-1;
				j=-1;
				break;
			case "JV":
				i=1;
				j=1;
				break;
			case "JZ":
				i=-1;
				j=1;
				break;
			default:
				return this;
		}
		if(Main.mapa.isExist(this.x+i, this.y+j)){
			helper=Main.mapa.Mapa[this.x+i][this.y+j];
		}
		return helper;
	}
	
	//add light to this block
	public void autoSetLightness(int i, int j) {
		int dist = 4;
		double minDist=-1;
		for(float k=-dist ; k<=dist ; k++){
			for(float l=-dist ; l<=dist ; l++){
				double actDist=Helpers.getDist(i+k, j+l, i, j);
				if(Main.mapa.isExist((int)(i+k), (int)(j+l))&&actDist<=dist&&Main.mapa.Mapa[(int)(i+k)][(int)(j+l)].type==0){
					if(minDist==-1){
						minDist=actDist;
					}
					else{
						if(minDist>actDist){
							minDist=actDist;
						}
					}
				}
			}
		}
		this.lightnes=255-(int)(minDist*(255/dist));
		if(this.lightnes<0){
			this.lightnes=0;
		}
		if(this.lightnes>255){
			this.lightnes=255;
		}
		if(minDist==-1){
			this.lightnes=0;
		}
		this.addLightnessToColor();
	}

	public void draw(Graphics2D g2) {
		if(this.image==null){
			g2.setColor(this.color);
			g2.fillRect((int)(this.x*Block.size-Main.players.offsetX), (int)(this.y*Block.size-Main.players.offsetY),Block.size,Block.size);
		}
		else{
			int x=(int)(this.x*Block.size-Main.players.offsetX);
			int y=(int)(this.y*Block.size-Main.players.offsetY);
			g2.drawImage(this.image, x, y, x+Block.size, y+Block.size, this.sur[0], this.sur[1], this.sur[0]+16, this.sur[1]+16, null);
		}
	}

	public void setLightnes(int light){
		this.lightnes=light;
		addLightnessToColor();
	}
	
	private void setCollionable() {
		if(this.type==0||this.type==4||this.type==5){
			this.isCollisable=false;
		}
		else{
			this.isCollisable=true;
		}	
	}

	public void setDestructibility(){
		if(this.type==3){
			this.isDestructible=false;
		}
		else{
			this.isDestructible=true;
		}
	}

	public String toString(){
		return this.x+"x"+this.y+"="+this.type;
	}

	public double getHealt() {
		return healt;
	}

}
