package com.voxel.main;

public class MainVoxel2 {
	
	private static float[][][] mapa = new float[16][64][16];
	
	public static void main(String[] args) {
		double start = System.currentTimeMillis();
		
		System.out.println("mapa sa vytvorila za "+(System.currentTimeMillis() - start)+" milisekúnd");
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
