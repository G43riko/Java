package week.two;

public class Kvader {
	private float stranaA, stranaB, stranaC;
	
	public static void main(String[] args){
		Kvader a = new Kvader(1,2,3);
		Kvader b = new Kvader(1.2f, 2.3f, 3.1f);
		Kvader c = new Kvader(1203974378, 227287497, 27848927);
		
		a.setStrany(4, 5, 6);
	}

	public Kvader(int A, int B, int C){
		this.stranaA = A;
		this.stranaB = B;
		this.stranaC = C;
	}
	
	public Kvader(float A, float B, float C){
		this.stranaA = A;
		this.stranaB = B;
		this.stranaC = C;
	}
	
	public Kvader(long A, long B, long C){
		this.stranaA = A;
		this.stranaB = B;
		this.stranaC = C;
	}
	
	public void setStrany(float a, float b, float c){
		this.stranaA = a;
		this.stranaB = b;
		this.stranaC = c;
	}
	
	public float getStranaA() {
		return stranaA;
	}

	public void setStranaA(float stranaA) {
		this.stranaA = stranaA;
	}

	public float getStranaB() {
		return stranaB;
	}

	public void setStranaB(float stranaB) {
		this.stranaB = stranaB;
	}

	public float getStranaC() {
		return stranaC;
	}

	public void setStranaC(float stranaC) {
		this.stranaC = stranaC;
	}
}
