package week.five;

public class S {
	public static final int rok = 2001;
	public static String mesiac = "december";
	public static String dajMenoAMesiac(){
		return (S.rok+" "+S.mesiac);
	}
	
	public S(){
		System.out.println("statická premenná je: "+S.mesiac);
		System.out.println("statická konštanta je: "+S.rok);
		System.out.println("statická metóda vráti rok a mesiac: "+S.dajMenoAMesiac());
	}
}
