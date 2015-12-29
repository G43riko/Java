package glib.util;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ResourceLoader {
	private static String TEXTURES_FOLDER = "textures";
	private static String SHADERS_FOLDER  = "shaders";
//	private static String MODELS_FOLDER   = "models";
	
	public static InputStream load(String fileName){
		InputStream input = ResourceLoader.class.getResourceAsStream(fileName);
		if(input == null)
			input = ResourceLoader.class.getResourceAsStream("/" + fileName);
		
		return input;
	}
	
	public static Image loadTexture(String fileName){
		try {
			return ImageIO.read(load(TEXTURES_FOLDER + "/" + fileName));
		} catch (IOException e) {
			GLog2.write("Nepodarilo sa naèítaš súbor: " + TEXTURES_FOLDER + "/" + fileName);
		}
		return null;
	}
	
	public static InputStreamReader loadShader(String fileName){
		return new InputStreamReader(load(SHADERS_FOLDER + "/" + fileName));
	}
	
	public static AudioInputStream loadAudio(String fileName){
		try {
			return AudioSystem.getAudioInputStream(load(fileName));
		} catch (UnsupportedAudioFileException | IOException e) {
			GLog2.write("Nepodarilo sa naèítaš súbor: " + fileName);
		}
		return null;
	}
}
