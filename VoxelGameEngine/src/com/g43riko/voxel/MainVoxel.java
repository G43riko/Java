package com.g43riko.voxel;

import com.g43riko.core.CoreEngine;

public class MainVoxel{
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final int FPS = 30;
	public static final String TITLE = "VoxelGameEngine";
	
	public static void main(String[] args){
		CoreEngine engine = new CoreEngine(WIDTH,HEIGHT,FPS,new Tester());
		engine.createWindow(TITLE, false);
		engine.start();
		engine.cleanUp();
	}
}
