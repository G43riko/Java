package game.object;

import org.json.JSONObject;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import game.rendering.RenderingEngine;
import game.util.Maths;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector3f;


public abstract class GameObject {
	private GVector3f position;
	private GVector3f rotation;
	private GVector3f scale;
	private int type;
	
	/*
	 * 1 - Camera
	 * 2 - Entity
	 * 3 - SkyBox
	 * 4 - DirectionalLight
	 * 5 - PointLight
	 * 6 - SpotLight
	 * 7 - Particle
	 * 8 - Box
	 * 9 - chunk
	 * 10 - world
	 * 11 - particleEmmiter
	 * 12 - Player
	 * 13 - HUD
	 * 14 - explosion
	 * 15 - sandbox
	 */
	public GameObject(GVector3f position,  int type) {
		this(position, new GVector3f(0,0,0), new GVector3f(1,1,1), type);
	}
	
	public GameObject(GVector3f position, GVector3f rotation, GVector3f scale, int type) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.type = type;
	}

	public void move(GVector3f vec){
		position = position.add(vec);
	}
	
	public void rotate(GVector3f vec){
		rotation = rotation.add(vec);
	}
	
//	public void rotate(GVector3f dir,float angle){
//		rotation = rotation.Rotate(dir, angle);
//		rotation = rotation.Rotate(new GQuaternion(dir, angle));
//	}

	public GVector3f getPosition() {
		return position;
	}
	
	public void setPosition(GVector3f position) {
		this.position = position;
	}
	
	public GVector3f getRotation() {
		return rotation;
	}
	
	public void setRotation(GVector3f rotation) {
		this.rotation = rotation;
	}
	
	public GVector3f getScale() {
		return scale;
	}
	
	public void setScale(GVector3f scale) {
		this.scale = scale;
	}
	
	public void setScale(float scale) {
		this.scale = new GVector3f(scale, scale, scale);
	}
	
	public void render(RenderingEngine renderingEngine) {}
	
	
	public void lookAt(GVector3f pos){
	}
	
	public void input() {}

	public void update() {}

	public int getType() {
		return type;
	}
	
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
	
	public GMatrix4f getTransformationMatrix(){
		Matrix4f trans = Maths.createTransformationMatrix(new Vector3f(getPosition().getX(),getPosition().getY(),getPosition().getZ()), 
				 getRotation().getX(), getRotation().getY(), getRotation().getZ(), getScale().getX());
		return Maths.MatrixToGMatrix(trans);
	}
	
}
