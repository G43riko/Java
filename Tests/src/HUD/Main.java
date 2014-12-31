package HUD;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{
	private JPanel contentPanel;
	private JPanel bottomMenu;
	private JPanel rightMenu;
	private int width = 1280;
	private int height = 720;
	
	public static void main(String[] args){
		Main game = new Main("tester");
		
	}
	
	public Main(String title){
		setTitle(title);
		setVisible(true);
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		/*
		 * fps
		 * number of blocks
		 * number of drawing blocks
		 * points
		 * polygons
		 * camera rotation
		 * camera position XY
		 * selected tile XYZ
		 */
		
		this.contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBackground(Color.red);
		add(contentPanel);
		
		bottomMenu = new JPanel();
		contentPanel.add(bottomMenu,BorderLayout.SOUTH);
		bottomMenu.setBackground(Color.green);
		bottomMenu.setPreferredSize(new Dimension(100,100));
		
		rightMenu = new JPanel();
		contentPanel.add(rightMenu,BorderLayout.EAST);
		//rightMenu.setBackground(Color.blue);
		rightMenu.setPreferredSize(new Dimension(200,200));
		rightMenu.setLayout(new FlowLayout());
		
		JCheckBox a = new JCheckBox("Wireframe");
		//hairButton.setBackground(rightMenu.getBackground());
	    a.setSelected(true);
	    rightMenu.add(a);
	    /*
	     * a - wireframe
	     * b - mipmapping
	     * c - textures
	     * d - infos
	     * e - ambient light
	     * f - directional light
	     * g - shadows
	     * h - ambient occusion
	     * i - hide models
	     * j - hide paths
	     */
	    
	    repaint();
	}
}
