package week.one;

import java.util.Scanner;



public class TriCisla {
	public static void main(String[] argc){
		Scanner scanner = new Scanner( System.in );
		int max = 0, min=0, a, b, c;
		System.out.println( "Zadajte tri ��sla oddelen� medzerov: " );
		a = scanner.nextInt();
		b = scanner.nextInt();
		c = scanner.nextInt();
		
		max = Math.max(a, Math.max(b,c));
		
		min = Math.min(a,  Math.min(b,c));
		
		System.out.println("najva�ie ��slo je "+TriCisla.getMax(a,b,c));
		
		System.out.println("najva�ie ��slo je "+max);
		
		System.out.println("najmen�ie ��slo je "+TriCisla.getMin(a, b, c));
		
		System.out.println("najmen�ie ��slo je "+min);
	}
	
	public static int getMax(int a, int b, int c){
		int max = a;
		if(b>max){
			max=b;
		}
		if(c>max){
			max=c;
		}
		return max;
	}
	
	public static int getMin(int a, int b, int c){
		int mini = a;
		if(b<mini){
			mini=b;
		}
		if(c<mini){
			mini=c;
		}
		return mini;
	}
}
