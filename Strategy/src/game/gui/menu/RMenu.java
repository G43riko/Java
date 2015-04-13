package game.gui.menu;

import game.gui.Gui;
import game.main.MainStrategy;
import glib.util.GColor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class RMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	private final static int width = MainStrategy.WIDTH/6;
	private GColor bgColor = new GColor(1,0,1);
	
	//CONSTRUCTORS
	
	public RMenu(){
		init();
	}

	//OTHERS
	
	public void init(){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(width,width));
		setBorder(BorderFactory.createLineBorder(Color.black,1,true));
	}
	
	//GETTERS
	
	public GColor getBgColor() {
		return bgColor;
	}
}
