package org;

import org.engine.core.CoreEngine;
import org.tester.DefaultTester;

public class MainEngine {
	public static void main(String[] args){
		CoreEngine game = new DefaultTester();
		
		game.createWindow(game);
		game.init();
		game.start();
		game.cleanUp();
	}
}
