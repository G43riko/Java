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
		
		randEvent[Buildings.FARMA].add("Požiar");
		randEvent[Buildings.FARMA].add("Kobilky");
		randEvent[Buildings.FARMA].add("Suchota");
		
		randEvent[Buildings.PEKAREN].add("Myši");
		randEvent[Buildings.PEKAREN].add("Požiar");
		
		randEvent[Buildings.OBCHOD].add("Zlodeji");
		
		randEvent[Buildings.CHATRC_LOVCA].add("Útok medveda");
		randEvent[Buildings.CHATRC_LOVCA].add("Útok diviaka");
		randEvent[Buildings.CHATRC_LOVCA].add("Zavalenie stromom");
		randEvent[Buildings.CHATRC_LOVCA].add("Velká zima");
		randEvent[Buildings.CHATRC_LOVCA].add("Požiar");
		
		randEvent[Buildings.CHOVATEL_ZVIERAT].add("Útok dobytka");
		randEvent[Buildings.CHOVATEL_ZVIERAT].add("Útok vlkov");
		
		randEvent[Buildings.BANA].add("Zavalenie");
		randEvent[Buildings.BANA].add("Výbuch zemného plynu");
		randEvent[Buildings.BANA].add("Udusenie");
		randEvent[Buildings.BANA].add("Prebudenie draka");
		
		randEvent[Buildings.DREVORUBAC].add("Zavalenie stromom");
		randEvent[Buildings.DREVORUBAC].add("Útok medveda");
		randEvent[Buildings.DREVORUBAC].add("Útok diviaka");
		randEvent[Buildings.DREVORUBAC].add("Odseknutie konèatiny");
		
		randEvent[Buildings.TESAR].add("Odseknutie konèatiny");
		randEvent[Buildings.TESAR].add("Obrovská trieska");
		randEvent[Buildings.TESAR].add("Požiar");
		
		randEvent[Buildings.MASIAR].add("Odseknutie konèatiny");
		randEvent[Buildings.MASIAR].add("Nemàtve zviera");
		randEvent[Buildings.MASIAR].add("Požiar");
		
		randEvent[Buildings.KOVAC].add("Popáleniny");
		randEvent[Buildings.KOVAC].add("Požiar");
		
		randEvent[Buildings.PIVOVAR].add("Zavalenie");
		randEvent[Buildings.PIVOVAR].add("Požiar");
	}
}

