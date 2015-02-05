package game.gui.menu;

import game.main.MainStrategy;
import glib.util.GLog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TMenu extends JMenuBar{
	private static final long serialVersionUID = 1L;
	private JMenu menuA;
	private JMenu menuB;
	private MouseListener onClick = new MouseListener(){
		public void mouseClicked(MouseEvent e) {updateUI();}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	};
	
 	public TMenu(){
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

	private void initMenuB() {
		menuB = new JMenu("View");
		menuB.addMouseListener(onClick);
		add(menuB);
		
		initME();
	}
	
	private void initMenuA() {
		menuA = new JMenu("Files");
		menuA.addMouseListener(onClick);
		add(menuA);
		
		initNM();
		initOM();
		initSM();
		initE();
		
	}
	
	private void initME(){
		JMenuItem menuItem = new JMenuItem("Material editor", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GLog.write("klikol si na zobrazenie editora materiálov","menu");
			}
		});
		menuB.add(menuItem);
	}
	
	
	private void initE() {
		JMenuItem menuItem = new JMenuItem("Exit", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GLog.write("klikol si na Exit","menu");
				System.exit(0);
			}
		});
		menuA.add(menuItem);
	}

	private void initSM() {
		JMenuItem menuItem = new JMenuItem("Save Map", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GLog.write("klikol si na Save Map","menu");
			}
		});
		menuA.add(menuItem);
	}

	private void initOM() {
		JMenuItem menuItem = new JMenuItem("Opem Map", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GLog.write("klikol si na Open Map","menu");
			}
		});
		menuA.add(menuItem);
	}

	private void initNM(){
		JMenuItem menuItem = new JMenuItem("New Map", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GLog.write("klikol si na New Map","menu");
			}
		});
		menuA.add(menuItem);
	}
}
