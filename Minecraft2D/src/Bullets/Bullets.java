package Bullets;

import java.awt.Graphics2D;
import java.util.ArrayList;


public class Bullets {
	public ArrayList<BulletUniversal> bullets;
	
	public Bullets(){
		this.bullets = new ArrayList<BulletUniversal>();
	}
	
	public synchronized  void draw(Graphics2D g2){
		for(BulletUniversal bullet : this.bullets){
			bullet.draw(g2);
		}
	}
	
	public synchronized  void move(){
		for(BulletUniversal bullet : this.bullets){
			bullet.move();
		}
	}
	
	private synchronized void addBullet(double tx, double ty,int id){
		switch(id){
			case 0:
				bullets.add(BulletA.createBullet(tx, ty));
				break;
			case 1:
				bullets.add(BulletB.createBullet(tx, ty));
				break;
			case 2:
				bullets.addAll(BulletC.createBullet(tx, ty));
				break;
			case 3:
				bullets.addAll(BulletD.createBullet(tx, ty));
				break;
			case 4:
				bullets.add(BulletE.createBullet(tx, ty));
				break;
		}
	}
	
	public synchronized void fire(double tx, double ty, int id){
		this.addBullet(tx,ty,id);
	}
	
	public synchronized  void checkCollision(){
		for(int i=0 ; i<this.bullets.size() ; i++){
			if(this.bullets.get(i).checkCollion()){
				this.bullets.remove(i);
				i--;
			}
		}
	}
}
