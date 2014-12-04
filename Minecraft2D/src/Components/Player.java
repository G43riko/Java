package Components;
import java.awt.Color;
import java.awt.Graphics2D;

import Main.Main;

public class Player {
	public double x,y,offsetY,offsetX,dx,dy;
	public int w,h,maxdy=10,attack;
	private Color color;
	private int speed;
	public boolean isJumping,isMoving;

	public Player(int x, int y) {
		this.x=x;
		this.y=y;
		this.w=Block.size*2;
		this.h=Block.size*4;
		this.checkAndSetOffset();
		this.color=Color.PINK;
		this.speed=(int)Math.floor(Block.size/2.5);
		this.isMoving=false;
		this.isJumping=true;
	};

	public void draw(Graphics2D g2) {
		g2.setColor(this.color);
		g2.fillRect((int)(this.x-this.offsetX),(int)(this.y-this.offsetY),this.w,this.h);
	};

	public void move() {
		if(this.dx!=0){
			this.isMoving=true;
		}
		else{
			this.isMoving=false;
		}
		double oldX=this.x;
		this.x+=this.dx*this.speed;
		this.y+=this.dy;
		if(this.x<0||this.x>Block.size*Main.mapa.Mapa.length-2*Block.size){
			this.x-=this.dx*this.speed;
		}
		if(this.isJumping&&!this.isMoving){//ak skáèe/padá
			//this.checkSides(true);
			if(this.checkFloor(true)){//ked najde podlahu zastaví
				Main.oznamenia.add(new Warning("muhaa padol si na podlahu"+this.y));
				this.dy=0;
				this.isJumping=false;
			}	
			else{//ked nenajde tak zrýchly
				if(this.checkRoof()){
					Main.oznamenia.add(new Warning("nabural si s hora"+this.y));
					this.dy=1;
					if(this.isCentralize('y')){
						this.y+=2*Block.size;
					}
					else{
						this.y+=Block.size;
					}
					
				}
				if(this.dy>-this.maxdy&&this.dy<this.maxdy){
					this.dy+=Main.gravity;
				}
			}
		}
		else if(this.isMoving&&!this.isJumping){//ak neskáèe/nepadá
				if(this.checkSides(false)){
				}
				else {
					this.startFallIfCan();
				}
		}
		else if(this.isJumping&&this.isMoving){
			if(this.checkSides(false)){
				if(this.checkSides(false)){
					this.zarovnaj();
					this.y+=Block.size;
				}
			}
			if(this.checkFloor(true)){//ked najde podlahu zastaví
				Main.oznamenia.add(new Warning("padol si na podlahu"+this.y));
				this.dy=0;
				this.isJumping=false;
			}	
			else{//ked nenajde tak zrýchly
				if(this.checkRoof()){
					Main.oznamenia.add(new Warning("nabural si s hora"+this.y));
					this.dy=1;
					if(this.isCentralize('y')){
						this.y+=2*Block.size;
					}
					else{
						this.y+=Block.size;
					}
				}
				if(this.dy>-this.maxdy&&this.dy<this.maxdy){
					this.dy+=Main.gravity;
				}
			}
		};
		this.offsetX=(this.x-Main.WIDTH/2);
		this.offsetY=(this.y-Main.HEIGHT/2);

		this.checkAndSetOffset();
		
	}
	
	private void checkAndSetOffset(){
		if(this.offsetX<0){
			this.offsetX=0;
		}
		if(this.offsetX>(Main.mapa.Mapa.length*Block.size)-Main.WIDTH){
			this.offsetX=(Main.mapa.Mapa.length*Block.size)-Main.WIDTH; 
		}
		if(this.offsetY<0){
			this.offsetY=0;
		}				
		if(this.offsetY>(Main.mapa.Mapa[0].length*Block.size)-Main.HEIGHT){
			this.offsetY=(Main.mapa.Mapa[0].length*Block.size)-Main.HEIGHT;
		}
	}

	public void startJump() {
		if(!this.isJumping&&!this.checkRoof()){
			this.dy=-8;
			this.isJumping=true;
		}
	};
	
	public boolean checkFloor(boolean posun){
		int X = (int)Math.floor(this.x/Block.size);
		int Y = (int)Math.floor(this.y/Block.size);
		
		if(Main.mapa.Mapa[X][Y+4].type!=0){
			if(posun){
				this.y=(Y+4)*Block.size-this.h;
			}
			return true;
		}
		
		if(Main.mapa.Mapa[X+1][Y+4].type!=0){
			if(posun){
				this.y=(Y+4)*Block.size-this.h;
			}
			return true;
		}
		if(!this.isCentralize('x')){
			if(Main.mapa.Mapa[X+2][Y+4].type!=0){
				if(posun){
					this.y=(Y+4)*Block.size-this.h;
				}
				return true;
			}
		}
		return false;
	};
	
