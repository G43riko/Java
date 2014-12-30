package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.Main;
import terrains.Map;

public class TMenu extends JMenuBar{
	private JMenu menuA;
	private Map mapa;
	
	public TMenu(){
		if(Main.OSLOOK){
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e2) {
				e2.printStackTrace();
			}
		}
		menuA = new JMenu("Files");
		//menuA.setMnemonic(KeyEvent.VK_C);
		menuA.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		add(menuA);
		
		
		JMenuItem menuItem = new JMenuItem("New Map", KeyEvent.VK_T);
//		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
//				JOptionPane optionPane = new JOptionPane(
//					    "The only way to close this dialog is by\n"
//					    + "pressing one of the following buttons.\n"
//					    + "Do you understand?",
//					    JOptionPane.QUESTION_MESSAGE,
//					    JOptionPane.YES_NO_OPTION);
//				optionPane.setVisible(true);
				//optionPane.showInputDialog("nazdar");
				mapa.initDefaultMap();
			}
		});
		menuA.add(menuItem);
		
		
		menuItem = new JMenuItem("Open Map");
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fo = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("GameMap file","gm");
				fo.setFileFilter(filter);
		        int returnVal = fo.showOpenDialog(TMenu.this);
		        String newline = "novÈ nieËo";
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fo.getSelectedFile();
		            mapa.loadMap(file);
		            //This is where a real application would open the file.
		            System.out.println("Opening: " + file.getName());
		            if(Main.ALLERTS){
		            	JOptionPane.showMessageDialog(TMenu.this,"Map was successfully loaded","Attention",JOptionPane.PLAIN_MESSAGE);
		            }
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
				FileNameExtensionFilter filter = new FileNameExtensionFilter("GameMap file","gm");
				fs.setFileFilter(filter);
		        int returnVal = fs.showSaveDialog(TMenu.this);
		        String newline = "novÈ nieËo";
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		        	File fileToSave = fs.getSelectedFile();
		        	try {
						PrintStream file = new PrintStream(fileToSave);
						file.println(mapa.saveMap());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						System.out.println("lutujeme ale s˙bor nebolo moûnÈ najsù");
						e1.printStackTrace();
					}
		        	if(Main.ALLERTS){
		        		JOptionPane.showMessageDialog(TMenu.this,"Map saved successfully","Attention",JOptionPane.PLAIN_MESSAGE);
		        	}
		            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		        }
			}
			
		});
		menuA.add(menuItem);
		
		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuA.add(menuItem);	
	}
	
	public void setMap(Map map){
		mapa = map;
	}
}
