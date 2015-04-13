package game.component;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;

import game.util.Maths;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector3f;

public class BasicCamera extends GameComponent {
	protected final static float ROTATION_SPEED = 0.6f;
	protected final static float MOVE_SPEED = 0.3f;
	protected final static GVector3f up = new GVector3f(0,1,0);
	protected final static boolean VERTICAL = false; 
	
	protected float FOV = 70;
	protected float NEAR_PLANE = 0.1f;
	protected float FAR_PLANE = 1000;
	protected float ASPECT_RATIO;
	
	private GMatrix4f projectionMatrix;
	private GVector3f forward;
	
	//CONSTRUCTORS
	
	public BasicCamera() {
		this(new GVector3f(0,1,5));
	}
	
	public BasicCamera(GVector3f position) {
		super(position, GameComponent.CAMERA);
		createProjectionMatrix();
	}
	
	//OTHERS
	
	private void createProjectionMatrix(){
		ASPECT_RATIO  = (float)Display.getWidth()/(float)Display.getHeight();
		float y_scale = (1f / (float)Math.tan(Math.toRadians(FOV / 2f))) * ASPECT_RATIO;
		float x_scale = y_scale/ASPECT_RATIO;
		
		float frustum_length = FAR_PLANE - NEAR_PLANE;
		
		Matrix4f mat = new Matrix4f();
		mat.m00 = x_scale;
		mat.m11 = y_scale;
		mat.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		mat.m23 = -1;
		mat.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		mat.m33 = 0;
		projectionMatrix = Maths.MatrixToGMatrix(mat);
	}

	public void updateForward(){
		double x = Math.cos(Math.toRadians(360-getYaw()))*Math.cos(Math.toRadians(getPitch()));
		double y = Math.sin(Math.toRadians(360-getYaw()))*Math.cos(Math.toRadians(getPitch()));
		double z = Math.sin(Math.toRadians(getPitch()));
		forward = new GVector3f((float)y,(float)z,(float)x);
	}
	
	//GOERS
	

	public void goForward(){
		if(VERTICAL)
			move(getForward().mul(-MOVE_SPEED));
		else
			move(up.cross(getForward()).cross(up).mul(-MOVE_SPEED));
	}
	
	public void goBack(){
		if(VERTICAL)
			move(getForward().mul(MOVE_SPEED));
		else
			move(up.cross(getForward()).cross(up).mul(MOVE_SPEED));
	}

	public void goRight(){
		move(up.cross(getForward()).mul(MOVE_SPEED));
	}
	
	public void goLeft(){
		move(up.cross(getForward()).mul(-MOVE_SPEED));
	}
	
	public void goUp(){
		move(up.mul(MOVE_SPEED));
	}
	
	public void goDown(){
		move(up.mul(-1).mul(MOVE_SPEED));
	}

	
	//GETTERS
	
	public GMatrix4f getProjectionMatrix() {
		return projectionMatrix;
	}

	
	public GVector3f getForward() {
		return forward;
	}
	
	//GETTERS-ANGLES

	public float getPitch(){
		return getRotation().getX();
	}
	
	public float getYaw(){
		return getRotation().getY();
	}

	
	//GETTERS-VECTORS
	
	public GVector3f getForwardVector(){
		if(VERTICAL)
			return forward.mul(-MOVE_SPEED);
		
		return up.cross(forward).cross(up).mul(-MOVE_SPEED);
	}
	
	public GVector3f getBackVector(){
		if(VERTICAL)
			return forward.mul(MOVE_SPEED);
		
		return up.cross(forward).cross(up).mul(MOVE_SPEED);
	}
	
	public GVector3f getRightVector(){
		return up.cross(forward).mul(MOVE_SPEED);
	}
	
	public GVector3f getLeftVector(){
		return up.cross(forward).mul(-MOVE_SPEED);
	}
	
	public GVector3f getUpVector(){
		return up.mul(MOVE_SPEED);
	}
	
	public GVector3f getDownVector(){
		return up.mul(-MOVE_SPEED);
	}
}
