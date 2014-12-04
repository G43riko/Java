package week.three;

public class H {
	private String meno;
	private int numero;
	
	public static int pocetInstancii=0;
	
	public static void main(String[] args){
		H[] zoznam = new H[10];
		
		for(int i=0 ; i<10 ; i++){
			zoznam[i]= new H();
		}
	}
	
	public H(){
		H.pocetInstancii++;
		System.out.println("Aktualne je vytvorených "+H.pocetInstancii+" inštancií");
	}
}
