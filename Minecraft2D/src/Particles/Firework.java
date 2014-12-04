package Particles;

import java.awt.Color;

import Components.Block;
import Main.Main;

public class Firework {

	public Firework(double x, double y,int particlesNum) {
		for(int i=0 ; i<particlesNum ; i++){
			double angle=Math.random()*Math.PI*2;
			double dx=Math.sin(angle)*((Math.random()*20)-8);
			double dy=Math.cos(angle)*((Math.random()*20)-8);
			int lifes=(int)Math.floor(Math.random()*400)+100;
			Color color = new Color((int)Math.floor(Math.random()*255),(int)Math.floor(Math.random()*255),(int)Math.floor(Math.random()*255),255);

			ParticleCirc casticka = new ParticleCirc(x, y, dx, dy, color, lifes/4, Block.size/1.5,.4);
			casticka.setDvindle(true);
			casticka.setGravity(true, 4);
			Main.particlesCirc.add(casticka);
		}
	}

}
