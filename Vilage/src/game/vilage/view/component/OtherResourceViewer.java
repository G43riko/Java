package game.vilage.view.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.vilage.buldings.BasicBuilding;
import game.vilage.resources.Resources;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.ws.Action;

public class OtherResourceViewer extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JLabel owned;
	private JLabel required;
	private JButton buy;
	private byte type;
	private BasicBuilding parent;
	
	//ACTIONS
	
	private ActionListener buyEvent = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			parent.wantBuy(type, Integer.parseInt(required.getText()) - Integer.parseInt(owned.getText()));
		}
	};
	
	//CONSTRUCTORS
	
	/**
	 * @param type
	 * @param need
	 * @param have
	 * @param parent
	 */
	public OtherResourceViewer(byte type, int need, int have, BasicBuilding parent){
		this.parent = parent;
		this.type = type;
		JLabel l = new JLabel(Resources.getName(type)+": ") ;
		add(l);
		add(owned = new JLabel(String.valueOf(have)));
		add(new JLabel(" / "));
		add(required = new JLabel(String.valueOf(need)));
		
		buy = new JButton("Dok˙più");
		
		buy.addActionListener(buyEvent);
		
		buy.setVisible(have < need);
		
		add(buy);
	}

	//OTHERS

	/**
	 * uptadne poËet surovÌn
	 */
	public void updateValue() {
		if(parent.getResources().getOwned().containsKey(type))
			owned.setText(String.valueOf(parent.getResources().getOwned().get(type)));
		
		buy.setVisible(parent.getResources().getOwned(type) < parent.getResources().getRequired(type));
	}
	
	/**
	 * remove all elements
	 */
	public void clear(){
		removeAll();
	}
	
	//GETTERS
	
	/**
	 * @return byte type
	 */
	public byte getType() {
		return type;
	}
	
	//SETTERS
	
	/**
	 * @param val
	 */
	public void setValue(int val){
		owned.setText(val+"");
	}
}
