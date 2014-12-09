package Buildings.Infrastructures;

import Buildings.Building;
import Peoples.Obyvatel;

public class PoliceStation extends Building{
	public int price = 3000;
	
	public PoliceStation(){
		
	}

	@Override
	public void dajZamestancomVyplatu() {
		// TODO Auto-generated method stub
		
	}
	
	public void vyhodZamestnanca(Obyvatel obyvatel){
		this.getPeopleIn().remove(obyvatel);
	}
	public void vyhodZamestnanca(){
		this.getPeopleIn().remove((int)(Math.random()*this.getPeopleIn().size()));
	}
}
