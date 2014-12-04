package Goniometry;

public class Vector3d {
	
	private double x,y,z;
	
	public Vector3d(double x, double y, double z){
		this.x=x;
		this.y=y;
		this.z=z;
	};
	
	public Vector3d(Point3d a, Point3d b){
		this.x=a.x-b.x;
		this.y=a.y-b.y;
		this.z=a.z-b.z;
	};
	
	public double dot(Vector3d v){
        return x * v.x + y * v.y + z * v.z;
    };
    
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
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public Vector3d cross(Vector3d v){
    	double xx = y * v.z - z * v.y;
    	double yy = z * v.y - x * v.z;
    	double zz = x * v.y - y * v.x;
        return new Vector3d(xx, yy, zz);
    };
    
	public double getLength(){
		return Math.sqrt(this.x*this.x+this.y*this.y+this.z*this.z);
	};
	
	public double Max(){
		return Math.max(this.x,Math.max(this.y,this.z));
	};
	
	public Vector3d normalize(){
		double Dlzka=this.getLength();
		double bx=this.x/Dlzka;
		double by=this.y/Dlzka;
		double bz=this.z/Dlzka;
		return new Vector3d(bx,by,bz);
	};
	
	public Vector3d Scitanie(Vector3d v){
		double bx=v.x+this.x;
		double by=v.y+this.y;
		double bz=v.z+this.z;
		return new Vector3d(bx,by,bz);
	};
	
	public Vector3d Odcitanie(Vector3d v){
		double bx=v.x-this.x;
		double by=v.y-this.y;
		double bz=v.z-this.z;
		return new Vector3d(bx,by,bz);
	};
	
	public Vector3d Nasobenie(Vector3d v){
		double bx=v.x*this.x;
		double by=v.y*this.y;
		double bz=v.z*this.z;
		return new Vector3d(bx,by,bz);
	};
	
	public Vector3d Delenie (Vector3d v){
		double bx=v.x*this.x;
		double by=v.y*this.y;
		double bz=v.z*this.z;
		return new Vector3d(bx,by,bz);
	};
	
	public Vector3d Absolute (){
		double bx=Math.abs(this.x);
		double by=Math.abs(this.y);
		double bz=Math.abs(this.z);
		return new Vector3d(bx,by,bz);
	};
}
