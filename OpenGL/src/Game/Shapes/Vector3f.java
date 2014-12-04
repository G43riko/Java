package Game.Shapes;

public class Vector3f {
	private float x,y,z;
	public Vector3f(float x, float y, float z){
		this.x = x; 
		this.y = y;
		this.z = z;
	}
	
	public void setPos(Vector3f pos){
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
	}
	
	public Vector3f getPos(){
		return this;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
}
