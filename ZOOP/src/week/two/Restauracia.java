package week.two;

public class Restauracia {

	private int pocetKucharov, pocetCasnikov;
	
	public Restauracia(int... args){
		if(args.length==2){
			this.pocetKucharov=args[0];
			this.pocetCasnikov=args[1];
		}
	}
	
	public int pocetZamestnancov(){
		return this.pocetCasnikov+this.pocetKucharov;
	}
	
	public static void main(String[] args) {
		Restauracia restika = new Restauracia(10,25);
	}

	public int getPocetKucharov() {
		return pocetKucharov;
	}

	public void setPocetKucharov(int pocetKucharov) {
		this.pocetKucharov = pocetKucharov;
	}

	public int getPocetCasnikov() {
		return pocetCasnikov;
	}

	public void setPocetCasnikov(int pocetCasnikov) {
		this.pocetCasnikov = pocetCasnikov;
	}

}
