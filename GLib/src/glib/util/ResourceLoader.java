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
	public static InputStream load(String fileName){
		InputStream input = ResourceLoader.class.getResourceAsStream(fileName);
		if(input == null){
			input = ResourceLoader.class.getResourceAsStream("/"+fileName);
		}
		return input;
	}
	
	public static Image loadTexture(String fileName){
		try {
			return ImageIO.read(load("textures/"+fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static InputStreamReader loadShader(String fileName){
		return new InputStreamReader(load("shaders/"+fileName));
	}
	
	
	public static AudioInputStream loadAudio(String fileName){
		try {
			return AudioSystem.getAudioInputStream(load(fileName));
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
