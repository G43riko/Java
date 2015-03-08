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
		return mat;
	}
	
	public static Matrix4f GMatrixToMatrix(GMatrix4f a){
		Matrix4f mat = new Matrix4f();
		mat.m00 = a.get(0, 0);
		mat.m10 = a.get(1, 0);
		mat.m20 = a.get(2, 0);
		mat.m30 = a.get(3, 0);
		mat.m01 = a.get(0, 1);
		mat.m11 = a.get(1, 1);
		mat.m21 = a.get(2, 1);
		mat.m31 = a.get(3, 1);
		mat.m02 = a.get(0, 2);
		mat.m12 = a.get(1, 2);
		mat.m22 = a.get(2, 2);
		mat.m32 = a.get(3, 2);
		mat.m03 = a.get(0, 3);
		mat.m13 = a.get(1, 3);
		mat.m23 = a.get(2, 3);
		mat.m33 = a.get(3, 3);
		return mat;
	}
}
