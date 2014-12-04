package Game;

import org.lwjgl.input.Keyboard;

public class Player {
	public static float hor;
	public static float ver;
	double x,y,z;
	double rx,ry,rz;
	double px,py,pz;
	double dx,dy,dz;
	double ux,uy,uz;
	double speed;
	
	public Player(double x,double y,double z){
		this.x=x;
		this.y=y;
		this.z=z;
		
		this.rx=0;
		this.ry=0;
		this.rz=0;
		
		this.dx=0;
		this.dy=0;
		this.dz=1;
		
		this.speed=0.01;
		
		this.reloadR();
		this.reloadP();
		this.reloadU();
	}
	
	public void move(){
		if(Keyboard.isKeyDown(Keyboard.KEY_W)||Keyboard.isKeyDown(Keyboard.KEY_UP)){
			this.goForward();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			this.goLeft();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)||Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			this.goBack();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			this.goRight();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			ry -= this.speed*10;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			ry += this.speed*10;
		}
	}
	
	public void reloadP(){
		this.px=this.dx;
		this.py=0;
		this.pz=this.dz;
	}
	
	public void reloadR(){
		this.rx=-this.dz/Math.sqrt(this.dz*this.dz+this.dx*this.dx);
		this.rz=this.dx/Math.sqrt(this.dz*this.dz+this.dx*this.dx);
	}
	
	public void reloadU(){
		this.ux=(-this.rz*this.dy);
		this.uy=(this.rz*this.dx-this.rx*this.dz);
		this.uz=(this.rx*this.dy);
	}
	
	public void goForward(){
		double moves=this.speed;
		this.x+=this.px*moves;
		this.y+=this.py*moves;
		this.z+=this.pz*moves;
	}
	
	public void goBack(){
		double moves=this.speed;
		this.x-=this.px*moves;
		this.y-=this.py*moves;
		this.z-=this.pz*moves;
	}
	
	public void goLeft(){
		double moves=this.speed;
		this.x-=this.rx*moves;
		this.y-=this.ry*moves;
		this.z-=this.rz*moves;
	}
	
	public void goRight(){
		double moves=this.speed;
		this.x+=this.rx*moves;
		this.y+=this.ry*moves;
		this.z+=this.rz*moves;
	}
	
	public void move(float amt, float dir){
		z += amt * Math.sin(Math.toRadians(ry + 90 * dir));
		x += amt * Math.cos(Math.toRadians(ry + 90 * dir));
	}

	public void rotateY(float amt){
		ry += amt;
	}
}
