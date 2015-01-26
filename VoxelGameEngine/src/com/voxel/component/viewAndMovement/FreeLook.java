//package GameEngine.components;
//
//import GameEngine.core.Input;
//import GameEngine.core.Quaternion;
//import GameEngine.core.Vector2f;
//import GameEngine.core.Vector3f;
//import GameEngine.rendering.Window;
//
//public class FreeLook extends GameComponent
//{
//	private static final Vector3f Y_AXIS = new Vector3f(0,1,0);
//
//	private boolean m_mouseLocked = false;
//	private float   m_sensitivity;
//	private int     m_unlockMouseKey;
//	public FreeLook(float sensitivity)
//	{
//		this(sensitivity, Input.KEY_ESCAPE);
//	}
//
//	public FreeLook(float sensitivity, int unlockMouseKey)
//	{
//		this.m_sensitivity = sensitivity;
//		this.m_unlockMouseKey = unlockMouseKey;
//	}
//
//	@Override
//	public void input(float delta)
//	{
//		Vector2f centerPosition = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);
//
//		if(Input.getKey(m_unlockMouseKey))
//		{
//			Input.SetCursor(true);
//			m_mouseLocked = false;
//		}
//		if(Input.GetMouseDown(0))
//		{
//			Input.SetMousePosition(centerPosition);
//			Input.SetCursor(false);
//			m_mouseLocked = true;
//		}
//
//		if(m_mouseLocked)
//		{
//			Vector2f deltaPos = Input.GetMousePosition().Sub(centerPosition);
//
//			boolean rotY = deltaPos.GetX() != 0;
//			boolean rotX = deltaPos.GetY() != 0;
//
//			if(rotY)
//				getTransform().Rotate(Y_AXIS, (float) Math.toRadians(deltaPos.GetX() * m_sensitivity));
//			if(rotX)
//				getTransform().Rotate(getTransform().getRotation().GetRight(), (float) Math.toRadians(-deltaPos.GetY() * m_sensitivity));
//
//			if(rotY || rotX)
//				Input.SetMousePosition(centerPosition);
//		}
//	}
//}

package com.voxel.component.viewAndMovement;

import org.lwjgl.opengl.Display;

import glib.util.GLog;
import com.voxel.core.util.GVector2f;
import com.voxel.core.util.GVector3f;

import com.voxel.component.GameComponent;
import com.voxel.core.Input;


public class FreeLook extends GameComponent{
	public final static GVector3f yAxis = new GVector3f(0, 1, 0);
	private GVector2f centerPosition = new GVector2f(Display.getWidth()/2,Display.getHeight()/2);
	private boolean mouseLocked = false;
	private int unlockMouseKey;
	private float sensitivity;
	
	public FreeLook(float sensitivity){
		this(sensitivity,Input.KEY_ESCAPE);
	}
	
	public FreeLook(float sensitivity,int unlockMouseKey){
		this.sensitivity = sensitivity;
		this.unlockMouseKey = unlockMouseKey;
	}
	
	public void input(){
		if(Input.getKey(unlockMouseKey)){
			Input.SetCursor(true);
			mouseLocked = false;
		}
		
		if(Input.GetMouse(0)){
			Input.SetMousePosition(centerPosition);
			Input.SetCursor(false);
			mouseLocked = true;
		}
		float strong = 3;
		if(Input.getKey(Input.KEY_LEFT)){
			getTransform().rotate(yAxis, (float) Math.toRadians(-sensitivity * strong));
		}
		if(Input.getKey(Input.KEY_RIGHT)){
			getTransform().rotate(yAxis, (float) Math.toRadians(sensitivity * strong));
		}
		if(Input.getKey(Input.KEY_UP)){
			getTransform().rotate(getTransform().getRotation().getRight(), (float) Math.toRadians(-sensitivity * strong));
		}
		if(Input.getKey(Input.KEY_DOWN)){
			getTransform().rotate(getTransform().getRotation().getRight(), (float) Math.toRadians(sensitivity * strong));
		}
		
		if(mouseLocked){
			GVector2f deltaPos = Input.GetMousePosition().sub(centerPosition);
			
			boolean rotY =deltaPos.getX() !=0;
			boolean rotX =deltaPos.getY() !=0;

			if(rotY){
				getTransform().rotate(yAxis, (float) Math.toRadians(deltaPos.getX() * sensitivity));
			}
			if(rotX){
				getTransform().rotate(getTransform().getRotation().getRight(), (float) Math.toRadians(-deltaPos.getY() * sensitivity));
			}
			
			if(rotY || rotX){
				Input.SetMousePosition(centerPosition);
			}
		}
		
	}
	
}
