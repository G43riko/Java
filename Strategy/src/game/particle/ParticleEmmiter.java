package game.particle;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import java.util.ArrayList;

import game.object.GameObject;
import game.rendering.RenderingEngine;
import game.rendering.material.Texture2D;
import glib.math.GMath;
import glib.util.vector.GVector3f;

public class ParticleEmmiter extends GameObject{
	private ArrayList<Particle> particles;
	private int maxParticles = 1000;
	private int particlesPerFrame = 100;
	
	private float speed = 0.01f;
	private float speedRandomnes = 0.01f;
	
	private GVector3f color = new GVector3f(1,0,1);
	private float coloRandomness = 0.3f;
	
	private float size = 1;
	private float sizeRandomness = 0.9f;
	
	private float life = 100;
	private float lifeRandomness = 20;
	
	private GVector3f direction = new GVector3f(0,0,0);
	private float directionRandomness = 360;
	
	private Texture2D texture = new Texture2D("particle.png");
//	private Texture2D texture = new Texture2D("fire.jpg");
	private boolean dwindle = true;
	
	public ParticleEmmiter(GVector3f position){
		super(position,  11);
		particles = new ArrayList<Particle>();
		particlesPerFrame = (int)(maxParticles/life);
		update();
	}
	
	public void update(){
		ArrayList<Particle> forDelete = new ArrayList<Particle>();
		for(Particle p : particles){
			p.update();
			if(p.isDead())
				forDelete.add(p);
		}
		
		
		particles.removeAll(forDelete);
		
		for(int i=0 ; i<particlesPerFrame ; i++){
			if(particles.size()<maxParticles){
				GVector3f dir = direction.randomize(Math.toRadians(directionRandomness)).Normalized();
				GVector3f col = color.randomize(coloRandomness).Normalized();
				float speed = GMath.randomize(this.speed, speedRandomnes);
				float size = GMath.randomize(this.size, sizeRandomness);
				float life = GMath.randomize(this.life, lifeRandomness);
				Particle particle = new Particle(getPosition(),dir, speed, size,life,texture,col);
				particle.setDwindle(dwindle);
				particles.add(particle);
			}
		}
	}
	
	public void render(RenderingEngine renderingEngine){
		glDisable(GL_DEPTH_TEST);
		
		for(Particle p : particles){
			p.render(renderingEngine);
		}
		glEnable(GL_DEPTH_TEST);
	}
}
