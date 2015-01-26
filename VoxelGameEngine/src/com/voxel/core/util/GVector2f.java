package com.voxel.core.util;

public class GVector2f 
{
	private float m_x;
	private float m_y;
	
	public GVector2f(float x, float y)
	{
		this.m_x = x;
		this.m_y = y;
	}

	public float getLength()
	{
		return (float)Math.sqrt(m_x * m_x + m_y * m_y);
	}

	public float max()
	{
		return Math.max(m_x, m_y);
	}

	public float Dot(GVector2f r)
	{
		return m_x * r.getX() + m_y * r.getY();
	}
	
	public GVector2f normalize()
	{
		float length = getLength();
		
		return new GVector2f(m_x / length, m_y / length);
	}

	public float Cross(GVector2f r)
	{
		return m_x * r.getY() - m_y * r.getX();
	}

	public GVector2f Lerp(GVector2f dest, float lerpFactor)
	{
		return dest.sub(this).mul(lerpFactor).add(this);
	}

	public GVector2f rotate(float angle)
	{
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);
		
		return new GVector2f((float)(m_x * cos - m_y * sin),(float)(m_x * sin + m_y * cos));
	}
	
	public GVector2f add(GVector2f r)
	{
		return new GVector2f(m_x + r.getX(), m_y + r.getY());
	}
	
	public GVector2f add(float r)
	{
		return new GVector2f(m_x + r, m_y + r);
	}
	
	public GVector2f sub(GVector2f r)
	{
		return new GVector2f(m_x - r.getX(), m_y - r.getY());
	}
	
	public GVector2f sub(float r)
	{
		return new GVector2f(m_x - r, m_y - r);
	}
	
	public GVector2f mul(GVector2f r)
	{
		return new GVector2f(m_x * r.getX(), m_y * r.getY());
	}
	
	public GVector2f mul(float r)
	{
		return new GVector2f(m_x * r, m_y * r);
	}
	
	public GVector2f div(GVector2f r)
	{
		return new GVector2f(m_x / r.getX(), m_y / r.getY());
	}
	
	public GVector2f div(float r)
	{
		return new GVector2f(m_x / r, m_y / r);
	}
	
	public GVector2f abs()
	{
		return new GVector2f(Math.abs(m_x), Math.abs(m_y));
	}
	
	public String toString()
	{
		return "(" + m_x + " " + m_y + ")";
	}

	public GVector2f Set(float x, float y) { this.m_x = x; this.m_y = y; return this; }
	public GVector2f Set(GVector2f r) { Set(r.getX(), r.getY()); return this; }

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

	public boolean equals(GVector2f r)
	{
		return m_x == r.getX() && m_y == r.getY();
	}
}