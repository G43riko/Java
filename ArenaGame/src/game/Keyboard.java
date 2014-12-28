package game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Keyboard implements KeyListener {
	public static boolean[] keys = new boolean[4];

	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode()==87){
			keys[0] = true;
		}
		if(arg0.getKeyCode()==65){
			keys[1] = true;
		}
		if(arg0.getKeyCode()==83){
			keys[2] = true;
		}
		if(arg0.getKeyCode()==68){
			keys[3] = true;
		}
		// TODO Auto-generated method stub
	};

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==87){
			keys[0] = false;
		}
		if(arg0.getKeyCode()==65){
			keys[1] = false;
		}
		if(arg0.getKeyCode()==83){
			keys[2] = false;
		}
		if(arg0.getKeyCode()==68){
			keys[3] = false;
		}
	};

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	};

}
