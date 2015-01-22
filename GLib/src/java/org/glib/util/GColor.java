package java.org.glib.util;

import java.awt.Color;

public class GColor extends Color{
	private static final long serialVersionUID = 1L;
	
	public GColor(float r, float g, float b, float a) {
		super(r, g, b, a);
	}
	
	public GColor(float r, float g, float b) {
		super(r, g, b);
	}
}
