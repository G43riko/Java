package glib.swing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import glib.util.vector.GVector2f;

public class GGraphDrawer {
	private final GVector2f rightBottom;
	private final GVector2f size; 
	
	private Color 	color 		= Color.black;
	private int 	borderRound	= 10;
	private int 	frames 		= 10;
	private int 	bgOpacity 	= 40;
	private float 	heightRatio = 1.5f;
	private int 	MaxValue	= -1;
	
	//CONSTRUCTORS
	
	public GGraphDrawer(Color color, GVector2f size, GVector2f rightBottom){
		this.rightBottom = rightBottom;
		this.color = color;
		this.size = size;
	}
	
	public GGraphDrawer(Color color, int frames, int bgOpacity, GVector2f size, GVector2f rightBottom){
		this.rightBottom = rightBottom;
		this.bgOpacity = bgOpacity;
		this.frames = frames;
		this.color = color;
		this.size = size;
	}
	
	//OTHERS
	
	public void drawGraph(Graphics2D g2, List<Integer> data){
		drawGraph(g2, data, color);
	}
	
	public void drawGraph(Graphics2D g2, List<Integer> data, Color color){
		if(data.isEmpty())
			return;
		
		int maxValue = MaxValue == -1 ? (int)(Collections.max(data) * heightRatio) : MaxValue;
		
		if(maxValue == 0)
			return;
		
		data = data.stream().limit(frames + 1).collect(Collectors.toList());
		
		int frameWidth = size.getXi() / frames;
		int frameHeight = size.getYi() / maxValue;
		int i = 0;

		int[] xPos = new int[data.size() + 2];
		int[] yPos = new int[data.size() + 2];
		
		
		do{
			xPos[i] = rightBottom.getXi() - i * frameWidth;
			yPos[i] = rightBottom.getYi() - data.get(i) * frameHeight;
		}
		while(++i < data.size());
		
		xPos[i] = rightBottom.getXi() - (data.size() - 1) * frameWidth;
		yPos[i] = rightBottom.getYi();
		
		i++;
		
		xPos[i] = rightBottom.getXi();
		yPos[i] = rightBottom.getYi();
		
		g2.setColor(color);
		g2.drawRoundRect(rightBottom.getXi() - size.getXi(), 
				 		 rightBottom.getYi() - size.getYi(), 
				 		 size.getXi(), 
				 		 size.getYi(),
				 		 borderRound,
				 		 borderRound);
		
		g2.drawPolygon(xPos, yPos, data.size() + 2);
		
		g2.setColor(new Color(g2.getColor().getRed(), 
							  g2.getColor().getGreen(), 
							  g2.getColor().getBlue(), 
							  bgOpacity));
		g2.fillPolygon(xPos, yPos, data.size() + 2);
	}


	//SETTERS

	public void setHeightRatio(float heightRatio) {this.heightRatio = heightRatio;}
	public void setBorderRound(int borderRound) {this.borderRound = borderRound;}
	public void setBgOpacity(int bgOpacity) {this.bgOpacity = bgOpacity;}
	public void setMaxValue(int maxValue) {this.MaxValue = maxValue;}
	public void setFrames(int frames) {this.frames = frames;}
	public void setMaxValue(){this.MaxValue = -1; }
	
}
