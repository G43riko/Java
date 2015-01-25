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
import org.lwjgl.util.vector.Vector2f;
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
	private Entity entity2 = null;
	private Selector selector = null;
	private int pocetBlokov = 0;
	
	public void init(){
		isLoading = true;
		createFrame();
		Renderer.initGraphics();
		
		
		loader  = new Loader();
		renderer = new Renderer();
		shader = new StaticShader();
		
		//RawModel model = OBJLoader.loadObjModel("box", loader);
		RawModel model = Box.getModel(loader, .1f, 1000, .1f);
		ModelTexture texture = new ModelTexture("dirt.jpg");
		TexturedModel textureModel = null;
		textureModel = new TexturedModel(model,texture);
		Entity entity = new Entity(textureModel,-5,-1,0,0,0,0,0.5f);
		selector = new Selector(entity);
		light = new Light(new Vector3f(20,200,20),new Vector3f(1,1,1));
		terrain = new Terrain("HeightMap", loader);
		
		RawModel mieridlo = Box.getModel(loader, 5, 5, 5);
		ModelTexture texture2 = new ModelTexture("dirt.jpg");
		TexturedModel textureModel2 = new TexturedModel(mieridlo,texture2);
		this.entity2 = new Entity(textureModel2,-5,-1,0,0,0,0,0.5f);
		
		//camera = new Camera();
		
		mapa = new Map(32,32,loader);
		//mapa.initDefaultMap();
		mapa.initMapFromHeighMap("heightMap.png");
		pocetBlokov = mapa.getNumBlock();
		
		rmenu.setMinimap(mapa.getTerrain());
		tmenu.setMap(mapa);
		camerka = new Camerka(shader);
		mapa.camera = camerka;
		bmenu.setCamerka(camerka);
//		bmenu.setActBlock(mapa.getTop(selector.getSur()));
		logs = new Logs();
		isLoading = false;
	}
	
	public void mainLoop(){
		while(!Display.isCloseRequested()&&!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			if(isLoading){
				continue;
			}
			double time = System.currentTimeMillis();
			//Input.update(camera, null);
			Renderer.clearScreen(rmenu);
			rmenu.useOptions();
			rmenu.getMinimap().update();
			camerka.update(bmenu);
			
//			float x = (float)Math.tan(Math.toRadians(90-camerka.getPitch()))*camerka.getPosition().y;
//			float rot =(float)Math.toRadians(camerka.getYaw());
//			rot = (float)Math.PI/4;
//			selector.getEntity().setLocation(camerka.getTargetPosition().x, camerka.getPosition().y-20, camerka.getTargetPosition().y);
			selector.input(mapa,bmenu);
			
			bmenu.updateCameraWindow();
//			if(mapa.getTop(selector.getSur())!=bmenu.getActBlock()){
//				bmenu.setActBlock(mapa.getTop(selector.getSur()));
//			}
//			bmenu.updateBlockWindow();
//			selector.getEntity().setLocation((float)Math.sin(rot)*x, camerka.getPosition().y-20,(float)Math.cos(rot)*x);
			
			shader.start();
			shader.loadTypeOfView(rmenu.getTypeOfView());
			shader.loadLight(light);
			shader.loadViewMatrix(camerka);
			
//			System.out.println(mapa.getNumBlock());
//			shader.loadChangeColor(true);
//			shader.loadColor(new Vector3f(1,0,1));
//			glBegin(GL_LINES);
//				float x = -20;
//				float y = -20;
//				glVertex3f(x, 0, y);
//				glVertex3f(x,20, y);
//			glEnd();
//			shader.loadChangeColor(false);
//			Vector3f vpred = camerka.getForward();
//			vpred.negate(vpred);
//			entity2.setLocation(camerka.getPosition().x+vpred.x*30, 
//								camerka.getPosition().y+vpred.y*30, 
//								camerka.getPosition().z+vpred.z*30);
//			System.out.println(entity2.getX()+" "+entity2.getY()+" "+entity2.getZ());
//			renderer.render(entity2, shader);
			
			if(Map.select.selected != null){
				glPolygonMode(GL_FRONT_AND_BACK,GL_LINE);
				if(rmenu.getWireframe())
					glPolygonMode(GL_FRONT,GL_LINE);
				shader.loadChangeColor(true);
				shader.loadColor(new Vector3f(1,1,1));
				Map.select.selected.setScale(Map.select.selected.getScale()+.1f);
				renderer.render(Map.select.selected, shader);
				Map.select.selected.setScale(Map.select.selected.getScale()-.1f);
				shader.loadChangeColor(false);
				if(!rmenu.getWireframe())
					glPolygonMode(GL_FRONT,GL_FILL);
			}
			
//			shader.loadChangeColor(true);
//			shader.loadColor(new Vector3f(1,0,1));
//			selector.draw(renderer, shader);
//			shader.loadChangeColor(false);
//			double time = System.currentTimeMillis();
			String txt = mapa.draw(renderer, shader)+"/"+pocetBlokov+" - "+(System.currentTimeMillis()-time);
			//toto by sa dalu urèite upravi nejako
			
			
			//camera.init3DProjection();
			//camera.useView();
			//mapa.draw();
			
			
			//tu sa až kreslí;
//			Logs.initGL(Display.getWidth(), Display.getHeight());
//			Camera.init2DProjection();
//			logs.update();
//			Camera.init3DProjection();
			window.update();
			if(System.currentTimeMillis() - time > 200){
				System.out.println("celé kolo trvalo " +(System.currentTimeMillis() - time)+" milisekúnd" );
			}
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
		//System.exit(0);
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
