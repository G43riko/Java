package week.three.dva;

public class B extends A{
	public B(){
		System.out.println("meno v A�ku je : "+super.vek);
	}
	
	public static void main(String[] args){
		new B();
	}
}
