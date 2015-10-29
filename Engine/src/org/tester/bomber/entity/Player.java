package org.tester.bomber.entity;

import org.engine.app.GameAble;
import org.engine.component.object.GameObject;



public class Player extends GameObject{
	private int		speed;
	private int		healt;
	private int		range;
	private int		direction		= 2;
	private int		demage;
	private int		cadenceBonus	= 0;
	private String	name;
	private String	image;
	private boolean	moving;

	public Player(GameAble parent) {
		super(parent, null, null);
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean 		isMoving() {return moving;}
	public String 		getImage() {return image;}
	public String 		getName() {return name;}
	public int 			getSpeed() {return speed;}
	public int 			getDemage() {return demage;}
	public int 			getDirection() {return direction;}
	public int 			getCadenceBonus() {return cadenceBonus;}

	public void setDirection(int direction) {this.direction = direction;}
	public void setMoving(boolean moving) {this.moving = moving;}
}
