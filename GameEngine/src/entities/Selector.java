package entities;

public class Selector extends BasicEntity{
	private int speed;
	
	public Selector(float x, float y, float z, float rx, float ry, float rz, float scale) {
		super(x, y, z, rx, ry, rz, scale);
		speed = 3;
	}
	
	public void goUp(){
		move(0,speed,0);
	}
	
	public void goDown(){
		move(0,-speed,0);
	}
	
	public void goForward(){
		move(0,0,speed);
	}
	public void goBack(){
		move(0,0,-speed);
	}
	
	public void goLeft(){
		move(-speed,0,0);
	}
	
	public void goRight(){
		move(speed,0,0);
	}

}
