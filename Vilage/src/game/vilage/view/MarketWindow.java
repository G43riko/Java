package game.vilage.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import game.vilage.buldings.Buildings;
import game.vilage.buldings.Market;
import game.vilage.resources.Suroviny;
import game.vilage.view.component.MarketResourceSelector;

public class MarketWindow extends Window{
	private static final long serialVersionUID = 1L;
	
	private Market market;
	private JScrollPane panel;
	private JTextArea text;
	private HashMap<Byte, MarketResourceSelector> resourcesSelectors = new HashMap<Byte, MarketResourceSelector>();
	
	//CONSTRUCTORS
	
	public MarketWindow(Market market) {
		this.market = market;
		init();
	}
	
	//OTHERS
	
	private void init(){
		setTitle("Medieaval Online Shop");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(getResouceSelectors(),BorderLayout.NORTH);
		add(getBottomPanel(),BorderLayout.CENTER);
	}
	
	
	public void appendNotice(int type, int value, byte resource){
		switch(type){
			case Market.GOODS_SHIPPED:
				appendNotice("Market: Bola odoslan· poloûka: "+Suroviny.getName(resource)+" "+value+" ks");
				break;
			case Market.REQUEST_WAS_SENT:
				appendNotice("Market: éiadosù o : "+Suroviny.getName(resource)+" "+value+" ks  bola odoslan· k: "+Buildings.getName(Suroviny.getBuildingFromProduct(resource)));
				break;
			case Market.GOODES_RECEIVED:
				appendNotice("Market: Bolo doruËen˝ch "+value+" ks  suroviny "+Suroviny.getName(resource));
				break;
			default:
				appendNotice("System: Lutujeme ale nastala straön· chyba:");
		}
		
	}
	
	public void appendNotice(String s){
		String time = new SimpleDateFormat("HH:mm  d.M.Y").format(new Date(System.currentTimeMillis()));
		text.append(s+" o: "+time+"\n");
		
	}
	
	public void updateValue(byte type){
		resourcesSelectors.get(type).Update(market.getResources().get(type));
	}

	//GETTERS

	public JPanel getResouceSelectors(){
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(4,1));
		
		for(Entry<Byte, Integer> e : market.getResources().entrySet()){
			resourcesSelectors.put(e.getKey(), new MarketResourceSelector(e.getKey(), e.getValue(), market));
			panel.add(resourcesSelectors.get(e.getKey()));
		}
		
		setPreferredSize(new Dimension(300,300));
		
		return panel;
	}
	
	public JScrollPane getBottomPanel(){
		panel = new JScrollPane(text = new JTextArea());
		panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		

		text.setEditable(false);
		
		return panel;
	}
	
}
