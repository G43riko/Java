package PathFinder_2;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class Main extends JFrame{

	private static final long serialVersionUID = 1L;
	private final String TITLE = "ArenaGame";
	private MouseEvents mouseEvent = new MouseEvents();
	private KeyEvents keyEvent = new KeyEvents();
	public static Display display;
	public static int WIDTH,HEIGHT,gameIs;
	public static boolean isRunning;
	public static boolean fullScreen=true;
	public static Mapa[][] map;
	public static int block=40;
	public static boolean onlyGoal,findGoal,removedPossibles;
	public static int[] goal = null;
	public static int[] start = null;
	
	public static void main(String[] args) {
		
		Main game = new Main();
		
		game.init();
		
		display.start();
		
		Main.newGame();
	};
	
	private void init(){
		Main.WIDTH =800;
		Main.HEIGHT =600;
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
		display.addMouseListener(mouseEvent);
		display.addKeyListener(keyEvent);
	};
	
	public static void newGame(){
		Main.goal=Main.start=null;
		Main.onlyGoal=Main.findGoal=removedPossibles=true;
		Mapa.create((int)Main.HEIGHT/Main.block,(int)Main.WIDTH/Main.block);
		//Mapa.create(5,5);
	}

}
