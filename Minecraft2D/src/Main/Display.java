package Main;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;


public class Display extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	private Thread t;
	private Graphics2D g2;
	private G2D g2d;
	private int ticks, FPS;
	
	public Display(){
		this.setSize(Main.WIDTH, Main.HEIGHT);
		this.g2d=new G2D();
	};
	
	private void Render(){
		
	};
	
	private void Update(){
		BufferStrategy buffer = this.getBufferStrategy();
		if(buffer==null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = buffer.getDrawGraphics();
		this.g2 = (Graphics2D) g;
		switch(Main.gameIs){
			case 0:
				g2d.drawMenu(g2);
				break;
			case 1:
				g2d.drawGame(g2);
				break;
			case 2:
				g2d.drawPause(g2);
				break;
		};
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
		this.FPS = 0;
		this.ticks = 0;
		while(Main.isRunning){
			long nowTimeCycle = System.nanoTime();
			unprocessedTicks+=(nowTimeCycle - lastTimeCycle) / nsPerTick;
			lastTimeCycle = nowTimeCycle;
			
			while(unprocessedTicks >= 1){
				this.ticks++;
				this.Update();
				unprocessedTicks--;
			}
			
			FPS++;
			this.Render();
			if(System.currentTimeMillis() - lastTimeOutput>1000){
				lastTimeOutput+=1000;
				//System.out.println("Ticks: "+ticks+" FPS: "+FPS);
				Main.FPS=this.FPS;
				Main.ticks=this.ticks;
				this.FPS=0;
				this.ticks=0;
			}
		}
	}

}
