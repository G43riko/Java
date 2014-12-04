package week.one;

import java.util.Scanner;

public class HexaNum {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String hexa = in.next();
		int decimal = Integer.parseInt(hexa, 16);
		System.out.print(decimal);
	}
}
