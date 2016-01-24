package glib.util.noise;

import java.util.Random;

import glib.math.GMath;

public class PseudoRandom {
	private final static float AMPLITUDE = 70;
	private final static float ROUGHTNESS = 0.3f;
	private final static int OCTAVES = 3;
	private Random random = new Random();
	private int seed;
	
	public float generateHeight(int x, int y){
		float result = 0;
		float d = (float)Math.pow(2, OCTAVES - 1);
		
		for(int i=0 ; i<OCTAVES ; i++){
			float freq = (float) (Math.pow(2, i) / d);
			float amp = (float) Math.pow(ROUGHTNESS, i) * AMPLITUDE;
			result += getInterpolateNoise(x * freq, y * freq) * amp;
		}
		
		return result;
	}
	
	private float getInterpolateNoise(float x, float y){
		int intX = (int)x;
		int intY = (int)y;
		float fractX = x - intX;
		float fractY = y - intY;
		
		float v1 = getSmoothNoise(intX, intY);
		float v2 = getSmoothNoise(intX + 1, intY);
		float v3 = getSmoothNoise(intX, intY + 1);
		float v4 = getSmoothNoise(intX + 1, intY + 1);
		float i1 = GMath.interpolateLinearSmooth(v1, v2, fractX);
		float i2 = GMath.interpolateLinearSmooth(v3, v4, fractX);
		return GMath.interpolateLinearSmooth(i1, i2, fractY);
		
	}
	
	private float getSmoothNoise(int x, int y){
		float corners = (getNoise(x + 1, y + 1) +
				   		 getNoise(x + 1, y - 1) +
				   		 getNoise(x - 1, y + 1) +
				   		 getNoise(x - 1, y - 1)) / 16;
		
		float sides = (getNoise(x, y + 1) +
		   		 	   getNoise(x, y - 1) +
		   		 	   getNoise(x - 1, y) +
		   		 	   getNoise(x - 1, y)) / 8;
		
		float center = getNoise(x, y) / 4;
		
		return corners + sides + center;
	}
	
	private float getNoise(int x, int y){
		random.setSeed(x * 15516 + y * 5448 + seed);
		return random.nextFloat();
	}
}
