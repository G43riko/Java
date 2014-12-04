package PathFinder_2;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class Display extends Canvas implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Thread t;
	private Graphics g;
	public G2D g2d;
	
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
		this.g = buffer.getDrawGraphics();
		switch(Main.gameIs){
			case 0:
				g2d.drawMenu(g);
				break;
			case 1:
				g2d.drawGame(g);
				break;
		};
		g.dispose();
		buffer.show();
	};
	
	public void start(){
		Main.gameIs=1;
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
				//System.out.println("Ticks: "+ticks+" FPS: "+FPS);
				FPS=0;
				ticks=0;
			}
		}
	}

}
