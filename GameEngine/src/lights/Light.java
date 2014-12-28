package lights;

import org.lwjgl.util.vector.Vector3f;

public class Light {
	private Vector3f position;
	private Vector3f color;
	
	public Light(Vector3f light, Vector3f color) {
		super();
		this.position = light;
		this.color = color;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public void setPosition(Vector3f light) {
		this.position = light;
	}
	
	public Vector3f getColor() {
		return color;
	}
	
	public void setColor(Vector3f color) {
		this.color = color;
	}
	
	public void move(float x, float y, float z){
		this.position.x+=x;
		this.position.y+=y;
		this.position.z+=z;
	}
}
