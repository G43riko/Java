package week.eight;

public class A {
	static int pocet = 0; 
	int p = 1; 
	
	public A(int pocet) { 
		this.pocet++; 
		this.p = pocet; 
		pocet = 3; 
	} 
	
	public static void main(String args[]){
		new A(2);
		System.out.println(A.pocet);
	}
}
