package game.vilage.view.component;

import game.vilage.buldings.Market;
import game.vilage.resources.Resources;

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
	private JButton button;
	private byte type;
	private Market market;
	
	//ACTIONS
	private ActionListener buyEvent = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			market.wantBuy(type, (int)value.getValue());
		}
	};
	
	private ChangeListener changeValueEvent = new ChangeListener(){
		@Override
		public void stateChanged(ChangeEvent e) {
			try{
				button.setEnabled((int)((JSpinner)e.getSource()).getValue() > 0 );
			}
			catch(ClassCastException exception){
				float number = (float)((JSpinner)e.getSource()).getValue();
				button.setEnabled((int)number > 0);
			}
		}
	};
	
	//CONSTRUCTORS
	
	/**
	 * @param type
	 * @param max
	 * @param market
	 */
	public MarketResourceSelector(byte type, int max, Market market){
		this.market = market;
		this.type = type;
		this.name = new JLabel(Resources.getName(type));
		this.max = new JLabel(String.valueOf(max));
		button = new JButton("k�pi�");
		value = new JSpinner(new SpinnerNumberModel(0,mininmum,max*2+20,1));

		init();
		
		makeEnable(type == Resources.DREVO || type == Resources.NASTROJ);	//povol� iba k�pu surov�n ktor� s� dokon�en�
	}
	
	//OTHERS
	
	/**
	 *inicializuje panel so surovinamy
	 */
	private void init(){
		button.addActionListener(buyEvent);
//		button.setVisible(false);
		
		value.setPreferredSize(new Dimension(40,20));
		value.addChangeListener(changeValueEvent);
		add(name);
		add(value);
		add(max);
		add(button);
	}
	
	/**
	 * sfunk�n� tla��ko
	 * @param val
	 */
	private void makeEnable(boolean val){
		value.setEnabled(val);
		button.setEnabled(val);
	}
	/**
	 * updatne po�et surov�n
	 * @param maximum
	 */
	public void update(int maximum){
		max.setText(String.valueOf(maximum));
		value.setValue(0);
		((SpinnerNumberModel)value.getModel()).setMaximum(maximum*2+20);
	}
}
