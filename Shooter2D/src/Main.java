import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class Main extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private final String TITLE = "Shooter 2D";
	private MouseEvents mouseEvent = new MouseEvents();
	private KeyEvents keyEvent = new KeyEvents();
	private WindowEvents windowEvent = new WindowEvents();
	public static Player player;
	public static Display display;
	public static int WIDTH,HEIGHT,gameIs,score,EnemyNum;
	public static boolean isRunning;
	public static Bullets bullets;
	public static Enemy[] enemies;
	public final static int BulletNum = 50;
	public static boolean debbuging=true;
	public static boolean moveUpDown=false;
	public static boolean toCenterAfterGetDemage=true;
	public static boolean sound=false;
	public static boolean bulletAreBouncing=true;
	public static boolean fullScreen=true;
	public static String weaponType="a";
	
	
	public static void main(String[] args) {
		
		Main game = new Main();
		
		game.init();
		
		display.start();

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
		Main.EnemyNum = 200;
		Main.bullets=new Bullets(Main.BulletNum);
		display=new Display();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(display);
		this.pack();
		this.setTitle(TITLE);
		this.setVisible(true);
		display.addMouseListener(mouseEvent);
		display.addKeyListener(keyEvent);
		this.addWindowListener(windowEvent);
		
	};
	
	public static void newGameInit(){
		
		Main.enemies=new Enemy[Main.EnemyNum];
		
		for( int i=0 ; i<Main.EnemyNum; i++){
			Main.enemies[i]=null;
		}
		
		Main.score=0;
	};
	
	public static void addEnemy(int num){
		Enemy[] helper = new Enemy[Main.enemies.length+num];
		int i;
		for(i=0 ; i<Main.enemies.length ; i++){
			helper[i]=Main.enemies[i];
		}
		for(;i<helper.length ; i++){
			helper[i]=Enemy.createEnemy();
		}
		Main.EnemyNum+=num;
		Main.enemies=helper;
	};
	
	public static void onResize(){
		
	}
}
