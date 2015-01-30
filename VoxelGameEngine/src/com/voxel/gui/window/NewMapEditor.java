package com.voxel.gui.window;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JDialog;

import com.voxel.core.Window;

public class NewMapEditor extends JDialog{
	private static final long serialVersionUID = 1L;

	public NewMapEditor(){
		initFrame();
		init();
	}
	
	private void init() {
		
		setVisible(true);
	}

	private void initFrame(){
		int width = 600;
		int height = 400;
		setModal(true);
		setSize(new Dimension(width,height));
		setLocation((int)Window.getCenter().getX() - width, (int)Window.getCenter().getY() - height);
		setTitle("New map options");
		getContentPane().setLayout(new GridBagLayout());
	}
}
