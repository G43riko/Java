package game.vilage.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Window extends JFrame{
	
	public Window(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(new Dimension(800,600));
		setTitle("Village");
		setResizable(true);
	}
	
	public void init(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)(screenSize.getWidth()-getWidth())/2;
		int centerY = (int)(screenSize.getHeight()-getHeight())/2;
		setLocation(centerX, centerY);

	}
}
