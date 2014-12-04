package Goniometry;

public class Quaternion {
	private double x,y,z,w;
	
	public Quaternion(double x, double y,double z,double w ){
		this.x=x;
		this.y=y;
		this.z=z;
		this.w=w;
	}
	
	public double length(){
		return Math.sqrt(x*x+y*y+z*z+w*w);
	}
	
	public Quaternion Normalize(){
		double length=this.length();
		this.x/=length;
		this.y/=length;
		this.z/=length;
		this.w/=length;
		
		return this;
	}
	public Quaternion Conjugate(){
		return new Quaternion(-this.x,-this.y,-this.z,this.w);
	}
	
	public Quaternion Mul(Quaternion r){
		double w_=this.w * r.w - this.x*r.x - this.y*r.y - this.z*r.z;
		double x_=this.x * r.w + this.w*r.x + this.y*r.z - this.z*r.y;
		double y_=this.y * r.w + this.w*r.y + this.z*r.x - this.x*r.z;
		double z_=this.z * r.w + this.w*r.z + this.x*r.y - this.y*r.x;
		return new Quaternion(x_,y_,z_,w_);
	}
	
	public Quaternion Mul(Vector3d r){
		double w_=-this.x * r.getX() - this.y*r.getY() - this.z*r.getZ();
		double x_= this.w * r.getX() + this.y*r.getZ() + this.z*r.getY();
		double y_= this.w * r.getY() + this.z*r.getX() + this.x*r.getZ();
		double z_= this.w * r.getZ() + this.x*r.getY() + this.y*r.getX();
		
		return new Quaternion(x_,y_,z_,w_);
	}
}
