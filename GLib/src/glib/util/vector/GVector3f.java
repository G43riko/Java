package glib.util.vector;

import glib.math.GMath;

public class GVector3f {
	private float x;
	private float y;
	private float z;
	
	public GVector3f(){
		this(0,0,0);
	}
	
	public GVector3f(String n){
		this(Math.random(),Math.random(),Math.random());
	}
	
	public GVector3f(float val){
		this.x = val;
		this.y = val;
		this.z = val;
	}
	
	public GVector3f(double x, double y, double z){
		this.x = (float)x;
		this.y = (float)y;
		this.z = (float)z;
	}
	
	public GVector3f(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
		
	public GVector3f(GVector2f v, float z){
		this.x = v.getX();
		this.y = v.getY();
		this.z = z;
	}
	
	public GVector3f(GVector3f v){
		this.x = v.getX();
		this.y = v.getY();
		this.z = v.getZ();
	}
	
	public GVector3f randomize(double angle){
		float ang = (float)(angle/1.5);
		return this.add(new GVector3f((float)(ang*Math.random()-ang/2),
									  (float)(ang*Math.random()-ang/2),
									  (float)(ang*Math.random()-ang/2)));
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
	
	public float dot(GVector3f v){
		return x * v.getX() + y * v.getY() + z * v.getZ();
	}
	
	public GVector3f cross(GVector3f v){
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
	
	public GVector3f toRadians(){
		return new GVector3f((float)Math.toRadians(x), (float)Math.toRadians(y), (float)Math.toRadians(z));
	}
	
	public GVector3f toDegrees(){
		return new GVector3f((float)Math.toDegrees(x), (float)Math.toDegrees(y), (float)Math.toDegrees(z));
	}
	
//	public GVector3f eulerToDirectional(){
//		//x = pitch
//		//y = yaw
//		//t = roll;
//		GVector3f v = this.getInstance();
//		double rotX = Math.cos(v.getY())*Math.cos(v.getX());
//		double rotY = Math.sin(v.getY())*Math.cos(v.getX());
//		double rotZ = Math.sin(v.getX());
//		
////		return new GVector3f((float)rotX, (float)rotY, (float)rotZ);
//		return new GVector3f((float)-rotY, (float)rotZ, (float)rotX);
//	}
	
	public GVector3f add(GVector3f v){
		return new GVector3f(x + v.getX(), y + v.getY(), z + v.getZ());
	}
	
	public GVector3f add(float num){
		return new GVector3f(x + num, y + num, z + num);
	}
	
	public GVector3f sub(GVector3f v){
		return new GVector3f(x - v.getX(), y - v.getY(), z - v.getZ());
	}
	
	public GVector3f sub(float num){
		return new GVector3f(x - num, y - num, z - num);
	}
	
	public GVector3f mul(GVector3f v){
		return new GVector3f(x * v.getX(), y * v.getY(), z * v.getZ());
	}
	
	public GVector3f mul(float num){
		return new GVector3f(x * num, y * num, z * num);
	}
	
	public GVector3f div(GVector3f v){
		return new GVector3f(x / v.getX(), y / v.getY(), z / v.getZ());
	}
	
	public GVector3f div(float num){
		return new GVector3f(x / num, y / num, z / num);
	}
	
	public GVector3f Rotate(GVector3f axis, float angle){
		float sinAngle = (float)Math.sin(-angle);
		float cosAngle = (float)Math.cos(-angle);

		return this.cross(axis.mul(sinAngle)).add(           //Rotation on local X
				(this.mul(cosAngle)).add(                     //Rotation on local Z
						axis.mul(this.dot(axis.mul(1 - cosAngle))))); //Rotation on local Y
	}
	
	public boolean isNull(){
		return x==0.0 && y==0.0 && z==0.0;
	}
	
	public boolean isNegative(){
		return x<=0 && y<=0 && z<=0;
	}
	
	public GVector3f Rotate(GQuaternion rotation){
		//rotation = new Quaternion(axis, angle);
		GQuaternion conjugate = rotation.conjugate();

		GQuaternion w = rotation.mul(this).mul(conjugate);

		return new GVector3f(w.getX(), w.getY(), w.getZ());
	}
	
	public GVector3f abs(){
		return new GVector3f(Math.abs(x), Math.abs(y), Math.abs(z));
	}
	
	public static boolean intersectRayWithSquare(GVector3f R1, GVector3f R2, GVector3f S1, GVector3f S2, GVector3f S3) {
		 GVector3f dS21 = S2.sub(S1);
		 GVector3f dS31 = S3.sub(S1);
		 GVector3f n = dS21.cross(dS31);

		 GVector3f dR = R1.sub(R2);

		 float ndotdR = n.dot(dR);

		 if (Math.abs(ndotdR) < 1e-6f) { // Choose your tolerance
			 return false;
		 }

		 float t = -n.dot(R1.sub(S1)) / ndotdR;
		 GVector3f M = R1.add(dR.mul(t));

		 GVector3f dMS1 = M.sub(S1);
		 float u = dMS1.dot(dS21);
		 float v = dMS1.dot(dS31);

		 return (u >= 0.0f && u <= dS21.dot(dS21)&& v >= 0.0f && v <= dS31.dot(dS31));
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
		return temp1.cross(temp2).getLength()/temp1.getLength();
	}
	
	public GVector3f getInstance(){return new GVector3f(x,y,z); }
	
	public GVector2f getXY() { return new GVector2f(x, y); }
	public GVector2f getYZ() { return new GVector2f(y, z); }
	public GVector2f getZX() { return new GVector2f(z, x); }
	public GVector2f getYX() { return new GVector2f(y, x); }
	public GVector2f getZY() { return new GVector2f(z, y); }
	public GVector2f getXZ() { return new GVector2f(x, z); }

	public float average(){
		return (x+y+z) / 3;
	}
	
	public GVector3f set(float x, float y, float z){ this.x = x; this.y = y; this.z = z; return this; }
	public GVector3f set(GVector3f r) { set(r.getX(), r.getY(), r.getZ()); return this; }
	
	public GVector3f addToX(float x){
		this.x += x; 
		return this; 
	}
	
	public GVector3f addToY(float y){
		this.y += y; 
		return this; 
	}
	
	public GVector3f addToZ(float z){
		this.z += z; 
		return this; 
	}
	
	public float getX() {return x;}
	public float getY() {return y;}
	public float getZ() {return z;}
	public int getXi() {return (int)x;}
	public int getYi() {return (int)y;}
	public int getZi() {return (int)z;}
	
	public void setX(float x) {this.x = x;}
	public void setY(float y) {this.y = y;}
	public void setZ(float z) {this.z = z;};

	public String toString(){
		return "(" + x + " " + y + " " + z + ")";
	}
	
	public boolean equals(Object o){
		GVector3f v =(GVector3f)o;
		return x == v.getX() && y == v.getY() && z == v.getZ();
	}

}
