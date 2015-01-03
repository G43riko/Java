package Main;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Block {
	private float range = 3f;
	private Vector2f pos;
	private Vector3f color = new Vector3f();
	public int type = 0;
	
	public Block(int x, int y,int type){
		pos = new Vector2f(x,y);
		this.type = type;
		switch(type){
			case 0:
				break;
			case 1:
				color = new Vector3f(1,0,0);
				break;
			case 2:
				color = new Vector3f(0,1,0);
		}
	}
	
	public void draw(){
		if(type==0)
			return;
		float surX = -1+Map.blockX*pos.x;
		float surY = -1+Map.blockY*pos.y;
		
		Vector3f totoPos = new Vector3f((surX+Map.blockX/2),(surY+Map.blockY/2),0);
		Vector3f mousePos = new Vector3f((float)Mouse.getX()/Display.getWidth()*2-1,(float)Mouse.getY()/Display.getHeight()*2-1,1);
		Vector3f toMysVec = new Vector3f();
		Vector3f.sub(totoPos, mousePos, toMysVec);
		color.x=0;
		if(toMysVec.length()<range)
			
			color.x=(float)Math.pow(Vector3f.dot(new Vector3f(0,0,-1), toMysVec.normalise(toMysVec)), 15);
		glBegin(GL_TRIANGLES);
		{
			glColor3f(color.x,color.y,color.y);	glVertex3f(surX, surY,  1f);
			glColor3f(color.x,color.y,color.y);	glVertex3f(surX+Map.blockX, surY,  1f);
			glColor3f(color.x,color.y,color.y);	glVertex3f(surX+Map.blockX, surY+Map.blockY,  1f );
			
			glColor3f(color.x,color.y,color.y);	glVertex3f(surX, surY,  1f);
			glColor3f(color.x,color.y,color.y);	glVertex3f(surX, surY+Map.blockY,  1f);
			glColor3f(color.x,color.y,color.y);	glVertex3f(surX+Map.blockX, surY+Map.blockY,  1f );
		}
		glEnd();
	}
}
