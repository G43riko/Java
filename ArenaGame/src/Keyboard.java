import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Keyboard implements KeyListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println(arg0.getKeyCode());
		switch(arg0.getKeyCode()){
			case 32:
				Main.gameIs=3;
				break;
			}
	};

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	};

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	};

}
