package octtrees;

import glib.util.vector.GVector2f;

public class MainOctree {
	public static void main(String[] args) {
		Tree2D<?> t = new Tree2D<String>(new GVector2f(), new GVector2f(100, 100));
		
		for(int i=0 ; i<9 ; i++){
			System.out.println("detí: " + t.getNumberOfChildrens() + " == " + t.getNumberOfEndChildrens());
			System.out.println("trvalo to: " + divideAndGetTime(t));
		}
		
		
		System.out.println("loop: " + loopAndGetTime(t));
		
		//21844
//		t.divide();
	}
	
	private static long divideAndGetTime(Tree2D<?> t){
		long time = System.currentTimeMillis();
		t.divide();
		return (System.currentTimeMillis() - time);
	}
	private static long loopAndGetTime(Tree2D<?> t){
		long time = System.currentTimeMillis();
		for(int i=0 ; i<t.getNumberOfChildrens() ; i++);
//		for(Tree2D<?> d : t.getDescendants());
		return (System.currentTimeMillis() - time);
	}
}
