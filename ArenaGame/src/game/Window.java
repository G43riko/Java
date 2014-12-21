package game;


import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Window extends JFrame {
	public static int WIDTH = Index.WIDTH;
	public static int HEIGHT = Index.HEIGHT;
	private JPanel contentPanel;
	private JPanel menu;
	private Canvas canvas;
	private Keyboard keyboard = new Keyboard();
	private Mouse mouse;
	
	public Window(Game game){
		setSize(Index.WIDTH+200+6, Index.HEIGHT+28);
		setTitle(Index.TITLE);
		setResizable(false);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setVisible(true);
		
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		add(contentPanel);
		
		menu = new JPanel();
		menu.setBackground(Color.red);
		menu.setLayout(new WrapLayout());
		for(int i=0 ; i<10 ; i++){
			JButton but = new JButton("cislo je "+i);
			but.setPreferredSize(new Dimension(150,20));
			menu.add(but);
		}
		//contentPanel.add(menu, BorderLayout.EAST);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setViewportView(menu);
		scroll.setPreferredSize(new Dimension(200,0));
		contentPanel.add(scroll, BorderLayout.EAST);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(80,80));
		contentPanel.add(canvas, BorderLayout.CENTER);
		
		
		contentPanel.updateUI();
		mouse = new Mouse(game);
		
		canvas.addKeyListener(keyboard);
		canvas.addMouseListener(mouse);
		canvas.addMouseMotionListener(mouse);
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WIDTH = canvas.getWidth();
		HEIGHT = canvas.getHeight();
		
	}

	public JPanel getMenu() {
		return menu;
	}

	public Canvas getCanvas() {
		return canvas;
	}

}
