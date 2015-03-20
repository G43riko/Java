package game.util;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import game.object.Camera;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class MousePicker {
	private GVector3f currentRay;
	
	private GMatrix4f projectionMatrix;
	private GMatrix4f viewMatrix;
	private Camera camera;
	
	public MousePicker(Camera camera) {
		this.projectionMatrix = camera.getProjectionMatrix();
		this.camera = camera;
		this.viewMatrix  = Maths.MatrixToGMatrix(Maths.createViewMatrix(camera));
	}

	public GVector3f getCurrentRay() {
		return currentRay;
	}
	
	public void update(){
		viewMatrix = Maths.MatrixToGMatrix(Maths.createViewMatrix(camera));
		currentRay = calculateMouseRay();
	}
	
	private GVector3f calculateMouseRay(){
		float mouseX = Mouse.getX();
		float mouseY = Mouse.getY();
		GVector2f normalizedCoords = getNormalizedDeviceCoords(mouseX, mouseY);
		Vector4f clipCoords = new Vector4f(normalizedCoords.getX(), normalizedCoords.getY(), -1f, 1f);
		Vector4f eyeCoords = toEyeCoords(clipCoords);
		GVector3f worlRay = toWorldCoords(eyeCoords);
		return worlRay;
	}
	
	private GVector3f toWorldCoords(Vector4f eyeCoords){
		Matrix4f invertedview = new Matrix4f();
		Matrix4f.invert(Maths.GMatrixToMatrix(viewMatrix), invertedview);
		Vector4f rayWorld = Matrix4f.transform(invertedview, eyeCoords, null);
		GVector3f mouseRay = new GVector3f(rayWorld.x, rayWorld.y, rayWorld.z);
		return mouseRay.Normalized();
	}
	
	private Vector4f toEyeCoords(Vector4f clipCoords){
		Matrix4f invertedProjection = new Matrix4f();
		Matrix4f.invert(Maths.GMatrixToMatrix(projectionMatrix), invertedProjection);
		Vector4f eyeCoords = Matrix4f.transform(invertedProjection, clipCoords, null);
		return new Vector4f(eyeCoords.x, eyeCoords.y, -1f, 0f);
	}
	
	private GVector2f getNormalizedDeviceCoords(float mouseX, float mouseY){
		float x = (2f*mouseX)/Display.getWidth() - 1;
		float y = (2f*mouseY)/Display.getHeight() - 1;
		return new GVector2f(x,y);
	}
	
}
