package org;

import org.engine.DefaultApp;
import org.engine.core.CoreEngine;

public class MainEngine {
	public static void main(String[] args){
		CoreEngine game = new DefaultApp();
//		CoreEngine game = new VoxelTester();
		
		game.init();
		game.start();
		game.cleanUp();
	}
}
