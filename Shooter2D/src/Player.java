import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Player {
	public int x,y,dx,dy,width,height;
	public int healt,attack,speed,isFlashing;
	public long lastFire;
	public boolean isShooting;
	private Image image;
	
	Player(int x,int y){
		this.width=50;
		this.height=70;
		this.x=Main.WIDTH/2-this.width/2;
		this.y=Main.HEIGHT-this.height;
		this.healt=10;
		this.dx=0;
		this.dy=0;
		this.speed=10;
		this.attack=1;
		this.lastFire=System.currentTimeMillis();
		this.isShooting=false;
		this.image=new ImageIcon("c:\\Player.png").getImage();
		this.image=new ImageIcon(ClassLoader.getSystemResource("\\Images\\Player.png")).getImage();
		this.isFlashing=0;
	};
	
	public void draw(Graphics g){
		g.drawImage(image, this.x,this.y,this.width,this.height, null);
	};
	
	public void move(){
		this.x+=this.dx*this.speed;
		this.y+=this.dy*this.speed;
		
		/* Check Canvas Border */
		
		if(this.x<0||this.x+this.width>Main.WIDTH){
			this.x-=this.dx*this.speed;
		}
		if(this.y<0||this.y+this.height>Main.HEIGHT){
			this.y-=this.dy*this.speed;
		}	
	};
	
	public boolean fire(){
		/*
		if(System.currentTimeMillis()-Main.player.lastFire>this.fireOffset){
			this.lastFire=System.currentTimeMillis();
			for( int i=0 ; i<Main.BulletNum; i++){
				if(Main.bullets[i]==null){
					Main.bullets[i]=new Bullet(this.x+this.width/2-new Bullet(1,1,1,1).width/2,this.y,0,-1);
					return true;
				}
			}
		}
		
		*/
		if(Main.bullets.fire(Main.weaponType)){
			return true;
		}
		return false;
	};
	
}
