package Main;

import java.util.ArrayList;

import Buildings.Building;
import Buildings.Building.types;
import Components.Pocasie;
import Components.Pozemok;
import Peoples.Clovek;
import Peoples.Obyvatel;

public class City {
	public static int days;
	public static int size;
	public static float dan = 1.5f;
	private int money;
	private String name;
	private ArrayList<Pozemok> pozemky = new ArrayList<Pozemok>();
	private ArrayList<Obyvatel> obyvatelia = new ArrayList<Obyvatel>();
	private Pocasie pocasie = new Pocasie();
	
	
	public City(String Name){
		days=0;
		this.name = Name;
		if(Main.vypisy){
			System.out.println("mesto "+Name+" bolo založené");
		}
	}
	
	public void initCity(){
		money = Constants.PENAZY_NA_ZACIATKU;
		pozemky.clear();
		obyvatelia.clear();
		Main.napis(name+" bol postaveny");
		
		for(int i=0 ; i<Constants.POZEMKOV_NA_ZACIATKU ; i++){
			kupPozemok();
		}

		postav(Building.types.TownHall);
		postav(Building.types.House);
		
		Main.napis("bolo kúpených "+Constants.POZEMKOV_NA_ZACIATKU+" pozemkov");
		
		for(int i=0 ; i<Constants.LUDI_NA_ZACIATKU ; i++){
			obyvatelia.add(new Obyvatel((int)(Math.random()*2)).findHome(pozemky,this).findJob(pozemky, this));
		}
		
		Main.napis("bolo pridaných "+Constants.LUDI_NA_ZACIATKU+" obyvatelov");
	}

	public void day() {
		days++;
		pocasie.setRandPocasie();
		Main.napis("\nje "+pocasie.getMorningText()+" ráno, "+days+" dní od založenia");
		
		doAtMorning();
		Main.napis("dnes tu máme "+pocasie.getEveningtext()+" veèer tak dobrú noc");
		if(days%7==0){
			for(Pozemok p:pozemky){
				if(p.getBudova()!=null){
					p.getBudova().dajZamestancomVyplatu();
				}
			}
		}
		if(days%30==0){
			int suma = 0;
			for(Obyvatel p:obyvatelia){
				suma += p.zaplatDane(this);
			}
			System.out.println("teraz ludia zaplatili "+suma+" na daniach!!!");
		}
		
		importPeople();
		doAtEvening();
		Main.napis("Mesto má aktúalne " + this.money + " penazí");
	}

	private void importPeople() {
		int num = (int)(Math.random()*this.obyvatelia.size()/20);
		for(int i=0 ; i<num ; i++){
			this.obyvatelia.add(new Obyvatel((int)(Math.random()*2)).findHome(pozemky,this).findJob(pozemky, this));
		}
		
		Main.napis("Dnes sa pristahovalo do mesta " + num + " obyvatelov, vytajte nový obyvatelia " + name + " :)");
		Main.napis(name + " má teraz "+this.obyvatelia.size() + " obyvatelov");
	}

	private void doAtEvening() {
		for(Clovek c:obyvatelia){
			c.chodDomov();
		}
		for(Clovek c:obyvatelia){
			c.chodSpat();
		}
	}

	private void doAtMorning() {
		for(Clovek c:obyvatelia){
			c.chodPracovat();
		}
	}
	
	public Pozemok postavRobotu() {
		int num = (int)Math.floor(Math.random()*2);
		for(Pozemok p: pozemky){
			if(p.getBudova() == null){
				switch(num){
					case 0:
						p.postav(Building.types.Office);
						break;
					case 1:
						p.postav(Building.types.Factory);
						break;
				}
				return p;
			}
		}
		Pozemok novy = kupPozemok();
		switch(num){
			case 0:
				novy.postav(Building.types.Office);
				break;
			case 1:
				novy.postav(Building.types.Factory);
				break;
		}
		
		return novy;
	}
	
	public Pozemok postav(types building){
		for(Pozemok p: pozemky){
			if(p.getBudova() == null){
				p.postav(building);
				return p;
			}
		}
		Pozemok novy = kupPozemok(); 
		novy.postav(building);
		return novy;
	}
	
	public Pozemok postavDom() {

		for(Pozemok p: pozemky){
			if(p.getBudova() == null){
				p.postav(Building.types.House);
				return p;
			}
		}
		Pozemok novy = kupPozemok(); 
		novy.postav(Building.types.House);
		return novy;
	}
	
	public Pozemok kupPozemok(){
		if(Main.vypisy){
			System.out.println("Bol kúpený "+(pozemky.size()+1)+" pozemok");
		}
		Pozemok novy = new Pozemok(this);
		pozemky.add(novy);
		return novy;
	}
	
	public void takeMoney(int money){
		this.money -= money;
	}
	
	public void addMoney(int money){
		this.money += money;
	}

}
