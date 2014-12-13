package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class TMenu extends JMenuBar{
	JMenu menuA;
	
	public TMenu(){
		menuA = new JMenu("Files");
		//menuA.setMnemonic(KeyEvent.VK_C);
		menuA.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		add(menuA);
		
		
		JMenuItem menuItem = new JMenuItem("New Map", KeyEvent.VK_T);
//		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
		"This doesn't really do anything");
		menuA.add(menuItem);
		
		
		menuItem = new JMenuItem("Open Map");
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fo = new JFileChooser();
		        int returnVal = fo.showOpenDialog(TMenu.this);
		        String newline = "nové nieèo";
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fo.getSelectedFile();
		            //This is where a real application would open the file.
		            System.out.println("Opening: " + file.getName());
		        } else {
		        	System.out.println("Open command cancelled by user.");
		        }
			}
			
		});
		menuA.add(menuItem);
		
		
		menuItem = new JMenuItem("Save Map");
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fs = new JFileChooser();
		        int returnVal = fs.showSaveDialog(TMenu.this);
		        String newline = "nové nieèo";
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		        	File fileToSave = fs.getSelectedFile();
		            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		        }
			}
			
		});
		menuA.add(menuItem);
		
		
	}
}
