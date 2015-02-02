package com.voxel.gui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.voxel.rendering.RenderingEngine;

public class Timing extends JPanel{
	private static final long serialVersionUID = 1L;
	private static Timing act;
	private JLabel renderTime;
	private JLabel numberOfTriangel;
	private JLabel numberOfBoxSides;
	
	public Timing(int width){
		
		int offset = 6;
		setPreferredSize(new Dimension(width-offset*2,400));
		setLocation(offset, 0);
		
		renderTime = new JLabel();
		add(renderTime);
		numberOfTriangel = new JLabel();
		add(numberOfTriangel);
		numberOfBoxSides = new JLabel();
		add(numberOfBoxSides);
		act = this;
	}
	
	public static void update(){
		if(act==null)
			return;
		Integer.numberOfLeadingZeros(2);
		act.renderTime.setText("renderTime: "+String.format("%03d", (int)RenderingEngine.renderTime));
		act.numberOfTriangel.setText("numberOfTriangel: "+String.format("%05d", (int)RenderingEngine.numOfTriangels));
		act.numberOfBoxSides.setText("numberOfBoxSides: "+String.format("%05d", (int)RenderingEngine.numOfRenderedBoxSides));
	}
}
