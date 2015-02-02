package com.voxel.gui;

import glib.util.GColor;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.voxel.core.GameObject;

public class Selection extends JPanel{
	private static final long serialVersionUID = 1L;
	private static Selection copy;
	private static GameObject selected;
	private JLabel px,py,pz;
	private JLabel rx,ry,rz;
	private JLabel sx,sy,sz;
	
	public Selection(int width){
		selected = null;
		int offset = 6;
		setPreferredSize(new Dimension(width-offset*2,100));
		setLocation(offset, 0);
		setBackground(GColor.RED);
		setLayout(new GridBagLayout());
		copy = this;
		init();
		
	}
	
	private void init(){
		GridBagConstraints c;
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		px = new JLabel("px: 000 "); 
		add(px,c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		py = new JLabel("py: 000 "); 
		add(py,c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		pz = new JLabel("pz: 000 "); 
		add(pz,c);
		
		
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		rx = new JLabel("rx: 000 "); 
		add(rx,c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		ry = new JLabel("ry: 000 "); 
		add(ry,c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		rz = new JLabel("rz: 000 "); 
		add(rz,c);
		
		
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		sx = new JLabel("sx: 000 "); 
		add(sx,c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		sy = new JLabel("sy: 000 "); 
		add(sy,c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		sz = new JLabel("sz: 000 "); 
		add(sz,c);
		
	}

	public static void addObject(GameObject o){
		selected = o;
		System.out.println("pridalo s: "+o.getTransform());
		copy.update();
	}
	
	public void update(){
		px.setText("px: "+String.format("%03d ",(int)selected.getTransform().getPosition().getX()));
		py.setText("py: "+String.format("%03d ",(int)selected.getTransform().getPosition().getY()));
		pz.setText("pz: "+String.format("%03d ",(int)selected.getTransform().getPosition().getZ()));
		
		rx.setText("rx: "+String.format("%03d ",(int)selected.getTransform().getRotation().getEuler().getX()));
		ry.setText("ry: "+String.format("%03d ",(int)selected.getTransform().getRotation().getEuler().getY()));
		rz.setText("rz: "+String.format("%03d ",(int)selected.getTransform().getRotation().getEuler().getZ()));
		
		sx.setText("sx: "+String.format("%03d ",(int)selected.getTransform().getScale().getX()));
		sy.setText("sy: "+String.format("%03d ",(int)selected.getTransform().getScale().getY()));
		sz.setText("sz: "+String.format("%03d ",(int)selected.getTransform().getScale().getZ()));
	}
}
