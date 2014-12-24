package Goniometry;

public class Vector2f {
	
	private float x,y;
	
	public Vector2f(){
		this(0,0);
	};
	
	public Vector2f(float x, float y){
		this.x=x;
		this.y=y;
	};
	
	public Vector2f(Vector2f v){
		this.x = v.getX();
		this.y = v.getY();
	};
	
	public float dot(Vector2f v){
        return x * v.getX() + y * v.getY();
    };
    
	public float getLength(){
		//vráti dåžku vektora;
		return (float)Math.sqrt(this.x*this.x+this.y*this.y);
	};
	
	public float max(){
		//vráti najvaèšiu hodnotu
		return Math.max(this.x,this.y);
	};
	
	public float min(){
		//vráti najmanšiu hodnotu
		return Math.min(this.x,this.y);
	};
	
	public Vector2f normalize(){
		//vráti nový normálový vektor
		float dlzka=this.getLength();
		float bx=this.x/dlzka;
		float by=this.y/dlzka;
		return new Vector2f(bx,by);
	};
	
	public void normalizeThis(){
		//normalizuje vektor
		float dlzka=this.getLength();
		this.x/=dlzka;
		this.y/=dlzka;
	}
	
	public Vector2f rotate(float angle){
		//vráti nový otoèený vektor
		float rad=(float)Math.toRadians(angle);
		
		float cos=(float)Math.cos(rad);
		float sin=(float)Math.sin(rad);
		return new Vector2f((x*cos-y*sin),(x*sin+y*cos));
	}
	
	public void rotateThis(float angle){
		float rad=(float)Math.toRadians(angle);
		
		float cos=(float)Math.cos(rad);
		float sin=(float)Math.sin(rad);
		this.x = (x*cos-y*sin);
		this.y = (x*sin+y*cos);
	}
	
	public float dist(Vector2f v){
		//ako keby boly hodnoty suradnice
		float dx = x - v.x;
		float dy = y - v.y;
		return (float)Math.sqrt(dx * dx + dy * dy);
	}
	
	public Vector2f negate() {
        return new Vector2f(-x, -y);
    }
	
	public void negateThis(){
		this.x *= -1;
		this.y *= -1;
	}
	
	public float angleBetween(Vector2f v) {
		float dotProduct = dot(v);
		float angle = (float)Math.acos(dotProduct);
		return angle;
	}
	
	public void add(Vector2f v){
		//sèítanie
		this.x += v.getX();
		this.y += v.getY();
	};
	
	public void add(float num){
		//sèítanie
		this.x += num;
		this.y += num;
	};
	
	public void sub(Vector2f v){
		//odcitanie
		this.x -= v.getX();
		this.y -= v.getY();
	};
	
	public void sub(float num){
		this.x -= num;
		this.y -= num;
	}
	
	public void mul(Vector2f v){
		this.x *= v.getX();
		this.y *= v.getY();
	};
	
	public void mul(float num){
		this.x *= num;
		this.y *= num;
	}
	
	public void div (Vector2f v){
		this.x /= v.getX();
		this.y /= v.getY();
	};
	
	public void div (float num){
		this.x /= num;
		this.y /= num;
	};
	
	public Vector2f abs(){
		return new Vector2f(Math.abs(this.x),Math.abs(this.y));
	}
	
	public void absThis(){
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
	
	public void set(float x, float y){
		this.x = x;
		this.y = y;
	};
	
	public void set(Vector2f a){
		set(a.getX(), a.getY());
	};
	
	public String toString(){
		return "["+this.x+"x"+this.y+"]";
	}
	
	public static Vector2f interpolateLinear(float scale, Vector2f startValue, Vector2f endValue, Vector2f store) {
        if (store == null) {
            store = new Vector2f();
        }
        store.setX(interpolateLinear(scale, startValue.getX(), endValue.getX()));
        store.setY(interpolateLinear(scale, startValue.getY(), endValue.getY()));
        return store;
    }
	
	public static Vector2f interpolateLinear(float scale, Vector2f startValue, Vector2f endValue) {
        return interpolateLinear(scale, startValue, endValue, null);
    }
	
	public static float interpolateLinear(float scale, float startValue, float endValue) {
        if (startValue == endValue) {
            return startValue;
        }
        if (scale <= 0f) {
            return startValue;
        }
        if (scale >= 1f) {
            return endValue;
        }
        return ((1f - scale) * startValue) + (scale * endValue);
    }
	
	public boolean isInRect(float x, float y, float w, float h){
		//èi sa vektor so hodnotaby ako suradnicami nachádza v štvorci
		if(this.x > x && this.x < x + w && this.y > y && this.y < y + h){
			return true;
		}
		return false;
	};
	
	public boolean isInCircle(float x, float y, float r){
		double dist;
		dist = (this.x - x) * (this.x - x) + (this.y - y) * (this.y - y);
		dist = Math.sqrt(dist);
		if(dist < r){
			return true;
		}
		return false;
	}
	
	public void move(double x, double y){
		//asi zbytoèné
		this.x += x;
		this.y += y;
	}

	public boolean equals(Vector3f v){
		return x == v.getX() && y == v.getY();
	};
}
