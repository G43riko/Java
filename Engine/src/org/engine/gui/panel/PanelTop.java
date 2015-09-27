package org.engine.gui.panel;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.engine.gui.Gui;
import org.engine.gui.components.swing.DirectionalLightPanel;
import org.engine.gui.components.swing.PointLightPanel;
import org.engine.gui.window.CameraWindow;
import org.engine.gui.window.RenderWindow;;

public class PanelTop extends JMenuBar{
	private static final long serialVersionUID = 1L;
	
	private JMenu menuFile;
	private JMenu menuView;
	private JMenu menuWindow;
	private Gui gui;
	
	public PanelTop(Gui gui) {
		this.gui = gui;
		initMenuFile();
		initMenuView();
		initMenuWindow();
	}

	private void initMenuWindow() {
		add(menuWindow = new JMenu("Window"));
		initCameraWindow();
	}

	private void initMenuView() {
		add(menuView = new JMenu("View"));
//		initMaterialEditor();
//		initPhysicsEditor();
//		initSceneEditor();
		initRenderingEditor();
		initViewEditor();
//		initTimingEditor();
		
	}

	private void initMenuFile() {
		add(menuFile = new JMenu("Files"));
		
//		initNewMap();
//		initOpenMap();
//		initSaveMap();
		initExit();
		
	}
	
	//INIT FILE
	
	private void initExit() {
		JMenuItem menuItem = new JMenuItem("Exit", KeyEvent.VK_T);
		menuItem.addActionListener(a-> gui.getCoreEngine().stop());
		menuFile.add(menuItem);
	}

	//INIT VIEW
	
	private void initRenderingEditor() {
		JMenuItem menuItem = new JMenuItem("RenderEditor", KeyEvent.VK_T);
		menuItem.addActionListener(a-> {
			gui.getPanelRight().removeAll();
			gui.getPanelRight().add(new RenderWindow(gui.getPanelRight(), gui));
			gui.getPanelRight().updateUI();
		});
		menuView.add(menuItem);
	}
	
	private void initViewEditor() {
		JMenuItem menuItem = new JMenuItem("ViewEditor", KeyEvent.VK_T);
		menuItem.addActionListener(a-> {
			gui.getPanelRight().removeAll();
			gui.getPanelRight().add(new DirectionalLightPanel(gui.getCoreEngine()));
			gui.getPanelRight().add(new PointLightPanel(gui.getCoreEngine()));
			gui.getPanelRight().updateUI();
		});
		menuView.add(menuItem);
	}
	
	//INIT WINDOW
	
	private void initCameraWindow() {
		JMenuItem menuItem = new JMenuItem("CameraWindow", KeyEvent.VK_T);
		menuItem.addActionListener(a-> JOptionPane.showMessageDialog(gui,new CameraWindow(), "Material editor", JOptionPane.PLAIN_MESSAGE));
		menuWindow.add(menuItem);
	}
}
