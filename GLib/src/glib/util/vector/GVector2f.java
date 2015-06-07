package glib.util.vector;

import glib.math.GMath;

public class GVector2f {
	private float x,y;
	
	public GVector2f(){
		this(0,0);
	};
	
	public GVector2f(float x, float y){
		this.x = x;
		this.y = y;
	};
	
	public GVector2f(double x, double y){
		this.x = (float)x;
		this.y = (float)y;
	};
	
	public GVector2f(GVector2f v){
		this.x = v.getX();
		this.y = v.getY();
	};
	
	public float dot(GVector2f v){
        return x * v.getX() + y * v.getY();
    };
    
	public float getLength(){
		return (float)Math.sqrt(this.x*this.x+this.y*this.y);
	};
	
	public float max(){
		return Math.max(this.x,this.y);
	};
	
	public float min(){
		return Math.min(this.x,this.y);
	};
	
	public void normalize(){
		float dlzka = this.getLength();
		this.x /= dlzka;
		this.y /= dlzka;
	}
	
	public GVector2f Normalized(){
		float dlzka = this.getLength();
		return new GVector2f(x/dlzka, y/dlzka);
	}
	
	public float cross(GVector2f r){
		return x * r.getY() - y * r.getX();
	}
	
	public void rotate(float angle){
		float rad=(float)Math.toRadians(angle);

		float cos=(float)Math.cos(rad);
		float sin=(float)Math.sin(rad);
		this.x = (x * cos - y * sin);
		this.y = (x * sin + y * cos);
	}
	
	public float dist(GVector2f v){
		//return distance between 2 point
		float dx = x - v.x;
		float dy = y - v.y;
		return (float)Math.sqrt(dx * dx + dy * dy);
	}
	
	public GVector2f Lerp(GVector2f dest, float lerpFactor)
	{
		return dest.sub(this).mul(lerpFactor).add(this);
	}
	
	public float distSQ(GVector2f v) {
		float distX = x - v.x;
		float distY = y - v.y;
		return distX * distX + distY * distY;
	}
	
	public void negate(){
		this.x *= -1;
		this.y *= -1;
	}
	
	public float angleBetween(GVector2f v) {
		float dotProduct = dot(v);
		float angle = (float)Math.acos(dotProduct);
		return angle;
	}
	
	public GVector2f add(GVector2f v){
		return new GVector2f(x + v.getX(), y + v.getY());
	};
	
	public GVector2f add(float num){
		return new GVector2f(x + num, y + num);
	};
	
	public GVector2f sub(GVector2f v){
		return new GVector2f(x - v.getX(), y - v.getY());
	};
	
	public GVector2f sub(float num){
		return new GVector2f(x - num, y - num);
	}
	
	public GVector2f mul(GVector2f v){
		return new GVector2f(x * v.getX(), y * v.getY());
	};
	
	public GVector2f mul(float num){
		return new GVector2f(x * num, y * num);
	}
	
	public GVector2f div (GVector2f v){
		return new GVector2f(x / v.getX(), y / v.getY());
	};
	
	public GVector2f div (float num){
		return new GVector2f(x / num, y / num);
	};
	
	public GVector2f abs(){
		return new GVector2f(Math.abs(x), Math.abs(y));
	}

	public float getX() {return x;}
	public float getY() {return y;}

	public int getXi() {return (int)x;}
	public int getYi() {return (int)y;}
	
	public void setX(float x) {this.x = x;}
	public void setY(float y) {this.y = y;}
	
	public void addToY(float y){this.y += y;}
	public void addToX(float x){this.x += x;}
	
	public void set(float x, float y){this.x = x;this.y = y;}
	
	public void set(GVector2f a){set(a.getX(), a.getY());}
	
	public String toString(){
		return "["+this.x+"x"+this.y+"]";
	}
	
	public static GVector2f interpolateLinear(float scale, GVector2f startValue, GVector2f endValue) {
		GVector2f result = new GVector2f();
	    result.setX(GMath.interpolateLinear(scale, startValue.getX(), endValue.getX()));
	    result.setY(GMath.interpolateLinear(scale, startValue.getY(), endValue.getY()));
        return result;
    }
	
	public GVector2f getInstatnce(){
		return new GVector2f(this);
	}
	
	public boolean equals(GVector2f v){
		return x == v.getX() && y == v.getY();
	};
	
	public String toDecimal(int i) {
		return "["+String.format("%0"+i+"d ",(int)x)+"x"+String.format("%0"+i+"d ",(int)y)+"]";
	};
	
	public boolean isInRect(GVector2f aPos, GVector2f aSize){
		return x > aPos.getX() && x < aPos.getX() + aSize.getX() && y > aPos.getY() && y < aPos.getY() + aSize.getY();
	}
}
