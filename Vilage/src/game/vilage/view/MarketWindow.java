package game.vilage.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
	private MarketWindow window;
	private HashMap<Byte, MarketResourceSelector> resourcesSelectors = new HashMap<Byte, MarketResourceSelector>();
	
	//ACTIONS
	
	private MouseAdapter showPopUpMenu = new MouseAdapter(){
		public void mouseReleased(MouseEvent e){
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    private void doPop(MouseEvent e){
	    	JMenuItem clearNoticesItem = new JMenuItem("Clear");
	    	clearNoticesItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					text.setText("");
				}
	    	});
	    	
	    	JPopupMenu menu = new JPopupMenu();
	    	menu.add(clearNoticesItem);
	        menu.show(e.getComponent(), e.getX(), e.getY());
	    }
	};
	
	private WindowListener onExit = new WindowListener(){
		public void windowActivated(WindowEvent e) {}
		public void windowClosed(WindowEvent e) {}
		public void windowClosing(WindowEvent e) {	//metoda pri zatv·ranÌ okna
			if(JOptionPane.showConfirmDialog(window, "Naozaj chceö skonËiù?","Posledn· öanca na n·vrat",0) == JOptionPane.YES_OPTION){	//ak chceme skonËiù
				window.dispose();	//zavrie sa okno
				market.getVillage().saveData();//uloûÌ sa aktualne mnoûstvo tovaru v budov·ch
				System.exit(1);	//ukonËÌ sa aplik·cia
			};
		}
		public void windowDeactivated(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowOpened(WindowEvent e) {}
		
	};
	
	//CONSTRUCTORS
	
	public MarketWindow(Market market) {
		this.market = market;
		this.window = this;
		init();
	}
	
	//OTHERS
	
	private void init(){
		setTitle("Medieaval Online Shop");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		add(getResouceSelectors(),BorderLayout.NORTH);
		add(getBottomPanel(),BorderLayout.CENTER);
		
		addWindowListener(onExit);
	}
	
	
	public void appendNotice(int type, int value, byte resource){	//podla typu ozn·menie vytvor˝ string
		switch(type){	//vyberie si aktualny typ ozn·menie
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
	
	public void appendNotice(String s){	//prilepÌ string
		String time = new SimpleDateFormat("HH:mm  d.M.Y").format(new Date(System.currentTimeMillis()));	//prid· aktualny Ëas
		text.append(s+" o: "+time+"\n");	//prilepÌ text
	}
	
	public void updateValue(byte type){	//aktualizuje panel s konkrÈtnou surovinou
		resourcesSelectors.get(type).update(market.getResources().get(type));
	}

	//GETTERS

	public JPanel getResouceSelectors(){
		JPanel panel = new JPanel(new GridLayout(5,1));
		
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
		text.addMouseListener(showPopUpMenu);
		return panel;
	}
	
}
