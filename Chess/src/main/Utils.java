package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Utils {
	public static void vypis(String co){
		System.out.println(co);
	}
	
	public static Texture textureLoader(String filename){
		try {
			return TextureLoader.getTexture("jpg", new FileInputStream(new File("res/tex/"+filename)));
		} catch (IOException e) {
			System.out.println(e);
		}
		return null;
	}
}
