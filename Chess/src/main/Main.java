package main;

public class Main {
	public static int fps = 30;
	public static void main(String[] args) {
		
		Game game = new Game();
		game.init();
		game.start();
		game.cleanUp();
	}

}
