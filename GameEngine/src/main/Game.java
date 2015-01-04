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
import entities.Selector;
import renderers.Renderer;
import shaders.StaticShader;
import shapes.threeDimensional.Box;
import shapes.twoDimensional.Rectangle;
import terrains.Map;
import terrains.Terrain;
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
	public static boolean isLoading = false;
	
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
	private Terrain terrain = null;
	private Selector selector = null;
	
	public void init(){
		isLoading = true;
		createFrame();
		Renderer.initGraphics();
		
		
		loader  = new Loader();
		renderer = new Renderer();
		shader = new StaticShader();
		
		//RawModel model = OBJLoader.loadObjModel("box", loader);
		RawModel model = Box.getModel(loader, .1f, 1000, .1f);
		ModelTexture texture = new ModelTexture(FileLoader.textureLoader("dirt.jpg"));
		TexturedModel textureModel = null;
		textureModel = new TexturedModel(model,texture);
		Entity entity = new Entity(textureModel,-5,-1,0,0,0,0,0.5f);
		selector = new Selector(entity);
		light = new Light(new Vector3f(20,20,20),new Vector3f(1,1,1));
		
		terrain = new Terrain("HeightMap", loader);
		
		//camera = new Camera();
		
//		mapa = new Map(8,4,8);
		mapa = new Map(32,4,32);
//		mapa = new Map(2,4,2);
		mapa.initDefaultMap(loader);
		rmenu.setMinimap(mapa.getTerrain());
		tmenu.setMap(mapa);

		camerka = new Camerka(shader);
		bmenu.setCamerka(camerka);
		//logs = new Logs();
		isLoading = false;
	}
	
	public void mainLoop(){
		while(!Display.isCloseRequested()&&!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			if(isLoading){
				continue;
			}
			//Input.update(camera, null);	
			Renderer.clearScreen(rmenu);
			rmenu.useOptions();
			rmenu.getMinimap().update();
			
			camerka.update();
			bmenu.updateCameraWindow();
			selector.input(mapa,camerka.getTargetPosition().x,camerka.getTargetPosition().y);
			float x = (float)Math.tan(Math.toRadians(90-camerka.getPitch()))*camerka.getPosition().y;
			float rot =(float)Math.toRadians(camerka.getYaw());
			rot = (float)Math.PI/4;
			//selector.getEntity().setLocation(camerka.getTargetPosition().x, camerka.getPosition().y-20, camerka.getTargetPosition().y);
			selector.getEntity().setLocation((float)Math.sin(rot)*x, camerka.getPosition().y-20,(float)Math.cos(rot)*x);
			shader.start();
			shader.loadTypeOfView(rmenu.getTypeOfView());
			shader.loadLight(light);
			shader.loadViewMatrix(camerka);

//			shader.loadChangeColor(true);
//			shader.loadColor(new Vector3f(1,0,1));
//			glBegin(GL_LINES);
//				float x = -20;
//				float y = -20;
//				glVertex3f(x, 0, y);
//				glVertex3f(x,20, y);
//			glEnd();
//			shader.loadChangeColor(false);
			
			shader.loadChangeColor(true);
			shader.loadColor(new Vector3f(1,0,1));
			selector.draw(renderer, shader);
			shader.loadChangeColor(false);
			mapa.draw(renderer, shader);
			terrain.draw(renderer, shader);
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
		
		bmenu = new BMenu();
		bmenu.init();
		contentPanel.add(bmenu,BorderLayout.SOUTH);
		
		rmenu = new RMenu();
		rmenu.init();
		contentPanel.add(rmenu,BorderLayout.EAST);
		
		tmenu = new TMenu(bmenu, rmenu);
		contentPanel.add(tmenu,BorderLayout.NORTH);
		
		canvas = new Canvas();
		contentPanel.add(canvas,BorderLayout.CENTER);
		
		return contentPanel;
	};
	
}
