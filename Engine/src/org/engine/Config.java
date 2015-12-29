package org.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

public class Config {

	public final static int			ENGINE_FPS				= 60;
	public final static boolean		ENGINE_POST_FX			= true;
	public static final GVector3f	ENGINE_DEFAULT_AMBIENT	= new GVector3f(0.5f);

	public final static int TEXTURE_DEFAULT_FILTER 	= GL_NEAREST;//GL_SMOOTH;
	public final static int TEXTURE_DEFAULT_WRAP 	= GL_REPEAT;//GL_CLAMP_TO_EDGE;
	
	public static final boolean		WINDOW_FULLSCREEN	= false;
	public final static GVector2f	WINDOW_SIZE			= new GVector2f(800, 600);

	public static final GVector3f	MOUSE_ATTENUATION	= null;
	public static final GVector3f	MOUSE_LIGHT_COLOR	= null;
	public static final GVector3f	MOUSE_AMBIENT_COLOR	= null;
	
	public static final int PLAYER_MAX_CLICK_DIST 		= 100;

}
