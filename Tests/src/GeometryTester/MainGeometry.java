package GeometryTester;

import static org.lwjgl.opengl.GL11.*;

import java.awt.geom.*;
import java.awt.geom.Point2D.Float;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;


public class MainGeometry {
	private float mouseX,mouseY;
	private Point2D.Float mouse;
	
	public static void main(String[] args) {
		MainGeometry geometry = new MainGeometry();
	}
	
	public MainGeometry(){
		try {
			Display.setDisplayMode(new DisplayMode(800,800));
			Display.setTitle("Teddst");
			Display.create();
			Mouse.create();
		} catch (LWJGLException e) {
			System.out.println("display nebol inicializovany");
			Display.destroy();
			System.exit(1);
		}
		GL11.glClearColor(1,1,1,1);
		while(!Display.isCloseRequested()){
			update();
		}
		Display.destroy();
		System.exit(0);
	}

	private void update(){
		mouseY = (float)Mouse.getY()/Display.getHeight()*2-1;
		mouseX = (float)Mouse.getX()/Display.getWidth()*2-1;
		mouse = new Point2D.Float(mouseX,mouseY); 
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		drawLines();
		
		//draw Circle and Dist to circle
		drawCircle(new Point2D.Float(0.5f,0.5f),0.2,32, new Vector3f(0,1,0));
		drawShortestDistToCircle(new Point2D.Float(0.5f,0.5f),0.2f);
		
		//draw Line and Dist to line
		Line2D.Float line = new Line2D.Float(0f,0f,-0.5f,-0.5f);
		drawLine(line,new Vector3f(0,1,0));
		drawShortestDistToLine(line);
		
		Display.update();
		Display.sync(30);
	}
	
	private void drawShortestDistToCircle(Point2D.Float pos, float radius) {
		Vector2f temp = new Vector2f((mouseX-pos.x),(mouseY-pos.y)).normalise(new Vector2f((mouseX-pos.x),(mouseY-pos.y)));
		float tempX = pos.x+temp.x*radius;
		float tempY = pos.y+temp.y*radius;
		drawLine(new Line2D.Float(mouseX,mouseY,tempX,tempY),new Vector3f(0,0,1));
	}

	private void drawCircle(Point2D.Float sur, double radius, int num, Vector3f color) {
		float[][] points= new float[num][2];
		for(int i=0 ; i<num ; i++){
			points[i][0] = sur.x + (float)(Math.cos(Math.toRadians(360/num*i))*radius);
			points[i][1] = sur.y + (float)(Math.sin(Math.toRadians(360/num*i))*radius);
			System.out.println(points[i][0]+" "+points[i][1]);
		}
		for(int i=0 ; i<num ; i++){
			if(i+1<num)
				drawLine(points[i][0],points[i][1],points[i+1][0],points[i+1][1],color);
			else
				drawLine(points[i][0],points[i][1],points[0][0],points[0][1],color);
		}
		
	}

	private void drawShortestDistToLine(Line2D.Float line){
		Point2D.Float bod = getIntersectionPoint(line,new Line2D.Float(mouseX+(line.x1-line.x2)*4,mouseY-(line.y1-line.y2)*4,
																	   mouseX-(line.x1-line.x2)*4,mouseY+(line.y1-line.y2)*4));
		if(bod==null){
		Point2D.Float p1 = new Point2D.Float(line.x1,line.y1);
		
		Point2D.Float p2 = new Point2D.Float(line.x2,line.y2);
		bod=(mouse.distance(p1)<mouse.distance(p2))?p1:p2;
		}
		drawLine(mouseX,mouseY,bod.x,bod.y,new Vector3f(0,0,1));
	}
	
	private void drawLine(Line2D.Float line,Vector3f color){
		drawLine(line.x1,line.y1,line.x2,line.y2,color);
	}
	
	private void drawLine(float Ax, float Ay, float Bx, float By,Vector3f color) {
		glBegin(GL_LINES);
		{
			glColor3f(color.x,color.y,color.z);	glVertex3f(Ax, Ay,1);
			glColor3f(color.x,color.y,color.z);	glVertex3f(Bx, By,1);
		}
		glEnd();
	}

	private void drawLines(){
		glBegin(GL_LINES);
		{
			glColor3f(1,0,0);	glVertex3f(-1, mouseY,1);
			glColor3f(1,0,0);	glVertex3f(1, mouseY,1);
			
			glColor3f(1,0,0);	glVertex3f(mouseX, -1,1);
			glColor3f(1,0,0);	glVertex3f(mouseX, 1,1);
		}
		glEnd();
	}
	
	public static double pointToLineDistance(double Ax, double Ay, double Bx,double By , double x, double y) {
		double normalLength = Math.sqrt((Bx-Ax)*(Bx-Ax)+(By-Ay)*(By-Ay));
	    return Math.abs((x-Ax)*(By-Ay)-(y-Ay)*(Bx-Ax))/normalLength;
	}
	
	public Point2D.Float getIntersectionPoint(Line2D.Float line1, Line2D.Float line2) {
	    if (! line1.intersectsLine(line2) ) return null;
	      double px = line1.getX1(),
	            py = line1.getY1(),
	            rx = line1.getX2()-px,
	            ry = line1.getY2()-py;
	      double qx = line2.getX1(),
	            qy = line2.getY1(),
	            sx = line2.getX2()-qx,
	            sy = line2.getY2()-qy;

	      double det = sx*ry - sy*rx;
	      if (det == 0) {
	        return null;
	      } else {
	        double z = (sx*(qy-py)+sy*(px-qx))/det;
	        if (z==0 ||  z==1) return null;  // intersection at end point!
	        return new Point2D.Float(
	          (float)(px+z*rx), (float)(py+z*ry));
	      }
	 } 

}
