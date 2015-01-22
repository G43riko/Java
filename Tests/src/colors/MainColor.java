package colors;

import java.awt.Color;

public class MainColor {

	public static void main(String[] args) {
		
		Color color = new Color(255/2, 255/2, 255/2);
		for(int i=0 ; i<5 ; i++){
			System.out.println(calcColor(color,30));
		}
	}
	
	public static Color calcColor(Color color, float offset){
		float value = (color.getRed() + color.getGreen() + color.getBlue())/3;
		float newValue = (float)(value + 2*Math.random()*offset-offset) / value;
		float r = color.getRed() * newValue;
		float g = color.getGreen()*newValue;
		float b = color.getBlue()*newValue;
		r = Math.min(Math.max(0,r),255);
		g = Math.min(Math.max(0,g),255);
		b = Math.min(Math.max(0,b),255);
		return new Color((int)r, (int)g , (int)b);
	}

}
