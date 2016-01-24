package glib.util.noise;
import glib.math.GMath;
import glib.util.vector.GVector2f;


public class ProceduralTerrain {
	private static float[][] mapa;
	
	public static float[][] create(int x2, int y2){
		mapa = new float[x2][y2];
		
		mapa[0][0] 		= (float)Math.random();
		mapa[0][y2 - 1] = (float)Math.random();
		mapa[x2 - 1][0] = (float)Math.random();
		mapa[x2 - 1][y2 - 1] = (float)Math.random();
		
		generateMap(0, 0, x2 - 1, y2 - 1);
		
		return mapa;
	}
	
	private static void generateMap(int x1, int y1, int x2, int y2) {
		float cl1 = mapa[x1][y1];
		float cl2 = mapa[x2][y1];
		float cl3 = mapa[x1][y2];
		float cl4 = mapa[x2][y2];
		
		GVector2f b1 = new GVector2f((x1 + x2) / 2, y1);
		mapa[b1.getXi()][b1.getYi()] =  GMath.average(cl1, cl2);
				
		GVector2f b2 = new GVector2f((x1 + x2) / 2, y2);
		mapa[b2.getXi()][b2.getYi()] =  GMath.average(cl3, cl4);
		
		GVector2f b3 = new GVector2f(x1, (y1 + y2) / 2);
		mapa[b3.getXi()][b3.getYi()] =  GMath.average(cl1, cl3);
		
		GVector2f b4 = new GVector2f(x2, (y1 + y2) / 2);
		mapa[b4.getXi()][b4.getYi()] =  GMath.average(cl2, cl4);
		
		GVector2f b5 = new GVector2f((x1 + x2) / 2, (y1 + y2) / 2);
		mapa[b5.getXi()][b5.getYi()] =  GMath.average(cl1, cl2, cl3, cl4) + (float)(Math.random()-0.5);
		
		if(x2 - x1 <= 2 || y2 - y1 <= 2)
			return;
		
		generateMap(x1, y1, b5.getXi(), b5.getYi());
		generateMap(b1.getXi(), b1.getYi(), b4.getXi(), b4.getYi());
		generateMap(b3.getXi(), b3.getYi(), b2.getXi(), b2.getYi());
		generateMap(b5.getXi(), b5.getYi(), x2, y2);
	}
//	
//	private static float ff(int x1, int y1, int x2, int y2){
//		return (float)(Math.random()-0.5) * ((x2 - x1) + (y2 - y1));
//	}
//	
//	private static float interval(float value, float min, float max){
//		return Math.min(max, Math.min(value,min));
//	}
}
