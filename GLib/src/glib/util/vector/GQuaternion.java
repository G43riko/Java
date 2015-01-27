package glib.util.vector;



public class GQuaternion{
	private float x;
	private float y;
	private float z;
	private float w;

	public GQuaternion(){
		this(0,0,0,1);
	}
	
	public GQuaternion(float x, float y, float z, float w){
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public GQuaternion(GVector3f axis, float angle){
		float sinHalfAngle = (float)Math.sin(angle / 2);

		this.x = axis.getX() * sinHalfAngle;
		this.y = axis.getY() * sinHalfAngle;
		this.z = axis.getZ() * sinHalfAngle;
		this.w = (float)Math.cos(angle / 2);
	}

	public GQuaternion(GMatrix4f rot){
		float trace = rot.get(0, 0) + rot.get(1, 1) + rot.get(2, 2);

		if(trace > 0){
			float s = 0.5f / (float)Math.sqrt(trace+ 1.0f);
			w = 0.25f / s;
			x = (rot.get(1, 2) - rot.get(2, 1)) * s;
			y = (rot.get(2, 0) - rot.get(0, 2)) * s;
			z = (rot.get(0, 1) - rot.get(1, 0)) * s;
		}
		else{
			if(rot.get(0, 0) > rot.get(1, 1) && rot.get(0, 0) > rot.get(2, 2)){
				float s = 2.0f * (float)Math.sqrt(1.0f + rot.get(0, 0) - rot.get(1, 1) - rot.get(2, 2));
				w = (rot.get(1, 2) - rot.get(2, 1)) / s;
				x = 0.25f * s;
				y = (rot.get(1, 0) + rot.get(0, 1)) / s;
				z = (rot.get(2, 0) + rot.get(0, 2)) / s;
			}
			else if(rot.get(1, 1) > rot.get(2, 2)){
				float s = 2.0f * (float)Math.sqrt(1.0f + rot.get(1, 1) - rot.get(0, 0) - rot.get(2, 2));
				w = (rot.get(2, 0) - rot.get(0, 2)) / s;
				x = (rot.get(1, 0) + rot.get(0, 1)) / s;
				y = 0.25f * s;
				z = (rot.get(2, 1) + rot.get(1, 2)) / s;
			}
			else{
				float s = 2.0f * (float)Math.sqrt(1.0f + rot.get(2, 2) - rot.get(0, 0) - rot.get(1, 1));
				w = (rot.get(0, 1) - rot.get(1, 0) ) / s;
				x = (rot.get(2, 0) + rot.get(0, 2) ) / s;
				y = (rot.get(1, 2) + rot.get(2, 1) ) / s;
				z = 0.25f * s;
			}
		}

		float length = (float)Math.sqrt(x * x + y * y + z * z + w * w);
		x /= length;
		y /= length;
		z /= length;
		w /= length;
	}
	
	public float getLength(){
		return (float)Math.sqrt(x * x + y * y + z * z + w * w);
	}
	
	public GQuaternion normalize(){
		float length = getLength();
		return new GQuaternion(x / length, y / length, z / length, w / length);
	}
	
	public GQuaternion conjugate(){
		return new GQuaternion(-x, -y, -z, w);
	}

	public GQuaternion mul(float r){
		return new GQuaternion(x * r, y * r, z * r, w * r);
	}

	public GQuaternion mul(GQuaternion r){
		float w_ = w * r.getW() - x * r.getX() - y * r.getY() - z * r.getZ();
		float x_ = x * r.getW() + w * r.getX() + y * r.getZ() - z * r.getY();
		float y_ = y * r.getW() + w * r.getY() + z * r.getX() - x * r.getZ();
		float z_ = z * r.getW() + w * r.getZ() + x * r.getY() - y * r.getX();
		
		return new GQuaternion(x_, y_, z_, w_);
	}
	
	public GQuaternion mul(GVector3f r){
		float w_ = -x * r.getX() - y * r.getY() - z * r.getZ();
		float x_ =  w * r.getX() + y * r.getZ() - z * r.getY();
		float y_ =  w * r.getY() + z * r.getX() - x * r.getZ();
		float z_ =  w * r.getZ() + x * r.getY() - y * r.getX();
		
		return new GQuaternion(x_, y_, z_, w_);
	}

	public GQuaternion sub(GQuaternion r){
		return new GQuaternion(x - r.getX(), y - r.getY(), z - r.getZ(), w - r.getW());
	}

	public GQuaternion add(GQuaternion r){
		return new GQuaternion(x + r.getX(), y + r.getY(), z + r.getZ(), w + r.getW());
	}
	
	public GQuaternion add(float r){
		return new GQuaternion(x + r, y + r, z + r, w + r);
	}
	
	public GMatrix4f toRotationMatrix(){
		return new GMatrix4f().initRotation(getForward(), getUp(), getRight());
	}

	public GVector3f getEuler (){
		float roll  = (float)Math.atan2(2*y*w - 2*x*z, 1 - 2*y*y - 2*z*z);
		float pitch = (float)Math.atan2(2*x*w - 2*y*z, 1 - 2*x*x - 2*z*z);
		float yaw   = (float)Math.asin(2*x*y + 2*z*w);
		return new GVector3f(yaw,pitch,roll);
	}
	
	public float dot(GQuaternion r){
		return x * r.getX() + y * r.getY() + z * r.getZ() + w * r.getW();
	}

	public GQuaternion NLerp(GQuaternion dest, float lerpFactor, boolean shortest){
		GQuaternion correctedDest = dest;

		if(shortest && this.dot(dest) < 0)
			correctedDest = new GQuaternion(-dest.getX(), -dest.getY(), -dest.getZ(), -dest.getW());

		return correctedDest.sub(this).mul(lerpFactor).add(this).normalize();
	}

	public GQuaternion SLerp(GQuaternion dest, float lerpFactor, boolean shortest){
		final float EPSILON = 1e3f;

		float cos = this.dot(dest);
		GQuaternion correctedDest = dest;

		if(shortest && cos < 0){
			cos = -cos;
			correctedDest = new GQuaternion(-dest.getX(), -dest.getY(), -dest.getZ(), -dest.getW());
		}

		if(Math.abs(cos) >= 1 - EPSILON)
			return NLerp(correctedDest, lerpFactor, false);

		float sin = (float)Math.sqrt(1.0f - cos * cos);
		float angle = (float)Math.atan2(sin, cos);
		float invSin =  1.0f/sin;

		float srcFactor = (float)Math.sin((1.0f - lerpFactor) * angle) * invSin;
		float destFactor = (float)Math.sin((lerpFactor) * angle) * invSin;

		return this.mul(srcFactor).add(correctedDest.mul(destFactor));
	}

	public GVector3f getForward(){return new GVector3f(0,0,1).Rotate(this);}
	public GVector3f getBack(){return new GVector3f(0,0,-1).Rotate(this);}
	public GVector3f getUp(){return new GVector3f(0,1,0).Rotate(this);}
	public GVector3f getDown(){return new GVector3f(0,-1,0).Rotate(this);}
	public GVector3f getRight(){return new GVector3f(1,0,0).Rotate(this);}
	public GVector3f getLeft(){return new GVector3f(-1,0,0).Rotate(this);}

	public GQuaternion set(float x, float y, float z, float w) { this.x = x; this.y = y; this.z = z; this.w = w; return this; }
	public GQuaternion set(GQuaternion r) { set(r.getX(), r.getY(), r.getZ(), r.getW()); return this; }

	public float getX(){return x;}
	public float getY(){return y;}
	public float getZ(){return z;}
	public float getW(){return w;}
	
	public void setX(float x){this.x = x;}
	public void setY(float y){this.y = y;}
	public void setZ(float z){this.z = z;}
	public void setW(float w){this.w = w;}

	public boolean equals(GQuaternion r){
		return x == r.getX() && y == r.getY() && z == r.getZ() && w == r.getW();
	}
}