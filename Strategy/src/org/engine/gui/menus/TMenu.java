package org.engine.gui.menus;

import glib.util.GLog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.MainStrategy;
import org.engine.gui.Gui;
import org.engine.gui.windows.PhysicsWindow;
import org.engine.gui.windows.RenderingWindow;
import org.engine.gui.windows.SceneWindow;
import org.engine.gui.windows.TextureWindow;
import org.engine.gui.windows.TimingWindow;

public class TMenu extends JMenuBar{
	private static final long serialVersionUID = 1L;
	
	private Gui gui;
	private JMenu menuA;
	private JMenu menuB;
	private MouseListener onClick = new MouseListener(){
		public void mouseClicked(MouseEvent e) {updateUI();}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	};
	
 	public TMenu(Gui gui){
 		this.gui = gui;
		if(MainStrategy.OSLOOK){
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e2) {
				e2.printStackTrace();
			}
		}
		
		initMenuA();
		initMenuB();
	}

 	//INIT MENUS
 	
	private void initMenuB() {
		menuB = new JMenu("View");
		menuB.addMouseListener(onClick);
		add(menuB);
		
		initMaterialEditor();
		initPhysicsEditor();
		initSceneEditor();
		initRenderingEditor();
		initTimingEditor();
	}
	
	private void initMenuA() {
		menuA = new JMenu("Files");
//		menuA.addMouseListener(onClick);
		add(menuA);
		
		initNewMap();
		initOpenMap();
		initSaveMap();
		initExit();
	}

	//INIT MENU B
	
	private void initSceneEditor() {
		JMenuItem menuItem = new JMenuItem("zobraziù scÈnu", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("klikol si na zobrazenie scÈny");
				gui.getRmenu().removeAll();
				gui.getRmenu().add(new SceneWindow(gui));
				gui.getRmenu().updateUI();
			}
		});
		menuB.add(menuItem);
	}

	private void initMaterialEditor(){
		JMenuItem menuItem = new JMenuItem("Material editor", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showConfirmDialog(new TextureWindow("texture.png"), gui);
				JOptionPane.showMessageDialog(gui,new TextureWindow("texture.png"), "Material editor", JOptionPane.PLAIN_MESSAGE);
//				GLog.write("klikol si na zobrazenie editora materi·lov","menu");
			}
		});
		menuB.add(menuItem);
	}
	
	private void initTimingEditor(){
		JMenuItem menuItem = new JMenuItem("Timing editor", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showConfirmDialog(new TextureWindow("texture.png"), gui);
				JOptionPane.showMessageDialog(gui,new TimingWindow(), "Timing editor", JOptionPane.PLAIN_MESSAGE);
//				GLog.write("klikol si na zobrazenie editora materi·lov","menu");
			}
		});
		menuB.add(menuItem);
	}
	
	private void initPhysicsEditor() {
		JMenuItem menuItem = new JMenuItem("zobraziù fyziku", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("klikol si na showPhysics");
				gui.getRmenu().removeAll();
				gui.getRmenu().add(new PhysicsWindow(gui.getRmenu()));
				gui.getRmenu().updateUI();
			}
		});
		menuB.add(menuItem);
	}
	
	private void initRenderingEditor() {
		JMenuItem menuItem = new JMenuItem("zobraziù Rendering", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				gui.getRmenu().removeAll();
				gui.getRmenu().add(new RenderingWindow(gui.getRmenu()));
				gui.getRmenu().updateUI();
			}
		});
		menuB.add(menuItem);
	}
	
	//INIT MENU A
	
	private void initExit() {
		JMenuItem menuItem = new JMenuItem("Exit", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GLog.write("klikol si na Exit","menu");
				System.exit(0);
			}
		});
		menuA.add(menuItem);
	}

	private void initSaveMap() {
		JMenuItem menuItem = new JMenuItem("Save Map", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GLog.write("klikol si na Save Map","menu");
			}
		});
		menuA.add(menuItem);
	}

	private void initOpenMap() {
		JMenuItem menuItem = new JMenuItem("Opem Map", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GLog.write("klikol si na Open Map","menu");
			}
		});
		menuA.add(menuItem);
	}

	private void initNewMap(){
		JMenuItem menuItem = new JMenuItem("New Map", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GLog.write("klikol si na New Map","menu");
			}
		});
		menuA.add(menuItem);
	}
}
