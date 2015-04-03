package game.vilage.view.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import game.vilage.quests.SubEvents;
import game.vilage.quests.SubQuests;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SubQuestSelector extends JPanel{
	private static final long serialVersionUID = 1L;

	private JComboBox<String> list;
	private JButton button;
	private PanelBottom parent;
	private List<Byte> subEvents;
	private SubQuestSelector toto;
	private byte subQuest;

	//ACTIONS
	
	private	ActionListener selectValue = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(list.getSelectedIndex()+1 == list.getItemCount()){
				parent.finishSubQuest(PanelBottom.SUCCESS, subQuest, (byte)0, list.getSelectedIndex());
				parent.showNext(toto);
				list.setEnabled(false);
			}
			else if(list.getSelectedIndex() > 0)
				parent.finishSubQuest(PanelBottom.FAILURE, subQuest, subEvents.get(list.getSelectedIndex()-1), list.getSelectedIndex());
		}
	};
	
	//CONSTRUCTORS

	public SubQuestSelector(byte subQuest, PanelBottom parent, int selectedValue){
		this.parent = parent;
		this.subQuest = subQuest;
		toto = this;
		add(new JLabel(SubQuests.getName(subQuest)));
		
		subEvents = SubEvents.getSubEventsfromsubQuest(subQuest);
		String[] texts = new String[subEvents.size()+2];
		texts[0] = "Vyberte moûnosù";
		for(int i=0 ; i<subEvents.size() ; i++){
			texts[i+1] = SubEvents.getName(subEvents.get(i));
		}
		texts[subEvents.size()+1] = "Hotovo";
		
		add(list = new JComboBox<String>(texts));
		list.setSelectedIndex(selectedValue);

		add(button = new JButton("potvrdiù"));
		button.addActionListener(selectValue);
	}
	
	//OTHERS
	
	public void makeEnable(boolean value){
		button.setVisible(value);
		list.setEnabled(value);
	}

	//GETTERS
	
	public int getSelectedIndex(){
		return list.getSelectedIndex();
	}
}
