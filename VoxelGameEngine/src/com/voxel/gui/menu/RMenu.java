package com.voxel.gui.menu;

import glib.util.GColor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.voxel.core.GameObject;
import com.voxel.gui.Minimap;
import com.voxel.gui.Tree;

public class RMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	private GColor bgColor = new GColor(1,0,1);
	private Minimap minimap;
	private Tree tree;
	
	
	public RMenu(GameObject root){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(200,200));
		setBorder(BorderFactory.createLineBorder(Color.black,1,true));
	}

	public void init(GameObject root){
		minimap = new Minimap(getPreferredSize().getWidth());
		add(minimap);
		
		tree = new Tree((int)getPreferredSize().getWidth(),root);
		add(tree);
		
	}
	
	public GColor getBgColor() {
		return bgColor;
	}
}
