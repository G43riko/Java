package org.engine.rendeing.material;

import glib.math.GMath;
import glib.util.Loader;
import glib.util.vector.GVector3f;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GImage extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private BufferedImage defaultImage;
	private JPanel topView;

	private final static int PANEL_WIDTH = 300;
//	private JPanel imageView;
	private final static int IMAGE_WIDTH = 500-20;
	private final static int IMAGE_HEIGHT = 500-20;
	private JSpinner intensityValue;
	private JSpinner randomizeValue;
	private JCheckBox randomizeTogether;
	private GImage toto;
	private GVector3f basicColor = new GVector3f(); 
	
	//ACTIONS
	
	private ActionListener makeGreyScale = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			greyScale();
			toto.repaint();
		}
	};
	
	private ActionListener removeColor = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			removeColor(Color.red);
			toto.repaint();
		}
	};
	
	private ActionListener reset = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			image = deepCopy(defaultImage);
			toto.repaint();
		}
	};
	
	private ActionListener inverse = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			inverse();
			toto.repaint();
		}
	};
	
	private ActionListener randomize = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			randomize((int)randomizeValue.getValue(), randomizeTogether.isSelected());
			toto.repaint();
		}
	};
	
	private ActionListener addColor = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			int v = (int)intensityValue.getValue();
			
			addColor(new GVector3f(v));
			toto.repaint();
		}
	};
	
	private ActionListener nieco = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			nieco(25);
//			changeColors("brg");
//			changeColors((int)(Math.random() * 6));
			toto.repaint();
		}
	};
	
	private ActionListener changeColors = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
