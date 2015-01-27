package com.voxel.rendering;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class Vertex {
	public static final int SIZE=8;
	private GVector3f pos;
	private GVector2f texCoord;
	private GVector3f normal;
	private GVector3f tangent;
	
	public Vertex(GVector3f pos){
		this(pos,new GVector2f(0,0));
	}
	
	public Vertex(GVector3f pos,GVector2f texCoord){
		this(pos,texCoord,new GVector3f(0,0,0));
	}
	
	public Vertex(GVector3f pos,GVector2f texCoord, GVector3f normal){
		this.pos = pos;
		this.texCoord = texCoord;
		this.normal = normal;
	}

	public Vertex(GVector3f pos,GVector2f texCoord, GVector3f normal, GVector3f tangent){
		this.pos = pos;
		this.texCoord = texCoord;
		this.normal = normal;
		this.tangent = tangent;
	}
	
	public GVector3f getPos(){
		return pos;
	}
	
	public void setPos(GVector3f pos){
		this.pos = pos;
	}

	public GVector2f getTexCoord() {
		return texCoord;
	}

	public void setTexCoord(GVector2f texCoord) {
		this.texCoord = texCoord;
	}

	public GVector3f getNormal() {
		return normal;
	}

	public void setNormal(GVector3f normal) {
		this.normal = normal;
	}

	public GVector3f getTangent() {
		return tangent;
	}

	public void setTangent(GVector3f tangent) {
		this.tangent = tangent;
	}
}	
