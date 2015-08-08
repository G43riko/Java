package org;

import org.engine.core.CoreEngine;
import org.tester.VoxelTester;

public class MainEngine {
	public static void main(String[] args){
//		CoreEngine game = new DefaultTester();
		CoreEngine game = new VoxelTester();
		
		game.createWindow(game);
		game.init();
		game.start();
		game.cleanUp();
	}
}
