package game.particle;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import game.main.Loader;
import game.object.GameObject;
import game.rendering.RenderingEngine;
import game.rendering.material.Texture2D;
import game.rendering.model.Model;
import game.util.Maths;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GQuaternion;
import glib.util.vector.GVector3f;


public class Particle extends GameObject{
	private GVector3f direction;
	private boolean dead = false;
	private float speed;
	private float gravity;
	private float life;
	private GVector3f color = new GVector3f(1,0,1);
	private static Model model = makeModel();
	private float startSize;
	private float startlife;
	private boolean dwindle;
	
	private Texture2D texture;

	public Particle(GVector3f position,GVector3f direction, float speed,float size, float maxLife, Texture2D texture, GVector3f color) {
		super(position, 7);
		setScale(size);
		this.startSize = size;
		this.direction = direction;
		this.speed = speed;
		this.startlife = maxLife;
		this.life = maxLife;
		this.texture = texture;
		this.color = color;
	}
	
	public void update(){
		move(direction.mul(speed));
		if(dwindle)
			setScale(life/startlife);
		life--;
		if(life<0)
			dead = true;
//		rotate(new GVector3f(0,1,0));
//		rotate(new GVector3f(0,1,0),1);
	};
	
	public void render(RenderingEngine renderingEngine){
		
		renderingEngine.renderParticle(this);
	}
	
	public GMatrix4f getTransformationMatrix(GVector3f pos){
		Matrix4f trans = Maths.createTransformationMatrix(new Vector3f(getPosition().getX(),getPosition().getY(),getPosition().getZ()), 
				 getRotation().getX(), getRotation().getY(), getRotation().getZ(), getScale().getX());
		
		GQuaternion res = new GQuaternion(new GMatrix4f().initRotation(pos.sub(getPosition()).Normalized(), new GVector3f(0,1,0)));
		return res.toRotationMatrix().mul(Maths.MatrixToGMatrix(trans));
		
		
	}
	
 	public static Model makeModel(){
		float w = 0.1f;
		float h = 0.1f;
		float d = 0.1f;
		float[] vertices = new float[]{-w,h,-d,	
									   -w,-h,-d,	
									   	w,-h,-d,	
									   	w,h,-d};
		float[] texture ={0,0,
				 		  0,1,
				 		  1,1,
				 		  1,0};
		
		int[] indices ={3,1,0,	
						2,1,3};
		
		float[] normals = {0.0000f,  0.0000f, -1.0000f,
				 		   0.0000f,  0.0000f, -1.0000f,
				 		   0.0000f,  0.0000f, -1.0000f,
				 		   0.0000f,  0.0000f, -1.0000f};
		
		return new Loader().loadToVAO(vertices, texture, normals, indices);
	}

	public static Model getModel() {
		return model;
	}

	public static void setModel(Model model) {
		Particle.model = model;
	}

	public GVector3f getColor() {
		return color;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDwindle(boolean dwindle) {
		this.dwindle = dwindle;
	}

	public Texture2D getTexture() {
		return texture;
	}
}

