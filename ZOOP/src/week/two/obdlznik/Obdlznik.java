package week.two.obdlznik;

public class Obdlznik {
	
	float x,y,w,h;
	
	public Obdlznik(float x, float y, float w, float h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
	};
	
	public float getObvod(){
		return this.w*2+this.h*2;
	};
	
	public float getObsah(){
		return this.w*this.h;
	};
	
	public void setPos(float x, float y){
		this.x=x;
		this.y=y;
	};

	public double getWidth() {
		return w;
	};

	public void setWidth(float w) {
		this.w = w;
	};

	public double getHeight() {
		return h;
	};

	public void setHeight(float h) {
		this.h = h;
	};

	public double getX() {
		return x;
	};

	public double getY() {
		return y;
	};

}
