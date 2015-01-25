package com.voxel.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BMenu extends JPanel{
	private static final long serialVersionUID = 1L;

	public BMenu(){
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(100,100));
		setBorder(BorderFactory.createLineBorder(Color.black,1,true));
	}
}
