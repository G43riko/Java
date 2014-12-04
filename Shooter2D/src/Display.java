import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class Display extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	private Thread t;
	private Graphics g;
	public G2D g2d;
	
	public Display(){
		this.setSize(Main.WIDTH, Main.HEIGHT);
		Main.gameIs=0;
		this.g2d=new G2D();
	};
	
	//thousands times per second;
	private void Render(){
	};
	
	//60 times per second;
	private void Update(){
		
		BufferStrategy buffer = this.getBufferStrategy();
		if(buffer==null){
			this.createBufferStrategy(3);
			return;
		}
		this.g = buffer.getDrawGraphics();
		switch(Main.gameIs){
			case 0:
				this.g2d.drawMenu(g);
				break;
			case 1:
				
				if(Main.player.isShooting){
					Main.player.fire();
				}
				
				Main.player.move();
				
				
				for( int i=0 ; i<Main.EnemyNum ; i++){
					Enemy bot=Main.enemies[i];
					if(bot!=null){
						bot.move();
						if(bot.checkBorders()){
							Main.enemies[i]=Enemy.createEnemy();
						}
						if(Helpers.collisionRectRect((int)bot.x,(int)bot.y, bot.width, bot.height,(int)Main.player.x,(int)Main.player.y, Main.player.width, Main.player.height)
							&&!bot.isExploding){
							
							if(Main.toCenterAfterGetDemage){
								Main.player.x=Main.WIDTH/2-Main.player.width/2;
								Main.player.y=Main.HEIGHT-Main.player.height;
							}
							
							if(Main.sound){
								Audio.Play("dead");
							}
							
							Main.player.dx=0;
							Main.player.isFlashing=200;
						    Main.player.healt--;
						    Main.enemies[i]=Enemy.createEnemy();
						}

						
						if(Main.enemies[i].isDead){
							Main.enemies[i]=Enemy.createEnemy();
						}
					}
				};
				
				Main.bullets.checkAll();
				Main.bullets.move();
				Main.bullets.checkBorder();
				Main.bullets.checkCollision();
				this.g2d.drawGame(g);
				
				break;
			case 2:
				this.g2d.drawPause(g);
				break;
			case 3:
				this.g2d.drawOptions(g);
				break;
		}
		g.dispose();
		buffer.show();
	};
	
	public void start(){
		Main.gameIs=0;
		this.t = new Thread(this);
		this.t.start();
		Main.isRunning=true;
	};
	
	public void stop(){
		if(Main.isRunning){
			Main.isRunning=false;
			try {
				this.t.join();
			} catch(Exception e ){
				e.printStackTrace();
				System.exit(0);
			}
		}
	};
	
	public void run() {
		long lastTimeCycle=System.nanoTime();
		long lastTimeOutput=System.currentTimeMillis();
		double unprocessedTicks=0;
		double nsPerTick = Math.pow(10,9)/60;
		int FPS = 0;
		int ticks = 0;
		while(Main.isRunning){
			long nowTimeCycle = System.nanoTime();
			unprocessedTicks+=(nowTimeCycle - lastTimeCycle) / nsPerTick;
			lastTimeCycle = nowTimeCycle;
			
			while(unprocessedTicks >= 1){
				ticks++;
				this.Update();
				unprocessedTicks--;
			}
			
			FPS++;
			this.Render();
			if(System.currentTimeMillis() - lastTimeOutput>1000){
				lastTimeOutput+=1000;
				System.out.println("Ticks: "+ticks+" FPS: "+FPS);
				FPS=0;
				ticks=0;
			}
		}
	}

}
