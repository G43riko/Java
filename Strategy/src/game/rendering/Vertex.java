package game.rendering;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class Vertex {
	public final static int SIZE = 8;
	private GVector3f position;
	private GVector2f textureCoord;
	private GVector3f normal;
	
	public Vertex(GVector3f position, GVector2f textureCoord, GVector3f normal) {
		super();
		this.position = position;
		this.textureCoord = textureCoord;
		this.normal = normal;
	}
	
	public GVector3f getPosition() {
		return position;
	}
	
	public GVector2f getTextureCoord() {
		return textureCoord;
	}
	
	public GVector3f getNormal() {
		return normal;
	}

	public void setPosition(GVector3f position) {
		this.position = position;
	}

	public void setTextureCoord(GVector2f textureCoord) {
		this.textureCoord = textureCoord;
	}

	public void setNormal(GVector3f normal) {
		this.normal = normal;
	}
}
