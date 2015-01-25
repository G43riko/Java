package com.voxel.gui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JPanel;

import com.voxel.core.Game;

public class Gui extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private BMenu bmenu;
	private RMenu rmenu;
	private TMenu tmenu;
	private Canvas canvas;
	
	public Gui(Game game) {
		setLayout(new BorderLayout());
		setBackground(Color.red);
		bmenu = new BMenu();
		add(bmenu,BorderLayout.SOUTH);
		
		rmenu = new RMenu();
		add(rmenu,BorderLayout.EAST);
		
		canvas = new Canvas();
		add(canvas,BorderLayout.CENTER);
		
		tmenu = new TMenu();
		add(tmenu,BorderLayout.NORTH);
	}
	
	public Canvas getCanvas(){
		return canvas;
	}
	
}
