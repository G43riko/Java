package game.vilage.view.component;

import game.vilage.view.OtherWindow;

import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelRight extends JPanel{
	private static final long serialVersionUID = 1L;
	DefaultListModel<String> listModel;
	JList<String> list;
	OtherWindow parent;
	private HashMap<String, Integer> ides = new HashMap<String, Integer>(); 
	
	//ACTIONS
	
	private ListSelectionListener listChanges = new ListSelectionListener(){
		@SuppressWarnings("unchecked")
		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting()){
				if(ides.containsKey((String)((JList<String>)e.getSource()).getSelectedValue()))
					parent.changeSelectQuest(ides.get((String)((JList<String>)e.getSource()).getSelectedValue()));
			}
		}
	};
	
	//CONSTRUCTORS
	
	public PanelRight(OtherWindow parent){
		this.parent = parent;
		setPreferredSize(new Dimension(200,200));
		list = new JList<String>(listModel = new DefaultListModel<String>());
		list.setPreferredSize(getPreferredSize());
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.addListSelectionListener(listChanges);
		list.setLayoutOrientation(JList.VERTICAL);
		add(list);
	}
	
	//OTHERS
	
	public void addString(String s, int id){
		ides.put(s, id);
		listModel.addElement(s);
	}
	
	public void removeString(String s){
		listModel.removeElement(s);
	}

	public void clear() {
		listModel.removeAllElements();
		ides.clear();
	}
}
