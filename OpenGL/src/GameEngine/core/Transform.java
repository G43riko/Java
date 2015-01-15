package GameEngine.core;

import GameEngine.core.util.Matrix4f;
import GameEngine.core.util.Quaternion;
import GameEngine.core.util.Vector3f;

public class Transform {
	private Transform  parent;
	private Matrix4f   parentMatrix;

	private Vector3f   pos;
	private Quaternion rot;
	private Vector3f   scale;

	private Vector3f   oldPos;
	private Quaternion oldRot;
	private Vector3f   oldScale;

	public Transform()
	{
		pos = new Vector3f(0,0,0);
		rot = new Quaternion(0,0,0,1);
		scale = new Vector3f(1,1,1);

		parentMatrix = new Matrix4f().InitIdentity();
	}

	public void update()
	{
		if(oldPos != null)
		{
			oldPos.Set(pos);
			oldRot.Set(rot);
			oldScale.Set(scale);
		}
		else
		{
			oldPos = new Vector3f(0,0,0).Set(pos).Add(1.0f);
			oldRot = new Quaternion(0,0,0,0).Set(rot).Mul(0.5f);
			oldScale = new Vector3f(0,0,0).Set(scale).Add(1.0f);
		}
	}

	public void rotate(Vector3f axis, float angle){
		rot = new Quaternion(axis, angle).Mul(rot).Normalized();
		System.out.println(rot.GetForward());
	}

	public void lookAt(Vector3f point, Vector3f up)
	{
		rot = getLookAtRotation(point, up);
	}

	public Quaternion getLookAtRotation(Vector3f point, Vector3f up){
		Quaternion res = new Quaternion(new Matrix4f().InitRotation(point.Sub(pos).Normalized(), up));
		return res;
	}

	public boolean hasChanged()
	{
		if(parent != null && parent.hasChanged())
			return true;

		if(!pos.equals(oldPos))
			return true;

		if(!rot.equals(oldRot))
			return true;

		if(!scale.equals(oldScale))
			return true;

		return false;
	}

	public Matrix4f getTransformation()
	{
		Matrix4f translationMatrix = new Matrix4f().InitTranslation(pos.GetX(), pos.GetY(), pos.GetZ());
		Matrix4f rotationMatrix = rot.ToRotationMatrix();
		Matrix4f scaleMatrix = new Matrix4f().InitScale(scale.GetX(), scale.GetY(), scale.GetZ());

		return getParentMatrix().Mul(translationMatrix.Mul(rotationMatrix.Mul(scaleMatrix)));
	}

	private Matrix4f getParentMatrix()
	{
		if(parent != null && parent.hasChanged())
			parentMatrix = parent.getTransformation();

		return parentMatrix;
	}

	public void setParent(Transform parent)
	{
		this.parent = parent;
	}

	public Vector3f getTransformedPos()
	{
		return getParentMatrix().Transform(pos);
	}

	public Quaternion getTransformedRot()
	{
		Quaternion parentRotation = new Quaternion(0,0,0,1);

		if(parent != null)
			parentRotation = parent.getTransformedRot();

		return parentRotation.Mul(rot);
	}

	public Vector3f getPosition()
	{
		return pos;
	}
	
	public void setPosition(Vector3f pos)
	{
		this.pos = pos;
	}

	public Quaternion getRotation()
	{
		return rot;
	}

	public void setRotation(Quaternion rotation)
	{
		this.rot = rotation;
	}

	public Vector3f getScale()
	{
		return scale;
	}

	public void setScale(Vector3f scale)
	{
		this.scale = scale;
	}
}
