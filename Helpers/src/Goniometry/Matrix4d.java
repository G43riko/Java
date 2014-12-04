package Goniometry;

public class Matrix4d {
	private double[][] m;
	
	public Matrix4d(){
		this.m=new double[4][4];
	}

	public double[][] getM() {
		return m;
	}
	
	public Matrix4d initIdentity(){
		this.m[0][0]=1;	this.m[0][1]=0;	this.m[0][2]=0;	this.m[0][3]=0;
		this.m[1][0]=0;	this.m[1][1]=1;	this.m[1][2]=0;	this.m[1][3]=0;
		this.m[2][0]=0;	this.m[2][1]=0;	this.m[2][2]=1;	this.m[2][3]=0;
		this.m[3][0]=0;	this.m[3][1]=0;	this.m[3][2]=0;	this.m[3][3]=1;
		return this;
	}
	
	public Matrix4d mul(Matrix4d r){
		Matrix4d res=new Matrix4d();
		
		for(int i=0 ; i<4 ; i++){
			for(int j=0 ; j<4 ; j++){
				r.set(i,j, m[i][0]*r.get(0,j)+
						   m[i][1]*r.get(1,j)+
						   m[i][2]*r.get(2,j)+
						   m[i][3]*r.get(3,j));
			}
		}
		
		return res;
	}
	
	public void setM(double[][] m) {
		this.m = m;
	}
	
	public double get(int x, int y) {
		return this.m[x][y];
	}
	
	public void set(int x, int y, double value){
		this.m[x][y]=value;
	}
}
