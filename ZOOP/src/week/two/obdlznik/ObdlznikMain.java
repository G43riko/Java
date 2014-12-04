package week.two.obdlznik;

public class ObdlznikMain {
	public static void main(String args){
		Obdlznik obd = new Obdlznik(10,10,30,20);
		System.out.println("obvod je: "+obd.getObvod()+" a obsah je: "+obd.getObsah());
	}
}
