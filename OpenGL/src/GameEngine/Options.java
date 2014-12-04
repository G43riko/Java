package GameEngine;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GameEngine.Menu.Component;
import GameEngine.Menu.PointLightMenu;

public class Options extends JFrame{
	private Game game;
	public JPanel contentPane;
	public ArrayList<Component> scena = new ArrayList<Component>();
	JButton setData = new JButton();
	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu();
	
	public static void main(String[] args){
		Options okno = new Options();
	}
	
	public void addGame(Game game){
		this.game = game;
	}
	public Options(){
		this(MainComponents.WIDTH,0,MainComponents.WIDTH,MainComponents.HEIGHT,"Options");
	}
	
	public Options(int w, int h){
		this(MainComponents.WIDTH,0,w,h,"Options");
	}
	
	public Options(int w, int h,String title){
		this(MainComponents.WIDTH,0,w,h,title);
	}
	
	public Options(int x, int y, int w, int h,String title){
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(w, h);
		setTitle(title);
		setLocation(x+10, y);
		
		FlowLayout layout = new FlowLayout();
		setLayout(layout);
		
		
		
				
		menuBar.add(addMenuNastavenia());
		menuBar.setSize(200,50);
		setJMenuBar(menuBar);
		
		contentPane = new JPanel();
		contentPane.setLayout(layout);
		add(contentPane);
		
		setData.setText("aktualizovaù");
		setData.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setAllData();
			}
		});
		
		contentPane.add(setData);
		
		setVisible(true);
		//pack();
	}
	
	public void init(){
		
	}
	
	private JMenu addMenuNastavenia(){
		menu = new JMenu("Nastavenia");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		
		
		return menu; 				
	}
	
	public void setLightPos(float x, float y, float z){

	}
	
	public void addLight(PointLight svetlo){
		PointLightMenu x = new PointLightMenu(svetlo);
		scena.add(x);
		add(x.getPanel());
		
		contentPane.updateUI();
		repaint();
	}

	public void getAllData() {
		int sceneNum = scena.size();
		for(int i=0 ; i<sceneNum ; i++){
			scena.get(i).getData();
		}
	}
	
	public void setAllData(){
		int sceneNum = scena.size();
		for(int i=0 ; i<sceneNum ; i++){
			scena.get(i).setData();
		}
	}
}
