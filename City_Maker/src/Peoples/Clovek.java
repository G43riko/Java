package Peoples;

import Buildings.Building;
import Main.City;

public abstract class Clovek {
	protected Building pracujeKde;
	protected Building byvamKde;
	protected int pohlavie;
	protected String meno = null;
	protected Building pozicia;
	protected int activita = 0;
	protected int money;
	protected int aktDan;
	
	/*
	 * 0 - undefined
	 * 1 - sleep
	 * 2 - working
	 * 3 - 
	 */
	
	public abstract void chodPracovat();
	public abstract void chodDomov();
	public abstract void chodSpat();
	public abstract int zaplatDane(City mesto);
	
	public void addMoney(float money){
		this.money += money;
		this.aktDan += money;
	}
}
