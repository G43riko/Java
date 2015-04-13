package game.component;

import org.engine.component.Camera;
import org.engine.component.GameComponent;
import org.engine.util.Maths;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;

import game.world.Chunk3D;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class CameraStrategy extends Camera{
	public static float MAX_ANGLE;
	private boolean mouseLocked = false;
	
	private GVector2f centerPosition = new GVector2f(Display.getWidth()/2,Display.getHeight()/2);
	
	
	//CONSTRUCTORS
	
	public CameraStrategy() {
		super();
		updateForward();
	}
	
	//OTHERS

	public boolean isVisible(GameComponent o){
		float distance = getPosition().dist(o.getPosition());
		
		GVector3f toObject =  o.getPosition().sub(getPosition()).Normalized();
		return distance > NEAR_PLANE && distance < FAR_PLANE && toObject.dot(getForward())<-0.6 && (Math.exp(-Math.pow((distance * 0.02), 1.5)) > 0.0001);
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
		CameraStrategy.MAX_ANGLE = vecRight.dot(getForward());
		
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
	
	public boolean isMouseLocked() {
		return mouseLocked;
	}
}
