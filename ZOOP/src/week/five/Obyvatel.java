package week.five;

public class Obyvatel {
	private String meno;
	private int vyska,vaha;
	
	public Obyvatel(){
		meno = "Undefined";
		vyska = 0;
		vaha = 0;
	}
	
	public Obyvatel(Obyvatel old){
		this.meno = old.meno;
		this.vyska = old.vyska;
		this.vaha = old.vaha;
	}
	
	public Obyvatel(String meno, int vyska, int vaha){
		this.meno = meno;
		this.vyska = vyska;
		this.vaha = vaha;
	}
}
