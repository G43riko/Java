package Niks;

import java.util.List;


public class Pizzeria {
	
	private String name;
	private int pizzaPerHour;
	private int pocetPeci;
	private int pizzaInPec=300;
	
	private List<Kuchar> kuchary;
	
	public void setPizzaPerHour(){
		this.pizzaPerHour=3600/this.pizzaInPec*this.pocetPeci;
	}
	
	public void Pizzeria(String name){
		this.name=name;
	}
	
	public void addKuchar(Kuchar kuchar){
		kuchary.add(kuchar);
	}
	
	public void vytvorPizzu(){
		
	}
}
