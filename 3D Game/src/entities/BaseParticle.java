package entities;

import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

public class BaseParticle extends Entity{
	private float horiz,vert;
	public float fi,teta;
	
	public BaseParticle(TexturedModel model, Vector3f pos,float scale) {
		super(model, pos, scale);
		horiz = 0;
		vert = 0;
		updateAngles();
	}
	public void updateAngles(){
		fi   = horiz * (float)Math.PI/180;  // vodorovne otocenie v radianoch
		teta = vert  * (float)Math.PI/180;  // zvisle otocenie v radianoch
	}
	
	public float getrRotX() {
		return (float)(Math.toDegrees(Math.cos(teta) * Math.sin(fi)));
		
	}
	public float getrRotY() {
		return (float)Math.toDegrees(Math.sin(teta));
	}
	public float getrRotZ() {
		return (float)(Math.toDegrees(-Math.cos(teta) * Math.cos(fi)));
	}

	public float getHoriz() {
		return horiz;
	}

	public void setHoriz(float horiz) {
		this.horiz = horiz;
	}

	public float getVert() {
		return vert;
	}

	public void setVert(float vert) {
		this.vert = vert;
	}

}