//			changeColors("brg");
			changeColors((int)(Math.random() * 6));
			toto.repaint();
		}
	};

	private ChangeListener changeLuminance = new ChangeListener(){
		public void stateChanged(ChangeEvent e) {
			setLiminance(((JSlider)e.getSource()).getValue());
			toto.repaint();
		}
	};
	
	private ChangeListener changeColorRed = new ChangeListener(){
		public void stateChanged(ChangeEvent e) {
			basicColor.setX(((JSlider)e.getSource()).getValue());
			updateColor();
			toto.repaint();
		}
	};
	
	private ChangeListener changeColorGreen = new ChangeListener(){
		public void stateChanged(ChangeEvent e) {
			basicColor.setY(((JSlider)e.getSource()).getValue());
			updateColor();
			toto.repaint();
		}
	};
	
	private ChangeListener changeColorBlue = new ChangeListener(){
		public void stateChanged(ChangeEvent e) {
			basicColor.setZ(((JSlider)e.getSource()).getValue());
			updateColor();
			toto.repaint();
		}
	};

	//CONSTRUCTORS
	
	
	public GImage(String fileName){
		try {
			image = ImageIO.read(Loader.loadFile("res/textures/"+fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		toto = this;
		defaultImage = deepCopy(image);
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		add(createImageView(),BorderLayout.CENTER);
		JScrollPane p = new JScrollPane(createRightView(),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(p,BorderLayout.EAST);
		
		setVisible(true);
	}
	
	//CHANGERS
	
	//CHANGERS
	
	private void inverse(){
		for(int i=0 ; i<image.getWidth() ; i++){
			for(int j=0 ; j<image.getHeight() ; j++){
				Color c = new Color(image.getRGB(i, j));
				changeColor(i,j,new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue()));
			}
		}
	}
	
	private void changeColor(int x, int y, Color c){
		image.setRGB(x, y, c.getRGB());
	}
	
//	private void changeColor(int minX, int minY, int maxX, int maxY, Color c){
//		for(int i=minX ; i<maxX ; i++){
//			for(int j=minY ; j<maxY ; j++){
//				changeColor(i,j,c);
//			}
//		}
//	}	
	
	private void setLiminance(int value){
		for(int i=0 ; i<image.getWidth() ; i++){
			for(int j=0 ; j<image.getHeight() ; j++){
				Color c = new Color(defaultImage.getRGB(i, j));
				
				int r = c.getRed() + value;
				int g = c.getGreen() + value;
				int b = c.getBlue() + value;
				changeColor(i,j,new Color((int)GMath.between(r, 0, 255), (int)GMath.between(g, 0, 255), (int)GMath.between(b, 0, 255)));
			}
		}
	}
	
	private void nieco(float offset){
		for(int i=0 ; i<image.getWidth() ; i++){
			for(int j=0 ; j<image.getHeight() ; j++){
				Color c = new Color(image.getRGB(i, j));
//				GVector3f oldColor = new GVector3f(c.getRed(), c.getGreen(), c.getBlue());
				
//				GVector3f left = new GVector3f(0,0,255);
//				GVector3f right = new GVector3f(0,255,0);
				
//				GVector3f res = GVector3f.interpolateLinear((float)1/image.getWidth()*i, left, right);
				
//				float r = c.getRed() + res.getX();
//				float g = c.getGreen() + res.getY();
//				float b = c.getBlue() + res.getZ();
				
				float r = (float)c.getRed() / 255 * 100 + 155;
				float g = (float)c.getGreen()  / 255 * 100 + 155;
				float b = (float)c.getBlue() / 255 * 100 + 155;
				
				float min = 0;
				float max = 255;
				
				changeColor(i,j,new Color((int)GMath.between(r, min, max), (int)GMath.between(g, min, max), (int)GMath.between(b, min, max)));
			}
		}
	}
	
	private void greyScale(){
		for(int i=0 ; i<image.getWidth() ; i++){
			for(int j=0 ; j<image.getHeight() ; j++){
				Color c = new Color(image.getRGB(i, j));
				int averageColor = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
				changeColor(i,j,new Color(averageColor, averageColor, averageColor));
			}
		}
	}
	
	private void removeColor(Color n){
		int r = 1 - Math.min(1, n.getRed());
		int g = 1 - Math.min(1, n.getGreen());
		int b = 1 - Math.min(1, n.getBlue());
		
		for(int i=0 ; i<image.getWidth() ; i++){
			for(int j=0 ; j<image.getHeight() ; j++){
				Color c = new Color(image.getRGB(i, j));
				changeColor(i,j,new Color(c.getRed() * r, c.getGreen() * g, c.getBlue() * b));
			}
		}
	}
	
//	private void changeColors(String s){
//		for(int i=0 ; i<image.getWidth() ; i++){
//			for(int j=0 ; j<image.getHeight() ; j++){
//				Color c = new Color(image.getRGB(i, j));
//				GVector3f n = new GVector3f();
//				switch(s){
//					case "rbg":
//						n = new GVector3f(c.getRed(),c.getBlue() , c.getGreen() );
//						break;
//					case "rgb":
//						n = new GVector3f(c.getRed(), c.getGreen() ,c.getBlue() );
//						break;
//					case "brg":
//						n = new GVector3f(c.getBlue(), c.getRed(), c.getGreen() );
//						break;
//					case "bgr":
//						n = new GVector3f(c.getBlue() , c.getGreen(), c.getRed() );
//						break;
//					case "gbr":
//						n = new GVector3f(c.getGreen(), c.getBlue(), c.getRed() );
//						break;
//					case "grb":
//						n = new GVector3f( c.getGreen(), c.getRed(), c.getBlue() );
//						break;
//				}
//				
//				changeColor(i,j,new Color(n.getXi(), n.getYi(), n.getZi()));
//			}
//		}
//	}
	
	private void changeColors(int s){
		for(int i=0 ; i<image.getWidth() ; i++){
			for(int j=0 ; j<image.getHeight() ; j++){
				Color c = new Color(image.getRGB(i, j));
				GVector3f n = new GVector3f();
				switch(s){
					case 0:
						n = new GVector3f(c.getRed(),c.getBlue() , c.getGreen() );
						break;
					case 1:
						n = new GVector3f(c.getRed(), c.getGreen() ,c.getBlue() );
						break;
					case 2:
						n = new GVector3f(c.getBlue(), c.getRed(), c.getGreen() );
						break;
					case 3:
						n = new GVector3f(c.getBlue() , c.getGreen(), c.getRed() );
						break;
					case 4:
						n = new GVector3f(c.getGreen(), c.getBlue(), c.getRed() );
						break;
					case 5:
						n = new GVector3f( c.getGreen(), c.getRed(), c.getBlue() );
						break;
				}
				
				changeColor(i,j,new Color(n.getXi(), n.getYi(), n.getZi()));
			}
		}
	}
	
	private void randomize(int value, boolean together){
		for(int i=0 ; i<image.getWidth() ; i++){
			for(int j=0 ; j<image.getHeight() ; j++){
				Color c = new Color(image.getRGB(i, j));
				float r, g, b;
				if(together){
					float val = (float)((Math.random()-0.5) * value);
					r = c.getRed() + val;
					g = c.getGreen() + val;
					b = c.getBlue() + val;	
				}
				else{
					r = c.getRed() + (float)((Math.random()-0.5) * value);
					g = c.getGreen() + (float)((Math.random()-0.5) * value);
					b = c.getBlue() + (float)((Math.random()-0.5) * value);	
				}
				changeColor(i,j,new Color((int)GMath.between(r, 0, 255), (int)GMath.between(g, 0, 255), (int)GMath.between(b, 0, 255)));
			}
		}
	}
	
	private void addColor(GVector3f n){
		for(int i=0 ; i<image.getWidth() ; i++){
			for(int j=0 ; j<image.getHeight() ; j++){
				Color c = new Color(image.getRGB(i, j));
				
				int r = c.getRed() + n.getXi();
				int g = c.getGreen() + n.getYi();
				int b = c.getBlue() + n.getZi();
				changeColor(i,j,new Color((int)GMath.between(r, 0, 255), (int)GMath.between(g, 0, 255), (int)GMath.between(b, 0, 255)));
			}
		}
	}
	
	private void updateColor() {
		for(int i=0 ; i<image.getWidth() ; i++){
			for(int j=0 ; j<image.getHeight() ; j++){
				Color c = new Color(defaultImage.getRGB(i, j));
				
				int r = c.getRed() + basicColor.getXi();
				int g = c.getGreen() + basicColor.getYi();
				int b = c.getBlue() + basicColor.getZi();
				changeColor(i,j,new Color((int)GMath.between(r, 0, 255), (int)GMath.between(g, 0, 255), (int)GMath.between(b, 0, 255)));
			}
		}
	}
	
	//CREATORS
	
	private JSlider createSlider(ChangeListener c, int width, int min, int max, int act){
//		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
//		labelTable.put(0,new JLabel(min+""));
//		labelTable.put(50,new JLabel("0.5"));
//		labelTable.put(100,new JLabel("1.0"));
//		labelTable.put(150,new JLabel("1.5"));
//		labelTable.put(200,new JLabel(max+""));
		
		JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, act);
//		slider.setPaintTicks(true);
//		slider.setPaintLabels(true);
		slider.setPreferredSize(new Dimension(width-80,30));
//		slider.setLabelTable(labelTable);
		slider.addChangeListener(c);
		
		return slider;
	}
	
	private JPanel createImageView(){
		JPanel imagePanel = new JPanel(){
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
				 super.paintComponent(g);
				 g.drawImage(image, 0, 0, (int)getPreferredSize().getWidth(), (int)getPreferredSize().getHeight(),null);
			}
		};
		imagePanel.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
		
		JPanel imageView = new JPanel();
		imageView.setBackground(Color.red);
		imageView.add(imagePanel);
		return imageView;
	}
	
	private JPanel createPanel(Component... components){
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(PANEL_WIDTH - 20, 50));
		for(Component c : components){
			panel.add(c);
		}
		return panel;
	}
	
	private JPanel createRightView(){
		topView = new JPanel();
		topView.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_WIDTH*3));
		JButton b = new JButton("èiernobiele");
		b.addActionListener(makeGreyScale);
		topView.add(createPanel(b));
		
		b = new JButton("removeColor");
		b.addActionListener(removeColor);;
		topView.add(createPanel(b));
		
		b = new JButton("reset");
		b.addActionListener(reset);
		topView.add(createPanel(b));
		
		b = new JButton("inverse");
		b.addActionListener(inverse);
		topView.add(createPanel(b));
		
		b = new JButton("change colors");
		b.addActionListener(changeColors);
		topView.add(createPanel(b));
		
		randomizeTogether = new JCheckBox("spolu");
		randomizeValue = new JSpinner(new SpinnerNumberModel(0,-512,512,5));
		b = new JButton("randomize");
		b.addActionListener(randomize);
		topView.add(createPanel(b, randomizeTogether, randomizeValue));
		
		
		intensityValue = new JSpinner(new SpinnerNumberModel(0,-255,255,5));
		b = new JButton("addColor");
		b.addActionListener(addColor);
		topView.add(createPanel(b, intensityValue));
		
		b = new JButton("nieco");
		b.addActionListener(nieco);
		topView.add(createPanel(b));
		
		topView.add(createPanel(new JLabel("Luminence"), createSlider(changeLuminance, PANEL_WIDTH, -255, 255, 0)));
		
		topView.add(createPanel(new JLabel("RED"), createSlider(changeColorRed, PANEL_WIDTH, -255, 255, 0)));
		topView.add(createPanel(new JLabel("GREEN"), createSlider(changeColorGreen, PANEL_WIDTH, -255, 255, 0)));
		topView.add(createPanel(new JLabel("BLUE"), createSlider(changeColorBlue, PANEL_WIDTH, -255, 255, 0)));
		
		return topView;
	}
	
	//STATIC
	
	//OTHERS
	
	private static BufferedImage deepCopy(BufferedImage bi) {
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
		}
	
	//MAIN
	
	public static void main(String[] args){
		new GImage("texture.png");
	}
}
