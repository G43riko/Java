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
		
		subEventsID = SubEvents.getSubEventsfromsubQuest(subQuest);	//naËÌta udalosti k aktualnemu subquestu
		
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
		if(list.getSelectedIndex() + 1 == list.getItemCount()){	//ak bola oznaËen· moûnost splnenia subquestu
			parent.finishSubQuest(PanelBottom.SUCCESS, subQuestID, (byte)0, list.getSelectedIndex());	//ozn·mi sa rodiËovy ûe sa splnil quest
			parent.showNext(toto);	//povol˝ manipul·cia s dalöÌm subquestom
		}
		else if(list.getSelectedIndex() > 0)	//in·Ë ak bola vybran· moûnosù neuspechu questu
			parent.finishSubQuest(PanelBottom.FAILURE, subQuestID, subEventsID.get(list.getSelectedIndex()-1), list.getSelectedIndex());	//povie sa to rodiËovy
	}
	
	/**
	 * @param selectedValue
	 */
	private void init(int selectedValue){
		add(new JLabel(SubQuests.getName(subQuestID)));	//prÌda n·zov subquestu
		
		String[] texts = new String[subEventsID.size()+2];	//vytvorÌ pole pre nazvy udalostÌ
		texts[0] = "Vyberte moûnosù";	//priradÌ prv˙ moûnost
		for(int i=0 ; i<subEventsID.size() ; i++){	//prejde vöetk˝mi udalostami
			texts[i+1] = SubEvents.getName(subEventsID.get(i));	//prÌda do pola jej n·zov
		}
		texts[subEventsID.size()+1] = "Hotovo";	//prid· posledn˙ moûnosù
		
		add(list = new JComboBox<String>(texts));	//prid· jcombobox zo zoznamom udalosti
		list.setSelectedIndex(selectedValue);	//nastav˝ sa vybran· moûnosù podla aktualneho stavu questu
		
		add(selectButton = new JButton("potvrdiù"));	//prid· sa tlaËÌtko potvrdiù
		selectButton.addActionListener(a -> selectValue());	//pridÌ sa k tlaËÌtku actionListener
		
		add(selectRandomButton = new JButton("Lucky day"));	//prid· sa tlaËÌtko n·hodn˝ v˝ber
		selectRandomButton.addActionListener(a -> selectRandomValue());	//pridÌ sa k tlaËÌtku actionListener
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
