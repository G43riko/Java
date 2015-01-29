package com.voxel.gui;

import glib.util.GColor;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneLayout;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import com.voxel.core.GameObject;
import com.voxel.main.MainVoxel2;

public class Tree extends JPanel{
	private static final long serialVersionUID = 1L;
	private JScrollPane scroll;
	private JTree tree;
	private GameObject root;
	
	public Tree(int width, GameObject root){
		this.root = root;
		tree = createTree();
		scroll = createScrollPane(width,6,tree);
		add(scroll);
	}
	
	private JTree createTree() {
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(MainVoxel2.TITLE);
		top = addAllNodes(root,top);
		
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		return tree;
	}
	
	private DefaultMutableTreeNode addAllNodes(GameObject object, DefaultMutableTreeNode top){
		for(GameObject g:object.children){
			DefaultMutableTreeNode novy = new DefaultMutableTreeNode(g.getName());
			top.add(novy);
			addAllNodes(g,novy); 
		}
		return top;
	}

	public JScrollPane createScrollPane(int width,int offset, JTree tree){
		scroll = new JScrollPane(tree);
		scroll.setPreferredSize(new Dimension(width-offset*2,400));
		scroll.setLocation(offset, 0);
		scroll.setBackground(GColor.CYAN);
		scroll.setLayout(new ScrollPaneLayout());
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		return scroll;
	}
}
