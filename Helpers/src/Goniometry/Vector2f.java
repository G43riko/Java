package Goniometry;

public class Vector2f {
	
	private float x,y;
	
	public Vector2f(float x, float y){
		this.x=x;
		this.y=y;
	};
	
	public Vector2f(Point2f a, Point2f b){
		this.x=a.getX()-b.getX();
		this.y=a.getY()-b.getY();
	};
	
	public double dot(Vector2f v){
        return x * v.x + y * v.y;
    };
    
	public float getLength(){
		return (float)Math.sqrt(this.x*this.x+this.y*this.y);
	};
	
	public double max(){
		return Math.max(this.x,this.y);
	};
	
	public Vector2f normalize(){
		float Dlzka=this.getLength();
		float bx=this.x/Dlzka;
		float by=this.y/Dlzka;
		return new Vector2f(bx,by);
	};
	
	public Vector2f rotate(float angle){
		float rad=(float)Math.toRadians(angle);
		float cos=(float)Math.cos(rad);
		float sin=(float)Math.sin(rad);
		return new Vector2f((x*cos-y*sin),(x*sin+y*cos));
		
	}
	
	public Vector2f Scitanie(Vector2f v){
		float bx=v.x+this.x;
		float by=v.y+this.y;
		return new Vector2f(bx,by);
	};
	
	public Vector2f Odcitanie(Vector2f v){
		float bx=v.x-this.x;
		float by=v.y-this.y;
		return new Vector2f(bx,by);
	};
	
	public Vector2f Nasobenie(Vector2f v){
		float bx=v.x*this.x;
		float by=v.y*this.y;
		return new Vector2f(bx,by);
	};
	
	public Vector2f Delenie (Vector2f v){
		float bx=v.x*this.x;
		float by=v.y*this.y;
		return new Vector2f(bx,by);
	};
	
	public Vector2f absolute (){
		float bx=Math.abs(this.x);
		float by=Math.abs(this.y);
		return new Vector2f(bx,by);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	};
	
	public String toString(){
		return "["+this.x+"x"+this.y+"]";
	}
	
	
}
