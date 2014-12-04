package PathFinder_1;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyEvents implements KeyListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println(arg0.getKeyCode());
		switch(arg0.getKeyCode()){
			case 97:
				PathFinding.findPossibles();
				break;
			case 98:
				PathFinding.findBestWay(0);
				break;
			case 99:
				PathFinding.moveToPossibles();
				break;
			case 100:
				PathFinding.findPath(1);
				break;
			case 101:
				PathFinding.repairPath();
				break;	
			case 32:
				Main.removedPossibles=false;
				Main.findGoal=false;
				break;
			case 10:
				Main.newGame();
				break;
			case 27:
				System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
