package Goniometry;

public class Vector2d {
	
	private double x,y;
	
	public Vector2d(double x, double y){
		this.x=x;
		this.y=y;
	};
	
	public Vector2d(Point2d a, Point2d b){
		this.x=a.getX()-b.getX();
		this.y=a.getY()-b.getY();
	};
	
	public double dot(Vector2d v){
        return x * v.x + y * v.y;
    };
    
	public double getLength(){
		return Math.sqrt(this.x*this.x+this.y*this.y);
	};
	
	public double max(){
		return Math.max(this.x,this.y);
	};
	
	public Vector2d normalize(){
		double Dlzka=this.getLength();
		double bx=this.x/Dlzka;
		double by=this.y/Dlzka;
		return new Vector2d(bx,by);
	};
	
	public Vector2d rotate(double angle){
		double rad=Math.toRadians(angle);
		double cos=Math.cos(rad);
		double sin=Math.sin(rad);
		return new Vector2d((x*cos-y*sin),(x*sin+y*cos));
		
	}
	
	public Vector2d Scitanie(Vector2d v){
		double bx=v.x+this.x;
		double by=v.y+this.y;
		return new Vector2d(bx,by);
	};
	
	public Vector2d Odcitanie(Vector2d v){
		double bx=v.x-this.x;
		double by=v.y-this.y;
		return new Vector2d(bx,by);
	};
	
	public Vector2d Nasobenie(Vector2d v){
		double bx=v.x*this.x;
		double by=v.y*this.y;
		return new Vector2d(bx,by);
	};
	
	public Vector2d Delenie (Vector2d v){
		double bx=v.x*this.x;
		double by=v.y*this.y;
		return new Vector2d(bx,by);
	};
	
	public Vector2d absolute (){
		double bx=Math.abs(this.x);
		double by=Math.abs(this.y);
		return new Vector2d(bx,by);
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
	};
}
