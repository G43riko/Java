package Game;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import Game.Shapes.Box;

public class RenderUtil {
	public Player player;
	private Vector2f mousePos;
	public static void main(String[] args){
		RenderUtil game= new RenderUtil();
		
		game.player=new Player(0,0,0);
		game.initDisplay();
		Mouse.setCursorPosition(600, 450);
		game.gameLoop();
	}
	
	public void initDisplay(){
		try {
			Display.setDisplayMode(new DisplayMode(1200,900));
			Display.create();
			
		} catch (LWJGLException e) {
			System.out.println(e);
		}
	}
	
	public static Texture loadTexture(String key){
		try {
			return TextureLoader.getTexture("jpg", new FileInputStream(new File("res/"+key+".jpg")));
		} catch (IOException e) {
			System.out.println(e);
		}
		return null;
	}
	
	public void gameLoop(){
		
		Camera cam=new Camera(70,(float)Display.getWidth()/(float)Display.getHeight(),0.3f,1000);
		
		this.player.z=-10;
		Box box = new Box(0,0,0,2,8,4);
		//box.setColor(Color.red);
		Texture dirt = RenderUtil.loadTexture("wood");
		
		box.setTexture(dirt, true);
		int num=10;
		Box[][] pole = new Box[num][num];
		for(int i=0 ; i<num ;i++){
			for(int j=0 ; j<num ; j++){
				pole[i][j] = new Box(i*2-10,-2,j*2-10,1,1,1);
				pole[i][j].setTexture(dirt, true);
			}
		}
		
		while(!Display.isCloseRequested()){
			
			boolean forward = Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP);
			boolean backward = Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN);
			boolean left = Keyboard.isKeyDown(Keyboard.KEY_A);
			boolean right = Keyboard.isKeyDown(Keyboard.KEY_D);
			if(forward)
				this.player.move(0.01f,1);
			if(backward)
				this.player.move(-0.01f,1);
			if(left)
				this.player.move(0.01f,0);//cam.rotateY(-0.1f);
			if(right)
				this.player.move(-0.01f,0);//cam.rotateY(0.1f);

			if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
				this.player.rotateY(-0.1f);
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
				this.player.rotateY(0.1f);
			/*
			this.player.move();
			 */
			this.player.reloadR();
			this.player.reloadP();
			this.player.reloadU();
			
			cam.reload(this.player);
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glLoadIdentity();
			cam.useView();
			
			
			for(int i=0 ; i<num ;i++){
				for(int j=0 ; j<num ; j++){
					pole[i][j].draw();
				}
			}
			box.draw();
			Display.update();
		}
		
		Display.destroy();
	}
} 
