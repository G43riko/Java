package PathFinder_2;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseEvents implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(Main.start==null){
			int X=(int)Math.floor(arg0.getX()/Main.block);
			int Y=(int)Math.floor(arg0.getY()/Main.block);
			Main.map[X][Y].type=1;
			Main.start=new int[]{X,Y};
		}
		else if(Main.goal==null){
			int X=(int)Math.floor(arg0.getX()/Main.block);
			int Y=(int)Math.floor(arg0.getY()/Main.block);
			Main.map[X][Y].type=2;
			Main.goal=new int[]{X,Y};

		}
		else{
			int X=(int)Math.floor(arg0.getX()/Main.block);
			int Y=(int)Math.floor(arg0.getY()/Main.block);
			if(Main.map[X][Y].type==3){
				Main.map[X][Y].type=0;
			}
			else{
				Main.map[X][Y].type=3;
			}
		}
		
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
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
