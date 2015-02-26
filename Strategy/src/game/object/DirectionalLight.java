package game.object;

import glib.util.vector.GVector3f;

public class DirectionalLight extends GameObject{
	private GVector3f color;
	
	public DirectionalLight(GVector3f position, GVector3f rotation, GVector3f scale, int type) {
		super(new GVector3f(), new GVector3f(), new GVector3f(1,1,1),4);
	}

	public GVector3f getColor() {
		return color;
	}
	
}
