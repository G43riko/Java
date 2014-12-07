package Components;

public class Pocasie {
	private float teplota;
	private int stavPocasia;
	
//	0 - slnecno;
//	1 - prsi;
//	2 - snezi; 
//	3 - vychrica;
	
	public Pocasie(){
		
		setRandPocasie();
		teplota = (float)Math.random()*40-10;
	}
	
	public void zacniBitSlnecno(){
		stavPocasia = 0;
	}
	
	public void zacniPrsat(){
		stavPocasia = 1;
	}
	
	public void zacniSnezit(){
		stavPocasia = 2;
	}
	
	public void zacniBitVychrica(){
		stavPocasia = 3;
	}

	public float getTeplotaCelzius() {
		return teplota;
	}

	public String getStavPocasia() {
		switch(stavPocasia){
			case 0:
				return "je slne�no";
			case 1:
				return "pr��";
			case 2:
				return "sne��";
			case 3:
				return "je v�chrica";
			default:
				return "nie�o je zle po�asie sa nezistilo";
		}
	}

	public String getMorningText() {
		switch(stavPocasia){
			case 0:
				return "kr�sne slne�n�";
			case 1:
				return "�kared� upr�an�";
			case 2:
				return "velmi usne�en�";
			case 3:
				return "riadne uf�kan�";
			default:
				return "dos� nejasn�";
		}
	}

	public String getEveningtext() {
		switch(stavPocasia){
			case 0:
				return "slne�n�";
			case 1:
				return "upr�an�";
			case 2:
				return "usne�en�";
			case 3:
				return "uf�kan�";
			default:
				return "nejasn�";
		}
	}

	public void setRandPocasie() {
		this.stavPocasia=(int)(Math.random()*4);
	}
}
