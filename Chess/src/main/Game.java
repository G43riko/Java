package main;

import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.glPolygonMode;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;	
import stolen.Loader;
import entities.Camera;
import entities.Selector;

public class Game {
	public static int width = 1280;
	public static int height = 720;
	public static String title = "nadpis";
	public static boolean fullscreen = false;
	public static boolean mipMapping = true;
	public static boolean wireFrame = false;
	
	private Window window;
	private Level actLevel; 
	private Camera camera;
	private Selector selector;
	private Shader shader;
	private Entita model;
	
	private Loader loader;
	
	public void init() {
		window = new Window(width,height,"Chess");
		camera = new Camera(70,Display.getWidth()/Display.getHeight(),0.3f,1000);
		shader = new Shader("shader");
		
		actLevel = new Level(20,10,20);
		
		loader = new Loader();
		
//		RawModel drakModel = OBJLoader.loadObjModel("box", loader);
//		TexturedModel drakTexturedModel = new TexturedModel(drakModel,new ModelTexture(loader.loadTexture("stallTexture.png")));
//		Entity drak = new Entity(drakTexturedModel,	new Vector3f(0,0,-25),0,0,0,1);
		
		
		selector = new Selector(0,1,0);
		selector.setSize(2.1f,1.1f,2.1f);
		
		model = new Entita("stall");
	}

	public void start() {
		while(!Display.isCloseRequested()){
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)&&fullscreen){
				break;
			}
			if(wireFrame){
				glPolygonMode(GL_FRONT_AND_BACK,GL_LINE);
			}
			Input.update(camera,selector);
			Renderer.clearScreen();
			camera.useView();
			
			actLevel.draw();
			
			selector.draw();
			
			model.draw();
			
			window.update();
			//info.draw();
		}
	}
	
	public void cleanUp(){
		shader.cleanUp();
		window.cleanUp();
	}

}
