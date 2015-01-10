package game.components;
import game.core.Vector2f;
import game.main.Game;
import game.towers.Tower;
import game.towers.TowerA;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Mouse implements MouseListener, MouseMotionListener {
	private Game game;
	public static boolean leftDown = false;
	public Mouse(Game game) {
		this.game = game;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
//		if(e.getButton()==1)
//			game.setTarget(new Vector2f(e.getX(),e.getY()));
//		else if (e.getButton()==3)
//			Tower.addTower(new TowerA(e.getX(),e.getY(),100,1));
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton()==1)
			game.everybodyCameHere(new Vector2f(e.getX(),e.getY()));
		else if (e.getButton()==3)
			Tower.addTower(new TowerA(e.getX(),e.getY(),200,1));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
