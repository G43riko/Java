package Goniometry;


public class Line2d {
	private Point2d a,b;
	private double c;
	private Vector2d nv,sv;
	
	Line2d(double x1, double y1, double x2, double y2){
		this.a=new Point2d(x1, y1);
		this.b=new Point2d(x2, y2);
		this.sv = new Vector2d(this.b.getX() - this.a.getX(), this.b.getY() - this.a.getY());
		this.nv = new Vector2d(this.sv.getY(), -this.sv.getX());
		this.c = -this.nv.getX() * this.a.getX() - this.nv.getY() * this.a.getY();
	}
	
	Line2d(Point2d a, Point2d b){
		this.a=a;
		this.b=b;
		this.sv = new Vector2d(b.getX() - a.getX(), b.getY() - a.getY());
		this.nv = new Vector2d(this.sv.getY(), -this.sv.getX());
		this.c = -this.nv.getX() * a.getX() - this.nv.getY() * a.getY();
	}
	
	public double getLength(){
		return Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));
	}
	
	
}
