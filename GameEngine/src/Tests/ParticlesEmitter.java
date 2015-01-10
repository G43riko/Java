package Tests;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

public class ParticlesEmitter {
	private ArrayList<Particle> particles;
	private Vector3f position;
	private Vector3f direction;
	private float angle;
	private int maxParticles;
	private int ppf;//particles per frame
	
	public ParticlesEmitter(){
		particles = new ArrayList<Particle>();
	}
	
	public void emmite(){
		for(int i=0 ; i<ppf ; i++){
			particles.add(new Particle());
		}
	}
	
	public void draw(){
		
	}
	
	public void move(){
		for(Particle p:particles){
			if(p.move())
				particles.remove(p);
		}
	}
}
