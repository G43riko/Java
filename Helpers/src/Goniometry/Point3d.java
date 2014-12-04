package Goniometry;

public class Point3d {
	double x,y,z;
	
	public Point3d(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
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

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	public void move(double x, double y, double z){
		this.x += x;
		this.y += y;
		this.z += z;
	}
	
	public void setPos(double x,double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public String toString(){
		return ("("+this.x+","+this.y+","+this.z+")");
	}
}
