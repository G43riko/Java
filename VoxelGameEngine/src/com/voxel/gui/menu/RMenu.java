package com.voxel.gui.menu;

import glib.util.GColor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.voxel.core.GameObject;
import com.voxel.gui.Minimap;
import com.voxel.gui.Selection;
import com.voxel.gui.Timing;
import com.voxel.gui.Tree;

public class RMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	private final static int width = 200;
	private GColor bgColor = new GColor(1,0,1);
	private Minimap minimap;
	private Tree tree;
	private Timing timing;
	private Selection selection;
	
	
	
	public RMenu(GameObject root){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(width,width));
		setBorder(BorderFactory.createLineBorder(Color.black,1,true));
	}

	public void init(GameObject root){
		minimap = new Minimap(width);
		add(minimap);
		
		tree = new Tree(width,root);
		add(tree);
		
//		selection = new Selection(width);
//		add(selection);
		
		timing = new Timing(width);
		add(timing);
		
	}
	
	public GColor getBgColor() {
		return bgColor;
	}
	
	public Tree getTree(){
		return tree;
	}
}
