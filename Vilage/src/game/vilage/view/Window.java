package game.vilage.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public abstract class Window extends JFrame{
	private static final long serialVersionUID = 1L;
	private final static int CASCADE_OFFSET = 20;
	
	private static Window lastWindow = null;
	
	//CONSTRUCTORS
	
	public Window(){
		initFrame();
		lastWindow = this;
	}
	
	//OTHERS
	
	private void initFrame(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(new Dimension(800,600));
		setTitle("Village");
		setResizable(false);
		
		if(lastWindow == null){
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int centerX = (int)(screenSize.getWidth()-getWidth())/2;
			int centerY = (int)(screenSize.getHeight()-getHeight())/2;
			setLocation(centerX, centerY);
		}
		else
			setLocation(lastWindow.getLocation().x+CASCADE_OFFSET, lastWindow.getLocation().y+CASCADE_OFFSET);
		
	}
	
	protected void onClose(){
		
	}
}
