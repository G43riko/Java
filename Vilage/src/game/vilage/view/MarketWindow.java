package game.vilage.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Map.Entry;









import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import game.vilage.buldings.Market;
import game.vilage.resources.Suroviny;
import game.vilage.view.component.ResourceSelector;

public class MarketWindow extends Window{
	private Market market;
	private JScrollPane panel;
	
	public MarketWindow(Market market) {
		this.market = market;
		init();
		setTitle("Medieaval Online Shop");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(getResouceSelectors(),BorderLayout.NORTH);
		add(getBottomPanel(),BorderLayout.CENTER);
	}
	
	public JPanel getResouceSelectors(){
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(4,1));
		
		for(Entry<Byte, Integer> e : market.getResources().entrySet())
			panel.add(new ResourceSelector(e.getKey(), e.getValue()));
		
		setPreferredSize(new Dimension(300,300));
		
		return panel;
	}
	
	public JScrollPane getBottomPanel(){
		panel = new JScrollPane();
		
		panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		return panel;
	}
}
