package GameEngine;

import GameEngine.core.CoreEngine;

public class MainGame {
	public static void main(String[] args){
		CoreEngine engine = new CoreEngine(800,600,60,new TestGame());
		engine.createWindow("3D Game Engine");
		engine.start();
	}
}
