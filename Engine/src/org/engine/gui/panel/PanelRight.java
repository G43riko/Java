package org.engine.gui.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.engine.gui.Gui;

public class PanelRight extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private int width;
	
	//CONSTRUCTORS
	
	public PanelRight(Gui gui){
		width = gui.getCoreEngine().getWidth() / 6;
		init();
	}
	
	//OTHERS
	
	public void init(){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(width,width));
		setBorder(BorderFactory.createLineBorder(Color.black,1,true));
	}

}
