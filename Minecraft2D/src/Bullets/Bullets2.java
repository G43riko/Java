package Bullets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import Components.Block;
import Main.Main;


public class Bullets2 {
	public List<BulletA> bulletA;
	public List<BulletB> bulletB;
	public List<BulletC> bulletC;
	public List<BulletD> bulletD;
	//public List<BulletE> bulletE;
	//public List<BulletF> bulletF;
	
	public Bullets2(){
		this.bulletA = new ArrayList<BulletA>();
		this.bulletB = new ArrayList<BulletB>();
		this.bulletC = new ArrayList<BulletC>();
		this.bulletD = new ArrayList<BulletD>();
		//this.bulletE = new ArrayList<BulletE>();
		//this.bulletF = new ArrayList<BulletF>();
	}
	
	public synchronized  void draw(Graphics2D g2){
		for(BulletA bullet : this.bulletA){
			bullet.draw(g2);
		}
		for(BulletB bullet : this.bulletB){
			bullet.draw(g2);
		}
		for(BulletC bullet : this.bulletC){
			bullet.draw(g2);
		}
		for(BulletD bullet : this.bulletD){
			bullet.draw(g2);
		}
		/*
		for(BulletE bullet : this.bulletE){
			bullet.draw(g2);
		}
		for(BulletF bullet : this.bulletF){
			bullet.draw(g2);
		}
		*/
	}
	
	public synchronized  void move(){
		for(BulletA bullet : this.bulletA){
			bullet.move();
		}
		for(BulletB bullet : this.bulletB){
			bullet.move();
		}
		for(BulletC bullet : this.bulletC){
			bullet.move();
		}
		for(BulletD bullet : this.bulletD){
			bullet.move();
		}
		/*
		for(BulletE bullet : this.bulletE){
			bullet.move();
		}
		for(BulletF bullet : this.bulletF){
			bullet.move();
		}
		*/
	}
	
	private synchronized void addA(double tx, double ty){
		double x=Main.players.x+Main.players.w-5;
		double y=Main.players.y+5;
		double triangle_x = tx - x;
		double triangle_y = ty - y;
		double angle = Math.atan2(triangle_y, triangle_x);
		double dx=Math.cos(angle);
		double dy=Math.sin(angle);
		BulletA co=new BulletA(x,y, dx, dy, Color.PINK, 200, Block.size,8);
		co.setGravity(true,80,1);
		this.bulletA.add(co);
	}
	
	private synchronized void addB(double tx, double ty) {
		double x=Main.players.x+Main.players.w-5;
		double y=Main.players.y+5;
		double triangle_x = tx - x;
		double triangle_y = ty - y;
		double angle = Math.atan2(triangle_y, triangle_x);
		double dx=Math.cos(angle);
		double dy=Math.sin(angle);
		BulletB co=new BulletB(x,y, dx, dy, Color.ORANGE, 200, Block.size,8);
		this.bulletB.add(co);
	}

	private synchronized void addC(double tx, double ty) {
		double x=Main.players.x+Main.players.w-5;
		double y=Main.players.y+5;
		double triangle_x = tx - x;
		double triangle_y = ty - y;
		double angle = Math.atan2(triangle_y, triangle_x);
		for(int i=0 ; i<60 ; i++){
			double helper=angle+Math.random()*Math.PI/20-Math.PI/40;
			double dx=Math.cos(helper);
			double dy=Math.sin(helper);
			double size = Math.random()*Block.size/4;
			double speed=15+(Math.random()*4-2);
			BulletC co=new BulletC(x,y, dx, dy, Color.DARK_GRAY, 200, size,speed);
			co.setDrawColor(Color.BLACK);
			co.setGravity(true, 100,100);
			co.setExplosivity(false);
			this.bulletC.add(co);
		}
	}
	
	private synchronized void addD(double tx, double ty) {
		double x=Main.players.x+Main.players.w-5;
		double y=Main.players.y+5;
		double triangle_x = tx - x;
		double triangle_y = ty - y;
		double angle = Math.atan2(triangle_y, triangle_x);
		for(int i=0 ; i<120 ; i++){
			double helper=angle+Math.random()*Math.PI/10-Math.PI/20;
			double dx=Math.cos(helper);
			double dy=Math.sin(helper);
			double size = Math.random()*Block.size/1.5;
			double speed=5+(Math.random()*10-4);
			BulletD co=new BulletD(x,y, dx, dy, Color.GREEN, 200, size,speed);
			co.setDrawColor(Color.BLACK);
			co.setDvindle(true);
			co.setGravity(true, 40,1);
			co.setExplosivity(false);
			this.bulletD.add(co);
		}
	}
	
	public synchronized void fire(double tx, double ty, int id){
		switch(id){
			case 0:
				this.addA(tx,ty);
				break;
			case 1:
				this.addB(tx,ty);
				break;
			case 2:
				this.addC(tx,ty);
				break;
			case 3:
				this.addD(tx,ty);
				break;
				/*
			case 4:
				this.addE(tx,ty);
				break;
			case 4:
				this.addF(tx,ty);
				break;
			*/
		}
	}
	
	public synchronized  void checkCollision(){
		for(int i=0 ; i<this.bulletA.size() ; i++){
			if(this.bulletA.get(i).checkCollion()){
				this.bulletA.remove(i);
				i--;
			}
		}
		
		for(int i=0 ; i<this.bulletB.size() ; i++){
			if(this.bulletB.get(i).checkCollion()){
				this.bulletB.remove(i);
				i--;
			}
		}
		
		for(int i=0 ; i<this.bulletC.size() ; i++){
			if(this.bulletC.get(i).checkCollion()){
				this.bulletC.remove(i);
				i--;
			}
		}
		
		for(int i=0 ; i<this.bulletD.size() ; i++){
			if(this.bulletD.get(i).checkCollion()){
				this.bulletD.remove(i);
				i--;
			}
		}
		/*
		for(int i=0 ; i<this.bulletE.size() ; i++){
			if(this.bulletE.get(i).checkCollion()){
				this.bulletE.remove(i);
				i--;
			}
		}
		
		for(int i=0 ; i<this.bulletF.size() ; i++){
			if(this.bulletF.get(i).checkCollion()){
				this.bulletF.remove(i);
				i--;
			}
		}
		*/
	}
}
