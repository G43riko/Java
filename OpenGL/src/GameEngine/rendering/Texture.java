package GameEngine.rendering;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.newdawn.slick.opengl.TextureLoader;

import GameEngine.core.Util;
import GameEngine.rendering.resourceManagement.TextureResource;

public class Texture {
	private static HashMap<String,TextureResource> loadedTextures = new HashMap<String,TextureResource>();
	private TextureResource resource;
	private String fileName;
	
	public Texture(String fileName){
		this.fileName = fileName;
		TextureResource oldResource = loadedTextures.get(fileName);
		if(oldResource != null){
			resource = oldResource;
			resource.addReference();
		}
		else{
			resource =  new TextureResource(loadTexture(fileName));
			loadedTextures.put(fileName, resource);
		}
		
		//this.id = loadTexture(name);
	}
	
	protected void finalize(){
		if(resource.removeReference()&&!fileName.isEmpty()){
			loadedTextures.remove(fileName);
		}
	}
	
	public void bind(){
		glBindTexture(GL_TEXTURE_2D,resource.getId());
	}
	
	public int getID(){
		return resource.getId();
	}
	
	private int loadTexture(String filename){
		String[] splitArray = filename.split("\\.");
		String ext = splitArray[splitArray.length-1];

		try{
			BufferedImage image = ImageIO.read(new File("res/textures/"+filename));
			int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
			ByteBuffer buffer = Util.createByteBuffer(image.getWidth() * image.getHeight() * 4);
			
			boolean hasAlpha = image.getColorModel().hasAlpha();
			 
			for(int y=0 ; y<image.getHeight() ; y++){
				for(int x=0 ; x<image.getHeight() ; x++){
					int pixel = pixels[y*image.getWidth()+x];
					buffer.put((byte)((pixel >> 16)&0xFF));
					buffer.put((byte)((pixel >> 8)&0xFF));
					buffer.put((byte)((pixel)&0xFF));
					if(hasAlpha)
						buffer.put((byte)((pixel >> 24)&0xFF));
					else
						buffer.put((byte)(0xFF));
					
				}
			}
			buffer.flip();
			
			int id = glGenTextures();
			glBindTexture(GL_TEXTURE_2D, id);
			
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
			
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
			
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
			
			return id;
		}
		catch(Exception e){
			System.exit(1);
		}

		return 0;
		
	}
}
