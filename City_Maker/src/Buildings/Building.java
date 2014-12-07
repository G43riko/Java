package Buildings;

import java.util.ArrayList;

import Peoples.Clovek;

public abstract class Building implements BuildingsDo{
	
	protected int Healt, maxPeople;
	protected float moneyPerYear;
	private  ArrayList<Clovek> peopleIn;
	public int price;
	
	public static enum types {TownHall, PoliceStation, Hospital, House, Office, Factory};
	
	public Building(){
		setPeopleIn(new ArrayList<Clovek>());
	}

	public int getMaxPeople() {
		return maxPeople;
	}

	public ArrayList<Clovek> getPeopleIn() {
		return peopleIn;
	}

	public void setPeopleIn(ArrayList<Clovek> peopleIn) {
		this.peopleIn = peopleIn;
	};
	
	
	
	
}
