package game.particle;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import java.util.ArrayList;

import game.object.GameObject;
import game.rendering.RenderingEngine;
import game.rendering.material.Texture2D;
import game.rendering.model.Model;
import glib.util.vector.GVector3f;

public class ParticleEmmiter extends GameObject{
	private Texture2D texture;
	
	private ArrayList<Particle> particles;
	private ArrayList<Model> models;
	
	private GVector3f gravity = new GVector3f(0,0.05,0);
	private GVector3f color = new GVector3f(1,1,1);
	private GVector3f direction = new GVector3f(0,1,0);
	
	private int numOfModels;
	private int alphaPower = 10;
	private int maxParticles = 10000;
	private int particlesPerFrame = 10;
	
	private float positionRandomness = 0.0f;
	private float speed = 0.02f;
	private float speedRandomnes = 0.01f;
	private float colorRandomness = 0f;
	private float size = 1f;
	private float sizeRandomness = 1f;
	private float life = 1000;
	private float lifeRandomness = 20;
	private float directionRandomness = 180;
	
	private boolean dwindle = true;
	private boolean fading = true;
	
	public ParticleEmmiter(GVector3f position){
		super(position,  11);
		particles = new ArrayList<Particle>();
		models = new ArrayList<Model>();
		createModels("smoke.png",1,1);
//		createModels("fire.png",4,4);
//		createModels("gabo.png",2,2);
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
				particles.add(createParticle());
			}
		}
	}
	
	public Particle createParticle(){
		GVector3f pos = getPosition().randomize(positionRandomness);
		
		Particle particle = new Particle(pos,texture,color.randomize(colorRandomness),this);
		particle.setSpeed(speed, speedRandomnes);
		particle.setDirection(direction,directionRandomness);
		particle.setLife(life,lifeRandomness);
		particle.setSize(size, sizeRandomness);
		return particle;
	}
	
	public void render(RenderingEngine renderingEngine){
		glDisable(GL_DEPTH_TEST);
		
//		for(Particle p : particles){
//			p.render(renderingEngine);
//		}
		for(int i=particles.size()-1 ; i>=0 ; i--){
			particles.get(i).render(renderingEngine);
		}
		glEnable(GL_DEPTH_TEST);
	}

	private void createModels(String fileName, int numX, int numY){
		texture = new Texture2D(fileName);
		numOfModels = numX * numY;
		float imageWidth = 1f/numX;
		float imageHeight = 1f/numY;
		for(int j=0 ; j<numY ; j++){
			for(int i=0 ; i<numX ; i++){
				models.add(Particle.makeModel(imageWidth*i, imageWidth*i+imageWidth, imageHeight*j, imageHeight*j+imageHeight));
			}
		}
	}
	
	public Model getModel(int i){
		return models.get(i);
	}
	
	public int getNumOfModels() {
		return numOfModels;
	}

	
	public int getAlphaPower() {
		return alphaPower;
	}

	public Texture2D getTexture() {
		return texture;
	}

	public GVector3f getGravity() {
		return gravity;
	}

	public boolean isDwindle() {
		return dwindle;
	}

	public boolean isFadding() {
		return fading;
	}
}
