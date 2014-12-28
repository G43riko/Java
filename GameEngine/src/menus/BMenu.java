package menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entities.Camerka;

public class BMenu extends JPanel{
	private JPanel cameraWindow;
	private JLabel[] cameraData;
	private JButton cameraReset;
	
	public void init(){	
		//setBackground(Color.green);
		setPreferredSize(new Dimension(100,100));
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		String[] petStrings = { "Grass", "Dirt", "Water", "Rock" };

		//Create the combo box, select item at index 4.
		//Indices start at 0, so 4 specifies the pig.
		JComboBox petList = new JComboBox(petStrings);
		petList.setSelectedIndex(3);
		add(initCameraWindow());

		add(petList);
	}
	
	private JPanel initCameraWindow(){
		cameraData = new JLabel[6];
		
		cameraWindow = new JPanel();
		cameraWindow.setLayout(new GridBagLayout());
		
		JPanel helper = new JPanel();
		helper.setLayout(new FlowLayout());
		helper.add(new JLabel("p. x:"));
		cameraData[0] = new JLabel("0");
		helper.add(cameraData[0]);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		cameraWindow.add(helper, c);
		
		helper = new JPanel();
		helper.setLayout(new FlowLayout());
		helper.add(new JLabel("p. y:"));
		cameraData[1] = new JLabel("0");
		helper.add(cameraData[1]);
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		cameraWindow.add(helper, c);
		
		helper = new JPanel();
		helper.setLayout(new FlowLayout());
		helper.add(new JLabel("p. z:"));
		cameraData[2] = new JLabel("0");
		helper.add(cameraData[2]);
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		cameraWindow.add(helper, c);
		
		helper = new JPanel();
		helper.setLayout(new FlowLayout());
		helper.add(new JLabel("r. x:"));
		cameraData[3] = new JLabel("0");
		helper.add(cameraData[3]);
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		cameraWindow.add(helper, c);
		
		helper = new JPanel();
		helper.setLayout(new FlowLayout());
		helper.add(new JLabel("r. y:"));
		cameraData[4] = new JLabel("0");
		helper.add(cameraData[4]);
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		cameraWindow.add(helper, c);
		
		helper = new JPanel();
		helper.setLayout(new FlowLayout());
		helper.add(new JLabel("r. z:"));
		cameraData[5] = new JLabel("0");
		helper.add(cameraData[5]);
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		cameraWindow.add(helper, c);
		
		
		helper = new JPanel();
		helper.setLayout(new FlowLayout());
		cameraReset = new JButton("reset");
		cameraReset.setPreferredSize(new Dimension(140,20));
		helper.add(cameraReset);
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		cameraWindow.add(helper, c);
		return cameraWindow;
	}
	
	public void updateCameraWindow(Camerka camerka){
		cameraData[0].setText(String.valueOf((int)camerka.getPosition().x));
		cameraData[1].setText(String.valueOf((int)camerka.getPosition().y));
		cameraData[2].setText(String.valueOf((int)camerka.getPosition().z));
		cameraData[3].setText(String.valueOf((int)camerka.getPitch()));
		cameraData[4].setText(String.valueOf((int)camerka.getYaw()));
		cameraData[5].setText(String.valueOf((int)camerka.getRoll()));
	}
	
}
