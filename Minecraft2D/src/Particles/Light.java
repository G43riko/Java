package Particles;
import java.awt.Color;


public class Light {
	private int lightness;
	public double x,y;
	public Color color;
	public boolean change;
	
	public Light(double x, double y,int l){
		this.x = x;
		this.y = y;
		this.change = true;
		this.lightness = l;
	}

	public int getLightness() {
		return lightness;
	}
	
	public void setLightness(int l) {
		this.lightness = l;
		this.change=true;
	}

	public boolean isChange() {
		return change;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setChange(boolean change) {
		this.change = change;
	}
	
	public void setPos(double x, double y){
		this.x = x;
		this.y = y;
		this.change=true;
	}
}
