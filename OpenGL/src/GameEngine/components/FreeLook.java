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

package GameEngine.components;

import GameEngine.core.Input;
import GameEngine.core.util.Quaternion;
import GameEngine.core.util.Vector2f;
import GameEngine.core.util.Vector3f;
import GameEngine.rendering.Window;

public class FreeLook extends GameComponent{
	public final static Vector3f yAxis = new Vector3f(0, 1, 0);
	private Vector2f centerPosition = new Vector2f(Window.getWidth()/2,Window.getHeight()/2);
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
	
	public void input(float delta){
		
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
			//getTransform().setRotation(getTransform().getRotation().Mul(new Quaternion(getTransform().getRotation().GetRight(),(float)Math.toRadians(-sensitivity))).Normalized());
			getTransform().rotate(yAxis, (float) Math.toRadians(-sensitivity * strong));
		}
		if(Input.getKey(Input.KEY_RIGHT)){
			//getTransform().setRotation(getTransform().getRotation().Mul(new Quaternion(getTransform().getRotation().GetRight(),(float)Math.toRadians(sensitivity))).Normalized());
			getTransform().rotate(yAxis, (float) Math.toRadians(sensitivity * strong));
		}
		if(Input.getKey(Input.KEY_UP)){
			//getTransform().setRotation(getTransform().getRotation().Mul(new Quaternion(yAxis,(float)Math.toRadians(-sensitivity))).Normalized());
			getTransform().rotate(getTransform().getRotation().GetRight(), (float) Math.toRadians(-sensitivity * strong));
		}
		if(Input.getKey(Input.KEY_DOWN)){
			//getTransform().setRotation(getTransform().getRotation().Mul(new Quaternion(yAxis,(float)Math.toRadians(sensitivity))).Normalized());
			getTransform().rotate(getTransform().getRotation().GetRight(), (float) Math.toRadians(sensitivity * strong));
		}
		
		if(mouseLocked){
			Vector2f deltaPos = Input.GetMousePosition().Sub(centerPosition);
			
			boolean rotY =deltaPos.GetX() !=0;
			boolean rotX =deltaPos.GetY() !=0;

			if(rotY){
				getTransform().rotate(yAxis, (float) Math.toRadians(deltaPos.GetX() * sensitivity));
			}
			if(rotX){
				getTransform().rotate(getTransform().getRotation().GetRight(), (float) Math.toRadians(-deltaPos.GetY() * sensitivity));
			}
			
			if(rotY || rotX){
				Input.SetMousePosition(centerPosition);
			}
		}
		
	}
	
}
