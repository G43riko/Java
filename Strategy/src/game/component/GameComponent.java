package game.component;

import org.json.JSONObject;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import game.rendering.RenderingEngine;
import game.util.Maths;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector3f;


public abstract class GameComponent {
	private GVector3f position;
	private GVector3f rotation;
	private GVector3f scale;
	private int type;
	
	public final static int CAMERA = 1;
	public final static int ENTITY = 2;
	public final static int SKY_BOX = 3;
	public final static int DIRECTIONAL_LIGHT = 4;
	public final static int POINTLIGHT = 5;
	public final static int SPOTLIGHT = 6;
	public final static int PARTICLE = 7;
	public final static int BOX = 8;
	public final static int CHUNK = 9;
	public final static int WORLD = 10;
	public final static int PARTICLE_EMMITER = 11;
	public final static int PLAYER = 12;
	public final static int HUD = 13;
	public final static int EXPLOSION = 14;
	public final static int SANDBOX = 15;
	public final static int LINE = 16;
	public final static int TOWER = 17;
	public final static int ENEMY = 18;
	public final static int GAME_OBJECT = 19;
	
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
	
	public void render(RenderingEngine renderingEngine) {}
	
	public void lookAt(GVector3f pos){
	}
	
	public void input() {}

	public void update() {}

	public JSONObject toJSON(){
		return null;
	}
	
	public GMatrix4f lookAt(GVector3f eye, GVector3f target, GVector3f up)
	{
		GVector3f forward = target.sub(eye).Normalized();
		GVector3f side = forward.cross(up).Normalized();
		GMatrix4f m = new GMatrix4f().initIdentity();
		m.set(0, 0, side.getX());	m.set(0, 1, up.getX());	m.set(0, 2, -forward.getX());
		m.set(1, 0, side.getY());	m.set(1, 1, up.getY());	m.set(1, 2, -forward.getY());
		m.set(2, 0, side.getZ());	m.set(2, 1, up.getZ());	m.set(2, 2, -forward.getZ());
		m.set(0, 3, -eye.getX());	m.set(1, 3, -eye.getY());m.set(2, 3, -eye.getX());
		return m;
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
	
	public int getType() {
		return type;
	}
	
	public GMatrix4f getTransformationMatrix(){
		Matrix4f trans = Maths.createTransformationMatrix(new Vector3f(getPosition().getX(),getPosition().getY(),getPosition().getZ()), 
				 getRotation().getX(), getRotation().getY(), getRotation().getZ(), getScale().getX());
		return Maths.MatrixToGMatrix(trans);
	}
	
}
