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
import javax.xml.ws.Action;

import game.vilage.buldings.Buildings;
import game.vilage.buldings.Market;
import game.vilage.resources.Resources;
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
		@Override
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
		@Action
		public void windowActivated(WindowEvent e) {}
		public void windowClosed(WindowEvent e) {}
		public void windowClosing(WindowEvent e) {	//metoda pri zatv�ran� okna
			if(JOptionPane.showConfirmDialog(window, "Naozaj chce� skon�i�?","Posledn� �anca na n�vrat",0) == JOptionPane.YES_OPTION){	//ak chceme skon�i�
				window.dispose();	//zavrie sa okno
				market.getVillage().saveData();//ulo�� sa aktualne mno�stvo tovaru v budov�ch
				System.exit(1);	//ukon�� sa aplik�cia
			};
		}
		public void windowDeactivated(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowOpened(WindowEvent e) {}
		
	};
	
	//CONSTRUCTORS
	
	/**
	 * @param Market market
	 */
	public MarketWindow(Market market) {
		this.market = market;
		this.window = this;
		init();
	}
	
	//OTHERS
	
	/**
	 * @param void
	 * @return void
	 */
	private void init(){
		setTitle("Medieaval Online Shop");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		add(getResouceSelectors(),BorderLayout.NORTH);
		add(getBottomPanel(),BorderLayout.CENTER);
		
		addWindowListener(onExit);
	}
	
	
	/**
	 * podla typu ozn�menie vytvor� string
	 * @param type
	 * @param value
	 * @param resource
	 */
	public void appendNotice(int type, int value, byte resource){	
		switch(type){	//vyberie si aktualny typ ozn�menie
			case Market.GOODS_SHIPPED:
				appendNotice("Market: Bola odoslan� polo�ka: "+Resources.getName(resource)+" "+value+" ks");
				break;
			case Market.REQUEST_WAS_SENT:
				appendNotice("Market: �iados� o : "+Resources.getName(resource)+" "+value+" ks  bola odoslan� k: "+Buildings.getName(Resources.getBuildingFromProduct(resource)));
				break;
			case Market.GOODES_RECEIVED:
				appendNotice("Market: Bolo doru�en�ch "+value+" ks  suroviny "+Resources.getName(resource));
				break;
			default:
				appendNotice("System: Lutujeme ale nastala stra�n� chyba:");
		}
	}
	
	/**
	 * prilep� ozn�menie
	 * @param s
	 */
	public void appendNotice(String s){
		String time = new SimpleDateFormat("HH:mm  d.M.Y").format(new Date(System.currentTimeMillis()));	//prid� aktualny �as
		text.append(s+" o: "+time+"\n");	//prilep� text
	}
	
	/**
	 * aktualizuje panel s konkr�tnou surovinou
	 * @param type
	 */
	public void updateValue(byte type){
		resourcesSelectors.get(type).update(market.getResources().get(type));
	}

	//GETTERS

	/**
	 * vr�ti selector surov�n
	 * @return JPanel
	 */
	public JPanel getResouceSelectors(){
		JPanel panel = new JPanel(new GridLayout(5,1));
		
		for(Entry<Byte, Integer> e : market.getResources().entrySet()){
			resourcesSelectors.put(e.getKey(), new MarketResourceSelector(e.getKey(), e.getValue(), market));
			panel.add(resourcesSelectors.get(e.getKey()));
		}
		
		setPreferredSize(new Dimension(300,300));
		
		return panel;
	}
	
	/**
	 * vr�ti spodn� panel
	 * @return JScrollPane
	 */
	public JScrollPane getBottomPanel(){
		panel = new JScrollPane(text = new JTextArea());
		panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		

		text.setEditable(false);
		text.addMouseListener(showPopUpMenu);
		return panel;
	}
	
}
