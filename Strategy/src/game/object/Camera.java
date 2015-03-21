package game.object;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;

import game.util.Maths;
import game.world.Chunk3D;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class Camera extends GameObject{
	private final static float ROTATION_SPEED = 0.6f;
	private final static float MOVE_SPEED = 0.3f;
	private final static GVector3f up = new GVector3f(0,1,0);
	private float FOV = 70;
	private float NEAR_PLANE = 0.1f;
	private float FAR_PLANE = 1000;
	private float ASPECT_RATIO;
	public static float MAX_ANGLE;
	private final static boolean VERTICAL = false; 
	private boolean mouseLocked = false;
	
	private GVector2f centerPosition = new GVector2f(Display.getWidth()/2,Display.getHeight()/2);
	private GVector3f forward;
	private GMatrix4f projectionMatrix;
	
	//CONSTRUCTORS
	
	public Camera() {
		this(new GVector3f(0,1,5));
	}
	
	public Camera(GVector3f position) {
		super(position, GameObject.CAMERA);
		createProjectionMatrix();
		updateForward();
	}
	
	//OTHERS

	public void goForward(){
		if(VERTICAL)
			move(forward.mul(-MOVE_SPEED));
		else
			move(up.cross(forward).cross(up).mul(-MOVE_SPEED));
	}
	
	public void goBack(){
		if(VERTICAL)
			move(forward.mul(MOVE_SPEED));
		else
			move(up.cross(forward).cross(up).mul(MOVE_SPEED));
	}

	public void goRight(){
		move(up.cross(forward).mul(MOVE_SPEED));
	}
	
	public void goLeft(){
		move(up.cross(forward).mul(-MOVE_SPEED));
	}
	
	public void goUp(){
		move(up.mul(MOVE_SPEED));
	}
	
	public void goDown(){
		move(up.mul(-1).mul(MOVE_SPEED));
	}

	public void lockMouse(){
		Mouse.setCursorPosition(centerPosition.getXi(),centerPosition.getYi());
//		Mouse.setGrabbed(true);
		mouseLocked = true;
	}
	
	public void unlockMouse(){
		Mouse.setGrabbed(false);
		mouseLocked = false;
	}
	
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

	private void calcFrustum(){
		float Hnear = 2 * (float)Math.tan(FOV / 2) * NEAR_PLANE;
		float Wnear = Hnear * ASPECT_RATIO;
		float Hfar = 2 * (float)Math.tan(FOV / 2) * FAR_PLANE;
		float Wfar = Hfar * ASPECT_RATIO;
		
		GVector3f up = new GVector3f(0,1,0);
		GVector3f right = getForward().cross(up);
		
		GVector3f Cnear = getPosition().add(getForward().mul(NEAR_PLANE));
		GVector3f Cfar = getPosition().add(getForward().mul(FAR_PLANE));
		
		GVector3f nearLeft = Cnear.sub(right.mul(Wnear / 2));
		GVector3f nearRight = Cnear.add(right.mul(Wnear / 2));
		GVector3f nearUp = Cnear.add(up.mul(Hnear / 2));
		GVector3f nearBottom = Cnear.sub(up.mul(Hnear / 2));
		
		GVector3f farLeft = Cfar.sub(right.mul(Wfar / 2));
		GVector3f farRight = Cfar.add(right.mul(Wfar / 2));
		GVector3f farUp = Cfar.add(up.mul(Wfar / 2));
		GVector3f farBottom = Cfar.sub(up.mul(Wfar / 2));
		
		GVector3f vecLeft = farLeft.sub(nearLeft).Normalized();
		GVector3f vecRight = farRight.sub(nearRight).Normalized();
		GVector3f vecUp = farUp.sub(nearUp).Normalized();
		GVector3f vecBottom = farBottom.sub(nearBottom).Normalized();
		Camera.MAX_ANGLE = vecRight.dot(getForward());
		
//		GVector3f nearTopLeft = Cnear.add(up.mul(Hnear / 2)).sub(right.mul(Wnear / 2));
//		GVector3f nearTopRight = Cnear.add(up.mul(Hnear / 2)).add(right.mul(Wnear / 2));
//		GVector3f nearBottomLeft = Cnear.sub(up.mul(Hnear / 2)).sub(right.mul(Wnear / 2));
//		GVector3f nearBottomRight = Cnear.sub(up.mul(Hnear / 2)).add(right.mul(Wnear / 2));
//		
//		GVector3f farTopLeft = Cfar.add(up.mul(Hfar / 2)).sub(getForward().cross(new GVector3f(0,1,0)).mul(Wfar / 2));
//		GVector3f farTopRight = Cfar.add(up.mul(Hfar / 2)).add(getForward().cross(new GVector3f(0,1,0)).mul(Wfar / 2));
//		GVector3f farBottomLeft = Cfar.sub(up.mul(Hfar / 2)).sub(getForward().cross(new GVector3f(0,1,0)).mul(Wfar / 2));
//		GVector3f farBottomRight = Cfar.sub(up.mul(Hfar / 2)).add(getForward().cross(new GVector3f(0,1,0)).mul(Wfar / 2));
	}

	public boolean intersect(GVector3f a, GVector3f b, GVector3f c){
		return GVector3f.intersectRayWithSquare(getPosition(), getPosition().add(getForward().mul(1000)), a, b,c);
		
	}
	
	public void updateForward(){
		double x = Math.cos(Math.toRadians(360-getYaw()))*Math.cos(Math.toRadians(getPitch()));
		double y = Math.sin(Math.toRadians(360-getYaw()))*Math.cos(Math.toRadians(getPitch()));
		double z = Math.sin(Math.toRadians(getPitch()));
		forward = new GVector3f((float)y,(float)z,(float)x);
//		System.out.println(forward);
//		forward = getRotation().toRadians().eulerToDirectional();
//		System.out.println(forward);
	}

	public boolean mouseMove() {
		GVector2f deltaPos = new GVector2f();
		deltaPos = new GVector2f(Mouse.getX(),Mouse.getY()).sub(centerPosition);
		
		boolean rotY = deltaPos.getX() !=0;
		boolean rotX = deltaPos.getY() !=0;

		if(rotX){
			getRotation().setX(getRotation().getX() - (deltaPos.getY() * ROTATION_SPEED/2));
		}
		if(rotY){
			getRotation().setY(getRotation().getY() + (deltaPos.getX() * ROTATION_SPEED/2));
		}
		
		if(rotY || rotX){
			Mouse.setCursorPosition((int)centerPosition.getX(), (int)centerPosition.getY());
			return true;
		}
		return false;
	}

	//GETTERS
	
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
	
	public float getPitch(){
		return getRotation().getX();
	}
	
	public float getYaw(){
		return getRotation().getY();
	}

	public GMatrix4f getProjectionMatrix() {
		return projectionMatrix;
	}
	
	public GVector3f getForward() {
		return forward;
	}

	public boolean isMouseLocked() {
		return mouseLocked;
	}
}
