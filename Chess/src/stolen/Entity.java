package stolen;

import org.lwjgl.util.vector.Vector3f;


public class Entity {
	private TexturedModel model;
	private Vector3f position;
	protected float rotX, rotY, rotZ;
	private float scale;
	
	public Entity(TexturedModel model){
		this(model,new Vector3f(0,0,0));
	}
	public Entity(TexturedModel model,float x, float y, float z){
		this(model, new Vector3f(x,y,z));
	}
	public Entity(TexturedModel model,Vector3f pos){
		this(model,pos,0,0,0);
	}
	public Entity(TexturedModel model,Vector3f pos,float scale){
		this(model,pos,0,0,0,scale);
	}
	public Entity(TexturedModel model, Vector3f pos, float rx, float ry, float rz){
		this(model, pos,rx,ry,rz,1);
	}
	public Entity(TexturedModel model, Vector3f pos, float rx, float ry, float rz, float scale){
		position = pos;
		rotX = rx;
		rotY = ry;
		rotZ = rz;
		this.scale = scale;
		this.model = model;
	}
	
	public void increasePosition(float dx, float dy, float dz){
		position.x += dx;
		position.y += dy;
		position.z += dz;
	}
	
	public void increaseRotation(float dx, float dy, float dz){
		rotX += dx;
		rotY += dy;
		rotZ += dz;
	}
	
	public TexturedModel getModel() {
		return model;
	}
	public void setModel(TexturedModel model) {
		this.model = model;
	}
	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public float getRotX() {
		return rotX;
	}
	public void setRotX(float rotX) {
		this.rotX = rotX;
	}
	public float getRotY() {
		return rotY;
	}
	public void setRotY(float rotY) {
		this.rotY = rotY;
	}
	public float getRotZ() {
		return rotZ;
	}
	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}
	public float getScale() {
		return scale;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}
}
