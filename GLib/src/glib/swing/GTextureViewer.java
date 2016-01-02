package glib.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GTextureViewer extends JPanel{
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private BufferedImage image;
	private String fileName;
	
	public GTextureViewer(BufferedImage image){
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.image = image;
		setPreferredSize(new Dimension(256,256));
	}
	
	public GTextureViewer(String fileName,int width, int height){
		this.width = width;
		this.height = height;
		this.fileName = fileName;
		try {                
			image = ImageIO.read(new File("res/textures/"+fileName));
		} catch (IOException e) { e.printStackTrace(); }
		setPreferredSize(new Dimension(256,256));
	}
	
	protected void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 g.drawImage(image, 0, 0, getPreferredSize().width, getPreferredSize().height,0,0,width, height, null);
	}
	
	public String toString(){
		return fileName + " [" + width +" x" + height + "]";
	}
}
