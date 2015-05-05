package game.vilage.view.component;

import game.vilage.quests.Quest;
import game.vilage.view.OtherWindow;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
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
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting()){
				Quest q = ((JList<Quest>)e.getSource()).getSelectedValue();
				if(parent.getParrent().getQuests().contains(q)){
					if(parent.getParrent().getResources().canWork())
						parent.changeSelectedQuest(q);
					else{
						JOptionPane.showMessageDialog(parent, "Nedostatok surovÌn!!.", "Straön˝ error mesidû",JOptionPane.WARNING_MESSAGE);
						list.clearSelection();
					}
				}
			}
		}
	};
	
	private MouseListener mouseListener = new MouseListener(){
		@Override
		public void mouseClicked(MouseEvent e) {
			if(SwingUtilities.isRightMouseButton(e)){
				doPopup(e);
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
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
	 * inicializuje prav˝ panel
	 */
	private void init(){
		setPreferredSize(new Dimension(200,200));
		list = new JList<Quest>(listModel = new DefaultListModel<Quest>());
		list.setPreferredSize(getPreferredSize());
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		list.addListSelectionListener(listChanges);
		list.addMouseListener(mouseListener);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setBackground(parent.getBackground());
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
	 * vymaûe vöetky questy
	 */
	private void removeAllQuests() {
		int i = list.getModel().getSize() - 1;
		for(;i>=0 ; i--){
			removeQuest(i);
		}
		
	}
	
	/**
	 * vymaûe quest
	 * @param row
	 */
	private void removeQuest(int row) {
		Quest questForRemove = list.getModel().getElementAt(row);
		parent.getParrent().getQuests().remove(questForRemove);
		parent.getParrent().getVillage().appentNotice(parent.getParrent().sign()+" Quest: "+questForRemove+" bol vymazan˝");
		parent.removeQuest(questForRemove);
	}
	
	/**
	 * 
	 * @param vytvor˝ kontekxovÈ menu po kliknutÌ myöou
	 */
	private void doPopup(MouseEvent e){

    	JPopupMenu menu = new JPopupMenu();
    	
    	
		JMenuItem clearquest = new JMenuItem("Vymazaù");
		clearquest.addActionListener(a -> {
			int row = list.locationToIndex(e.getPoint());
            int n =JOptionPane.showConfirmDialog(parent, "Naozaj si prajete vymazaù quest??","Removing quest",JOptionPane.YES_NO_OPTION);
            if(n==JOptionPane.YES_OPTION)
            	removeQuest(row);
		});
    	menu.add(clearquest);
    	
    	JMenuItem clearallQuests = new JMenuItem("Vymazaù vöetky");
		clearallQuests.addActionListener(a -> {
            int n =JOptionPane.showConfirmDialog(parent, "Naozaj si prajete vymazaù vöetky questy??","Removing quest",JOptionPane.YES_NO_OPTION);
            if(n==JOptionPane.YES_OPTION)
            	removeAllQuests();
		});
    	menu.add(clearallQuests);
		
        menu.show(e.getComponent(), e.getX(), e.getY());
	}
	
	/**
	 * vymaûe cel˝ panel
	 */
	public void clear() {
		listModel.removeAllElements();
		ides.clear();
	}
}
