package game.vilage.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Window extends JFrame{
	
	public void init(){
		setSize(new Dimension(800,600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Village");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)(screenSize.getWidth()-getWidth())/2;
		int centerY = (int)(screenSize.getHeight()-getHeight())/2;
		setLocation(centerX, centerY);
		
		setResizable(true);
//		setVisible(true);
	}
}
