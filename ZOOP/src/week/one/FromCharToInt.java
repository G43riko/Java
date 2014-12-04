package week.one;

import java.util.Scanner;

public class FromCharToInt {
	public static void main(String[] args) {
		Scanner scanner = new Scanner( System.in );
		char a = scanner.next().charAt(0);
		String b = Integer.toHexString(a);
		System.out.println(a+" == "+b);
	}
}