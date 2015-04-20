package game.components;


import game.gui.Slider;
import game.gui.TwoFields;
import game.main.Game;
import game.main.Index;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Window extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int WIDTH = Index.WIDTH;
	public static int HEIGHT = Index.HEIGHT;
	private JPanel contentPanel;
	private JPanel menu;
	private Canvas canvas;
	private Keyboard keyboard = new Keyboard();
	private Mouse mouse;
	public Slider enemyNum,blocksNum;
	public TwoFields enemySpeed, enemySize;
	
	public Window(Game game){
		setSize(Index.WIDTH+200+6, Index.HEIGHT+28);
		setTitle(Index.TITLE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
//		JLabel label = new JLabel();
//		label.setText("nepriatelov");
//		menu.add(label);
//		
//		numb = new JFormattedTextField(NumberFormat.getNumberInstance());
//		numb.setText("500");
//		numb.addKeyListener(new KeyListener(){
//			public void keyPressed(KeyEvent e) {
//				if(e.getKeyCode()==10){
//					number.setValue(Integer.valueOf(numb.getText()));
//				}
//			}
//			public void keyReleased(KeyEvent e) {}
//			public void keyTyped(KeyEvent e) {}
//		});
//		numb.setPreferredSize(new Dimension(40,20));
//		menu.add(numb);
//		
//		
//		int max = 1000;
//		number = new JSlider(JSlider.HORIZONTAL,0,max, max/2);
//		number.setPreferredSize(new Dimension(120,40));
//		number.setBackground(menu.getBackground());
//		number.setMajorTickSpacing(max);
//		number.setMinorTickSpacing(0);
//		number.setPaintTicks(true);
//		number.setPaintLabels(true);
//		number.addChangeListener(new ChangeListener(){
//			public void stateChanged(ChangeEvent arg0) {
//				numb.setText(String.valueOf(number.getValue()));
//			}
//		});
//		menu.add(number);
		
		enemyNum = new Slider("Nepriatelov",1000,1); 
		menu.add(enemyNum);
		
		blocksNum = new Slider("políèok",99,30);
		menu.add(blocksNum);
		
		enemySpeed = new TwoFields("rychlost",6,10);
		menu.add(enemySpeed);
		
		enemySize = new TwoFields("velkost",8,20);
		menu.add(enemySize);
		
		
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
