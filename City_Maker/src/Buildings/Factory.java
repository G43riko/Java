package Buildings;

public class Factory extends Building{
	public int price = 70000;
	public Factory(){
		this.Payout = (int)Math.floor(Math.random()*400)+200;
		this.maxPeople = (int)Math.floor(Math.random()*400)+100;
	}

}
