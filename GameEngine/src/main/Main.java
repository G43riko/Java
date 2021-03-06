package main;

public class Main {
	public final static boolean FULLSCREEN = false;
	public final static boolean ALLERTS = false;
	public final static boolean OSLOOK = false;
	public final static boolean VSYNC = true;
	public final static String TITLE = "Game Engine";
	public static final int FPS = 60;
	public static int WIDTH = 1280;
	public static int HEIGHT = 720;
	
	public static void main(String[] args){
		Game game = new Game();
		game.init();
		game.mainLoop();
		game.cleanUp();
		System.exit(0);
	}
}
