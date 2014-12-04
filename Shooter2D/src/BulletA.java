import java.awt.Color;
import java.awt.Graphics;


public class BulletA {
	public int width,height,speed, attack;
	public double x,y,dx,dy;
	private Color color;
	public static final int fireOffset=200;
	
	BulletA(double x, double y,double dx,double dy){
		this.x=x;
		this.y=y;
		this.attack=Main.player.attack+4;
		this.speed=30;
		this.color= Color.green;
		this.width=10;
		this.height=this.speed;
		this.dx=dx;
		this.dy=dy;
		if(Main.sound){
			Audio.Play("laser");
		}
	};
	
	public void draw(Graphics g){
		g.setColor(this.color);
		g.fillRoundRect((int)this.x, (int)this.y, this.width, this.height, this.width, this.height);
	};
	
	public void move(){
		this.x+=this.dx*this.speed;
		this.y+=this.dy*this.speed;
	};
	
	public boolean checkBorder(){
		if(Main.bulletAreBouncing){
			if(this.x<0||this.x+this.width>Main.WIDTH){
				this.dx*=-1;
				this.x+=this.dx*this.speed;
			}
		}
		else{
			if(this.x+this.width<0||this.x>Main.WIDTH){
				return true;
			}
		}
		
		if(this.y+this.height<0||this.y>Main.HEIGHT){
			return true;
		}
		return false;
	};
	
	public boolean checkCollision(){
		for(int j=0 ; j<Main.enemies.length ; j++){
			Enemy bot=Main.enemies[j];
			if(bot!=null){
				if(Helpers.collisionRectRect((int)this.x,(int)this.y,this.width,this.height ,(int)bot.x,(int)bot.y,bot.width,bot.height)&&!Main.enemies[j].isExploding){
					Main.enemies[j].healt-=this.attack;
					if(Main.sound){
						Audio.Play("demage");
					}
					if(bot.checkLifes()){
						Main.enemies[j].explosionStart((int)Math.random()*6+7);
						Main.score+=10;
						if(Main.score%1000==0){
							Main.addEnemy(10);
						}
					}
					return true;
				}
			}
		}
		return false;
	};
}
