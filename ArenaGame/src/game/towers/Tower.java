package game.towers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import game.core.Vector2f;
import game.maps.Block;
import game.units.Unit;

public class Tower {
	public static ArrayList<Tower> towers = new ArrayList<Tower>();
	
	private ArrayList<Bullet> shots = new ArrayList<Bullet>();
	private Vector2f pos;
	private float range;
	private float dps;//demage per second;
	private long lastShot,cadence;
	private Color color = Color.YELLOW;
	
	public Tower(float x, float y, float range, float dps){
		pos = new Vector2f(x,y);
		this.range = range;
		this.dps = dps;
		cadence = 1000;
		lastShot = System.currentTimeMillis();
		System.out.println("lastShot sa nastavil na: "+lastShot);
	}
	
	public void draw(Graphics2D g2){
		g2.setColor(color);
		g2.fillRect((int)(pos.getX()), (int)(pos.getY()), (int)Block.size.getX(), (int)Block.size.getY());
		
		g2.setColor(Color.BLACK);
		g2.drawArc((int)((pos.getX()+Block.size.getX()/2)-range), (int)((pos.getY()+Block.size.getY()/2)-range), (int)range*2, (int)range*2, 0, 360);
		
		for(int i=0 ; i<shots.size() ; i++){
			shots.get(i).draw(g2);
		}
	}
	
	public void move(){
		for(int i=0 ; i<shots.size() ; i++){
			if(shots.get(i).move()){
				if(shots.get(i).getTarget().takeLife(dps)){
					Unit.units.remove(shots.get(i).getTarget());
				};
				shots.remove(i);
				i--;
			}
		}
	}
	
	public static void addTower(Tower t){
		towers.add(t);
	}
	
	public void shot(){
		for(int i=0 ; i<Unit.units.size() ; i++){
			Unit u = Unit.units.get(i);
			if(System.currentTimeMillis()-lastShot>cadence&&u.getPos().dist(this.pos)<range){
				lastShot = System.currentTimeMillis();
				shots.add(new Bullet(u,this.pos.getInstance()));
				break;
			}
		}
	}
	
}
