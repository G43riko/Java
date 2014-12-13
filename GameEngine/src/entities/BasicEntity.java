package entities;
/*
 * ud·va iba kde sa objekt vyskytuje a jeho rot·ciu a velkosù
 * neobsahuje rozmery
 * m· metÛdy na pohyb
 */
public abstract class BasicEntity {
	protected float x,y,z;
	protected float rx, ry, rz;
	protected float scale;
	
	public BasicEntity(float x, float y, float z, float rx, float ry, float rz, float scale) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.rx = rx;
		this.ry = ry;
		this.rz = rz;
		this.scale = scale;
	}

	public void setLocation(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void setRotation(float rx, float ry, float rz){
		this.rx = rx;
		this.ry = ry;
		this.rz = rz;
	}
	
	public void move(float x, float y, float z){
		this.x += x;
		this.y += y;
		this.z += z;
	}
	
	public void rotate(float rx, float ry, float rz){
		this.rx += rx;
		this.ry += ry;
		this.rz += rz;
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

	public float getRx() {
		return rx;
	}

	public void setRx(float rx) {
		this.rx = rx;
	}

	public float getRy() {
		return ry;
	}

	public void setRy(float ry) {
		this.ry = ry;
	}

	public float getRz() {
		return rz;
	}

	public void setRz(float rz) {
		this.rz = rz;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
}
