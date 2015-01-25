package glib.util.noise;

public class NoiseTester {

	public static void main(String[] args) {
		float[][][] mapa = new float[16][64][16];
		
		double start = System.currentTimeMillis();
		mapa = SimplexNoise.generateOctavedSimplexNoise(16, 64, 16, 6, 0.8f, 0.008f);
		System.out.println("mapa sa vytvorila za "+(System.currentTimeMillis() - start)+" milisekúnd");
		start = System.currentTimeMillis();
		for(int i=0 ; i<mapa.length ; i++){
			for(int j=0 ; j<mapa[i].length ; j++){
				for(int k=0 ; k<mapa[i][j].length ; k++){
					mapa[i][j][k] = (float)Math.random();
				}
			}
		}
		System.out.println(System.currentTimeMillis() - start);

	}

}
