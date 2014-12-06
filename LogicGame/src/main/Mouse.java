package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {
	
	private boolean leftIsDown = false;
	private boolean rightIsDown = false;
	private Level level;
	public Point movable = null;
	public float clickPositionX;
	public float clickPositionY;
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getButton()==1){
			this.movable = level.checkClick(arg0.getX(),arg0.getY());
			if(this.movable!=null){
				clickPositionX = arg0.getX();
				clickPositionY = arg0.getY();
			}
			this.leftIsDown = true;
		}
		else if(arg0.getButton()==3){
			
			this.rightIsDown = true;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(arg0.getButton()==1){
			if(movable!=null){
				level.checkEdgeColisions();
			}
			this.movable = null;
			this.leftIsDown = false;
		}
		else if(arg0.getButton()==3){
			this.rightIsDown = false;
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		if(leftIsDown){
			if(movable!=null){
				movable.setX((int)(arg0.getX()-Point.size/2));
				movable.setY((int)(arg0.getY()-Point.size/2));
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}
	
	public boolean IsleftDown(){
		return this.leftIsDown;
	}
	
	public boolean IsRightDown(){
		return this.leftIsDown;
	}

	public void addLevel(Level actLevel) {
		this.level = actLevel;
		
	}
}


