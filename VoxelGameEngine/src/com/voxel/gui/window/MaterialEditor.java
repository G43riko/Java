package com.voxel.gui.window;

import glib.util.GLog;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.voxel.core.Window;
import com.voxel.gui.component.GFloatEditor;
import com.voxel.gui.component.TextureViewer;
import com.voxel.gui.component.GVector3Editor;


public class MaterialEditor extends JDialog{
	private static final long serialVersionUID = 1L;
	private JComboBox<String> selectedBlock;
	private GVector3Editor mapColor;
	private TextureViewer textureViewer;
	private JCheckBox transparent;
	private JCheckBox repeat;
	private GFloatEditor specular;
	private GFloatEditor exponent;
	private GFloatEditor repX;
	private GFloatEditor repY;
	
	
	public MaterialEditor(){
		initFrame();
		GridBagConstraints c;
		
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		getContentPane().add(createBlockSelector());
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		mapColor = new GVector3Editor("map color",255);
		getContentPane().add(mapColor, c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.gridheight = 3;
		textureViewer = new TextureViewer("dirt.jpg",256,256);
		getContentPane().add(textureViewer, c);
		
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		transparent = new JCheckBox("Transparent",false);
		getContentPane().add(transparent, c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		repeat = new JCheckBox("Repeat",false);
		getContentPane().add(repeat, c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		repX = new GFloatEditor("RepeatX",10,1,1);
		getContentPane().add(repX, c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		repY = new GFloatEditor("RepeatY",10,1,1);
		getContentPane().add(repY, c);
		
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		specular = new GFloatEditor("Specular",5.1f,0.1f,1);
		getContentPane().add(specular, c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		exponent = new GFloatEditor("Exponent",20,0.5f,8);
		getContentPane().add(exponent, c);
		

		setVisible(true);
	}
	
	private JPanel createBlockSelector(){
		JPanel blockSelector = new JPanel();
		
		blockSelector.setLayout(new FlowLayout());
		blockSelector.add(new JLabel("Block"));
		String[] views = { "Dirt", "Grass", "Rock", "Water"};
		selectedBlock = new JComboBox<String>(views);
		selectedBlock.setSelectedIndex(0);
		blockSelector.add(selectedBlock);
	
		return blockSelector;
	}
	
	private void initFrame(){
		int width = 600;
		int height = 400;
		setModal(true);
		setSize(new Dimension(width,height));
		setLocation((int)Window.getCenter().getX() - width, (int)Window.getCenter().getY() - height);
		setTitle("Material Editor");
		getContentPane().setLayout(new GridBagLayout());
	}
	
}
