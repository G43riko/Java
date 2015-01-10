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
import GameEngine.core.Quaternion;
import GameEngine.core.Vector2f;
import GameEngine.core.Vector3f;
import GameEngine.rendering.Window;

public class FreeLook extends GameComponent{
	public final static Vector3f yAxis = new Vector3f(0, 1, 0);
	private Vector2f centerPosition = new Vector2f(Window.getWidth()/2,Window.getHeight()/2);
	private boolean mouseLocked = false;
	
	public void move(Vector3f dir, float amt){
		getTransform().setPosition(getTransform().getPosition().Add(dir.Mul(amt)));
	}
	
	public void input(float delta){
		float sensitivity = 0.3f;
		float movAmt = (float)(10 * delta);
		float rotAmt = (float)(100 * delta);
		
		if(Input.getKey(Input.KEY_ESCAPE)){
			Input.SetCursor(true);
			mouseLocked = false;
		}
		
		if(Input.GetMouse(0)){
			Input.SetMousePosition(centerPosition);
			Input.SetCursor(false);
			mouseLocked = true;
		}
		
		
		if(Input.getKey(Input.KEY_W)){
			move(getTransform().getRotation().GetForward(),movAmt);
		}
		if(Input.getKey(Input.KEY_S)){
			move(getTransform().getRotation().GetForward(),-movAmt);
		}
		if(Input.getKey(Input.KEY_A)){
			move(getTransform().getRotation().GetLeft(),movAmt);
		}
		if(Input.getKey(Input.KEY_D)){
			move(getTransform().getRotation().GetRight(),movAmt);
		}
		if(Input.getKey(Input.KEY_UP)){
			getTransform().setRotation(getTransform().getRotation().Mul(new Quaternion(getTransform().getRotation().GetRight(),(float)Math.toRadians(-sensitivity))).Normalized());
		}
		if(Input.getKey(Input.KEY_DOWN)){
			getTransform().setRotation(getTransform().getRotation().Mul(new Quaternion(getTransform().getRotation().GetRight(),(float)Math.toRadians(sensitivity))).Normalized());
		}
		if(Input.getKey(Input.KEY_LEFT)){
			getTransform().setRotation(getTransform().getRotation().Mul(new Quaternion(yAxis,(float)Math.toRadians(-sensitivity))).Normalized());
		}
		if(Input.getKey(Input.KEY_RIGHT)){
			getTransform().setRotation(getTransform().getRotation().Mul(new Quaternion(yAxis,(float)Math.toRadians(sensitivity))).Normalized());
		}
		if(Input.getKey(Input.KEY_SPACE)){
			move(new Vector3f(0,1,0),movAmt);
		}
		if(Input.getKey(Input.KEY_LSHIFT)){
			move(new Vector3f(0,1,0),-movAmt);
		}
		if(mouseLocked){
			Vector2f deltaPos = Input.GetMousePosition().Sub(centerPosition);
			
			boolean rotY =deltaPos.GetX() !=0;
			boolean rotX =deltaPos.GetY() !=0;

			if(rotY){
				getTransform().Rotate(yAxis, (float) Math.toRadians(deltaPos.GetX() * sensitivity));
			}
			if(rotX){
				getTransform().Rotate(getTransform().getRotation().GetRight(), (float) Math.toRadians(-deltaPos.GetY() * sensitivity));
			}
			
			if(rotY || rotX){
				Input.SetMousePosition(centerPosition);
			}
		}
		
	}
	
}
