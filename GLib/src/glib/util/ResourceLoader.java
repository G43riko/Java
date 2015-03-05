package glib.util;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class ResourceLoader {
	private static InputStream load(String fileName){
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
}
