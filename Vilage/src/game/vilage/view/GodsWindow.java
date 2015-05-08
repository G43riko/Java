package game.vilage.view;

import game.vilage.Village;
import game.vilage.resources.Resources;

import java.awt.FlowLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GodsWindow extends Window{
	private static final long serialVersionUID = 1L;
	
	private Village village;
	private Date date;
	private JButton addRandomEventButton;
	private JLabel time;
	
	private JSpinner timeDelay;
	
	public GodsWindow(Village village){
		this.village = village;
		setSize(250,100);
		setTitle("Options");
		setLayout(new FlowLayout());
		setVisible(true);
		setResizable(false);
		setLocation(0, 0);
		
		date = new Date(System.currentTimeMillis());
		time = new JLabel("èas: "+date.toString());
		add(time);
		
		timeDelay = new JSpinner(new SpinnerNumberModel(1000,0,2000,100));
		timeDelay.addChangeListener(a->
			village.setDelay((int)timeDelay.getValue())
		);
		add(timeDelay);
		
		add(addRandomEventButton = new JButton("kúp nieèo"));
		addRandomEventButton.addActionListener(a -> buySomething());
	}

	public void update(){
		date.setTime(date.getTime()+1000);
		time.setText("èas: "+date.toString());
	};
	
	public void addDay(){
//		date.setD
	}
	
	private void buySomething() {
		village.getMarket().wantBuy(Resources.getRandResource(), (int)(Math.random()*100));
	}
}
