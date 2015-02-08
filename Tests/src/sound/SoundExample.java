package sound;

import java.io.IOException;
 
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.openal.AL;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;
 
public class SoundExample {
	/** The ogg sound effect */
	private Audio oggEffect;
	/** The ogg stream thats been loaded */
	private Audio oggStream;
 
	/**
	 * Start the test 
	 */
	public void start() {
		initGL(800,600);
		init();
 
		while (true) {
			update();
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			render();
 
			Display.update();
			Display.sync(100);
 
			if (Display.isCloseRequested()) {
				Display.destroy();
				AL.destroy();
				System.exit(0);
			}
		}
	}
 
	/**
	 * Initialise the GL display
	 * 
	 * @param width The width of the display
	 * @param height The height of the display
	 */
	private void initGL(int width, int height) {
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
 
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);        
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);                    
 
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
        GL11.glClearDepth(1);                                       
 
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
 
        GL11.glViewport(0,0,width,height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
 
    /**
    * Initialise resources
    */
    public void init() {
 
        try {
	    oggEffect = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("testdata/restart.ogg"));
 
	    oggStream = AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource("testdata/bongos.ogg"));
        } catch (IOException e) {
	    e.printStackTrace();
	}
    }
 
	/**
	 * Game loop update
	 */
	public void update() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_Q) {
					// play as a one off sound effect
					oggEffect.playAsSoundEffect(1.0f, 1.0f, false);
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_W) {
					oggStream.playAsMusic(1.0f, 1.0f, true);
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					oggEffect.stop();
					oggStream.stop();
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_E) {
					oggStream.playAsMusic(1.0f, 2.0f, true);
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_R) {
					oggStream.playAsMusic(1.0f, 3.0f, true);
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_T) {
					oggStream.playAsMusic(1.0f, 4.0f, true);
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_U) {
					oggStream.playAsMusic(1.0f, 5.0f, true);
				}
			}
		}
 
		// polling is required to allow streaming to get a chance to
		// queue buffers.
		SoundStore.get().poll(0);
	}
 
	/**
	 * Game loop render
	 */
	public void render() {
 
	}
 
	/**
	 * Main method
	 */
	public static void main(String[] argv) {
		SoundExample soundExample = new SoundExample();
		soundExample.start();
	}
}