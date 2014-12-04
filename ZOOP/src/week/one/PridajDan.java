package week.one;

import java.util.Scanner;

public class PridajDan {

	public static void main(String[] args) {
		Scanner scanner = new Scanner( System.in );
		int a;
		System.out.print( "Zadajte cenu bez dane: " );
		a = scanner.nextInt();
		System.out.println( "Predajna cena s danou (19%): "+PridajDan.pridajDan(a));
	}
	
	public static int pridajDan(int a){
		a=a+(int)a/100*19;
		return a;
	}

}
