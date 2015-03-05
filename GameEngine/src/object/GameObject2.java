package object;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import rendering.RenderingEngine;
import utils.Maths;

public class GameObject2 {
	private Vector3f position;
	private Vector3f rotation;
	private Vector3f scale;
	
	public GameObject2(){
		position = new Vector3f();
		rotation = new Vector3f();
		scale = new Vector3f(1,1,1);
	}
	
	public void render(RenderingEngine renderingEngine){
		
	}
	public void input(){
		
	}
	public void update(){
		
	}
	
	public Matrix4f getTransformationMatrix(){
		return Maths.createTransformationMatrix(new Vector3f(position.x,position.y,position.z), 
				rotation.x, rotation.y, rotation.z, scale.x);
	}
}
