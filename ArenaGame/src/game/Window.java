package game;


import game.gui.Slider;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Window extends JFrame {
	public static int WIDTH = Index.WIDTH;
	public static int HEIGHT = Index.HEIGHT;
	private JPanel contentPanel;
	private JPanel menu;
	private Canvas canvas;
	private Keyboard keyboard = new Keyboard();
	private Mouse mouse;
	private JSlider number;
	public JFormattedTextField numb;
	
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
		
		
		JButton but = new JButton("nová hra");
		but.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				game.wantNewGame = true;
			}
		});
		but.setPreferredSize(new Dimension(150,20));
		menu.add(but);
		
		JLabel label = new JLabel();
		label.setText("nepriatelov");
		menu.add(label);
		
		numb = new JFormattedTextField(NumberFormat.getNumberInstance());
		numb.setText("500");
		numb.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10){
					number.setValue(Integer.valueOf(numb.getText()));
				}
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		});
		numb.setPreferredSize(new Dimension(40,20));
		menu.add(numb);
		
		
		int max = 1000;
		number = new JSlider(JSlider.HORIZONTAL,0,max, max/2);
		number.setPreferredSize(new Dimension(120,40));
		number.setBackground(menu.getBackground());
		number.setMajorTickSpacing(max);
		number.setMinorTickSpacing(0);
		number.setPaintTicks(true);
		number.setPaintLabels(true);
		number.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				numb.setText(String.valueOf(number.getValue()));
			}
		});
		menu.add(number);
		
		menu.add(new Slider());
		menu.add(new Slider());
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
			Thread.sleep(400);
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
