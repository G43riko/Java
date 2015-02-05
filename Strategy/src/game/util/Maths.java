package game.util;

import game.object.Camera;
import glib.util.vector.GMatrix4f;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;


public class Maths {
	public static Matrix4f createTransformationMatrix(Vector3f translation,float rx, float ry, float rz, float scale){
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation,matrix,matrix);
		Matrix4f.rotate((float)Math.toRadians(rx),new Vector3f(1,0,0) ,matrix,matrix);
		Matrix4f.rotate((float)Math.toRadians(ry),new Vector3f(0,1,0) ,matrix,matrix);
		Matrix4f.rotate((float)Math.toRadians(rz),new Vector3f(0,0,1) ,matrix,matrix);
		Matrix4f.scale(new Vector3f(scale,scale,scale),matrix, matrix);
		return matrix;
	};
	
	public static Matrix4f createViewMatrix(Camera camera){
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.setIdentity();
		Matrix4f.rotate((float)Math.toRadians(camera.getPitch()),new Vector3f(1,0,0) ,viewMatrix,viewMatrix);
		Matrix4f.rotate((float)Math.toRadians(camera.getYaw()),new Vector3f(0,1,0) ,viewMatrix,viewMatrix);
		Vector3f cameraPos = new Vector3f(camera.getPosition().getX(),camera.getPosition().getY(),camera.getPosition().getZ());
		Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
		Matrix4f.translate(negativeCameraPos,viewMatrix,viewMatrix);
		return viewMatrix;
	}
	
	public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.scale(new Vector3f(scale.x, scale.y, 1f), matrix, matrix);
		return matrix;
	}
	
	
	public static GMatrix4f MatrixToGMatrix(Matrix4f a){
		GMatrix4f mat = new GMatrix4f();
		mat.set(0, 0, a.m00);
		mat.set(1, 0, a.m10);
		mat.set(2, 0, a.m20);
		mat.set(3, 0, a.m30);
		mat.set(0, 1, a.m01);
		mat.set(1, 1, a.m11);
		mat.set(2, 1, a.m21);
		mat.set(3, 1, a.m31);
		mat.set(0, 2, a.m02);
		mat.set(1, 2, a.m12);
		mat.set(2, 2, a.m22);
		mat.set(3, 2, a.m32);
		mat.set(0, 3, a.m03);
		mat.set(1, 3, a.m13);
		mat.set(2, 3, a.m23);
		mat.set(3, 3, a.m33);


//		mat.set(0, 0, a.m00);
//		mat.set(1, 0, a.m01);
//		mat.set(2, 0, a.m02);
//		mat.set(3, 0, a.m03);
//		mat.set(0, 1, a.m10);
//		mat.set(1, 1, a.m11);
//		mat.set(2, 1, a.m12);
//		mat.set(3, 1, a.m13);
//		mat.set(0, 2, a.m20);
//		mat.set(1, 2, a.m21);
//		mat.set(2, 2, a.m22);
//		mat.set(3, 2, a.m23);
//		mat.set(0, 3, a.m30);
//		mat.set(1, 3, a.m31);
//		mat.set(2, 3, a.m32);
//		mat.set(3, 3, a.m33);
		return mat;
	}
}
