package game.vilage.quests;

import game.vilage.buldings.Buildings;

import java.util.ArrayList;

public class Events {
	private static LittleEvents[] randEvent = new LittleEvents[15];
	
	private class LittleEvents{
		private ArrayList<String> events = new ArrayList<String>();
		public void add(String event){
			this.events.add(event);
		}
		public void get(){
			this.events.get((int)(Math.random()*this.events.size()));
		}
	}

	public Events(){
		for(int i=0 ; i<15 ; i++){
			randEvent[i] = new LittleEvents();
		}
		
		randEvent[Buildings.FARMA].add("Po�iar");
		randEvent[Buildings.FARMA].add("Kobilky");
		randEvent[Buildings.FARMA].add("Suchota");
		
		randEvent[Buildings.PEKAREN].add("My�i");
		randEvent[Buildings.PEKAREN].add("Po�iar");
		
		randEvent[Buildings.OBCHOD].add("Zlodeji");
		
		randEvent[Buildings.CHATRC_LOVCA].add("�tok medveda");
		randEvent[Buildings.CHATRC_LOVCA].add("�tok diviaka");
		randEvent[Buildings.CHATRC_LOVCA].add("Zavalenie stromom");
		randEvent[Buildings.CHATRC_LOVCA].add("Velk� zima");
		randEvent[Buildings.CHATRC_LOVCA].add("Po�iar");
		
		randEvent[Buildings.CHOVATEL_ZVIERAT].add("�tok dobytka");
		randEvent[Buildings.CHOVATEL_ZVIERAT].add("�tok vlkov");
		
		randEvent[Buildings.BANA].add("Zavalenie");
		randEvent[Buildings.BANA].add("V�buch zemn�ho plynu");
		randEvent[Buildings.BANA].add("Udusenie");
		randEvent[Buildings.BANA].add("Prebudenie draka");
		
		randEvent[Buildings.DREVORUBAC].add("Zavalenie stromom");
		randEvent[Buildings.DREVORUBAC].add("�tok medveda");
		randEvent[Buildings.DREVORUBAC].add("�tok diviaka");
		randEvent[Buildings.DREVORUBAC].add("Odseknutie kon�atiny");
		
		randEvent[Buildings.TESAR].add("Odseknutie kon�atiny");
		randEvent[Buildings.TESAR].add("Obrovsk� trieska");
		randEvent[Buildings.TESAR].add("Po�iar");
		
		randEvent[Buildings.MASIAR].add("Odseknutie kon�atiny");
		randEvent[Buildings.MASIAR].add("Nem�tve zviera");
		randEvent[Buildings.MASIAR].add("Po�iar");
		
		randEvent[Buildings.KOVAC].add("Pop�leniny");
		randEvent[Buildings.KOVAC].add("Po�iar");
		
		randEvent[Buildings.PIVOVAR].add("Zavalenie");
		randEvent[Buildings.PIVOVAR].add("Po�iar");
	}
}

