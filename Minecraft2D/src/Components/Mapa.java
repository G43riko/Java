package Components;
import java.awt.Color;
import java.awt.Graphics2D;

import Main.Main;
import Utils.Helpers;
import Utils.MapLoader;


public class Mapa {
	public Block[][] Mapa;
	public MapLoader data = new MapLoader("map.txt");
	
	public Mapa(){
		this.Create(200, 400);
	};
	
	public void Create(int x, int y ){
		this.Mapa=new Block[y][x];
		for(int i=0 ; i<y ; i++){
			for(int j=0 ; j<x ; j++){
				this.Mapa[i][j]=new Block(0,i,j);
			}
		}
		int j,nah,stred = this.Mapa[0].length/2;
		int lastGroundLevel=stred;
		for(int i=0 ; i<this.Mapa.length ; i++){
			double nahoda=Helpers.choose(new double[]{-2,-1,-1,0,0,0,0,0,1,1,2});
			if(i==0){
				nahoda=0;
			}
			while((int)((double)lastGroundLevel+nahoda)<0||(int)((double)lastGroundLevel+nahoda)>=this.Mapa[i].length){
				nahoda=Helpers.choose(new double[]{-2,-1,-1,0,0,0,0,0,1,1,2});
			}
			this.Mapa[i][(int)((double)lastGroundLevel+nahoda)]=new Block(1,i,(int)((double)lastGroundLevel+nahoda));
			nah=(int)Helpers.choose(new double[]{5,6,7,8,9,10});
			/*add grass*/
			for(j=0 ; j<nah ; j++){
				if((int)((double)lastGroundLevel+nahoda)+j>=this.Mapa[i].length){
					break;
				}
				this.Mapa[i][(int)((double)lastGroundLevel+nahoda)+j]=new Block(1,i,(int)((double)lastGroundLevel+nahoda)+j);
			}
			/*add rock*/
			for(;(int)((double)lastGroundLevel+nahoda)+j<this.Mapa[i].length ; j++){
				this.Mapa[i][(int)((double)lastGroundLevel+nahoda)+j]=new Block(2,i,(int)((double)lastGroundLevel+nahoda)+j);
			}
			/*add ground*/
			lastGroundLevel+=nahoda;
			if(x>1){
				for(j=1 ; j<Helpers.choose(new double[]{5,6,7,8,9,10,11,12,13}) ; j++){
					this.Mapa[i][x-j]=new Block(3,i,x-j);
				}
			}			
		}
		if(x>1){
			this.addTrees();
		}
	};
	
	public boolean isExist(int x,int y){
		if(x>=0&&x<Mapa.length&&y>=0&&y<Mapa[x].length){
			return true;
		}
		return false;
	};
	
	public void setLighness(){
		for(int i=0 ; i<Main.lights.size() ; i++){
			if(Main.lights.get(i).isChange()){
				int dist = Main.lights.get(i).getLightness();
				int X = (int)Math.floor(Main.lights.get(i).x/Block.size);
				int Y = (int)Math.floor(Main.lights.get(i).y/Block.size);
				for(int j=X-dist ; j<=X+dist ; j++){
					for(int k=Y-dist ; k<=Y+dist ; k++){
						if(this.isExist(j, k)){
							double distNow = Helpers.getDist(X, Y, j, k);
							double svetlo = (255-distNow*255/dist);
							if(svetlo<this.Mapa[j][k].lightnes){
								continue;
							}
							if(svetlo>255){
								svetlo=255;
							}
							if(svetlo<0){
								svetlo=0;
							}
							//System.out.println(j+" a "+k+" som nastavil svetlo na "+svetlo);
							Main.lights.get(i).setChange(false);
							this.Mapa[j][k].setLightnes((int)svetlo);
						}
					}
				}
			}
		}
	};
	
	private void addTrees() {
		int k;
		for(int i=0 ; i<this.Mapa.length ; i++){
			boolean findGrass=false;
			for(int j=0 ; j<this.Mapa[i].length ; j++){
				if(!findGrass&&this.Mapa[i][j].type==1){
					findGrass=true;
					boolean can=true;
					int rand=(int)Math.floor(Math.random()*400)+10;
					for(k=0 ; k<rand ; k++){
						if(!this.isExist(i+k, j)||!this.isExist(i-k, j)){
							can=false;
							break;
						}
						if((this.inColumnIs(i+k,5)||this.inColumnIs(i-k,5))){
							can=false;
							break;
						}
					}
					if(can){
						int size=(int)Helpers.choose(new double[]{4,5,6,7});
						if(this.isExist(i, j-size-2)){
							for(k=1 ; k<size ; k++){
								this.Mapa[i][j-k].setType(4);
							}
							this.Mapa[i][j-k].setType(5);
							this.Mapa[i+1][j-k+1].setType(5);
							this.Mapa[i-1][j-k+1].setType(5);
							this.Mapa[i+1][j-k].setType(5);
							this.Mapa[i-1][j-k].setType(5);
							break;
						}
					}
				}
			}
		}
	}
	
