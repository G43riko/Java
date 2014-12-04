package Peoples;

import java.util.ArrayList;

import Components.Pozemok;
import Helpers.FileLoader;
import Main.City;
import Main.Main;

public class Obyvatel extends Clovek{
	public Obyvatel(){
		this.pohlavie = (char)Math.floor(Math.random()*2);
		this.meno = FileLoader.getMeno();
		this.money = (int)(Math.random()*2000);
	}
	
	public void chodPracovat() {
		this.pozicia = pracujeKde;
		this.activita = 2;
	}
	
	public void chodDomov(){
		this.pozicia = byvamKde;
		this.activita = 0;
	}

	public void chodSpat(){
		if(pozicia!=byvamKde){
			chodDomov();
		}
		this.activita = 1;
	}

	public Obyvatel findHome(ArrayList<Pozemok> pozemky, City city) {
		for(Pozemok p:pozemky){
			if(p.getBudova()==null){
				break;
			}
			if(p.getBudova().getClass().getName()=="Buildings.House"){
				if(p.getBudova().getPeopleIn().size()<p.getBudova().getMaxPeople()){
					p.getBudova().getPeopleIn().add(this);
					return this;
				}
			};
		}
		Pozemok p =city.postavDom();
		p.getBudova().getPeopleIn().add(this);
		Main.napis("Postavil sa nový obytný dom pre "+p.getBudova().getMaxPeople()+" ludí");
		return this;
	}
	
	public Obyvatel findJob(ArrayList<Pozemok> pozemky, City city){
		for(Pozemok p:pozemky){
			if(p.getBudova()==null){
				break;
			}
			String name = p.getBudova().getClass().getName(); 
			if(name=="Buildings.Factory"||name=="Buildings.Offices"){
				if(p.getBudova().getPeopleIn().size()<p.getBudova().getMaxPeople()){
					p.getBudova().getPeopleIn().add(this);
					return this;
				}
			};
		}
		Pozemok p =city.postavRobotu();
		p.getBudova().getPeopleIn().add(this);
		Main.napis("Postavila sa nová budova: "+p.getBudova().getClass().getName()+" pre "+p.getBudova().getMaxPeople()+" ludí");
		return this;
	}

	@Override
	public int zaplatDane(City mesto) {
		int dan = (int)(((float)this.aktDan)/100*City.dan);
		this.money -= dan;
		mesto.addMoney(dan);
		this.aktDan = 0;
		return dan;
	}
}
