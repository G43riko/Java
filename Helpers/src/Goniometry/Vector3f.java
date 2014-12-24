package Goniometry;

public class Vector3f {
	private float x,y,z;
	
	public Vector3f(){
		this(0,0,0);
	}
	
	public Vector3f(float x, float y, float z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public Vector3f(Vector3f v){
		this.x = v.getX();
		this.y = v.getY();
		this.z = v.getZ();
	}
	
	public float getLength(){
		//vráti dåžku vektora;
		return (float)Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
	};
	
	public float max(){
		return Math.max(x, Math.max(y,z));
	}
	
	public float min(){
		return Math.min(x, Math.min(y,z));
	}
	
	public float Dot(Vector3f v){
		return x * v.getX() + y * v.getY() + z * v.getZ();
	}
	
	public Vector3f Cross(Vector3f v){
		float x_ = y * v.getZ() - z * v.getY();
		float y_ = z * v.getX() - x * v.getZ();
		float z_ = x * v.getY() - y * v.getX();
		
		return new Vector3f(x_, y_, z_);
	}
	
	public Vector3f normalize(){
		float length = getLength();
		
		return new Vector3f(x / length, y / length, z / length);
	}
	
	public void normalizeThis(){
		float length = getLength();
		x /= length;
		y /= length;
		z /= length;
	}
	
	public void Rotate(Vector3f axis, float angle){
		//komplikované 
	}
	
	public void add(Vector3f v){
		x += v.getX();
		y += v.getY();
		z += v.getZ();
	}
	
	public void add(float num){
		x += num;
		y += num;
		z += num;
	}
	
	public void sub(Vector3f v){
		x -= v.getX();
		y -= v.getY();
		z -= v.getZ();
	}
	
	public void sub(float num){
		x -= num;
		y -= num;
		z -= num;
	}
	
	public void mul(Vector3f v){
		x *= v.getX();
		y *= v.getY();
		z *= v.getZ();
	}
	
	public void mul(float num){
		x *= num;
		y *= num;
		z *= num;
	}
	
	public void div(Vector3f v){
		x /= v.getX();
		y /= v.getY();
		z /= v.getZ();
	}
	
	public void div(float num){
		x /= num;
		y /= num;
		z /= num;
	}
	
	public Vector3f abs(){
		return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
	}
	
	public void absThis(){
		x = Math.abs(x);
		y = Math.abs(y);
		z = Math.abs(z);
	}
	
	public String toString()
	{
		return "(" + x + " " + y + " " + z + ")";
	}
	
	public Vector2f getXY() { return new Vector2f(x, y); }
	public Vector2f getYZ() { return new Vector2f(y, z); }
	public Vector2f getZX() { return new Vector2f(z, x); }
	public Vector2f getYX() { return new Vector2f(y, x); }
	public Vector2f getZY() { return new Vector2f(z, y); }
	public Vector2f getXZ() { return new Vector2f(x, z); }

	public void set(float x, float y, float z){ 
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public void set(Vector3f r) { 
		set(r.getX(), r.getY(), r.getZ()); 
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
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	};
	
	public boolean equals(Vector3f v){
		return x == v.getX() && y == v.getY() && z == v.getZ();
	}
}
