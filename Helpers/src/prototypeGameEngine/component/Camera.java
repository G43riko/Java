package prototypeGameEngine.component;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import prototypeGameEngine.core.Input;
import Goniometry.Matrix4f;
import Goniometry.Vector2f;
import Goniometry.Vector3f;

public class Camera {
	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;
	
	private static final float ROTATION_SPEED = 0.6f;
	private static final float MOVE_SPEED = 0.3f;
	
	public static final float MAX_ANGLE = (float)Math.toRadians(40);
	
	private float pitch;
	private float yaw;
	private float roll;
	
	private Vector2f centerPosition;
	private Vector3f pos;
	private Vector3f forward;
	private Matrix4f projectionMatrix;
	
	private int unlockMouseKey = Keyboard.KEY_N;
	private int lockMouseKey = Keyboard.KEY_M;
	private int forwardKey = Keyboard.KEY_W;
	private int backKey = Keyboard.KEY_S;
	private int leftKey = Keyboard.KEY_A;
	private int rightKey = Keyboard.KEY_D;
	private int upKey = Keyboard.KEY_SPACE;
	private int downKey = Keyboard.KEY_LSHIFT;
	
	private boolean move = false;
	private boolean rotate = false;
	private boolean mouseLocked = false;
	
	public Camera(){
		createProjectionMatrix(Display.getWidth(),Display.getHeight());
		centerPosition = new Vector2f(Display.getWidth() / 2, Display.getHeight() / 2);
		updateForward();
		pos = new Vector3f(0,0,0);
		pitch = yaw = roll = 0;
	}
	
	public void input(){
		if(Keyboard.isKeyDown(forwardKey)){
			move = true;
			goForward();
		}
		if(Keyboard.isKeyDown(backKey)){
			move = true;
			goBack();
		}
		if(Keyboard.isKeyDown(rightKey)){
			move = true;
			goRight();
		}
		if(Keyboard.isKeyDown(leftKey)){
			move = true;
			goLeft();
		}
		if(Keyboard.isKeyDown(upKey)){
			move = true;
			goUp();
		}
		if(Keyboard.isKeyDown(downKey)){
			move = true;
			goDown();
		}
		
		if(Keyboard.isKeyDown(lockMouseKey)){
			Input.setMousePosition(centerPosition);
			Input.setCursor(false);
			mouseLocked = true;
		}
		
		if(Keyboard.isKeyDown(unlockMouseKey)){
			Input.setCursor(true);
			mouseLocked = false;
		}
		
		if(mouseLocked){
			Vector2f deltaPos = Input.getMousePosition();
			deltaPos.sub(centerPosition);
			
			boolean rotY =deltaPos.getX() !=0;
			boolean rotX =deltaPos.getY() !=0;

			if(rotX){
				pitch -= (deltaPos.getY() * ROTATION_SPEED/2);
			}
			if(rotY){
				yaw += (deltaPos.getX() * ROTATION_SPEED/2);
			}
			
			if(rotY || rotX){
				rotate = true;
				Input.setMousePosition(centerPosition);
			}
		}
		
		if(move || rotate){
			move = rotate = false;
			updateForward();
		}
	}
	
	private void createProjectionMatrix(float width, float height){
		float aspectRatio = width/height;
		projectionMatrix = new Matrix4f().initPerspective(FOV, aspectRatio, NEAR_PLANE, FAR_PLANE);
	}
	
	private void updateForward(){
		double x = Math.cos(Math.toRadians(360-yaw))*Math.cos(Math.toRadians(pitch));
		double y = Math.sin(Math.toRadians(360-yaw))*Math.cos(Math.toRadians(pitch));
		double z = Math.sin(Math.toRadians(pitch));
		forward = new Vector3f((float)y,(float)z,(float)x);
	}
	
	public void move(float x, float y, float z){
		pos.add(new Vector3f(x,y,z));
	}
	
	public void move(Vector3f v){
		pos.add(v);
	}
	
	public void goForward(){
		if(mouseLocked){
			move(forward.getInstance().sub(MOVE_SPEED));
		}
		else{
			double x = Math.cos(Math.toRadians(pitch)) * Math.sin(Math.toRadians(yaw)) * MOVE_SPEED;
			double z = -Math.cos(Math.toRadians(pitch)) * Math.cos(Math.toRadians(yaw)) * MOVE_SPEED;
			move((float)x, 0, (float)z);
		}
		
	}
	
	public void goBack(){
		if(mouseLocked){
			move(forward.getInstance().sub(-MOVE_SPEED));
		}
		else{
			double x = -Math.cos(Math.toRadians(pitch)) * Math.sin(Math.toRadians(yaw)) * MOVE_SPEED;
			double z = Math.cos(Math.toRadians(pitch)) * Math.cos(Math.toRadians(yaw)) * MOVE_SPEED;
			move((float)x, 0, (float)z);
		}
	}
	
	public void goLeft(){
		double z = Math.cos(Math.toRadians(pitch)) * Math.sin(Math.toRadians(yaw)) * MOVE_SPEED;
		double x = Math.cos(Math.toRadians(pitch)) * Math.cos(Math.toRadians(yaw)) * MOVE_SPEED;
		move((float)x, 0, (float)z);
	}
	
	public void goRight(){
		double z = Math.cos(Math.toRadians(pitch)) * Math.sin(Math.toRadians(yaw)) * MOVE_SPEED;
		double x = Math.cos(Math.toRadians(pitch)) * Math.cos(Math.toRadians(yaw)) * MOVE_SPEED;
		move((float)x, 0, (float)z);
	}
	
	public void goUp(){
		move(0,MOVE_SPEED,0);
	}
	
	public void goDown(){
		move(0,-MOVE_SPEED,0);
	}

	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}
}
