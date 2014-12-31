package GameCycle;

public class Start {

	public static void main(String[] args) {
		int Ticks=0,FPS=0;
		double GoalFPS=60;
		double cycleTime= Math.pow(10,9)/60;
		double startTime,nanoTime;
		while(true){
			startTime=System.currentTimeMillis();
			nanoTime=System.nanoTime();
			while(System.currentTimeMillis()-startTime<=1000){
				
				if(System.nanoTime()-nanoTime>cycleTime){
					FPS++;
					nanoTime=System.nanoTime();
					
				}
				Ticks++;
			}
			System.out.println("Ticks: "+Ticks+" FPS:"+FPS);
			Ticks=0;
			FPS=0;
		}

	}

}
