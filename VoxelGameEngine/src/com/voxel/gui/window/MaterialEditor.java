package com.voxel.gui.window;

import glib.swing.GFloatEditor;
import glib.swing.GVector3Editor;
import glib.swing.GTextureViewer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.JSONObject;

import com.voxel.core.Window;
import com.voxel.main.MainVoxel2;
import com.voxel.world.BlockInfo;


public class MaterialEditor extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> selectedBlock;
	private GVector3Editor mapColor;
	private GTextureViewer textureViewer;
	private JCheckBox transparent;
	private JCheckBox repeat;
	private GFloatEditor specular;
	private GFloatEditor exponent;
	private GFloatEditor repX;
	private GFloatEditor repY;
	private int selected = 0;
	
	private ChangeListener changer = new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
        	System.out.println("teraz sa nieèo zmenilo "+System.currentTimeMillis());
        	JSONObject act = BlockInfo.getBlockInfo(selected+1);
        	act.put("repX", repX.getValue());
    		act.put("repY", repY.getValue());
    		act.put("repeat", repeat.isSelected()?1:0);
    		act.put("transparent", transparent.isSelected()?1:0);
    		act.put("colorX", mapColor.getValX());
    		act.put("colorY", mapColor.getValY());
    		act.put("colorZ", mapColor.getValZ());
    		act.put("specular", specular.getValue());
    		act.put("exponent", exponent.getValue());
//    		System.out.println(BlockInfo.getBlockInfo(selected+1).get("specular"));
        }
	};
	
	public MaterialEditor(){
		initFrame();
		init();
	}
	
	private void init() {
		getContentPane().removeAll();
		GridBagConstraints c;
		
		/*
		 * add Block Selector
		 */
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		getContentPane().add(createBlockSelector());
		
		/*
		 * add Color Selector
		 */
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		mapColor = new GVector3Editor("map color",255,0,(float)BlockInfo.getBlockInfo(selected+1, "colorX"),
													  	(float)BlockInfo.getBlockInfo(selected+1, "colorY"),
													  	(float)BlockInfo.getBlockInfo(selected+1, "colorZ"));
		mapColor.addChangeListener(changer);
		getContentPane().add(mapColor, c);
		
		/*
		 * add Texture Viewer
		 */
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.gridheight = 3;
		textureViewer = new GTextureViewer(BlockInfo.BLOCKS[selected+1].getName().toLowerCase()+"_"+MainVoxel2.RESOLUTION+".jpg",256,256);
		getContentPane().add(textureViewer, c);
		
		/*
		 * add Transparency Selector;
		 */
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		transparent = new JCheckBox("Transparent",BlockInfo.getBlockInfo(selected+1, "transparent")==1?true:false);
		transparent.addChangeListener(changer);
		getContentPane().add(transparent, c);
		
		/*
		 * add Repeat Selector
		 */
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		repeat = new JCheckBox("Repeat",BlockInfo.getBlockInfo(selected+1, "repeat")==1?true:false);
		repeat.addChangeListener(changer);
		getContentPane().add(repeat, c);

		/*
		 * add Repeater
		 */
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 2;
		getContentPane().add(createRepeater(), c);
		
		/*
		 * add Specular Selector
		 */
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 2;
		getContentPane().add(createSpecular(), c);
		setVisible(true);
	}

	private JPanel createBlockSelector(){
		JPanel blockSelector = new JPanel();
		
		blockSelector.setLayout(new FlowLayout());
		blockSelector.add(new JLabel("Block"));
		String[] views = { "Grass", "Dirt", "Rock", "Water"};
		selectedBlock = new JComboBox<String>(views);
		selectedBlock.setSelectedIndex(selected);
		
		selectedBlock.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	selected = selectedBlock.getSelectedIndex();
		    	init();
		    }
		});
		
		
		blockSelector.add(selectedBlock);
	
		return blockSelector;
	}
	
	private JPanel createRepeater(){
		JPanel result = new JPanel();
		result.setLayout(new FlowLayout());
		repX = new GFloatEditor("RepeatX",10,0,1,(float)BlockInfo.getBlockInfo(selected+1).getDouble("repX"));
		repX.addChangeListener(changer);
		result.add(repX);
		
		repY = new GFloatEditor("RepeatY",10,0,1,(float)BlockInfo.getBlockInfo(selected+1).getDouble("repY"));
		repY.addChangeListener(changer);
		result.add(repY);
		
		return result;
	}
	
	private JPanel createSpecular(){
		JPanel result = new JPanel();
		result.setLayout(new FlowLayout());
		
		specular = new GFloatEditor("Specular",5.1f,0,0.1f,(float)BlockInfo.getBlockInfo(selected+1).getDouble("specular"));
		specular.addChangeListener(changer);
		result.add(specular);
		
		exponent = new GFloatEditor("Exponent",20,0,0.5f,(float)BlockInfo.getBlockInfo(selected+1).getDouble("exponent"));
		exponent.addChangeListener(changer);
		result.add(exponent);
		
		return result;
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
