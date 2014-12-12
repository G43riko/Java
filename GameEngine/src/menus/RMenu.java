package menus;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
		a = createCheckBox("Vsyc",Main.VSYNC);
		b = createCheckBox("Wireframe",false);
		c = createCheckBox("Textures",true);
		d = createCheckBox("Mipmapping",true);
		e = createCheckBox("Logs",true);
		f = createCheckBox("Models",true);
		g = createCheckBox("Shadows",true);
		
		createBGColorSliders();
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
		Display.setVSyncEnabled(a.isSelected());
		
		//b - WIREFRAME
		if(b.isSelected())
			glPolygonMode(GL_FRONT_AND_BACK,GL_LINE);
		else
			glPolygonMode(GL_FRONT_AND_BACK,GL_FILL);
		
		//c - TEXTURES
		if(c.isSelected())
			glEnable(GL_TEXTURE_2D);
		else
			glDisable(GL_TEXTURE_2D);
		
		//d - MIPMAPPING
		
		//e
		
	}

}
