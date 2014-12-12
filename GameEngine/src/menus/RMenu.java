package menus;

import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.glPolygonMode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.lwjgl.opengl.GL11;

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
	
	public void init() {
		setLayout(new FlowLayout());
		a = createCheckBox("Vsyc",Main.VSYNC);
		b = createCheckBox("Wireframe",false);
		c = createCheckBox("Textures",true);
		d = createCheckBox("Mipmapping",true);
		e = createCheckBox("Logs",true);
		f = createCheckBox("Models",true);
		g = createCheckBox("Shadows",true);
		
		createBGColorSliders();
		setPreferredSize(new Dimension(200,200));
	}
	
	private void createBGColorSliders(){
		BGRed = new JSlider(JSlider.HORIZONTAL,0,255, 0);
		BGRed.setMajorTickSpacing(255);
		BGRed.setMinorTickSpacing(0);
		BGRed.setPaintTicks(true);
		BGRed.setPaintLabels(true);
		add(BGRed);
		
		BGGreen = new JSlider(JSlider.HORIZONTAL,0,255, 255);
		BGGreen.setMajorTickSpacing(255);
		BGGreen.setMinorTickSpacing(0);
		BGGreen.setPaintTicks(true);
		BGGreen.setPaintLabels(true);
		add(BGGreen);
		
		BGBlue = new JSlider(JSlider.HORIZONTAL,0,255, 0);
		BGBlue.setMajorTickSpacing(255);
		BGBlue.setMinorTickSpacing(0);
		BGBlue.setPaintTicks(true);
		BGBlue.setPaintLabels(true);
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
		if(b.isSelected())
			glPolygonMode(GL_FRONT_AND_BACK,GL_LINE);
		else
			glPolygonMode(GL_FRONT_AND_BACK,GL_FILL);
		
	}

}
