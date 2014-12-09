package Buildings.Houses;

public class Skyscraper extends House{
	public int price = 20000;
	
	public Skyscraper(){
		super(100,200);
		najom = 200;
	}
	
	public void zvacsNajom(){
		najom *= 1.3;
	}
}
