package Niks;

import java.util.Vector;

public class DistTester {

	public static void main(String[] args) {
		System.out.println(pointToLineDistance(0f,0f,3f,4f,3f,0f));

	}
	
	public static double pointToLineDistance(double Ax, double Ay, double Bx,double By , double x, double y) {
		double normalLength = Math.sqrt((Bx-Ax)*(Bx-Ax)+(By-Ay)*(By-Ay));
	    return Math.abs((x-Ax)*(By-Ay)-(y-Ay)*(Bx-Ax))/normalLength;
	}

}
