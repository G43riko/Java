package glib.util;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class GVertex {
	private GVector3f position;
	private GVector2f texture;
	private GVector3f normal;
	private GVector3f tangent;
	
	public GVertex(GVector3f position) {
		this(position,null,null,null);
	}
	
	public GVertex(GVector3f position, GVector2f texture) {
		this(position,texture,null,null);
	}
	
	public GVertex(GVector3f position, GVector2f texture, GVector3f normal) {
		this(position,texture,normal,null);
	}
	
	public GVertex(GVector3f position, GVector2f texture, GVector3f normal, GVector3f tangent) {
		this.position = position;
		this.texture = texture;
		this.normal = normal;
		this.tangent = tangent;
	}
	
	public int getSize(){
		int result = 0;
		if(position != null)
			result += 3;
		if(texture != null)
			result += 2;
		if(normal != null)
			result += 3;
		if(tangent != null)
			result += 3;
		return result;
	}

	public GVector3f getPosition() {return position;}
	public GVector2f getTexture() {return texture;}
	public GVector3f getNormal() {return normal;}
	public GVector3f getTangent() {return tangent;}
}
