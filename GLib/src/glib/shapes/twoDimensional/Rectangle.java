package glib.shapes.twoDimensional;

import glib.util.vector.GVector2f;

public class Rectangle {
//	private GVector2f position;
	private GVector2f size;
	
	public Rectangle(GVector2f position, GVector2f size){
//		this.position = position;
		this.size = size;
	};
	
	public float getArea(){
		return size.mul();
	}
	
	public float getPerimeter(){
		return (size.getX() + size.getY()) * 2;
	}
	
	public float getDiagonal(){
		return (float)Math.sqrt(size.getX() * size.getX() + size.getY() * size.getY());
	}
	
	public float getRadiusOfcircumscribedCircle(){
		return getDiagonal() / 2;
	}
}
