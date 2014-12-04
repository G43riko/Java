package week.two.bod;


public class Point{
	private float a,b,c;
	
	public Point(){
		
	}
	
	public Point(float a, float b, float c){
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public float getA() {
		return a;
	}

	public void setA(float a) {
		this.a = a;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}

	public float getC() {
		return c;
	}

	public void setC(float c) {
		this.c = c;
	}
	
	public void setPos(float a, float b, float c){
		this.a = a;
		this.b = b;
		this.c = c;
	}
	public String toString(){
		return this.a+" "+this.b+" "+this.c+"\n";
	}
}
