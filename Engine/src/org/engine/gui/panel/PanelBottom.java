package org.engine.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.engine.gui.Gui;

public class PanelBottom extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private int height;

	//CONSTRUCTORS
	
	public PanelBottom(Gui gui){
		height = gui.getCoreEngine().getWidth() / 7;
		
		init();
	}

	//OTHERS
	
	private void init() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(height,height));
		setBorder(BorderFactory.createLineBorder(Color.black,1,true));
		
	}
}
