package main;

import static org.lwjgl.opengl.GL11.*;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Camerka;
import entities.Entity;
import renderers.Renderer;
import shaders.StaticShader;
import shapes.threeDimensional.Box;
import shapes.twoDimensional.Squad;
import terrains.Map;
import textures.ModelTexture;
import utils.FileLoader;
import utils.Logs;
import utils.OBJLoader;
import lights.Light;
import menus.RMenu;
import menus.BMenu;
import menus.TMenu;
import models.RawModel;
import models.TexturedModel;

public class Game extends JFrame{
	public static boolean mipMapping = true;
	
	private RMenu rmenu = null;
	private BMenu bmenu = null;
	private TMenu tmenu = null;
	private Window window = null;
	private Canvas canvas = null;
	private JPanel contentPanel = null;
	private Loader loader = null;
	private Renderer renderer = null;
	private Logs logs = null;
	private Map mapa = null;
	private Camera camera = null;
	private Camerka camerka = null;
	private StaticShader shader = null;
	private Light light = null;
	
	private Entity entity = null;
	
	public void init(){
		createFrame();
		Renderer.initGraphics();
		
		
		loader  = new Loader();
		renderer = new Renderer();
		shader = new StaticShader();
		camerka = new Camerka(shader);
		
		RawModel model = OBJLoader.loadObjModel("dragon", loader);
		ModelTexture texture = new ModelTexture(FileLoader.textureLoader("stall.png"));
//		texture.setShineDamper(10);
//		texture.setReflectivity(1);
		
		TexturedModel textureModel = null;
		textureModel = new TexturedModel(model,texture);
		entity = new Entity(textureModel,0,-1,0,0,0,0,0.5f);
		
		light = new Light(new Vector3f(20,20,20),new Vector3f(1,1,1));
		//camera = new Camera();
		
		//mapa = new Map(10,5,10);
		//mapa.initDefaultMap();
		
		
		
		//logs = new Logs();
	}
	
	public void mainLoop(){
		while(!Display.isCloseRequested()&&!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			
			//Input.update(camera, null);
			rmenu.useOptions();
			Renderer.clearScreen(rmenu);
			camerka.move();
			entity.rotate(0, 1, 0);
			shader.start();
			shader.loadLight(light);
			shader.loadViewMatrix(camerka);
			renderer.render(entity,shader);
			
			shader.stop();
			//toto by sa dalu urËite upraviù nejako
			
			
			//camera.init3DProjection();
			//camera.useView();
			//mapa.draw();
			
			
			//tu sa aû kreslÌ;
			//logs.update();
			
			window.update();
			
		}
	}
	
	private void createFrame(){
		initFrame();
		
		add(createContentPanel());
		contentPanel.updateUI();
		window = new Window(canvas);
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
	
	
	private JPanel createContentPanel(){
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBackground(Color.red);
		
		tmenu = new TMenu();
		contentPanel.add(tmenu,BorderLayout.NORTH);
		
		bmenu = new BMenu();
		bmenu.init();
		contentPanel.add(bmenu,BorderLayout.SOUTH);
		
		rmenu = new RMenu();
		rmenu.init();
		contentPanel.add(rmenu,BorderLayout.EAST);
		
		canvas = new Canvas();
		contentPanel.add(canvas,BorderLayout.CENTER);
		
		return contentPanel;
	};
	
}
