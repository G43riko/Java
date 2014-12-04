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
	private final String TITLE = "Bomber";
	private KeyEvents keyEvent = new KeyEvents();
	public  static Display display;
	public static int WIDTH,HEIGHT,gameIs;
	public static boolean isRunning;
	public static boolean fullScreen=false;
	private Map mapa;
	private Player player;
	public List<Bomb> bombs = new ArrayList<Bomb>();
	
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(display);
		this.pack();
		this.setTitle(TITLE);
		this.setVisible(true);
		this.player = new Player("gabo");
		this.mapa = new Map();
		display.addKeyListener(keyEvent);
		display.getG2d().addMap(mapa);
		display.getG2d().addPlayer(player);
		display.getG2d().addBombs(bombs);
		keyEvent.addPlayer(player);
		keyEvent.addMain(this);
		
		this.addComponentListener(new ComponentListener() {
		    public void componentResized(ComponentEvent e) {
		    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				Main.WIDTH = (int)screenSize.getWidth();
				Main.HEIGHT = (int)screenSize.getHeight();
		    }
			public void componentHidden(ComponentEvent e) {}
			public void componentMoved(ComponentEvent e) {}
			public void componentShown(ComponentEvent e) {}

		});
	}

}
