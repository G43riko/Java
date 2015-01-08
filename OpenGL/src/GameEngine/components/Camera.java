package GameEngine.components;

import GameEngine.core.CoreEngine;
import GameEngine.core.Input;
import GameEngine.core.Matrix4f;
import GameEngine.core.Quaternion;
import GameEngine.core.Time;
import GameEngine.core.Vector2f;
import GameEngine.core.Vector3f;
import GameEngine.rendering.RenderingEngine;
import GameEngine.rendering.Window;

public class Camera extends GameComponent{
	private Matrix4f projection;
	
	public Camera(float fov, float aspect, float zNear, float zFar){
		this.projection = new Matrix4f().initPerspective(fov,aspect,zNear,zFar);
	}
	
	public Matrix4f getViewProjection(){
		Matrix4f cameraRotation = getTransform().getRotation().ToRotationMatrix();
		Matrix4f cameraTranslation =  new Matrix4f().InitTranslation(-getTransform().getPosition().GetX(), 
																	 -getTransform().getPosition().GetY(), 
																	 -getTransform().getPosition().GetZ());
		return projection.Mul(cameraRotation.Mul(cameraTranslation));
	}
	
	public void addToEngine(CoreEngine engine){
		engine.getRenderingEngine().addCamera(this);
	};
}
