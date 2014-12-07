package Buildings.Houses;

import Buildings.Building;

public class House extends Building{
	public House(int peopleMin,int peopleMax){
		this.maxPeople = (int)Math.floor(Math.random()*peopleMax)+peopleMin;
	}

	@Override
	public void dajZamestancomVyplatu() {}
}
