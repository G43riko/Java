package week.five;

public class Clovek {
	private String meno;
	private int rokNarodenia;
	
	public Clovek(String meno, int narodenie){
		rokNarodenia = narodenie;
		this.meno = meno;
	}
	
	public static void vypisInformacie(Clovek c){
		System.out.println("Meno: "+c.getMeno()+" rok narodenie: "+c.getRokNarodenia());
	}
	
	public String getMeno(){
		return meno;
	}
	
	public int getRokNarodenia(){
		return rokNarodenia;
	}
}
