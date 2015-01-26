package glib.util.vector;

import glib.math.GMath;

public class GVector3f {
	private float x,y,z;
	
	public GVector3f(){
		this(0,0,0);
	}
	
	public GVector3f(float x, float y, float z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public GVector3f(GVector3f v){
		this.x = v.getX();
		this.y = v.getY();
		this.z = v.getZ();
	}
	
	public float getLength(){
		return (float)Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
	};
	
	public float dist(GVector3f vec){
		float distX = x-vec.getX();
		float distY = y-vec.getY();
		float distZ = z-vec.getZ();
		return (float)Math.sqrt(Math.pow((distX), 2) + Math.pow((distY), 2) + Math.pow((distZ), 2));
	}
	
	public float distSQ(GVector3f v) {
		float distX = x - v.x;
		float distY = y - v.y;
		float distZ = z - v.z;
		return distX * distX + distY * distY + distZ * distZ;
	}
	
	public float max(){
		return Math.max(x, Math.max(y,z));
	}
	
	public float min(){
		return Math.min(x, Math.min(y,z));
	}
	
	public float Dot(GVector3f v){
		return x * v.getX() + y * v.getY() + z * v.getZ();
	}
	
	public GVector3f Cross(GVector3f v){
		float x_ = y * v.getZ() - z * v.getY();
		float y_ = z * v.getX() - x * v.getZ();
		float z_ = x * v.getY() - y * v.getX();
		
		return new GVector3f(x_, y_, z_);
	}
	
	public GVector3f normalize(){
		float length = getLength();
		x /= length;
		y /= length;
		z /= length;
		return this;
	}
	
	public GVector3f Normalized(){
		float length = getLength();
		return new GVector3f(x / length, y / length, z / length);
	}
	
	public GVector3f Lerp(GVector3f dest, float lerpFactor){
		return dest.sub(this).mul(lerpFactor).add(this);
	}
	
	public GVector3f add(GVector3f v){
		x += v.getX();
		y += v.getY();
		z += v.getZ();
		return this;
	}
	
	public GVector3f add(float num){
		x += num;
		y += num;
		z += num;
		return this;
	}
	
	public GVector3f sub(GVector3f v){
		x -= v.getX();
		y -= v.getY();
		z -= v.getZ();
		return this;
	}
	
	public GVector3f sub(float num){
		x -= num;
		y -= num;
		z -= num;
		return this;
	}
	
	public GVector3f mul(GVector3f v){
		x *= v.getX();
		y *= v.getY();
		z *= v.getZ();
		return this;
	}
	
	public GVector3f mul(float num){
		x *= num;
		y *= num;
		z *= num;
		return this;
	}
	
	public GVector3f div(GVector3f v){
		x /= v.getX();
		y /= v.getY();
		z /= v.getZ();
		return this;
	}
	
	public GVector3f div(float num){
		x /= num;
		y /= num;
		z /= num;
		return this;
	}
	
	public GVector3f Rotate(GVector3f axis, float angle){
		float sinAngle = (float)Math.sin(-angle);
		float cosAngle = (float)Math.cos(-angle);

		return this.Cross(axis.mul(sinAngle)).add(           //Rotation on local X
				(this.mul(cosAngle)).add(                     //Rotation on local Z
						axis.mul(this.Dot(axis.mul(1 - cosAngle))))); //Rotation on local Y
	}
	
	public GVector3f Rotate(GQuaternion rotation){
		//rotation = new Quaternion(axis, angle);
		GQuaternion conjugate = rotation.conjugate();

		GQuaternion w = rotation.mul(this).mul(conjugate);

		return new GVector3f(w.getX(), w.getY(), w.getZ());
	}
	
	public GVector3f abs(){
		x = Math.abs(x);
		y = Math.abs(y);
		z = Math.abs(z);
		return this;
	}
	
	public String toString(){
		return "(" + x + " " + y + " " + z + ")";
	}
	
	public static GVector3f interpolateLinear(float scale, GVector3f startValue, GVector3f endValue) {
		GVector3f result = new GVector3f();
	    result.setX(GMath.interpolateLinear(scale, startValue.getX(), endValue.getX()));
	    result.setY(GMath.interpolateLinear(scale, startValue.getY(), endValue.getY()));
	    result.setZ(GMath.interpolateLinear(scale, startValue.getZ(), endValue.getZ()));
        return result;
    }
	
	public static float distanceVectorPoint(GVector3f a, GVector3f b, GVector3f point){
		GVector3f temp1 = a.sub(b);
		GVector3f temp2 = b.sub(point);
		return temp1.Cross(temp2).getLength()/temp1.getLength();
	}
	
	public GVector3f getInstance(){return new GVector3f(x,y,z); }
	
	public GVector2f getXY() { return new GVector2f(x, y); }
	public GVector2f getYZ() { return new GVector2f(y, z); }
	public GVector2f getZX() { return new GVector2f(z, x); }
	public GVector2f getYX() { return new GVector2f(y, x); }
	public GVector2f getZY() { return new GVector2f(z, y); }
	public GVector2f getXZ() { return new GVector2f(x, z); }

	public GVector3f set(float x, float y, float z){ this.x = x; this.y = y; this.z = z; return this; }
	public GVector3f set(GVector3f r) { set(r.getX(), r.getY(), r.getZ()); return this; }
	
	public void addToY(float y){this.y += y;}
	public void addToX(float x){this.x += x;}
	public void addToZ(float z){this.z += z;}
	
	public float getX() {return x;}
	public float getY() {return y;}
	public float getZ() {return z;}
	
	public void setX(float x) {this.x = x;}
	public void setY(float y) {this.y = y;}
	public void setZ(float z) {this.z = z;};
	
	public boolean equals(GVector3f v){
		return x == v.getX() && y == v.getY() && z == v.getZ();
	}
}
