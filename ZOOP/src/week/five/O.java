package week.five;

public class O {
	private int vek;
	private String meno;
	public static int numO = 0;
	public O(String m, int v){
		meno = m;
		vek = v;
		O.numO++;
		System.out.println("Aktuálne sa vytvorila "+O.numO+" inštancia objektu O s menom: "+m+" a vekom: "+v);
	}
}
