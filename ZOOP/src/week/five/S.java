package week.five;

public class S {
	public static final int rok = 2001;
	public static String mesiac = "december";
	public static String dajMenoAMesiac(){
		return (S.rok+" "+S.mesiac);
	}
	
	public S(){
		System.out.println("statick� premenn� je: "+S.mesiac);
		System.out.println("statick� kon�tanta je: "+S.rok);
		System.out.println("statick� met�da vr�ti rok a mesiac: "+S.dajMenoAMesiac());
	}
}
