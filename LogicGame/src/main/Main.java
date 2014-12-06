package main;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


public class Main extends JFrame{

	private static final long serialVersionUID = 1L;
	private final String TITLE = "LogicGame";
	private Keyboard keyEvent = new Keyboard();
	private Mouse mouseEvent = new Mouse();
	public  static Display display;
	public static int WIDTH,HEIGHT,gameIs;
	public static boolean isRunning;
	public static boolean fullScreen=false;

	
	public static void main(String[] args) {
		
		Main game = new Main();
		game.init();
		display.start();
	};
	
	private void init(){
		Main.WIDTH = 800;
		Main.HEIGHT = 600;
		this.setResizable(true);
		if(Main.fullScreen){
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Main.WIDTH = (int)screenSize.getWidth();
			Main.HEIGHT = (int)screenSize.getHeight();
			this.setResizable(false);
			this.setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setUndecorated(true);
		}
		display=new Display();
		mouseEvent.addLevel(display.getG2d().actLevel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(display);
		this.pack();
		this.setTitle(TITLE);
		this.setVisible(true);
		display.addKeyListener(keyEvent);
		display.addMouseListener(mouseEvent);
		display.addMouseMotionListener(mouseEvent);

	}

}
