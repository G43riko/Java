package menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class BMenu extends JPanel{
	
	public void init(){	
		//setBackground(Color.green);
		setPreferredSize(new Dimension(100,100));
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		String[] petStrings = { "Grass", "Dirt", "Water", "Rock" };

		//Create the combo box, select item at index 4.
		//Indices start at 0, so 4 specifies the pig.
		JComboBox petList = new JComboBox(petStrings);
		petList.setSelectedIndex(3);
		add(petList);
	}
	
}
