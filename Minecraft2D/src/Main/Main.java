package Main;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import Bullets.Bullets;
import Components.Block;
import Components.Mapa;
import Components.Player;
import Components.SideBar;
import Components.Warning;
import Inputs.KeyEvents;
import Inputs.MouseEvents;
import Particles.Light;
import Particles.ParticleCirc;


public class Main extends JFrame{

	private static final long serialVersionUID = 1L;
	private final String TITLE = "Minecraft2D";
	private MouseEvents mouseEvent = new MouseEvents();
	private KeyEvents keyEvent = new KeyEvents();
	public static int xOnScreen, yOnScreen;
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	public static int gameIs,FPS,ticks;
	public static double gravity = 0.5;
	public static Display display = null;
	public static Bullets bullets = null;
	public static SideBar sidebar = null;
	public static Player players = null;
	public static Mapa mapa = null;
	
	public static List<ParticleCirc> particlesCirc = new ArrayList<ParticleCirc>() ;
	public static ArrayList<Warning> oznamenia = new ArrayList<Warning>();
	public static ArrayList<Light> lights = new ArrayList<Light>();
	public static boolean[] keys;
	public static boolean isRunning;
	public static boolean LightsAndShadows=false;
	public static boolean BacgroundAsImage=true;
	public static boolean HelpingTexts=true;
	public static boolean fullScreen=true;
	public static boolean Warnings=false;
	public static boolean Images=false;
	public static boolean isClick=false;
	public static boolean Skosenie=true;
	public static boolean Sidebar=true;
	public static boolean Minimap=true;
	public static boolean Borders=true;
	public static boolean Player=true;
	
	public static void main(String[] args) {
		Main game = new Main();
		game.init();
		display.start();
		Main.newGameInit(1);
		if(Main.LightsAndShadows){
			Main.mapa.autoSetLightnessOnce(true);
		}
		Main.gameIs=1;
	};
	
	private void init(){
		this.setResizable(true);
		if(Main.fullScreen){
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Main.WIDTH = (int)screenSize.getWidth();
			Main.HEIGHT = (int)screenSize.getHeight();
			this.setResizable(false);
			this.setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setUndecorated(true);
		}
		Main.xOnScreen = (int)Math.floor(Main.WIDTH/Block.size);
		Main.yOnScreen = (int)Math.floor(Main.HEIGHT/Block.size);
		if(Main.Sidebar){
			Main.sidebar = new SideBar(10,40);
		}
		Main.bullets = new Bullets();
		display=new Display();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(display);
		this.pack();
		this.setTitle(TITLE);
		this.setVisible(true);
		display.addKeyListener(keyEvent);
		display.addMouseListener(mouseEvent);
	};
	
	public static void newGameInit(int Players){
		Main.particlesCirc.clear();
		Main.keys=new boolean[5];
		for(int i=0 ; i<Main.keys.length ; i++){
			Main.keys[i]=false;
		}
		Main.mapa = new Mapa();
		if(Main.Player){
			Main.players=new Player(100, Main.mapa.Mapa[0].length*Block.size/2-Block.size*10);
		}
	}
}
