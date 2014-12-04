package Particles;

import org.lwjgl.util.vector.Vector3f;

import Textures.ModelTexture;

public class BasicParticle {
	private Vector3f position;
	private Vector3f direction;
	
	private float speed;
	private float gravity;
	private float life;
	private float maxLife;
	
	private ModelTexture texture;

	public BasicParticle(Vector3f p, float s,float g, float l, ModelTexture t) {
		this.position = p;
		this.direction = new Vector3f((float)Math.random()*2-1,(float)Math.random()*2-1,(float)Math.random()*2-1);
		this.speed = s;
		this.gravity = g;
		this.maxLife = l;
		this.life = 0;
		this.texture = t;
	}
	
	public void move(){
		this.position.x+=this.direction.x;
		this.position.y+=this.direction.y;
		this.position.z+=this.direction.z;
		this.life++;
	};
	
	
}
