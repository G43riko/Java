package Buildings;

public class Offices extends Building{
	public int price = 30000;
	public Offices(){
		this.Payout = (int)Math.floor(Math.random()*200)+300;
		this.maxPeople = (int)Math.floor(Math.random()*300)+200;
	}
}
