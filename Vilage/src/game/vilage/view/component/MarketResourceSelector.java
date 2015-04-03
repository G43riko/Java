package game.vilage.view.component;

import game.vilage.buldings.Market;
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

public class MarketResourceSelector extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JLabel name;
	private JSpinner value;
	private JLabel max;
	private int mininmum = 0;
	private int maximum;
	private JButton button;
	private byte type;
	private Market market;
	
	//ACTIONS
	
	private ActionListener buyEvent = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			market.wantBuy(type, (int)value.getValue());
		}
	};
	
	private ChangeListener changeValueEvent = new ChangeListener(){
		public void stateChanged(ChangeEvent e) {
			button.setVisible((int)((JSpinner)e.getSource()).getValue() > 0 );
		}
	};
	
	//CONSTRUCTORS
	
	public MarketResourceSelector(byte type, int max, Market market){
		this.market = market;
		this.type = type;
		this.name = new JLabel(Suroviny.getName(type));
		this.max = new JLabel(String.valueOf(max));
		button = new JButton("k˙più");
		button.addActionListener(buyEvent);
		button.setVisible(false);
		maximum = max;
		init();
	}
	
	//OTHERS
	
	private void init(){
		value = new JSpinner(new SpinnerNumberModel(0,mininmum,maximum*2+20,1));
		value.setPreferredSize(new Dimension(40,20));
		value.addChangeListener(changeValueEvent);
		add(name);
		add(value);
		add(max);
		add(button);
	}
	
	public void Update(int maximum){
		this.maximum = maximum;
		max.setText(String.valueOf(maximum));
		value.setValue(0);
	}
}
