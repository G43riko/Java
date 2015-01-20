package week.eight;

public class A {
	static int pocet = 0; 
	private int cislo = 5;
	public A() { 
		pocet++; 
	} 
	
	public void finalize(){
		pocet--;
	}
	
	private class nieco{
		public String meno;
	}
	
	public static void main(String args[]){
		for(int i=0 ; i<29999 ; i++){
			new A();
		}
		for(int i=0 ; i<29999 ; i++){
		}
		for(int i=0 ; i<29999 ; i++){
		}
		System.out.println(A.pocet);
		A a = new A();
		a.cislo = 4;
	}
}
