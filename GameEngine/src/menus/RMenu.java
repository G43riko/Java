package menus;

import static org.lwjgl.opengl.GL11.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
	
	private JPanel checkBoxes;
	private JPanel bgColor;
	
	public JSlider BGRed;
	public JSlider BGGreen;
	public JSlider BGBlue;
	
	private JComboBox typeOfView;
	
	private Minimap minimap;
	
	public void init() {
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(200,200));
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		minimap = new Minimap(this.getPreferredSize().width-8);
		add(minimap);
		
		String[] views = { "Clasic view", "Depth", "Normals", "HeightMap"};
		typeOfView = new JComboBox(views);
		typeOfView.setSelectedIndex(0);
		add(typeOfView);
		
		add(createCheckBoxes());
		createBGColorSliders();
	}
	
	public int getTypeOfView(){
		return typeOfView.getSelectedIndex();
	}
	
	public JComboBox getTypeOfViewSelector(){
		return typeOfView;
	}
	
	private JPanel createCheckBoxes(){
		checkBoxes = new JPanel();
		checkBoxes.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		a = createCheckBox("Vsyc",Main.VSYNC);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		checkBoxes.add(a, gbc);
		
		gbc = new GridBagConstraints();
		b = createCheckBox("Wireframe",false);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		checkBoxes.add(b, gbc);
		
		
		gbc = new GridBagConstraints();
		c = createCheckBox("Textures",true);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		checkBoxes.add(c, gbc);
		
		gbc = new GridBagConstraints();
		d = createCheckBox("Mipmapping",true);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 1;
		checkBoxes.add(d, gbc);
		
		gbc = new GridBagConstraints();
		e = createCheckBox("Logs",true);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		checkBoxes.add(e, gbc);
		
		gbc = new GridBagConstraints();
		f = createCheckBox("Models",true);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 2;
		checkBoxes.add(f, gbc);
		
		gbc = new GridBagConstraints();
		g = createCheckBox("Shadows",true);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 3;
		checkBoxes.add(g, gbc);
		
		gbc = new GridBagConstraints();
		h = createCheckBox("Lights",true);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 3;
		checkBoxes.add(h, gbc);
		
		return checkBoxes;
	}
	
	private void createBGColorSliders(){
		bgColor = new JPanel();
		bgColor.setLayout(new BorderLayout());
		BGRed = new JSlider(JSlider.HORIZONTAL,0,255, 0);
		BGRed.setMajorTickSpacing(255);
		BGRed.setMinorTickSpacing(0);
		BGRed.setPaintTicks(true);
		BGRed.setPaintLabels(true);
		BGRed.setPreferredSize(new Dimension(getPreferredSize().width-10,50));
		bgColor.add(BGRed,BorderLayout.NORTH);
		
		BGGreen = new JSlider(JSlider.HORIZONTAL,0,255, 255);
		BGGreen.setMajorTickSpacing(255);
		BGGreen.setMinorTickSpacing(0);
		BGGreen.setPaintTicks(true);
		BGGreen.setPaintLabels(true);
		BGGreen.setPreferredSize(new Dimension(getPreferredSize().width-10,50));
		bgColor.add(BGGreen,BorderLayout.CENTER);
		
		BGBlue = new JSlider(JSlider.HORIZONTAL,0,255, 0);
		BGBlue.setMajorTickSpacing(255);
		BGBlue.setMinorTickSpacing(0);
		BGBlue.setPaintTicks(true);
		BGBlue.setPaintLabels(true);
		BGBlue.setPreferredSize(new Dimension(getPreferredSize().width-10,50));
		bgColor.add(BGBlue,BorderLayout.SOUTH);
		
		add(bgColor);
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

	public boolean getWireframe(){
		return b.isSelected();
	}
	
	public boolean getTextures(){
		return c.isSelected();
	}
	
	public void useOptions() {
		//a - VSYNC
		//Display.setVSyncEnabled(a.isSelected());
		
		//b - WIREFRAME
		if(b.isSelected())
			glPolygonMode(GL_FRONT,GL_LINE);
		else
			glPolygonMode(GL_FRONT,GL_FILL);
		
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
	
	public boolean showLights(){
		return h.isSelected();
	}

	public JPanel getCheckBoxes() {
		return checkBoxes;
	}

	public JPanel getBgColor() {
		return bgColor;
	}

}
