package Components;

import java.util.ArrayList;

import Buildings.Building;
import Buildings.Building.types;
import Buildings.Factory;
import Buildings.Hospital;
import Buildings.House;
import Buildings.Offices;
import Buildings.PoliceStation;
import Buildings.TownHall;
import Main.City;
import Peoples.Obyvatel;

public class Pozemok {
	private Building budova;
	private ArrayList<Obyvatel> ludia = new ArrayList<Obyvatel>();
	private int x,y;
	private int cena;
	private City mesto;
	
	public Pozemok(City mesto){
		this((int)Math.floor(Math.random()*City.size),(int)Math.floor(Math.random()*City.size),mesto);
		this.mesto = mesto;
	}
	
	public Pozemok(int x, int y,City mesto){
		this.cena = (int)(Math.random()*3000)+1500;
		this.x = x;
		this.y = y;
		this.setBudova(null);
		mesto.takeMoney(this.cena);;
	}
	
	public void postav(types building) {
		switch(building.ordinal()){
			case 0:
				this.setBudova(new TownHall());
				break;
			case 1:
				this.setBudova(new PoliceStation());
				break;
			case 2:
				this.setBudova(new Hospital());
				break;
			case 3:
				this.setBudova(new House());
				break;
			case 4:
				this.setBudova(new Offices());
				break;
			case 5:
				this.setBudova(new Factory());
				break;
		}
		mesto.takeMoney(this.getBudova().price);
	}

	public Building getBudova() {
		return budova;
	}

	public void setBudova(Building budova) {
		this.budova = budova;
	}
	
}
