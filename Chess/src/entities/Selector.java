package entities;

import java.awt.Color;

import maps.Block;

public class Selector extends Block{
	private int speed = 4;
	
	public Selector(int x, int y, int z){
		super(x,y,z,-1);
		this.w*=1.1;
		this.h*=1.1;
		this.d*=1.1;
		
		setColor(Color.RED);
		
	}
	
	public void goUp(){
		setY(getY()+speed/2);
	}
	
	public void goDown(){
		setY(getY()-speed/2);
	}
	
	public void goForward(float angle){
		if(angle<45||angle>=315){
			setZ(getZ()-speed);
		}
		else if(angle<135&&angle>=45){
			setX(getX()+speed);
		}
		else if(angle<225&&angle>=135){
			setZ(getZ()+speed);
		}
		else if(angle<315&&angle>=225){
			setX(getX()-speed);
		}
	}
	
	public void goBack(float angle){
		if(angle<45||angle>=315){
			setZ(getZ()+speed);
		}
		else if(angle<135&&angle>=45){
			setX(getX()-speed);
		}
		else if(angle<225&&angle>=135){
			setZ(getZ()-speed);
		}
		else if(angle<315&&angle>=225){
			setX(getX()+speed);
		}
	}
	
	public void goRight(float angle){
		if(angle<45||angle>=315){
			setX(getX()+speed);
		}
		else if(angle<135&&angle>=45){
			setZ(getZ()+speed);
		}
		else if(angle<225&&angle>=135){
			setX(getX()-speed);
		}
		else if(angle<315&&angle>=225){
			setZ(getZ()-speed);
		}
	}
	
	public void goLeft(float angle){
		if(angle<45||angle>=315){
			setX(getX()-speed);
		}
		else if(angle<135&&angle>=45){
			setZ(getZ()-speed);
		}
		else if(angle<225&&angle>=135){
			setX(getX()+speed);
		}
		else if(angle<315&&angle>=225){
			setZ(getZ()+speed);
		}
	}
}
