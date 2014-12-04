package Inputs;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Main.Main;
import Particles.Firework;



public class MouseEvents implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
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
		if(!Main.isClick){
			if(Main.gameIs==1){
				double dataX = arg0.getX()+Main.players.offsetX;
				double dataY = arg0.getY()+Main.players.offsetY;
				if(Main.Sidebar&&Main.sidebar.click(arg0.getX(),arg0.getY())){
					Main.isClick=true;
					return;
				}
				Main.isClick=true;
				if(Main.Sidebar){
					Main.bullets.fire(dataX,dataY,Main.sidebar.isSelect);
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Main.isClick=false;
	}

}
