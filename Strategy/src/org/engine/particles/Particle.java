package org.engine.particles;

import org.engine.component.GameComponent;
import org.engine.rendeing.material.Texture2D;
import org.engine.rendeing.model.Model;
import org.engine.util.Loader;
import org.engine.util.Maths;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.strategy.rendering.RenderingEngineStrategy;

import glib.math.GMath;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GQuaternion;
import glib.util.vector.GVector3f;


public class Particle extends GameComponent{
	
	private ParticleEmmiter parent;
	
	private GVector3f direction;
	private GVector3f color = new GVector3f(1,0,1);
	
	private float speed;
	private float life;
	
	private float startSize;
	private float startLife;
	private float alpha;
	private float changeTextEveryNthFrame;
	private float rotation;
	private float rotationSpeed;
	
	private float sizePerFrame;
	private float alphaPerFrame;
	
	private boolean dead;
	
	//CONSTRUCTORS
	
	public Particle(GVector3f position, Texture2D texture, GVector3f color, ParticleEmmiter parent) {
		super(position, GameComponent.PARTICLE);
		this.dead = false;
		this.color = color;
		this.alpha = 1;
		this.parent = parent;
		rotation = (float)(Math.random() * Math.PI * 2);
		rotationSpeed = (float)Math.toRadians((Math.random()-0.5f));
	}
	
	//OVERRIDES
	
	public void update(){
		move(direction.mul(speed));
		direction = direction.add(parent.getGravity());
		
		if(parent.isDwindle())
			setScale(getScale().add(sizePerFrame*20));
		if(parent.isFadding())
			alpha -= alphaPerFrame;
		
		life--;
		rotation+= rotationSpeed;
		
		if(life<=0 || alpha<=0)
			dead = true;
	};
	
	public void render(RenderingEngineStrategy renderingEngine){
		renderingEngine.renderParticle(this);
	}
	
	//OTHERS
	
 	public static Model makeModel(float minX, float maxX,float minY,float maxY){
		float w = 0.1f;
		float h = 0.1f;
		float d = 0.1f;
		float[] vertices = new float[]{-w,h,-d,	
									   -w,-h,-d,	
									   	w,-h,-d,	
									   	w,h,-d};
		float[] texture ={minX,minY,
				 		  minX,maxY,
				 		  maxX,maxY,
				 		  maxX,minY};
		
		int[] indices ={0,1,3,	
						3,1,2};
		
//		float[] normals = {0.0000f,  0.0000f, -1.0000f,
//				 		   0.0000f,  0.0000f, -1.0000f,
//				 		   0.0000f,  0.0000f, -1.0000f,
//				 		   0.0000f,  0.0000f, -1.0000f};
		
		return new Loader().loadToVAO(vertices, texture, indices);
	}

 	// setters
 	
	public void setSpeed(float speed, float random){
		this.speed = GMath.randomize(speed, random);
	}
	
	public void setSize(float size, float random){
		startSize = GMath.randomize(size, random);
		setScale(startSize);
		sizePerFrame = startSize/life;
	}
	
	public void setLife(float life, float random){
		startLife = GMath.randomize(life, random);
		this.life = startLife;
		alphaPerFrame = alpha/startLife*parent.getAlphaPower();
		changeTextEveryNthFrame = this.startLife / parent.getNumOfModels()+1;
	}
	
	public void setDirection(GVector3f dir, float random){
		direction = dir.randomize(Math.toRadians(random)).Normalized();
	}

 	// getters
	
	public Model getModel() {
		return parent.getModel((int)((startLife-life)/changeTextEveryNthFrame));
	}

	public GMatrix4f getTransformationMatrix(GVector3f pos){
		Matrix4f trans = Maths.createTransformationMatrix(new Vector3f(getPosition().getX(),getPosition().getY(),getPosition().getZ()), 
				 getRotation().getX(), getRotation().getY(), getRotation().getZ(), getScale().getX());
		GVector3f toCamera = pos.sub(getPosition()).Normalized();
		
		GQuaternion res = new GQuaternion(new GMatrix4f().initRotation(toCamera, new GVector3f(0,1,0)));
		res = new GQuaternion(toCamera, rotation).mul(res).normalize();
		return res.toRotationMatrix().mul(Maths.MatrixToGMatrix(trans));
	}
	
	public GVector3f getColor() {
		return color;
	}

	public Texture2D getTexture() {
		return parent.getTexture();
	}

	public float getAlpha() {
		return alpha;
	}

	public boolean isDead() {
		return dead;
	}

	public boolean isFadding() {
		return parent.isFadding();
	}
}

