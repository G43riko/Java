package prototypeGameEngine;

import prototypeGameEngine.core.CoreEngine;

public class MainPrototype {
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final int FPS = 30;
	public static final boolean FULLSCREEN = false;
	public static final String TITLE = "VoxelGameEngine";
	
	public static void main(String[] args){
		CoreEngine game = new CoreEngine(FPS,new Tester());
		game.createWindow(WIDTH, HEIGHT, TITLE);
		game.start();
		game.cleanUp();
	}
}
