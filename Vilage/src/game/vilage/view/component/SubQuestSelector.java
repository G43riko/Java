package game.vilage.view.component;

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
	private List<Byte> subEventsID;
	private SubQuestSelector toto;
	private byte subQuestID;
	
	//CONSTRUCTORS
	
	/**
	 * @param subQuest
	 * @param parent
	 * @param selectedSubEvent
	 */
	public SubQuestSelector(byte subQuest, PanelBottom parent, int selectedSubEvent){
		this.parent = parent;
		this.subQuestID = subQuest;
		this.toto = this;
		
		subEventsID = SubEvents.getSubEventsfromsubQuest(subQuest);	//na��ta udalosti k aktualnemu subquestu
		
		init(selectedSubEvent);	//inicializuje prvky gui
	}
	
	//OTHERS
	/**
	 * select random value after click on button
	 */
	private void selectRandomValue(){
		int num = list.getItemCount() - 1;
		num = (int)(Math.random() * num) + 1;
		list.setSelectedIndex(num);
		selectButton.doClick();
	}
	
	/**
	 * select actual value
	 */
	private void selectValue(){
		if(list.getSelectedIndex() + 1 == list.getItemCount()){	//ak bola ozna�en� mo�nost splnenia subquestu
			parent.finishSubQuest(PanelBottom.SUCCESS, subQuestID, (byte)0, list.getSelectedIndex());	//ozn�mi sa rodi�ovy �e sa splnil quest
			parent.showNext(toto);	//povol� manipul�cia s dal��m subquestom
		}
		else if(list.getSelectedIndex() > 0)	//in�� ak bola vybran� mo�nos� neuspechu questu
			parent.finishSubQuest(PanelBottom.FAILURE, subQuestID, subEventsID.get(list.getSelectedIndex()-1), list.getSelectedIndex());	//povie sa to rodi�ovy
	}
	
	/**
	 * @param selectedValue
	 */
	private void init(int selectedValue){
		add(new JLabel(SubQuests.getName(subQuestID)));	//pr�da n�zov subquestu
		
		String[] texts = new String[subEventsID.size()+2];	//vytvor� pole pre nazvy udalost�
		texts[0] = "Vyberte mo�nos�";	//prirad� prv� mo�nost
		for(int i=0 ; i<subEventsID.size() ; i++){	//prejde v�etk�mi udalostami
			texts[i+1] = SubEvents.getName(subEventsID.get(i));	//pr�da do pola jej n�zov
		}
		texts[subEventsID.size()+1] = "Hotovo";	//prid� posledn� mo�nos�
		
		add(list = new JComboBox<String>(texts));	//prid� jcombobox zo zoznamom udalosti
		list.setSelectedIndex(selectedValue);	//nastav� sa vybran� mo�nos� podla aktualneho stavu questu
		
		add(selectButton = new JButton("potvrdi�"));	//prid� sa tla��tko potvrdi�
		selectButton.addActionListener(a -> selectValue());	//prid� sa k tla��tku actionListener
		
		add(selectRandomButton = new JButton("Lucky day"));	//prid� sa tla��tko n�hodn� v�ber
		selectRandomButton.addActionListener(a -> selectRandomValue());	//prid� sa k tla��tku actionListener
	}
	
	
	/**
	 * make button enable or disable
	 * @param value
	 */
	public void makeEnable(boolean value){
		selectButton.setVisible(value);
		selectRandomButton.setVisible(value);
		list.setEnabled(value);
	}

	//GETTERS
	
	/**
	 * @return
	 */
	public int getSelectedIndex(){
		return list.getSelectedIndex();
	}
}
