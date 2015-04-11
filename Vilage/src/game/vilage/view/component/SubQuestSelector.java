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
	private JButton selectButton;
	private JButton selectRandomButton;
	private PanelBottom parent;
	private List<Byte> subEvents;
	private SubQuestSelector toto;
	private byte subQuest;

	//ACTIONS
	
	private	ActionListener selectValue = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(list.getSelectedIndex()+1 == list.getItemCount()){	//ak bola ozna�en� mo�nost splnenia subquestu
				parent.finishSubQuest(PanelBottom.SUCCESS, subQuest, (byte)0, list.getSelectedIndex());	//ozn�mi sa rodi�ovy �e sa splnil quest
				parent.showNext(toto);	//povol� manipul�cia s dal��m subquestom
			}
			else if(list.getSelectedIndex() > 0)	//in�� ak bola vybran� mo�nos� neuspechu questu
				parent.finishSubQuest(PanelBottom.FAILURE, subQuest, subEvents.get(list.getSelectedIndex()-1), list.getSelectedIndex());	//povie sa to rodi�ovy
		}
	};
	
	private	ActionListener selectRandomValue = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			int num = list.getItemCount() - 1;
			num = (int)(Math.random() * num) + 1;
			list.setSelectedIndex(num);
			selectButton.doClick();
		}
	};
	
	//CONSTRUCTORS

	public SubQuestSelector(byte subQuest, PanelBottom parent, int selectedValue){
		this.parent = parent;
		this.subQuest = subQuest;
		this.toto = this;
		
		subEvents = SubEvents.getSubEventsfromsubQuest(subQuest);	//na��ta udalosti k aktualnemu subquestu
		
		init(selectedValue);	//inicializuje prvky gui
	}
	
	//OTHERS
	
	private void init(int selectedValue){
		add(new JLabel(SubQuests.getName(subQuest)));	//pr�da n�zov subquestu
		
		String[] texts = new String[subEvents.size()+2];	//vytvor� pole pre nazvy udalost�
		texts[0] = "Vyberte mo�nos�";	//prirad� prv� mo�nost
		for(int i=0 ; i<subEvents.size() ; i++){	//prejde v�etk�mi udalostami
			texts[i+1] = SubEvents.getName(subEvents.get(i));	//pr�da do pola jej n�zov
		}
		texts[subEvents.size()+1] = "Hotovo";	//prid� posledn� mo�nos�
		
		add(list = new JComboBox<String>(texts));	//prid� jcombobox zo zoznamom udalosti
		list.setSelectedIndex(selectedValue);	//nastav� sa vybran� mo�nos� podla aktualneho stavu questu
		
		add(selectButton = new JButton("potvrdi�"));	//prid� sa tla��tko potvrdi�
		selectButton.addActionListener(selectValue);	//prid� sa k tla��tku actionListener
		
		add(selectRandomButton = new JButton("Lucky day"));	//prid� sa tla��tko n�hodn� v�ber
		selectRandomButton.addActionListener(selectRandomValue);	//prid� sa k tla��tku actionListener
	}
	
	
	public void makeEnable(boolean value){
		selectButton.setVisible(value);
		selectRandomButton.setVisible(value);
		list.setEnabled(value);
	}

	//GETTERS
	
	public int getSelectedIndex(){
		return list.getSelectedIndex();
	}
}
