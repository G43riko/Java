package org.engine.physics;

import glib.util.vector.GVector3f;

public class Enviroment {
	public static float SPEED = 1;
	public final static float FRICTION = 0.9f;
	public static GVector3f GRAVITY = new GVector3f(0,-0.03f,0);
	
	public final static float MIN_X = -50;
	public final static float MIN_Y = 0;
	public final static float MIN_Z = -50;
	public final static float MAX_X = 50;
	public final static float MAX_Y = 100;
	public final static float MAX_Z = 50;
}
