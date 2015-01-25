package com.voxel.main;

import com.voxel.core.CoreEngine;

public class MainVoxel2 {
	public final static boolean MIP_MAPPING = true;
	public final static boolean FULLSCREEN = false;
	public final static boolean ALLERTS = true;
	public final static boolean OSLOOK = false;
	public final static boolean VSYNC = true;
	public final static String TITLE = "VoxelGame2";
	private final static int FPS = 60;
	public static int WIDTH = 1280;
	public static int HEIGHT = 720;
	
	public static void main(String[] args) {
		CoreEngine voxel = new CoreEngine(FPS,new VoxelGame());
		voxel.createWindow();
		voxel.start();
		voxel.cleanUp();
	}

}
