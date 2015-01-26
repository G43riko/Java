package com.voxel.core.util;

public class GVector3f 
{
	private float m_x;
	private float m_y;
	private float m_z;
	
	public GVector3f(float x, float y, float z)
	{
		this.m_x = x;
		this.m_y = y;
		this.m_z = z;
	}

	public float getLength()
	{
		return (float)Math.sqrt(m_x * m_x + m_y * m_y + m_z * m_z);
	}

	public float max()
	{
		return Math.max(m_x, Math.max(m_y, m_z));
	}

	public float dot(GVector3f r)
	{
		return m_x * r.getX() + m_y * r.getY() + m_z * r.getZ();
	}
	
	public GVector3f cross(GVector3f r)
	{
		float x_ = m_y * r.getZ() - m_z * r.getY();
		float y_ = m_z * r.getX() - m_x * r.getZ();
		float z_ = m_x * r.getY() - m_y * r.getX();
		
		return new GVector3f(x_, y_, z_);
	}
	
	public GVector3f normalize()
	{
		float length = getLength();
		
		return new GVector3f(m_x / length, m_y / length, m_z / length);
	}

	public GVector3f rotate(GVector3f axis, float angle)
	{
		float sinAngle = (float)Math.sin(-angle);
		float cosAngle = (float)Math.cos(-angle);
		return this.cross(axis.mul(sinAngle)).add(           //Rotation on local X
				(this.mul(cosAngle)).add(                     //Rotation on local Z
						axis.mul(this.dot(axis.mul(1 - cosAngle))))); //Rotation on local Y
	}
	
	public GVector3f rotate(GQuaternion rotation){
		GQuaternion conjugate = rotation.conjugate();

		GQuaternion w = rotation.mul(this).mul(conjugate);

		return new GVector3f(w.getX(), w.getY(), w.getZ());
	}

	public GVector3f Lerp(GVector3f dest, float lerpFactor)
	{
		return dest.aub(this).mul(lerpFactor).add(this);
	}

	public GVector3f add(GVector3f r)
	{
		return new GVector3f(m_x + r.getX(), m_y + r.getY(), m_z + r.getZ());
	}
	
	public GVector3f add(float r)
	{
		return new GVector3f(m_x + r, m_y + r, m_z + r);
	}
	
	public GVector3f aub(GVector3f r)
	{
		return new GVector3f(m_x - r.getX(), m_y - r.getY(), m_z - r.getZ());
	}
	
	public GVector3f sub(float r)
	{
		return new GVector3f(m_x - r, m_y - r, m_z - r);
	}
	
	public GVector3f sub(GVector3f r)
	{
		return new GVector3f(m_x - r.getX(), m_y - r.getY(), m_z - r.getZ());
	}
	
	public GVector3f mul(GVector3f r)
	{
		return new GVector3f(m_x * r.getX(), m_y * r.getY(), m_z * r.getZ());
	}
	
	public GVector3f mul(float r)
	{
		return new GVector3f(m_x * r, m_y * r, m_z * r);
	}
	
	public GVector3f div(GVector3f r)
	{
		return new GVector3f(m_x / r.getX(), m_y / r.getY(), m_z / r.getZ());
	}
	
	public GVector3f div(float r)
	{
		return new GVector3f(m_x / r, m_y / r, m_z / r);
	}
	
	
	public GVector3f abs()
	{
		return new GVector3f(Math.abs(m_x), Math.abs(m_y), Math.abs(m_z));
	}
	
	public String toString()
	{
		return "(" + m_x + " " + m_y + " " + m_z + ")";
	}

	public GVector2f getXY() { return new GVector2f(m_x, m_y); }
	public GVector2f getYZ() { return new GVector2f(m_y, m_z); }
	public GVector2f getZX() { return new GVector2f(m_z, m_x); }
	public GVector2f getYX() { return new GVector2f(m_y, m_x); }
	public GVector2f getZY() { return new GVector2f(m_z, m_y); }
	public GVector2f getXZ() { return new GVector2f(m_x, m_z); }

	public GVector3f set(float x, float y, float z) { this.m_x = x; this.m_y = y; this.m_z = z; return this; }
	public GVector3f set(GVector3f r) { set(r.getX(), r.getY(), r.getZ()); return this; }

	public float getX()
	{
		return m_x;
	}

	public void setX(float x)
	{
		this.m_x = x;
	}

	public float getY()
	{
		return m_y;
	}

	public void setY(float y)
	{
		this.m_y = y;
	}

	public float getZ()
	{
		return m_z;
	}

	public void setZ(float z)
	{
		this.m_z = z;
	}

	public boolean equals(GVector3f r)
	{
		return m_x == r.getX() && m_y == r.getY() && m_z == r.getZ();
	}
}