package org.engine.component;

import org.engine.util.Maths;
import org.json.JSONObject;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.strategy.rendering.RenderingEngineStrategy;

import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector3f;


public abstract class GameComponent {
	private GVector3f position;
	private GVector3f rotation;
	private GVector3f scale;
	private int type;
	
	private boolean dead;
	private boolean active;
	
	public final static int CAMERA = 1;
	public final static int SKY_BOX = 3;
	public final static int DIRECTIONAL_LIGHT = 4;
	public final static int POINTLIGHT = 5;
	public final static int SPOTLIGHT = 6;
	public final static int PARTICLE = 7;
	public final static int PARTICLE_EMMITER = 11;
	public final static int HUD = 13;
	public final static int GAME_OBJECT = 19;
	public final static int MOVABLE = 21;
	public final static int WATER = 22;
	
	public final static int ENTITY = 2;
	public final static int BOX = 8;
	public final static int CHUNK = 9;
	public final static int WORLD = 10;
	public final static int PLAYER = 12;
	public final static int EXPLOSION = 14;
	public final static int SANDBOX = 15;
	public final static int LINE = 16;
	public final static int TOWER = 17;
	public final static int ENEMY = 18;
	public final static int PLANE = 20;
	
	// CONSTRUCTORS;
	
	public GameComponent(int type) {
		this(new GVector3f(), new GVector3f(), new GVector3f(1), type);
	}
	
	public GameComponent(GVector3f position,  int type) {
		this(position, new GVector3f(), new GVector3f(1), type);
	}
	
	public GameComponent(GVector3f position, GVector3f rotation, GVector3f scale, int type) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.type = type;
	}

	//OTHERS
	
	public void move(GVector3f vec){
		position = position.add(vec);
	}
	
	public void rotate(GVector3f vec){
		rotation = rotation.add(vec);
	}
	
	public void render(RenderingEngineStrategy renderingEngine) {}
	
	public void input() {}

	public void update() {}

	public JSONObject toJSON(){
		return null;
	}
	
	//GETTERS
	
	public GVector3f getPosition() {
		return position;
	}

	public GVector3f getRotation() {
		return rotation;
	}

	public GVector3f getScale() {
		return scale;
	}
	
	public int getType() {
		return type;
	}
	
	public GMatrix4f getTransformationMatrix(){
		Matrix4f trans = Maths.createTransformationMatrix(new Vector3f(getPosition().getX(),getPosition().getY(),getPosition().getZ()), 
				 getRotation().getX(), getRotation().getY(), getRotation().getZ(), getScale().getX());
		return Maths.MatrixToGMatrix(trans);
	}
	
	//SETTERS
	
	public void setPosition(GVector3f position) {
		this.position = position;
	}
	
	public void setRotation(GVector3f rotation) {
		this.rotation = rotation;
	}
	
	public void setScale(GVector3f scale) {
		this.scale = scale;
	}
	
	public void setScale(float scale) {
		this.scale = new GVector3f(scale, scale, scale);
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}	
}
