package game.vilage.view;

import game.vilage.Village;
import game.vilage.resources.Resources;

import java.awt.FlowLayout;

import javax.swing.JButton;

public class GodsWindow extends Window{
	private static final long serialVersionUID = 1L;
	
	private Village village;
	
	private JButton addRandomEventButton;
	
	public GodsWindow(Village village){
		this.village = village;
		setSize(200,100);
		setTitle("Options");
		setLayout(new FlowLayout());
		setVisible(true);
		setResizable(false);
		setLocation(0, 0);
		
		
		add(addRandomEventButton = new JButton("kúp nieèo"));
		addRandomEventButton.addActionListener(a -> buySomething());
	}

	private void buySomething() {
		village.getMarket().wantBuy(Resources.getRandResource(), (int)(Math.random()*100));
	}
}
