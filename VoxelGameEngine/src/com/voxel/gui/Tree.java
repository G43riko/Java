package com.voxel.gui;

import glib.util.GColor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneLayout;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import com.voxel.core.GameObject;
import com.voxel.main.MainVoxel2;

public class Tree extends JSplitPane{
	private static final long serialVersionUID = 1L;
	private static GameObject selected;
	
	private JLabel px,py,pz;
	private JLabel rx,ry,rz;
	private JLabel sx,sy,sz;
	private JLabel name;
	private JLabel id;
	
	private JScrollPane scroll;
	private JTree tree;
	private GameObject root;
	private JScrollPane view;
	
	public Tree(int width, GameObject root){
		super(JSplitPane.VERTICAL_SPLIT);
		this.root = root;
		tree = createTree();
		scroll = createScrollPane(width,6,tree);
		view = createView(width);
		view.setMinimumSize(new Dimension(width,width/3));
		
		setTopComponent(scroll);
		setBottomComponent(view);
	}
	
	private JScrollPane createView(int width) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,3));
		name = new JLabel("null");
		panel.add(new JLabel("selected: "));
		panel.add(name);
		id = new JLabel(" ");
		panel.add(id);
		
		
		px = new JLabel("px: 000"); 
		panel.add(px);
		py = new JLabel("py: 000"); 
		panel.add(py);
		pz = new JLabel("pz: 000"); 
		panel.add(pz);
		
		rx = new JLabel("rx: 000"); 
		panel.add(rx);
		ry = new JLabel("ry: 000"); 
		panel.add(ry);
		rz = new JLabel("rz: 000"); 
		panel.add(rz);
		
		sx = new JLabel("sx: 000"); 
		panel.add(sx);
		sy = new JLabel("sy: 000"); 
		panel.add(sy);
		sz = new JLabel("sz: 000"); 
		panel.add(sz);
		
		JScrollPane result = new JScrollPane(panel);
		result.setLayout(new ScrollPaneLayout());
		return result;
	}
	
	private JTree createTree() {
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(MainVoxel2.TITLE);
		top = addAllNodes(root,top);
        tree = new JTree(top);
        TreeSelectionListener listener = new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				ArrayList<GameObject> objects = root.getAllAttached();
				for(GameObject o : objects){
					if(tree.getLastSelectedPathComponent() == null)
						break;
					if(o.getName().equals(tree.getLastSelectedPathComponent().toString())){
						selected = o;
						name.setText(o.getName().split(" ")[0]);
						id.setText(o.getName().split(" ")[1]);
						update();
						break;
					}
				}
			}
		};
		tree.addTreeSelectionListener(listener);
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
		scroll.setPreferredSize(new Dimension(width-offset*2,300));
		scroll.setLocation(offset, 0);
		scroll.setBackground(GColor.CYAN);
		scroll.setLayout(new ScrollPaneLayout());
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		return scroll;
	}

	public void update(){
		if(selected == null)
			return;
		px.setText("px: "+String.format("%03d ",(int)selected.getTransform().getPosition().getX()));
		py.setText("py: "+String.format("%03d ",(int)selected.getTransform().getPosition().getY()));
		pz.setText("pz: "+String.format("%03d ",(int)selected.getTransform().getPosition().getZ()));
		
		rx.setText("rx: "+String.format("%03d ",(int)Math.toDegrees(selected.getTransform().getRotation().getEuler().getX())));
		ry.setText("ry: "+String.format("%03d ",(int)-Math.toDegrees(selected.getTransform().getRotation().getEuler().getY())));
		rz.setText("rz: "+String.format("%03d ",(int)Math.toDegrees(selected.getTransform().getRotation().getEuler().getZ())));
		
		sx.setText("sx: "+String.format("%03d ",(int)selected.getTransform().getScale().getX()));
		sy.setText("sy: "+String.format("%03d ",(int)selected.getTransform().getScale().getY()));
		sz.setText("sz: "+String.format("%03d ",(int)selected.getTransform().getScale().getZ()));
	}
}
