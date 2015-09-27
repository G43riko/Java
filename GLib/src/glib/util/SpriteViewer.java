package glib.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import glib.util.vector.GVector2f;

public class SpriteViewer {
	private static HashMap<String, SpriteViewer> loadedImages = new HashMap<String, SpriteViewer>();
	private GVector2f images;
	private GVector2f imageSize;
	private Image image;
	static{
		setImage("tileset.png", 29, 3);
		setImage("tileset2.png", 12, 10);
		setImage("tileset2-b.png", 16, 8);
		setImage("wall_sprite.png", 16, 1);
		setImage("wall_sprite2.png", 5, 1);
		setImage("tileset_test.png", 15, 1);
		setImage("tileset_test2.png", 15, 1);
		setImage("worker.png", 1, 4);
	}


	private SpriteViewer(String name, int x, int y){
		image = ResourceLoader.loadTexture(name);
		images = new GVector2f(x, y);
		imageSize = new GVector2f(image.getWidth(null), image.getHeight(null)).div(images);
	}
	
	public static void setImage(String name, int x, int y){
		if(loadedImages.containsKey(name))
			return;
		
		loadedImages.put(name, new SpriteViewer(name, x, y));
	}
	
	public static Image getImage(String name, int x, int y){
		SpriteViewer viewer = loadedImages.get(name);
		BufferedImage img = new BufferedImage(viewer.imageSize.getXi(), viewer.imageSize.getYi(), BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D tg2 = (Graphics2D)img.getGraphics();
		
		tg2.drawImage(viewer.image, 
					  0, 0, 
					  viewer.imageSize.getXi(), viewer.imageSize.getYi(), 
					  x * viewer.imageSize.getXi(), y * viewer.imageSize.getYi(),
					  (x + 1) * viewer.imageSize.getXi(), (y + 1) * viewer.imageSize.getYi(),
					  null);
		
		return img;
	}
}
