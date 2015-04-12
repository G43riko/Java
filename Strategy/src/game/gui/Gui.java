package game.gui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import game.core.CoreEngine;
import game.core.CoreGame;
import game.gui.menu.BMenu;
import game.gui.menu.RMenu;
import game.gui.menu.TMenu;

public class Gui extends JPanel{
	private static final long serialVersionUID = 1L;

	private Canvas canvas;
	private TMenu tmenu;
	private RMenu rmenu;
	private BMenu bmenu;
	
	public Gui(CoreEngine game) {
		setLayout(new BorderLayout());
		setBackground(Color.blue);
		init();
		canvas = new Canvas();
		add(canvas);
	}

		public void init() {
		
		tmenu = new TMenu(this);
		add(tmenu,BorderLayout.NORTH);
		
		bmenu = new BMenu();
		add(bmenu,BorderLayout.SOUTH);
		
		rmenu = new RMenu();
		add(rmenu,BorderLayout.EAST);
		
		canvas = new Canvas();
		canvas.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor"));
		add(canvas,BorderLayout.CENTER);
		
	}
	
	public Canvas getCanvas() {
		return canvas;
	}

	public RMenu getRmenu() {
		return rmenu;
	}

}
