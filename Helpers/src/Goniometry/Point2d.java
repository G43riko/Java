package Goniometry;

public class Point2d {
	private double x,y;
	
	public Point2d(double x, double y){
		this.x = x;
		this.y = y;
	};
	
	public boolean isInRect(double x, double y, double w, double h){
		if(this.x >x && this.x < x + w && this.y > y && this.y < y + h){
			return true;
		}
		return false;
	};
	
	public boolean isInCircle(double x, double y, double r){
		double dist;
		dist = (this.x - x) * (this.x - x) + (this.y - y) * (this.y - y);
		dist = Math.sqrt(dist);
		if(dist < r){
			return true;
		}
		return false;
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
	
	public void move(double x, double y){
		this.x += x;
		this.y += y;
	}
	
	public void setPos(double x,double y){
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return ("("+this.x+","+this.y+")");
	}
}
