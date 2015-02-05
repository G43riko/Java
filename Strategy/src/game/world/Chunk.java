package game.world;

import glib.util.noise.PerlinNoise;


public class Chunk {
	public static final int NUM_X = 32;
	public static final int NUM_Z = 32;
	public static float[][] map = PerlinNoise.GeneratePerlinNoise(PerlinNoise.generateWhiteNoise(Chunk.NUM_X *NUM_X, Chunk.NUM_Z*NUM_Z), 6, 0.7f, true);
	
	
}
