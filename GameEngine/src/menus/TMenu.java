package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import main.Game;
import main.Main;
import terrains.Map;

public class TMenu extends JMenuBar{
	private JMenu menuA;
	private JMenu menuB;
	private JMenu Rsubmenu;
	private Map mapa;
	
	private BMenu bmenu;
	private RMenu rmenu;
	
	private JCheckBoxMenuItem showRMenu;
	private JCheckBoxMenuItem showBMenu;
	
	private JCheckBoxMenuItem showMiniMap;
	private JCheckBoxMenuItem showViewControler;
	private JCheckBoxMenuItem showCheckOptions;
	private JCheckBoxMenuItem showViewBGColor;
	
	public TMenu(BMenu bmenu, RMenu rmenu){
		this.rmenu = rmenu;
		this.bmenu = bmenu;
		if(Main.OSLOOK){
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
	
	private void initMenuB(){
		menuB = new JMenu("View");
		//menuA.setMnemonic(KeyEvent.VK_C);
//		menuB.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		add(menuB);
		
		showRMenu = new JCheckBoxMenuItem("Show right Menu",true);
		showRMenu.setMnemonic(KeyEvent.VK_C);
//		showRMenu.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
		showRMenu.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				Rsubmenu.setEnabled(showRMenu.isSelected());
//				System.out.println(rmenu.isVisible());
//				rmenu.setVisible(showRMenu.isSelected());
			}
		});
		menuB.add(showRMenu);
		
		Rsubmenu = new JMenu("A submenu");
		
		showMiniMap = new JCheckBoxMenuItem("Show Minimap",true);
		showMiniMap.setMnemonic(KeyEvent.VK_C);
		showMiniMap.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				rmenu.getMinimap().setVisible(showMiniMap.isSelected());
			}
		});
		Rsubmenu.add(showMiniMap);
		
		showViewControler = new JCheckBoxMenuItem("Show view controler",true);
		showViewControler.setMnemonic(KeyEvent.VK_C);
		showViewControler.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				rmenu.getTypeOfViewSelector().setVisible(showViewControler.isSelected());
			}
		});
		Rsubmenu.add(showViewControler);
		
		showCheckOptions = new JCheckBoxMenuItem("Show check options",true);
		showCheckOptions.setMnemonic(KeyEvent.VK_C);
		showCheckOptions.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				rmenu.getCheckBoxes().setVisible(showCheckOptions.isSelected());
			}
		});
		Rsubmenu.add(showCheckOptions);
		
		showViewBGColor = new JCheckBoxMenuItem("Show BG editor",true);
		showViewBGColor.setMnemonic(KeyEvent.VK_C);
		showViewBGColor.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				rmenu.getBgColor().setVisible(showViewBGColor.isSelected());
			}
		});
		Rsubmenu.add(showViewBGColor);
		
		menuB.add(Rsubmenu);
		
		
		showBMenu = new JCheckBoxMenuItem("Show bottom Menu",true);
		showBMenu.setMnemonic(KeyEvent.VK_C);
		showBMenu.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				bmenu.setVisible(showBMenu.isSelected());
			}
		});
		menuB.add(showBMenu);
	}
	
	private void initMenuA(){
		menuA = new JMenu("Files");
		add(menuA);
		
		
		JMenuItem menuItem = new JMenuItem("New Map", KeyEvent.VK_T);
//		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
//				Game.isLoading = true;
				JTextField num = new JTextField();
				final JComponent[] inputs = new JComponent[] {
						new JLabel("new map size:"),
						num
				};
				JOptionPane.showMessageDialog(null, inputs, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
				mapa.initDefaultMap(Integer.valueOf(num.getText()),Integer.valueOf(num.getText()));
//				Game.isLoading = false;
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
