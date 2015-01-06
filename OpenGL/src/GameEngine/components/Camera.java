package GameEngine.components;

import GameEngine.core.Input;
import GameEngine.core.Matrix4f;
import GameEngine.core.Quaternion;
import GameEngine.core.Time;
import GameEngine.core.Vector2f;
import GameEngine.core.Vector3f;
import GameEngine.rendering.RenderingEngine;
import GameEngine.rendering.Window;

public class Camera extends GameComponent{
	public final static Vector3f yAxis = new Vector3f(0, 1, 0);
	
//	private Vector3f pos;
//	private Vector3f forward;
//	private Vector3f up;
	private Vector2f centerPosition = new Vector2f(Window.getWidth()/2,Window.getHeight()/2);
	private boolean mouseLocked = false;
	
	private Matrix4f projection;
	
	public Camera(float fov, float aspect, float zNear, float zFar){
//		this.up = new Vector3f(0,1,0);
//		this.forward = new Vector3f(0,0,1).Normalized();
//		this.pos = new Vector3f(5,0,5).Normalized();
		this.projection = new Matrix4f().initPerspective(fov,aspect,zNear,zFar);
		
	}
	
	public Matrix4f getViewProjection(){
//		Matrix4f cameraRotation = new Matrix4f().InitRotation(forward, up);
		Matrix4f cameraRotation = getTransform().getRotation().ToRotationMatrix();
		Matrix4f cameraTranslation =  new Matrix4f().InitTranslation(-getTransform().getPosition().GetX(), 
																	 -getTransform().getPosition().GetY(), 
																	 -getTransform().getPosition().GetZ());
		return projection.Mul(cameraRotation.Mul(cameraTranslation));
	}
	
//	public void rotateY(float angle){
//		Vector3f Haxis = yAxis.Cross(forward).Normalized();
//		forward.rotate(yAxis,angle).Normalized();
//		up = forward.Cross(Haxis).Normalized();
//	}
//	
//	public void rotateX(float angle){
//		Vector3f Haxis = yAxis.Cross(forward).Normalized();
//		forward.rotate(Haxis,angle).Normalized();
//		up = forward.Cross(Haxis).Normalized();
//		
//	}

	public void move(Vector3f dir, float amt){
//		pos = pos.Add(dir.Mul(amt));
		getTransform().setPosition(getTransform().getPosition().Add(dir.Mul(amt)));
	}
	
	public void addToRenderingEngine(RenderingEngine rengeringEngine){
		rengeringEngine.addCamera(this);
	};
	
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
		System.out.println(getTransform().getRotation().GetRight());
		if(mouseLocked){
			Vector2f deltaPos = Input.GetMousePosition().Sub(centerPosition);
			
			boolean rotY =deltaPos.GetX() !=0;
			boolean rotX =deltaPos.GetY() !=0;

			if(rotY){
//				rotateY((float)Math.toRadians((deltaPos.GetX() * sensitivity)));
				getTransform().setRotation(getTransform().getRotation().Mul(new Quaternion(yAxis,(float)Math.toRadians(deltaPos.GetX() * sensitivity))).Normalized());
			}
			if(rotX){
//				rotateX((float)Math.toRadians((-deltaPos.GetY() * sensitivity)));
				getTransform().setRotation(getTransform().getRotation().Mul(new Quaternion(getTransform().getRotation().GetRight().Normalized(),(float)Math.toRadians(-deltaPos.GetY() * sensitivity))).Normalized());
//				getTransform().setRotation(getTransform().getRotation().Mul(new Quaternion(new Vector3f(1,0,0),(float)Math.toRadians(-deltaPos.GetY() * sensitivity))).Normalized());
			}
			
			if(rotY || rotX){
				Input.SetMousePosition(centerPosition);
			}
		}
	}
	
//	public Vector3f getRight(){
//		return up.Cross(forward).Normalized();
//	}
//	
//	public Vector3f getLeft(){
//		return forward.Cross(up).Normalized();
//	}
//	
//	public Vector3f getTop(){
//		return getLeft().Cross(forward).Normalized();
//	}
//	
//	public Vector3f getDown(){
//		return getRight().Cross(forward).Normalized();
//	}
//	
//	public Vector3f getPos() {
//		return pos;
//	}
//
//	public void setPos(Vector3f pos) {
//		this.pos = pos;
//	}
//
//	public Vector3f getForward() {
//		return forward;
//	}
//
//	public void setForward(Vector3f forward) {
//		this.forward = forward;
//	}
//
//	public Vector3f getUp() {
//		return up;
//	}
//
//	public void setUp(Vector3f up) {
//		this.up = up;
//	}
}
