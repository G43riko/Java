package com.voxel.gui.component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TextureViewer extends JPanel{
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private BufferedImage image;
	
	public TextureViewer(String fileName,int width, int height){
		this.width = width;
		this.height = height;
		try {                
			image = ImageIO.read(new File("res/textures/"+fileName));
		} catch (IOException e) { e.printStackTrace(); }
		setPreferredSize(new Dimension(256,256));
	}
	
	protected void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 g.drawImage(image, 0, 0, width,height,null);
//		 g.drawImage(image, 0, 0, null);
	}
}
