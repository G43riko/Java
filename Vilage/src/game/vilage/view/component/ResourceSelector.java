package game.vilage.view.component;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ResourceSelector extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JLabel name;
	private JSpinner value;
	private JLabel max;
	private int mininmum = 0;
	private int maximum;
	
	public ResourceSelector(String name, int max){
		this.name = new JLabel(name);
		this.max = new JLabel(String.valueOf(max));
		maximum = max;
		init();
	}
	
	private void init(){
		value = new JSpinner(new SpinnerNumberModel(0,mininmum,maximum,1));
		value.setPreferredSize(new Dimension(40,20));
		add(name);
		add(value);
		add(max);
//		setVisible(true);
	}
}
