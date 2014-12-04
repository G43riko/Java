package Inputs;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Main.Main;


public class KeyEvents implements KeyListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		//System.out.println(arg0.getKeyCode());
		switch(arg0.getKeyCode()){
			case 17:
				Main.newGameInit(1);
				Main.gameIs=1;
				break;
			case 32:
				Main.players.startJump();
				break;
			case 27:
				System.exit(0);
			case 38:
				Main.players.dy=-1;
				break;
			case 39:
				Main.players.dx=1;
				break;
			case 65:
				Main.players.dx=-1;
				break;
			case 68:
				Main.players.dx=1;
				break;
			case 40:
				Main.players.dy=1;
				break;
			case 37:
				Main.players.dx=-1;
				break;
			case 97:
				Main.mapa.data.saveMap();
				break;
			case 98:
				Main.mapa.autoSetLightnessOnce(false);
				break;
			case 99:
				for(int i=0 ; i<Main.mapa.Mapa.length ; i++){
					for(int j=0 ; j<Main.mapa.Mapa[i].length ; j++){
						Main.mapa.Mapa[i][j].setBorders();
					}
				}
				break;
			case 73:
				Main.mapa.showMap();
				break;
			
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		 switch(arg0.getKeyCode()){
			case 38:
				Main.players.dy=0;
				break;
			case 39:
				Main.players.dx=0;
				break;
			case 40:
				Main.players.dy=0;
				break;
			case 37:
				Main.players.dx=0;
				break;
			case 65:
				Main.players.dx=0;
				break;
			case 68:
				Main.players.dx=0;
				break;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
