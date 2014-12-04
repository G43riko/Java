package Buildings;

public class House extends Building{
	public int price = 20000;
	
	public House(){
		this.maxPeople = (int)Math.floor(Math.random()*200)+100;
	}
}
