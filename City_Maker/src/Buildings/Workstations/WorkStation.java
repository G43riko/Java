package Buildings.Workstations;

import Buildings.Building;
import Buildings.BuildingsDo;
import Peoples.Clovek;

public class WorkStation extends Building {
	protected float Payout;
	
	public WorkStation(int PayoutMin,int PayoutMax, int PeopleMin, int PeopleMax){
		this.Payout = (int)Math.floor(Math.random()*(PayoutMax-PayoutMin))+PayoutMin;
		this.maxPeople = (int)Math.floor(Math.random()*(PeopleMax-PeopleMin))+PeopleMin;
	}
	
	public void dajZamestancomVyplatu(){
		for(Clovek c:getPeopleIn()){
			c.addMoney(this.Payout);
		}
	}
}
