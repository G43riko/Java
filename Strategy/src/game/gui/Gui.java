package game.gui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.engine.core.CoreEngine;

import game.gui.menu.BMenu;
import game.gui.menu.RMenu;
import game.gui.menu.TMenu;

public class Gui extends JPanel{
	private static final long serialVersionUID = 1L;

	private Canvas canvas;
	private TMenu tmenu;
	private RMenu rmenu;
	private BMenu bmenu;
	private CoreEngine coreEngine;
	
	public Gui() {
		this(null);
	}
	
	public Gui(CoreEngine coreEngine) {
		this.coreEngine = coreEngine;
		init();
		add(canvas = new Canvas());
	}
	
	public void update(){
		bmenu.update();
	}

	public void init() {
		setLayout(new BorderLayout());
		setBackground(Color.blue);
		
		add(tmenu = new TMenu(this), BorderLayout.NORTH);
		
		add(bmenu = new BMenu(), BorderLayout.SOUTH);
		
		add(rmenu = new RMenu(), BorderLayout.EAST);
		
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

	public CoreEngine getCoreEngine() {
		return coreEngine;
	}

	public BMenu getBmenu() {
		return bmenu;
	}
}
