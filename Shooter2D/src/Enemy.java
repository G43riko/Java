import java.awt.Color;
import java.awt.Graphics;


public class Enemy {
	public int width,height,speed,healt,attack;
	public double x,y,dx,dy;
	private Color color;
	public boolean isExploding, isDead;
	public ParticleRect[] particles;
	private int[][] ciary = new int[10][4];
	Enemy(double x,double y){
		this.x=x;
		this.y=y;
		this.width=45;
		this.height=10;
		this.speed=5;
		this.attack=1;
		this.healt=10;
		this.color=new Color((int)Math.floor(Math.random()*255),(int)Math.floor(Math.random()*255),(int)Math.floor(Math.random()*255),255);
		this.dx=(Math.random()*3)-1;
		this.dy=2;
		this.isExploding=false;
		this.isDead=false;
		for(int i=0 ; i<this.healt ; i++){
			this.ciary[i][0]=(int)(Math.random()*45);
			this.ciary[i][1]=(int)(Math.random()*10);
			this.ciary[i][2]=(int)(Math.random()*45);
			this.ciary[i][3]=(int)(Math.random()*10);
		}
	};
	
	public void move(){
		this.x+=this.dx;
		this.y+=this.dy;
		if(this.x<0||this.x+this.width>Main.WIDTH){
			this.dx*=-1;
		};
	}; 

	public void draw(Graphics g){
		if(this.isExploding){
			for(int j=0 ; j<this.particles.length ; j++){
				this.particles[j].draw(g);
				if(Main.gameIs==1){
					this.particles[j].move();
				}
				
				if(this.particles[j].color.getAlpha()<=2){
					this.isDead=true;
				}
			}
		}
		else{
			g.setColor(this.color);
			//g.fillRect(this.x, this.y, this.width, this.height);
			g.fillRoundRect((int)this.x, (int)this.y, this.width, this.height, this.width/2, this.height/2);
			
			g.setColor(Color.black);
			
			if(this.healt<=this.ciary.length/2){
				for(int i=0 ; i<this.ciary.length ; i++){
					g.drawLine((int)this.x+this.ciary[i][0], (int)this.y+this.ciary[i][1],(int)this.x+this.ciary[i][2], (int)this.y+this.ciary[i][3]);
				}
			}
		}
	};
	
	public boolean fire(){
		
		return false;
	};
	
	public boolean checkBorders(){
		if(this.y>Main.HEIGHT){
			return true;
		}
		return false;
	};
	
	public boolean checkLifes(){
		if(this.healt<=0){
			return true;
		}
		return false;
	};
	
	public static Enemy createEnemy(){
		return new Enemy((int)Math.floor(Math.random()*(Main.WIDTH-45)), -(int)Math.floor(Math.random()*300));
	};
	
	public void explosionStart(int num) {
		this.particles=new ParticleRect[num*num];
		int k=0;
		for(int i=0 ; i<num ; i++){
			for(int j=0 ; j<num ; j++){
				this.particles[k]=new ParticleRect(this.x+this.width/num*i,this.y+this.height/num*j,this.width/num,this.height/num,this.color,this.dx,this.dy);
				k++;
			}
		}
		this.isExploding=true;
	};
}
