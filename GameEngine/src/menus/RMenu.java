package menus;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import renderers.Renderer;
import terrains.Block;
import static org.lwjgl.opengl.GL20.*;
import main.Game;
import main.Main;

public class RMenu extends JPanel{
	
	private JCheckBox a;
	private JCheckBox b;
	private JCheckBox c;
	private JCheckBox d;
	private JCheckBox e;
	private JCheckBox f;
	private JCheckBox g;
	private JCheckBox h;
	
	public JSlider BGRed;
	public JSlider BGGreen;
	public JSlider BGBlue;
	
	private Minimap minimap;
	
	public void init() {
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(200,200));
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		minimap = new Minimap(this.getPreferredSize().width-8);
		add(minimap);
		add(createCheckBoxes());
		createBGColorSliders();
	}
	
	private JPanel createCheckBoxes(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		a = createCheckBox("Vsyc",Main.VSYNC);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(a, gbc);
		
		gbc = new GridBagConstraints();
		b = createCheckBox("Wireframe",false);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(b, gbc);
		
		
		gbc = new GridBagConstraints();
		c = createCheckBox("Textures",true);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(c, gbc);
		
		gbc = new GridBagConstraints();
		d = createCheckBox("Mipmapping",true);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(d, gbc);
		
		gbc = new GridBagConstraints();
		e = createCheckBox("Logs",true);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(e, gbc);
		
		gbc = new GridBagConstraints();
		f = createCheckBox("Models",true);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(f, gbc);
		
		gbc = new GridBagConstraints();
		g = createCheckBox("Shadows",true);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(g, gbc);
		
		gbc = new GridBagConstraints();
		h = createCheckBox("Something",true);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(h, gbc);
		
		return panel;
	}
	
	private void createBGColorSliders(){
		BGRed = new JSlider(JSlider.HORIZONTAL,0,255, 0);
		BGRed.setMajorTickSpacing(255);
		BGRed.setMinorTickSpacing(0);
		BGRed.setPaintTicks(true);
		BGRed.setPaintLabels(true);
		BGRed.setPreferredSize(new Dimension(getPreferredSize().width-10,50));
		add(BGRed);
		
		BGGreen = new JSlider(JSlider.HORIZONTAL,0,255, 255);
		BGGreen.setMajorTickSpacing(255);
		BGGreen.setMinorTickSpacing(0);
		BGGreen.setPaintTicks(true);
		BGGreen.setPaintLabels(true);
		BGGreen.setPreferredSize(new Dimension(getPreferredSize().width-10,50));
		add(BGGreen);
		
		BGBlue = new JSlider(JSlider.HORIZONTAL,0,255, 0);
		BGBlue.setMajorTickSpacing(255);
		BGBlue.setMinorTickSpacing(0);
		BGBlue.setPaintTicks(true);
		BGBlue.setPaintLabels(true);
		BGBlue.setPreferredSize(new Dimension(getPreferredSize().width-10,50));
		add(BGBlue);
	}
	
	public Color getBGcolor(){
		return new Color(BGRed.getValue(),BGGreen.getValue(),BGBlue.getValue());
	}
	
	public boolean isActive(String what){
		switch(what){
			case "b":
				return b.isSelected();
			default:
				return false;
		}
	}
	
	private JCheckBox createCheckBox(String name, boolean selected){
		JCheckBox novy = new JCheckBox(name);
		novy.setSelected(selected);
	    add(novy);
		return novy;
	}

	public void useOptions() {
		//a - VSYNC
		//Display.setVSyncEnabled(a.isSelected());
		
		//b - WIREFRAME
		if(b.isSelected())
			glPolygonMode(GL_FRONT_AND_BACK,GL_LINE);
		else
			glPolygonMode(GL_FRONT_AND_BACK,GL_FILL);
		
		//c - TEXTURES
//		c.addChangeListener(new ChangeListener(){
//			public void stateChanged(ChangeEvent arg0) {
//				Renderer.change(GL_TEXTURE_2D);
//			}
//			
//		});
		if(c.isSelected())
			glEnable(GL_TEXTURE_2D);
		else
			glDisable(GL_TEXTURE_2D);
		
		//d - MIPMAPPING
		
		//e
		
	}
	
	public void setMinimap(Block[][] minimap){
		this.minimap.setMinimap(minimap);
	}

	public Minimap getMinimap() {
		return minimap;
	}

}