	public void startFallIfCan(){
		if(!this.isJumping&&!this.checkFloor(true)){
			this.isJumping=true;
			this.dy=1;
		}
	};
	
	public boolean checkRoof(){
		int X = (int)Math.floor(this.x/Block.size);
		int Y = (int)Math.floor(this.y/Block.size);
		
		if(this.isCentralize('y')){
			if(Main.mapa.Mapa[X][Y+1].type!=0){
				//this.y=(Y)*Block.size-this.h;
				return true;
			}
			if(Main.mapa.Mapa[X+1][Y+1].type!=0){
				//this.y=(Y)*Block.size-this.h;
				return true;
			}
			if(!this.isCentralize('x')){
				if(!this.isJumping){
					if(Main.mapa.Mapa[X+2][Y+1].type!=0){
						//this.y=(Y)*Block.size-this.h;
						return true;
					}
				}
			}
		}
		else{
			if(Main.mapa.Mapa[X][Y].type!=0){
				//this.y=(Y)*Block.size-this.h;
				return true;
			}
			if(Main.mapa.Mapa[X+1][Y].type!=0){
				//this.y=(Y)*Block.size-this.h;
				return true;
			}
			if(!this.isCentralize('x')){
				if(!this.isJumping){
					if(Main.mapa.Mapa[X+2][Y].type!=0){
						//this.y=(Y)*Block.size-this.h;
						return true;
					}
				}
			}
			
		}
		
		return false;
	};
	
	public void zarovnaj(){
		this.x = Math.floor((this.x/Block.size))*Block.size;
		this.y = Math.floor((this.y/Block.size))*Block.size;
	};
	
	public boolean isCentralize(char... args){
		if(args.length==0){
			if(this.x%Block.size==0&&this.y%Block.size==0){
				return true;
			}
		}
		else{
			if(args[0]=='x'){
				if(this.x%Block.size==0){
					return true;
				}
			}
			if(args[0]=='y'){
				if(this.y%Block.size==0){
					return true;
				}
			}
		}
		return false;
		
		
	};
	
	public boolean checkSides(boolean dole){
		int X = (int)Math.floor(this.x/Block.size);
		int Y = (int)Math.floor(this.y/Block.size);
		
		//lavo 1
		if(Main.mapa.Mapa[X][Y].type!=0){
			this.x=(X+1)*Block.size;
			return true;
		}
		
		//lavo 2
		if(Main.mapa.Mapa[X][Y+1].type!=0){
			this.x=(X+1)*Block.size;
			return true;
		}
		//lavo 3
		if(Main.mapa.Mapa[X][Y+2].type!=0){
			this.x=(X+1)*Block.size;
			return true;
		}
		//lavo 4
		if(Main.mapa.Mapa[X][Y+3].type!=0){
			if(dole){
				this.x=(X+1)*Block.size;
				return true;
			}
			else{
				this.y=(Y-1)*Block.size;
			}
		}
		//pravo 1
		if(this.isCentralize('x')){
			if(Main.mapa.Mapa[X+1][Y].type!=0){
				this.x=(X)*Block.size;
				return true;
			}
			//pravo 2
			if(Main.mapa.Mapa[X+1][Y+1].type!=0){
				this.x=(X)*Block.size;
				return true;
			}
			//pravo 3
			if(Main.mapa.Mapa[X+1][Y+2].type!=0){
				this.x=(X)*Block.size;
				return true;
			}
			//pravo 4
			if(Main.mapa.Mapa[X+1][Y+3].type!=0){
				if(dole){
					this.x=(X)*Block.size;
					return true;
				}
				else{
					this.y=(Y-1)*Block.size;
				}
			}
		}
		else{
			if(Main.mapa.Mapa[X+2][Y].type!=0){
				this.x=(X)*Block.size;
				return true;
			}
			
			//pravo 2
			if(Main.mapa.Mapa[X+2][Y+1].type!=0){
				this.x=(X)*Block.size;
				return true;
			}
			//pravo 3
			if(Main.mapa.Mapa[X+2][Y+2].type!=0){
				this.x=(X)*Block.size;
				return true;
			}
			//pravo 4
			if(Main.mapa.Mapa[X+2][Y+3].type!=0){
				if(dole){
					this.x=(X)*Block.size;
					return true;
				}
				else{
					this.y=(Y-1)*Block.size;
				}
			}
		}		
		return false;
	}

}
