import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Keyboard implements KeyListener {
	private G2D g2d;
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println(arg0.getKeyCode());
		switch(arg0.getKeyCode()){
			case 32:
				Main.gameIs=3;
				break;
			case 17:
				g2d.simplification();
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
	public void setG2D(G2D g2d){
		this.g2d = g2d;
	}
}
