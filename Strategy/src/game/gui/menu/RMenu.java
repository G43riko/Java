package game.gui.menu;

import glib.util.GColor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class RMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	private final static int width = 200;
	private GColor bgColor = new GColor(1,0,1);
	
	public RMenu(){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(width,width));
		setBorder(BorderFactory.createLineBorder(Color.black,1,true));
	}

	public void init(){

	}
	
	public GColor getBgColor() {
		return bgColor;
	}
	

}
