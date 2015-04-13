package org.engine.particles;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.engine.component.GameComponent;
import org.engine.rendeing.material.Texture2D;
import org.engine.rendeing.model.Model;
import org.json.JSONObject;
import org.strategy.rendering.RenderingEngine;

import glib.util.vector.GVector3f;

public class ParticleEmmiter extends GameComponent{
	private Texture2D texture;
	
	private ArrayList<Particle> particles;
	private ArrayList<Model> models;
	
	private GVector3f gravity = new GVector3f(0,0.05,0);
	private GVector3f color = new GVector3f(1);
	private GVector3f direction = new GVector3f(0,1,0);
	
	private int numOfModels;
	private int alphaPower = 10;
	private int maxParticles = 10000;
	private int particlesPerFrame = 10;
	
	private float speed = 0.02f;
	private float size = 1f;
	private float life = 1000;
	private float positionRandomness = 0.0f;
	private float speedRandomnes = 0.01f;
	private float colorRandomness = 0f;
	private float sizeRandomness = 1f;
	private float lifeRandomness = 20;
	private float directionRandomness = 180;
	
	private boolean dwindle = true;
	private boolean fading = true;
	
	//CONSTRUCTORS
	
	public ParticleEmmiter(GVector3f position){
		super(position,  GameComponent.PARTICLE_EMMITER);
		particles = new ArrayList<Particle>();
		models = new ArrayList<Model>();
		createModels("smoke.png",1,1);
		particlesPerFrame = (int)(maxParticles/life);
		update();
	}
	
	public ParticleEmmiter(GVector3f position, String fileName){
		super(position,  11);
		
		loadFromJSON(new JSONObject(loadFromFile(fileName)));
		
		particles = new ArrayList<Particle>();
		models = new ArrayList<Model>();
		createModels("smoke.png",1,1);
		particlesPerFrame = (int)(maxParticles/life);
		update();
	}
	
	//OTHERS
	
	public void saveToFile(String fileName){
		File fileToSave = new File("res/components/particles/"+fileName);
		PrintStream file = null;;
		try {
			file = new PrintStream(fileToSave);
			file.println(toJSON());
			System.out.println("s˙bor "+fileName+" bol ˙speËne uloûen˝ medzy Ëastice");
		} catch (FileNotFoundException e1) {
			System.out.println("lutujeme ale s˙bor nebolo moûnÈ najsù");
			e1.printStackTrace();
		}
		file.close();
	}
	
	public void loadFromJSON(JSONObject o){
		texture = new Texture2D(o.getString("texture"));
		
		gravity = new GVector3f((float)o.getDouble("gravityX"), (float)o.getDouble("gravityY"), (float)o.getDouble("gravityZ"));
		color = new GVector3f((float)o.getDouble("colorX"), (float)o.getDouble("colorY"), (float)o.getDouble("colorZ"));
		direction = new GVector3f((float)o.getDouble("directionX"), (float)o.getDouble("directionY"), (float)o.getDouble("directionZ"));
		
		alphaPower = o.getInt("alphaPower");
		maxParticles = o.getInt("maxParticles");

		speed = (float)o.getDouble("speed");
		size = (float)o.getDouble("size");
		life = (float)o.getDouble("life");
		
		positionRandomness = (float)o.getDouble("positionRandomness");
		speedRandomnes = (float)o.getDouble("speedRandomnes");
		colorRandomness = (float)o.getDouble("colorRandomness");
		lifeRandomness = (float)o.getDouble("lifeRandomness");
		directionRandomness = (float)o.getDouble("directionRandomness");
		sizeRandomness = (float)o.getDouble("sizeRandomness");
		
		dwindle = o.getBoolean("dwindle");
		fading = o.getBoolean("fading");
		System.out.println("Ëastica bola ˙speöne naËÌtan·");
	}

	private String loadFromFile(String fileName){
		String line = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("res/components/particles/"+fileName));
			line = reader.readLine();
			System.out.println("s˙bor "+fileName+" bol ˙speËne otvoren˝");
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
	
	//CREATORS
	
	public Particle createParticle(){
		GVector3f pos = getPosition().randomize(positionRandomness);
		
		Particle particle = new Particle(pos,texture,color.randomize(colorRandomness),this);
		particle.setSpeed(speed, speedRandomnes);
		particle.setDirection(direction,directionRandomness);
		particle.setLife(life,lifeRandomness);
		particle.setSize(size, sizeRandomness);
		return particle;
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
	
	//OVERRIDES
	
	public JSONObject toJSON(){
		JSONObject o = new JSONObject();
		o.put("texture", texture.getFileName());
		o.put("gravityX", gravity.getX());
		o.put("gravityY", gravity.getY());
		o.put("gravityZ", gravity.getZ());
		
		o.put("colorX", color.getX());
		o.put("colorY", color.getY());
		o.put("colorZ", color.getZ());
		
		o.put("directionX", direction.getX());
		o.put("directionY", direction.getY());
		o.put("directionZ", direction.getZ());
		
		o.put("alphaPower", alphaPower);
		o.put("maxParticles", maxParticles);
		o.put("speed", speed);
		
		o.put("speedRandomnes", speedRandomnes);
		o.put("positionRandomness", positionRandomness);
		o.put("colorRandomness", colorRandomness);
		o.put("lifeRandomness", lifeRandomness);
		o.put("sizeRandomness", sizeRandomness);
		o.put("directionRandomness", directionRandomness);
		
		o.put("size", size);
		o.put("life", life);
		
		o.put("dwindle", dwindle);
		o.put("fading", fading);
		
		
		return o;
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

	//GETTERS
	
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
