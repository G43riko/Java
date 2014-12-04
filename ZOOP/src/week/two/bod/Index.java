package week.two.bod;

import java.util.ArrayList;
import java.util.Scanner;

public class Index {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Point bod = new Point();
		bod.setPos(in.nextInt(), in.nextInt(), in.nextInt());
		System.out.println(bod);
		
		
		ArrayList<Point> pole = new ArrayList<Point>();
		
		for(int i=0 ; i<10 ; i++){
			pole.add(new Point());
			pole.get(i).setPos((float)Math.random(), (float)Math.random(), (float)Math.random());
			
		}
		System.out.println(pole);
	}

}
