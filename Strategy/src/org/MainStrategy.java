package org;

import org.engine.core.CoreEngine;
import org.engine.main.MainTester;
import org.voxel.main.MainVoxel;

public class MainStrategy {
	public final static boolean MIP_MAPPING = true;
	public final static boolean FULLSCREEN = false;
	public final static boolean SHOW_GUI = true;
	public final static boolean ALLERTS = true;
	public final static boolean OSLOOK = false;
	public final static boolean VSYNC = true;
	
	public final static String TITLE = "StrategyGame";
	public final static int FPS = 60;
	public final static int  RESOLUTION = 64;
	public static int WIDTH = 1280;
	public static int HEIGHT = 720;
	
	public static void main(String[] args) {
//		CoreEngine game = new StrategyGame();
		CoreEngine game = new MainTester();
//		CoreEngine game = new ParticleTester();
//		CoreEngine game = new MainVoxel();
	
		game.createWindow(game);
		game.init();
		game.start();
		game.cleanUp();
	}

}
