package game.light;

import game.object.GameObject;
import glib.util.vector.GVector3f;

public class DirectionalLight extends GameObject{
	private GVector3f color;
	
	//CONSTRUCTORS
	
	public DirectionalLight(GVector3f position, GVector3f rotation, GVector3f scale, int type) {
		super(position, GameObject.DIRECTIONAL_LIGHT);
	}

	//GETTERS
	
	public GVector3f getColor() {
		return color;
	}
	
}
