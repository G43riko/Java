package main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import renderers.Renderer;
import terrains.Map;
import utils.Logs;
import menus.RMenu;
import menus.BMenu;

public class Game extends JFrame{
	private RMenu rmenu = null;
	private BMenu bmenu = null;
	private Window window = null;
	private Canvas canvas = null;
	private JPanel contentPanel = null;
	private Logs logs = null;
	private Map mapa = null;
	
	public void init(){
		createFrame();
		Renderer.initGraphics();
		
		mapa = new Map(10,4,10);
		mapa.initDefaultMap();
		
		//logs = new Logs();
	}
	
	public void mainLoop(){
		while(!Display.isCloseRequested()&&!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			Renderer.clearScreen();
			//toto by sa dalu urËite upraviù nejako
			GL11.glClearColor((float)rmenu.BGRed.getValue()/255,(float)rmenu.BGGreen.getValue()/255,(float)rmenu.BGBlue.getValue()/255, 1.0f);
			
			//logs.update();
			
			
			window.update();
		}
	}
	
	private void createFrame(){
		initFrame();
		
		createContentPanel();
		add(contentPanel);
		
		window = new Window(canvas);
		
		contentPanel.updateUI();
	};
	
	public void cleanUp(){
		window.cleanUp();
		System.exit(0);
	};
	
	private void initFrame(){
		setResizable(true);
		if(Main.FULLSCREEN){
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Main.WIDTH = (int)screenSize.getWidth();
			Main.HEIGHT = (int)screenSize.getHeight();
			setResizable(false);
			setExtendedState(Frame.MAXIMIZED_BOTH);
			setUndecorated(true);
		}
		setVisible(true);
		setTitle(Main.TITLE);
		setSize(Main.WIDTH, Main.HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	};
	
	private void createContentPanel(){
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBackground(Color.red);
		
		bmenu = new BMenu();
		bmenu.init();
		contentPanel.add(bmenu,BorderLayout.SOUTH);
		
		rmenu = new RMenu();
		rmenu.init();
		contentPanel.add(rmenu,BorderLayout.EAST);
		
		canvas = new Canvas();
		contentPanel.add(canvas,BorderLayout.CENTER);
	};
	
}
