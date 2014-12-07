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
				return "je slneèno";
			case 1:
				return "prší";
			case 2:
				return "sneí";
			case 3:
				return "je vıchrica";
			default:
				return "nieèo je zle poèasie sa nezistilo";
		}
	}

	public String getMorningText() {
		switch(stavPocasia){
			case 0:
				return "krásne slneèné";
			case 1:
				return "škaredé upršané";
			case 2:
				return "velmi usneené";
			case 3:
				return "riadne ufúkané";
			default:
				return "dos nejasné";
		}
	}

	public String getEveningtext() {
		switch(stavPocasia){
			case 0:
				return "slneènı";
			case 1:
				return "upršanı";
			case 2:
				return "usneenı";
			case 3:
				return "ufúkanı";
			default:
				return "nejasnı";
		}
	}

	public void setRandPocasie() {
		this.stavPocasia=(int)(Math.random()*4);
	}
}
