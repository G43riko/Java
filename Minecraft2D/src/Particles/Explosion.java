package Particles;
import java.awt.Color;

import Main.Main;


public class Explosion {
	Color[] colors=new Color[]{Color.BLACK,Color.DARK_GRAY,Color.LIGHT_GRAY,Color.ORANGE, Color.YELLOW};
	
	public Explosion(double x,double y,int particlesNum, int size){
		for(int i=0 ; i<particlesNum ; i++){
			
			int num=(int)Math.floor(Math.random()*this.colors.length);
			double angle=Math.random()*Math.PI*2;
			double dx=Math.sin(angle)*((Math.random()*5)-2);
			double dy=Math.cos(angle)*((Math.random()*5)-2);
			int lifes=(int)Math.floor(Math.random()*200)+50;
			
			ParticleCirc casticka = new ParticleCirc(x, y, dx, dy, this.colors[num], lifes/4, size/1.5,.4);
			casticka.setDvindle(true);
			Main.particlesCirc.add(casticka);
		}
		
	}
}
