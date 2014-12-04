import java.awt.Color;
import java.awt.Graphics;


public class BulletC {
	public int width,height,speed, attack;
	public double x,y,dx,dy;
	private Color color;
	public boolean isExploding,live,isDead;
	public static final int fireOffset=600;
	private final int explosionRadius=80;
	public ParticleCircle[] particles=null;
	
	BulletC(double x, double y,double dx,double dy){
		this.x=x;
		this.y=y;
		this.attack=Main.player.attack+10;
		this.speed=30;
		this.color= Color.red;
		this.width=10;
		this.height=this.speed;
		this.dx=dx;
		this.dy=0;
		this.isDead=false;
		if(Main.sound){
			Audio.Play("laser");
		}
	};
	
	public void draw(Graphics g){
		if(this.isExploding){
			if(this.particles==null){
				return;
			}
			this.live=false;
			for(int i=0 ; i<this.particles.length ; i++){
				if(this.particles[i]!=null){
					this.particles[i].draw(g);
					if(this.particles[i].radius<=0){
						this.particles[i]=null;
						continue;
					}
					live=true;
					if(Main.gameIs==1){
						this.particles[i].move();
					}
				}
			}
			if(!this.live){
				this.particles=null;
				this.isDead=true;
			}
		}
		else{
			g.setColor(this.color);
			g.fillRoundRect((int)this.x, (int)this.y, this.width, this.height, this.width, this.height);
		}
	};
	
	public void move(){
		this.x+=this.dx*this.speed;
		this.y+=this.dy*this.speed;
		if(this.dy>-0.7){
			this.dy-=0.030;
		}
	};
	
	public boolean checkBorder(){
		if(this.x+this.width<0||this.x>Main.WIDTH){
			return true;
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
					this.explosionStart(80);
					return true;
				}
				
			}
		}
		return false;
	};
	
	public void explosionStart(int num){
		if(this.isExploding){
			return;
		}
		this.dy=0;
		this.isExploding=true;
		this.particles=new ParticleCircle[num];
		for(int i=0 ; i<num ; i++){
			int radius=(int)Math.floor(Math.random()*50);
			Color[] colors={Color.YELLOW, Color.ORANGE,Color.DARK_GRAY};
			int cislo=(int)Math.floor(Math.random()*colors.length);
			this.particles[i]=new ParticleCircle(this.x, this.y, radius,colors[cislo],0,0);
		}
		for(int i=0 ; i<Main.EnemyNum ; i++){
			Enemy bot=Main.enemies[i];
			if(Helpers.getDist(bot.x,bot.y, this.x, this.y)<this.explosionRadius){
				Main.enemies[i].healt-=this.attack;
				if(Main.sound){
					Audio.Play("demage");
				}
				if(bot.checkLifes()){
					Main.enemies[i].explosionStart((int)Math.random()*6+7);
					Main.score+=10;
					if(Main.score%1000==0){
						Main.addEnemy(10);
					}
				}
			}
		}
		
	}
}
