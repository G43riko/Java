package Window;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Window extends JFrame{
	private JPanel contentPanel = new JPanel();
	private JMenuBar menuBar = new JMenuBar();
	
	public Window(){
		setTitle("Super Okno");
		setSize(new Dimension(800,600));
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		menuBar.add(addOptionsMenu());
		setJMenuBar(menuBar);
		
		setResizable(true);
		setVisible(true);
	}
	
	private JMenu addOptionsMenu(){
		JMenu menu = new JMenu("Nastavenia");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		
		JMenuItem menuItem = new JMenuItem("A text-only menu item",KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
		"This doesn't really do anything");
		menu.add(menuItem);
		
		return menu;
	}
}
