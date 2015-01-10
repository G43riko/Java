package game.main;

public class Index {
	public static final int HEIGHT = 720;
	public static final int WIDTH = 1280;
	public static final String TITLE = "Game";

	public static void main(String[] args){
		Game game = new Game();
		game.init();
		
		while(true){
			game.loop();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
