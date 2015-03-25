package game.vilage.view.component;

import game.vilage.Village;
import game.vilage.resources.Suroviny;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ResourceSelector extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JLabel name;
	private JSpinner value;
	private JLabel max;
	private int mininmum = 0;
	private int maximum;
	private JButton button;
	private byte type;
	
	private ActionListener buyEvent = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			System.out.println(value.getValue()+" "+type);
		}
	};
	
	public ResourceSelector(byte type, int max){
		this.type = type;
		this.name = new JLabel(Suroviny.getName(type));
		this.max = new JLabel(String.valueOf(max));
		button = new JButton("k˙più");
		button.addActionListener(buyEvent);
		maximum = max;
		init();
	}
	
	private void init(){
		value = new JSpinner(new SpinnerNumberModel(0,mininmum,maximum*2+20,1));
		value.setPreferredSize(new Dimension(40,20));
		add(name);
		add(value);
		add(max);
		add(button);
	}
}
