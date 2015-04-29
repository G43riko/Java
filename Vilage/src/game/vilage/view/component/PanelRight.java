package game.vilage.view.component;

import game.vilage.quests.Quest;
import game.vilage.view.OtherWindow;

import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelRight extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private DefaultListModel<Quest> listModel;
	private JList<Quest> list;
	private OtherWindow parent;
	private HashMap<String, Integer> ides = new HashMap<String, Integer>(); 
	
	//ACTIONS
	
	private ListSelectionListener listChanges = new ListSelectionListener(){
		@SuppressWarnings("unchecked")
		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting()){
				Quest q = ((JList<Quest>)e.getSource()).getSelectedValue();
				if(parent.getParrent().getQuests().contains(q)){
					if(parent.getParrent().getResources().canWork())
						parent.changeSelectedQuest(q);
					else{
						JOptionPane.showMessageDialog(parent, "Nedostatok surovín!!.", "Strašný error mesidž",JOptionPane.WARNING_MESSAGE);
						list.clearSelection();
					}
				}
			}
		}
	};
	
	//CONSTRUCTORS
	
	/**
	 * @param parent
	 */
	public PanelRight(OtherWindow parent){
		this.parent = parent;
		init();
	}
	
	//OTHERS
	
	/**
	 * inicializuje pravý panel
	 */
	private void init(){
		setPreferredSize(new Dimension(200,200));
		list = new JList<Quest>(listModel = new DefaultListModel<Quest>());
		list.setPreferredSize(getPreferredSize());
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.addListSelectionListener(listChanges);
		list.setLayoutOrientation(JList.VERTICAL);
		
		add(list);
	}
	
	/**
	 * @param quest
	 */
	public void addQuest(Quest quest){
		listModel.addElement(quest);
	}
	
	/**
	 * @param quest
	 */
	public void removeQuest(Quest quest){
		listModel.removeElement(quest);
	}

	/**
	 * 
	 */
	public void clear() {
		listModel.removeAllElements();
		ides.clear();
	}
}
