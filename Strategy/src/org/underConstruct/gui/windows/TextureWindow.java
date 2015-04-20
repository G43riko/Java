package org.underConstruct.gui.windows;

import glib.util.Loader;
import glib.util.vector.GVector4f;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.underConstruct.gui.component.Handlers;

public class TextureWindow extends JPanel{
	private static final long serialVersionUID = 1L;
	private final static int IMAGE_WIDTH = 480;
	private final static int IMAGE_HEIGHT = 480;
	
	private final static int PANEL_WIDTH = 200;
	
	private BufferedImage image;
	private BufferedImage defaultImage;
	private JPanel rightView;
	private JPanel imageView;
	private JSpinner randomizeValue;
	private JSpinner ostrost;
	
	private Handlers functions = new Handlers();
	
	private GVector4f minimum = new GVector4f(0, 0, 0, 0);
	private GVector4f maximum = new GVector4f(255, 255, 255, 255);
	private GVector4f colors = new GVector4f(0,0,0,0);

	private TextureWindow toto;
	
	//CONSTRUCTORS
	
	public TextureWindow(String fileName){
		try {
			image = ImageIO.read(Loader.loadFile("res/textures/"+fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		toto = this;
		defaultImage = deepCopy(image);
		
		
		setLayout(new BorderLayout());
		
		add(createImageView(),BorderLayout.CENTER);
		JScrollPane p = new JScrollPane(createRightView(),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		p.setPreferredSize(new Dimension(PANEL_WIDTH , 50));
		add(p,BorderLayout.EAST);
	}
	
	//CREATORS
	
	private JPanel createImageView(){
		JPanel imagePanel = new JPanel(){
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
				 super.paintComponent(g);
				 g.drawImage(image, 0, 0, (int)getPreferredSize().getWidth(), (int)getPreferredSize().getHeight(),null);
			}
		};
		imagePanel.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
		imagePanel.setBackground(Color.red);
		imageView = new JPanel();
		imageView.add(imagePanel);
		return imageView;
	}
	
	private JPanel createRightView(){
		rightView = new JPanel();
		JButton b;
		
		b = new JButton("greyScale");
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				functions.greyScale(image);
				toto.repaint();
			}
		});
		rightView.add(createPanel(b));
		
		
		b = new JButton("inverse");
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				functions.inverse(image);
				toto.repaint();
			}
		});

		rightView.add(createPanel(b));
		
		
		b = new JButton("reset");
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				image = deepCopy(defaultImage);
				toto.repaint();
			}
		});
		rightView.add(createPanel(b));
		
		b = new JButton("randomize");
		randomizeValue = new JSpinner(new SpinnerNumberModel(0,-255,255,5));
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				functions.randomize(image = deepCopy(defaultImage), (int)randomizeValue.getValue());
				toto.repaint();
			}
		});
		rightView.add(createPanel(b,randomizeValue));
		
		b = new JButton("ostrosù");
		ostrost = new JSpinner(new SpinnerNumberModel(0,-50,50,1));
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				functions.ostrost(image,deepCopy(image), (int)ostrost.getValue());
				toto.repaint();
			}
		});
		rightView.add(createPanel(b,ostrost));
		
		
		b = new JButton("colors");
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
//				image = deepCopy(defaultImage);
				JOptionPane.showMessageDialog(toto,showColor(), "Color editor", JOptionPane.PLAIN_MESSAGE);
				toto.repaint();
			}
		});
		rightView.add(createPanel(b));
		
		b = new JButton("maxAndMin");
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
//				image = deepCopy(defaultImage);
				JOptionPane.showMessageDialog(toto,showMaxAndMin()," Maximum and minimum of color", JOptionPane.PLAIN_MESSAGE);
				toto.repaint();
			}
		});
		rightView.add(createPanel(b));
		
		rightView.setPreferredSize(rightView.getSize());
		return rightView;
	}
	
	private JPanel createPanel(Component... components){
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(PANEL_WIDTH-30,50));
		for(Component c : components){
			panel.add(c);
		}
		return panel;
	}
	
	//SHOWS
	
	private JPanel showColor(){
		int color = 256;
		JPanel panel = new JPanel();
		
		JSlider slider;
		
		slider = new JSlider(JSlider.HORIZONTAL, -color, color, 0);
		slider.setPreferredSize(new Dimension(PANEL_WIDTH-80,30));
		slider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				colors.setX(((JSlider)e.getSource()).getValue());
				functions.updateColors(image = deepCopy(defaultImage),colors);
				toto.repaint();
			}
		});
		panel.add(slider);
		
		slider = new JSlider(JSlider.HORIZONTAL, -color, color, 0);
		slider.setPreferredSize(new Dimension(PANEL_WIDTH-80,30));
		slider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				colors.setY(((JSlider)e.getSource()).getValue());
				functions.updateColors(image = deepCopy(defaultImage),colors);
				toto.repaint();
			}
		});
		panel.add(slider);
		
		slider = new JSlider(JSlider.HORIZONTAL, -color, color, 0);
		slider.setPreferredSize(new Dimension(PANEL_WIDTH-80,30));
		slider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				colors.setZ(((JSlider)e.getSource()).getValue());
				functions.updateColors(image = deepCopy(defaultImage),colors);
				toto.repaint();
			}
		});
		panel.add(slider);
		
		slider = new JSlider(JSlider.HORIZONTAL, -color, color, 0);
		slider.setPreferredSize(new Dimension(PANEL_WIDTH-80,30));
		slider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				colors.setW(((JSlider)e.getSource()).getValue());
				functions.updateColors(image = deepCopy(defaultImage),colors);
				toto.repaint();
			}
		});
		panel.add(slider);
		
		
		
		
		return panel;
	}
	
	private JPanel showMaxAndMin(){
		JPanel panel = new JPanel();
		JPanel line;
		
		//RED
		
		line = new JPanel();
		JSpinner redMin = new JSpinner(new SpinnerNumberModel(0,0,255,5));
		redMin.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				minimum.setX((int)((JSpinner)e.getSource()).getValue());
				functions.maxAndMin(image = deepCopy(defaultImage), maximum, minimum);
				toto.repaint();
			}
		});
		
		line.add(new JLabel("red - min: "));
		line.add(redMin);
		
		JSpinner redMax = new JSpinner(new SpinnerNumberModel(255,0,255,5));
		redMax.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				maximum.setX((int)((JSpinner)e.getSource()).getValue());
				functions.maxAndMin(image = deepCopy(defaultImage), maximum, minimum);
				toto.repaint();
			}
		});
		line.add(new JLabel("max: "));
		line.add(redMax);
		
		panel.add(line);
		
		//GREEN
		
		line = new JPanel();
		JSpinner greenMin = new JSpinner(new SpinnerNumberModel(0,0,255,5));
		greenMin.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				minimum.setY((int)((JSpinner)e.getSource()).getValue());
				functions.maxAndMin(image = deepCopy(defaultImage), maximum, minimum);
				toto.repaint();
			}
		});
		
		line.add(new JLabel("green - min: "));
		line.add(greenMin);
		
		JSpinner greenMax = new JSpinner(new SpinnerNumberModel(255,0,255,5));
		greenMax.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				maximum.setY((int)((JSpinner)e.getSource()).getValue());
				functions.maxAndMin(image = deepCopy(defaultImage), maximum, minimum);
				toto.repaint();
			}
		});
		line.add(new JLabel("max: "));
		line.add(greenMax);
		
		panel.add(line);
		
		//BLUE
		
		line = new JPanel();
		JSpinner blueMin = new JSpinner(new SpinnerNumberModel(0,0,255,5));
		blueMin.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				minimum.setZ((int)((JSpinner)e.getSource()).getValue());
				functions.maxAndMin(image = deepCopy(defaultImage), maximum, minimum);
				toto.repaint();
			}
		});
		
		line.add(new JLabel("blue - min: "));
		line.add(blueMin);
		
		JSpinner blueMax = new JSpinner(new SpinnerNumberModel(255,0,255,5));
		blueMax.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				maximum.setZ((int)((JSpinner)e.getSource()).getValue());
				functions.maxAndMin(image = deepCopy(defaultImage), maximum, minimum);
				toto.repaint();
			}
		});
		line.add(new JLabel("max: "));
		line.add(blueMax);
		
		//GLOBAL
		
		line = new JPanel();
		JSpinner globalMin = new JSpinner(new SpinnerNumberModel(0,0,255,5));
		globalMin.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				minimum.setZ((int)((JSpinner)e.getSource()).getValue());
				functions.maxAndMin(image = deepCopy(defaultImage), maximum, minimum);
				toto.repaint();
			}
		});
		
		line.add(new JLabel("global - min: "));
		line.add(globalMin);
		
		JSpinner globalMax = new JSpinner(new SpinnerNumberModel(255,0,255,5));
		globalMax.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				maximum.setZ((int)((JSpinner)e.getSource()).getValue());
				functions.maxAndMin(image = deepCopy(defaultImage), maximum, minimum);
				toto.repaint();
			}
		});
		line.add(new JLabel("max: "));
		line.add(globalMax);
		
		panel.setPreferredSize(new Dimension(250, 100));
		panel.add(line);
		
		return panel;
	}
	
	//STATIC

	private static BufferedImage deepCopy(BufferedImage bi) {
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
	//OTHERS
	
}
