package GUI;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Main extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JPanel bottomMenu;
	private JPanel rightMenu;
	private Canvas canvas;
	private int WIDTH = 1280;
	private int HEIGHT = 720;
	private JCheckBox a;
	private boolean fullScreen = true;
	
	public static void main(String[] args){
		Main game = new Main("tester");
		game.update();
	}
	
	public Main(String title){
		setResizable(true);
		if(fullScreen){
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			WIDTH = (int)screenSize.getWidth();
			HEIGHT = (int)screenSize.getHeight();
			setResizable(false);
			setExtendedState(Frame.MAXIMIZED_BOTH);
			setUndecorated(true);
		}
		setVisible(true);
		setTitle(title);
		System.out.println(WIDTH);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		/*
		 * fps
		 * number of blocks
		 * number of drawing blocks
		 * points
		 * polygons
		 * camera rotation
		 * camera position XY
		 * selected tile XYZ
		 */
		
		this.contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBackground(Color.red);
		add(contentPanel);
		
		bottomMenu = new JPanel();
		contentPanel.add(bottomMenu,BorderLayout.SOUTH);
		bottomMenu.setBackground(Color.green);
		bottomMenu.setPreferredSize(new Dimension(100,100));
		
		rightMenu = new JPanel();
		contentPanel.add(rightMenu,BorderLayout.EAST);
		//rightMenu.setBackground(Color.blue);
		rightMenu.setPreferredSize(new Dimension(200,200));
		rightMenu.setLayout(new FlowLayout());
		
		a = new JCheckBox("Wireframe");
		//hairButton.setBackground(rightMenu.getBackground());
	    a.setSelected(true);
	    rightMenu.add(a);
	    
	    canvas = new Canvas();
	    contentPanel.add(canvas,BorderLayout.CENTER);
	    try {
			Display.setParent(canvas);
			Display.setVSyncEnabled(true);
	        Display.create();
	        Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	    /*
	     * a - wireframe
	     * b - mipmapping
	     * c - textures
	     * d - infos
	     * e - ambient light
	     * f - directional light
	     * g - shadows
	     * h - ambient occusion
	     * i - hide models
	     * j - hide paths
	     * k - VSync
	     * 
	     */
	    glClearColor(0.0f,0.0f,1f,0.0f);
	    //repaint();
	    contentPanel.updateUI();
	    
	}
	
	private void update(){
		while(!Display.isCloseRequested()&&!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			if(a.isSelected()){
				glClearColor(0.0f,1f,0.0f,0.0f);
			}
			else{
				glClearColor(0.0f,0.0f,1f,0.0f);
			}
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			Display.sync(60);
			Display.update();
		}
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
		System.exit(1);
	};
}
