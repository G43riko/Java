package Main;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Block {
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
