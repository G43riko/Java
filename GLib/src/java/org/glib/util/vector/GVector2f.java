package java.org.glib.util.vector;

public class GVector2f {
	private float x,y;
	
	public GVector2f(){
		this(0,0);
	};
	
	public GVector2f(float x, float y){
		this.x=x;
		this.y=y;
	};
	
	public GVector2f(GVector2f v){
		this.x = v.getX();
		this.y = v.getY();
	};
	
	public float dot(GVector2f v){
		//create dot product
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
		float dlzka=this.getLength();
		this.x/=dlzka;
		this.y/=dlzka;
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
	
	public void negate(){
		this.x *= -1;
		this.y *= -1;
	}
	
	public float angleBetween(GVector2f v) {
		float dotProduct = dot(v);
		float angle = (float)Math.acos(dotProduct);
		return angle;
	}
	
	public void add(GVector2f v){
		this.x += v.getX();
		this.y += v.getY();
	};
	
	public void add(float num){
		this.x += num;
		this.y += num;
	};
	
	public void sub(GVector2f v){
		this.x -= v.getX();
		this.y -= v.getY();
	};
	
	public void sub(float num){
		this.x -= num;
		this.y -= num;
	}
	
	public void mul(GVector2f v){
		this.x *= v.getX();
		this.y *= v.getY();
	};
	
	public void mul(float num){
		this.x *= num;
		this.y *= num;
	}
	
	public void div (GVector2f v){
		this.x /= v.getX();
		this.y /= v.getY();
	};
	
	public void div (float num){
		this.x /= num;
		this.y /= num;
	};
	
	public void abs(){
		this.x = Math.abs(this.x);
		this.y = Math.abs(this.y);
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
	
	public void addToY(float y){
		this.y += y;
	};
	
	public void addToX(float x){
		this.x += x;
	};
	
	public void set(float x, float y){
		this.x = x;
		this.y = y;
	};
	
	public void set(GVector2f a){
		set(a.getX(), a.getY());
	};
	
	public String toString(){
		return "["+this.x+"x"+this.y+"]";
	}
	
	public static GVector2f interpolateLinear(float scale, GVector2f startValue, GVector2f endValue, GVector2f store) {
        if (store == null)
            store = new GVector2f();
        store.setX(interpolateLinear(scale, startValue.getX(), endValue.getX()));
        store.setY(interpolateLinear(scale, startValue.getY(), endValue.getY()));
        return store;
    }
	
	public static GVector2f interpolateLinear(float scale, GVector2f startValue, GVector2f endValue) {
        return interpolateLinear(scale, startValue, endValue, null);
    }
	
	public static float interpolateLinear(float scale, float startValue, float endValue) {
        if (startValue == endValue)
            return startValue;
        
        if (scale <= 0f)
            return startValue;
        
        if (scale >= 1f)
            return endValue;
        
        return ((1f - scale) * startValue) + (scale * endValue);
    }
	
	public boolean isInRect(float x, float y, float w, float h){
		if(this.x > x && this.x < x + w && this.y > y && this.y < y + h)
			return true;
		return false;
	};
	
	public boolean isInCircle(float x, float y, float r){
		double dist;
		dist = (this.x - x) * (this.x - x) + (this.y - y) * (this.y - y);
		dist = Math.sqrt(dist);
		if(dist < r)
			return true;
		return false;
	}
	
	public GVector2f getInstatnce(){
		return new GVector2f(this);
	}
	
	public boolean equals(GVector2f v){
		return x == v.getX() && y == v.getY();
	};
}
