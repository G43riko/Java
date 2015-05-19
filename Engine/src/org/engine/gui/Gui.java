package org.engine.gui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.engine.core.CoreEngine;
import org.engine.gui.panel.PanelBottom;
import org.engine.gui.panel.PanelRight;
import org.engine.gui.panel.PanelTop;

public class Gui extends JPanel{
	private static final long serialVersionUID = 1L;

	private Canvas canvas;
	private CoreEngine coreEngine;
	
	private PanelTop panelTop;
	private PanelRight panelRight;
	private PanelBottom panelBottom;
	
	
	//CONSTRUCTORS
	
	public Gui(CoreEngine coreEngine) {
		this.coreEngine = coreEngine;
		init();
	}

	//OTHERS
	
	private void init() {
		setLayout(new BorderLayout());
		
		add(panelTop = new PanelTop(this), BorderLayout.NORTH);
		add(panelBottom = new PanelBottom(this), BorderLayout.SOUTH);
		add(panelRight = new PanelRight(this), BorderLayout.EAST);
		
		canvas = new Canvas();
		
		BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB); 
		canvas.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "blank cursor"));
		
		add(canvas,BorderLayout.CENTER);
	}

	public void tooglePanels(){
		
		panelTop.setVisible(panelTop.isVisible() == false);
		panelRight.setVisible(panelRight.isVisible() == false);
		panelBottom.setVisible(panelBottom.isVisible() == false);
	}
	
	//GETTERS
	
	public Canvas getCanvas() {
		return canvas;
	}

	public PanelTop getPanelTop() {
		return panelTop;
	}

	public PanelRight getPanelRight() {
		return panelRight;
	}

	public PanelBottom getPanelBottom() {
		return panelBottom;
	}

	public CoreEngine getCoreEngine() {
		return coreEngine;
	}
}

