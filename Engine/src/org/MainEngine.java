package org;

import org.engine.DefaultApp;
import org.engine.core.CoreEngine;

public class MainEngine {
	public static void main(String[] args){
		CoreEngine game = new DefaultApp();
		
		game.init();
		game.start();
		game.cleanUp();
	}
}
