package org.underConstruct.gui.component;

import glib.math.GMath;
import glib.util.GColor;
import glib.util.vector.GVector3f;
import glib.util.vector.GVector4f;

import java.awt.image.BufferedImage;

public class Handlers {

	public void greyScale(BufferedImage image) {
		GVector3f aver = new GVector3f();
		for(int i=0 ; i<image.getWidth() ; i++){
			for(int j=0 ; j<image.getHeight() ; j++){
				GColor c = new GColor(image.getRGB(i, j));
				aver = aver.add(new GVector3f(c.getRed(), c.getGreen(), c.getBlue()));
			}
		}
		aver = aver.div(image.getWidth() * image.getHeight());
		
		for(int i=0 ; i<image.getWidth() ; i++){
			for(int j=0 ; j<image.getHeight() ; j++){
				GColor c = new GColor(image.getRGB(i, j));
				int averageColor = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
				image.setRGB(i, j, new GColor(averageColor, averageColor, averageColor).getRGB());
			}
		}
	}
	
	public void inverse(BufferedImage image){
		
		for(int i=0 ; i<image.getWidth() ; i++){
			for(int j=0 ; j<image.getHeight() ; j++){
				GColor c = new GColor(image.getRGB(i, j));
				image.setRGB(i, j, new GColor(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue()).getRGB());
				
			}
		}
	}
	
	public void updateColors(BufferedImage image, GVector4f colors) {
		for(int i=0 ; i<image.getWidth() ; i++){
			for(int j=0 ; j<image.getHeight() ; j++){
				GColor c = new GColor(image.getRGB(i, j));

				
				float r = c.getRed() + colors.getX() + colors.getW();
				float g = c.getGreen() + colors.getY() + colors.getW();
				float b = c.getBlue() + colors.getZ() + colors.getW();
				
				image.setRGB(i, j, new GColor(GMath.between(r, 0, 255), GMath.between(g, 0, 255), GMath.between(b, 0, 255)).getRGB());
			}
		}
	}
	
	public void maxAndMin(BufferedImage image, GVector4f maximum, GVector4f minimum){
		for(int i=0 ; i<image.getWidth() ; i++){
			for(int j=0 ; j<image.getHeight() ; j++){
				GColor c = new GColor(image.getRGB(i, j));

				
				float r = (float)c.getRed() / 255 * maximum.getX() + minimum.getX();
				float g = (float)c.getGreen() / 255 * maximum.getY() + minimum.getY();
				float b = (float)c.getBlue() / 255 * maximum.getZ() + minimum.getZ();
				
				GColor newColor = new GColor((int)GMath.between(r, minimum.getW(), maximum.getW()), 
											 (int)GMath.between(g, minimum.getW(), maximum.getW()), 
											 (int)GMath.between(b, minimum.getW(), maximum.getW()));
				
				image.setRGB(i, j, newColor.getRGB());
			}
		}
	}

	public void randomize(BufferedImage image, float value) {
		GVector3f color = new GVector3f((float)(Math.random()-0.5f) * value, (float)(Math.random()-0.5f) * value, (float)(Math.random()-0.5f) * value);
		for(int i=0 ; i<image.getWidth() ; i++){
			for(int j=0 ; j<image.getHeight() ; j++){
				GColor c = new GColor(image.getRGB(i, j));
				GColor newColor = new GColor((int)GMath.between(c.getRed() + color.getX(), 0, 255), 
						   				   (int)GMath.between(c.getGreen() + color.getY(), 0, 255), 
						   				   (int)GMath.between(c.getBlue() + color.getZ(), 0, 255));
				image.setRGB(i, j, newColor.getRGB());
			}
		}
		
	}

	public void blur(BufferedImage image, BufferedImage newImage, float value){
		for(int i=0 ; i<image.getWidth() ; i++){
			for(int j=0 ; j<image.getHeight() ; j++){
				if(i==0 || j==0 || i+1 == image.getWidth() || j+1 == image.getHeight())
					continue;
				GColor c;
				GVector3f color = new GVector3f();
				c = new GColor(newImage.getRGB(i+1, j+1));
				color = color.add(new GVector3f(c.getRed(), c.getGreen(), c.getBlue()));
				c = new GColor(newImage.getRGB(i-1, j+1));
				color = color.add(new GVector3f(c.getRed(), c.getGreen(), c.getBlue()));
				c = new GColor(newImage.getRGB(i+1, j-1));
				color = color.add(new GVector3f(c.getRed(), c.getGreen(), c.getBlue()));
				c = new GColor(newImage.getRGB(i-1, j-1));
				color = color.add(new GVector3f(c.getRed(), c.getGreen(), c.getBlue()));
				
				color = color.div(4);
				
				image.setRGB(i, j, new GColor(color.getXi(), color.getYi(), color.getZi()).getRGB());
			}
		}
	}
	
	public void ostrost(BufferedImage image, BufferedImage newImage, float value){
		for(int i=0 ; i<image.getWidth() ; i++){
			for(int j=0 ; j<image.getHeight() ; j++){
				GColor c;
				GVector3f color = new GVector3f();
				if(i+1 < image.getWidth() && j+1 < image.getHeight()){
					c = new GColor(newImage.getRGB(i+1, j+1));
					color = color.add(new GVector3f(c.getRed(), c.getGreen(), c.getBlue()));
				}
				if(i>0 && j+1 < image.getHeight()){
					c = new GColor(newImage.getRGB(i-1, j+1));
					color = color.add(new GVector3f(c.getRed(), c.getGreen(), c.getBlue()));
				}
				if(i+1 < image.getWidth() && j>0){
					c = new GColor(newImage.getRGB(i+1, j-1));
					color = color.add(new GVector3f(c.getRed(), c.getGreen(), c.getBlue()));
				}
				if(i>0 && j>0){
					c = new GColor(newImage.getRGB(i-1, j-1));
					color = color.add(new GVector3f(c.getRed(), c.getGreen(), c.getBlue()));
				}
				color = color.div(4);
				
				c = new GColor(newImage.getRGB(i, j));
				
				float diference = (c.getRed()+ c.getGreen() + c.getBlue())/3 - (color.getX()+color.getY()+color.getZ())/3;
				diference *= value;
				float r = c.getRed() + diference;
				float g = c.getGreen() + diference;
				float b = c.getBlue() + diference;
				
				image.setRGB(i, j, new GColor(r,g,b).getRGB());
			}
		}
	}
}
