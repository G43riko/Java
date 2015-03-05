package com.g43riko;

import com.g43riko.core.CoreGame;

public class MainVoxel {
	public static void main(String[] args){
		CoreGame game = new VoxelGame();
		game.init();
		game.start();
		game.cleanUp();
	}
	
}