	public boolean checkTreesDist(int how,int from){
		for(int i=1 ; i<=how ; i++){
			if(this.isExist(from+i,0)&&!this.inColumnIs(from+i,5)&&this.isExist(from-i,0)&&!this.inColumnIs(from-i,5)){
				return false;
			}
		}
		return true;
	}
	
	public boolean inRowIs(int ktory,int co){
		for(int i=0 ; i<this.Mapa.length ; i++){
			if(this.Mapa[i][ktory].type==co){
				return true;
			}
		}
		return false;
	}
	
	public boolean inColumnIs(int ktory,int co){
		for(int i=0 ; i<this.Mapa[ktory].length ; i++){
			if(this.Mapa[ktory][i].type==co){
				return true;
			}
		}
		return false;
	}
	
	public boolean inAroundIs(int co,int x,int y,int dist){
		return false;
	}
	
	public void autoSetLightnessOnce(boolean everywhere){
		if(everywhere){
			for(int i=0 ; i<Main.mapa.Mapa.length ; i++){
				for(int j=0 ; j<Main.mapa.Mapa[i].length ; j++){
					if(this.Mapa[i][j].type!=0){
						this.Mapa[i][j].autoSetLightness(i,j);
					}
				}
			}
		}
		else{
			int X = (int)Math.floor(Main.players.x/Block.size);
			int Y = (int)Math.floor(Main.players.y/Block.size);
			int xOnScreen=(int)Math.floor(Main.WIDTH/Block.size);
			int yOnScreen=(int)Math.floor(Main.HEIGHT/Block.size);
			
			int iMin = X-xOnScreen;
			if(iMin<0){iMin=0;}
			
			int iMax = X+xOnScreen;
			if(iMax>this.Mapa.length){iMax=this.Mapa.length;}
			
			int jMin = Y-yOnScreen;
			if(jMin<0){jMin=0;}
			
			int jMax = Y+yOnScreen;
			if(jMax>this.Mapa[0].length){jMax=this.Mapa[0].length;}
			
			for(int i=iMin ; i<iMax ; i++){
				for(int j=jMin ; j<jMax ; j++){
					if(this.Mapa[i][j].type!=0){
						this.Mapa[i][j].autoSetLightness(i,j);
					}
				}
			}
		}
		
	}
	
	public void draw(Graphics2D g2){
		int X = (int)Math.floor(Main.players.x/Block.size);
		int Y = (int)Math.floor(Main.players.y/Block.size);

		
		
		int iMin = X-Main.xOnScreen/2;
		int iMax = X+Main.xOnScreen/2;
		if(iMin<0){
			iMin=0;
			iMax=Main.xOnScreen;
		}
		else if(iMax>this.Mapa.length){
			iMax=this.Mapa.length;
			iMin=this.Mapa.length-Main.xOnScreen;
		}
		int jMin = Y-Main.yOnScreen/2;
		int jMax = Y+Main.yOnScreen/2;
		if(jMin<0){
			jMin=0;
			jMax=Main.yOnScreen;
		}
		if(jMax>this.Mapa[0].length){
			jMax=this.Mapa[0].length;
			jMin=this.Mapa[0].length-Main.yOnScreen;
		}
		
		for(int i=iMin-1 ; i<=iMax+1 ; i++){
			for(int j=jMin-1 ; j<=jMax+1 ; j++){
				if(this.isExist(i,j)&&this.Mapa[i][j].type!=0){
					this.Mapa[i][j].draw(g2);
				}
			}
		}
		if(Main.Minimap){
			this.drawMiniMap(g2);
		}
	}
	
	public void drawMiniMap(Graphics2D g2){
		int width=200,height=100,offset=20;
		int x = Main.WIDTH-(width+offset);
		int y = offset;
		g2.setColor(Color.WHITE);
		g2.drawRect(x,y,width,height);
		/*
		for(int i=0 ; i<this.Mapa.length ; i++){
			int numY = this.Mapa[i].length;
			for(int j=0 ; j<numY ; j++){
				g2.setColor(this.Mapa[i][j].color);
				g2.fillRect(x+i*width/(numX-1),y+j*height/(numY-1),1,1);
			}
		}
		*/
	}
	
	public void showMap() {
		for(int i=0 ; i<this.Mapa.length ; i++){
			String riadok="";
			for(int j=0 ; j<this.Mapa[i].length ; j++){
				riadok+=this.Mapa[i][j].type;
			}
			System.out.println(riadok);
		}
		
	}
}
