import java.awt.Color;


public abstract class Unit {
	protected double x,y,dx,dy,speed,angle,newX,newY;
	protected String status,owner;
	protected Color color;
	protected int healt,attack,armor,maxHealt,maxArmor;
	protected static int radius=2;
}
