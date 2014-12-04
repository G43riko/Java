package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;


public class KeyEvents implements KeyListener {

	private Player player;
	private Main main;
	@Override
	public void keyPressed(KeyEvent arg0) {
		//System.out.println(arg0.getKeyCode());
		switch(arg0.getKeyCode()){
			case 38:
				player.move[0] = true;
				break;
			case 37:
				player.move[1] = true;
				break;
			case 40:
				player.move[2] = true;
				break;
			case 39:
				player.move[3] = true;
				break;
			case 32:
				Main.gameIs=3;
				break;
			case 17:
				main.bombs.add(new Bomb(player.getX(),player.getY(),player.getDosah()));
				break;
			}
	};

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch(arg0.getKeyCode()){
			case 38:
				player.move[0] = false;
				break;
			case 37:
				player.move[1] = false;
				break;
			case 40:
				player.move[2] = false;
				break;
			case 39:
				player.move[3] = false;
				break;
		}
	};

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	};
	
	public void addPlayer(Player kto){
		this.player = kto;
	}

	public void addMain(Main main) {
		this.main = main;
	}

}
