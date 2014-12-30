package Main;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glUseProgram;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class MainGame {
	private Map mapa;
	public static void main(String[] args) {
		MainGame game = new MainGame();

	}
	
	private void update(){
		System.out.println(Mouse.getX()+" "+Mouse.getY());
		mapa.draw();
		Display.update();
		Display.sync(120);
	}
	
	public MainGame(){
		mapa = new Map(128,72);
		try {
			Display.setDisplayMode(new DisplayMode(1280,720));
			Display.setTitle("Teddst");
			Display.create();
			Mouse.create();
		} catch (LWJGLException e) {
			System.out.println("display nebol inicializovany");
			Display.destroy();
			System.exit(1);
		}
		
		while(!Display.isCloseRequested()){
			update();
		}
		Display.destroy();
		System.exit(0);
	}

}
