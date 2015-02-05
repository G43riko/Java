package game.object;


import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import game.rendering.RenderingEngine;
import game.rendering.material.Texture2D;
import game.rendering.model.Model;
import game.rendering.shader.Shader;
import game.util.Maths;
import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector3f;

public class Entity extends GameObject{
	private Texture2D texture;
	private Model model;
	private Shader shader;
	
	public Entity(Model model, Texture2D texture){
		this(new GVector3f(),new GVector3f(),new GVector3f(1,1,1),model,texture);
	}
	
	public Entity(GVector3f position, GVector3f rotation, GVector3f scale, Model model, Texture2D texture) {
		super(position, rotation, scale,2);
		this.texture = texture;
		this.model = model;
	}

	public Texture2D getTexture() {
		return texture;
	}

	public void setTexture(Texture2D texture) {
		this.texture = texture;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	public void update(){};
	
	public void render(RenderingEngine renderer){
		renderer.renderEntity(this, RenderingEngine.entityShader);
	}

	public Shader getShader() {
		return shader;
	}

	public void setShader(Shader shader) {
		this.shader = shader;
	}

	public GMatrix4f getTransformationMatrix(){
		Matrix4f trans = Maths.createTransformationMatrix(new Vector3f(getPosition().getX(),getPosition().getY(),getPosition().getZ()), 
				 getRotation().getX(), getRotation().getY(), getRotation().getZ(), getScale().getX());
		return Maths.MatrixToGMatrix(trans);
	}
	
}
