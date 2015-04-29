package game.vilage.view.component;

import game.vilage.Village;
import game.vilage.buldings.BasicBuilding;
import game.vilage.buldings.Buildings;
import game.vilage.quests.Quest;
import game.vilage.quests.SubQuests;
import game.vilage.view.OtherWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelBottom extends JPanel{
	private static final long serialVersionUID = 1L;
	public final static byte FAILURE = 0;
	public final static byte SUCCESS = 1;
	
	private OtherWindow parent;
	private BasicBuilding grandParrent;
	private Village grandGrandParrent;
	private int actQuest;
	private ArrayList<SubQuestSelector> subQuests = new ArrayList<SubQuestSelector>();
	
	//CONSTRUCTORS
	
	/**
	 * @param parent
	 */
	public PanelBottom(OtherWindow parent){
		this.parent = parent;
		this.grandParrent = parent.getParrent();
		this.grandGrandParrent = grandParrent.getVillage();
		init();
	}
	
	//OTHERS
	
	/**
	 * inicializuje spodný panel
	 */
	private void init(){
		setPreferredSize(new Dimension(200,200));
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * @param newSubQuest
	 * @return
	 */
	private SubQuestSelector addSubQuest(SubQuestSelector newSubQuest){
		add(newSubQuest);
		subQuests.add(newSubQuest);
		return newSubQuest;
	}

	/**
	 * zobrazí další box so subQuestom v poradí
	 * alebo
	 * dokonèí quest
	 * @param box
	 */
	public void showNext(SubQuestSelector box) {
		subQuests.stream().filter(subQuest -> subQuest.equals(box)).forEach(subQuest -> {
			subQuest.makeEnable(false);
			int i = subQuests.indexOf(subQuest);
			if(i+1 < subQuests.size())
				subQuests.get(i+1).makeEnable(true);
			else
				finishQuest();
		});
	}

	/**
	 *	oznámi rodièovy dokonèenie questu
	 */
	private void finishQuest() {
		parent.finishQuest(actQuest);
	}
	
	/**
	 * funkcia ktorá spraví všetko potrebné po dokonèení subQuestu
	 * @param type
	 * @param subQuest
	 * @param subEvent
	 * @param index
	 */
	public void finishSubQuest(byte type, byte subQuest, byte subEvent, int index) {	//dokonèí nejaký subquest
		boolean result = type == SUCCESS;
		grandParrent.finishSubQuest(result, subQuest, subEvent);	//inrmuje rodièa o skonèení subquestu
		
		Quest actualQuest = grandParrent.getQuests().get(actQuest);	//najde sa aktualny quest 
		
		if(result){	//ak skonèil úspechom
			actualQuest.getSubQestsProgress().put(subQuest, index);	//zmení sa hodnota splnených subquestov
			actualQuest.completeSubQuest();	//zvaèší sa poèet splnených subquestov
			parent.getTopPanel().upadeProgress(actualQuest.getCompletedSubQuests());	//updatne sa progressbar
		}
		else{	//ináè
			StringBuilder message = new StringBuilder();
			if(JOptionPane.showConfirmDialog(this, "Quest sa nepodaril.\n Chceš to skúsi znovu?","Neúspech",0) == JOptionPane.NO_OPTION){	//ak nechce skúsi znovu
				
				message.append(grandParrent.sign()+"nesplnil misiu: ");
				message.append(actualQuest.getTitle());
				message.append(" pre: "+Buildings.getName(actualQuest.getFrom()));
				
				grandGrandParrent.appentNotice(message.toString());	//prilepí spravu o neuspechu
				grandParrent.getQuests().remove(actQuest);//odstráni quest
				parent.dispose();	//zavrie okno
			}
			else{	//ak chce skusi znovu
				message.append(grandParrent.sign()+"skúša znovu ulohu: "+SubQuests.getName(subQuest));
				grandGrandParrent.appentNotice(message.toString());	//napíše o tom správu
			}
		}
	}

	/**
	 * vymaže celý spodný panel
	 */
	private void clear() {
		subQuests.stream().forEach(box -> {
			box.removeAll();
			remove(box);
		});
		subQuests.clear();
	}

	//SETTERS
	
	/**
	 * nastavý sa aktualny quest
	 * @param actQuest
	 */
	public void setActQuest(Quest actQuest){
		this.actQuest = grandParrent.getQuests().indexOf(actQuest);	//priradí sa id aktualneho questu
		removeAll();	//vymaže sa všetko èo tu bolo predtým
		clear();	//vymažú sa všetky subquesty
		
		boolean isEnable = true;
		for(Entry<Byte, Integer> e : actQuest.getSubQestsProgress().entrySet()){	//popridáva subquesty a nastaví im vyditelnost podla aktualneho pokroku		
			if(e.getValue() > 0 && isEnable)
				addSubQuest(new SubQuestSelector(e.getKey(),this,e.getValue())).makeEnable(false);
			else if(e.getValue() == 0){
				if(isEnable){
					addSubQuest(new SubQuestSelector(e.getKey(),this,e.getValue())).makeEnable(true);
					isEnable = false;
				}
				else
					addSubQuest(new SubQuestSelector(e.getKey(),this,e.getValue())).makeEnable(false);
			}
		}
		
		//pridá sa tlaèítko na rýchle dokonèenie questu 
		JButton b = new JButton("dokonèi");
		b.addActionListener(a -> finishQuest());
		add(b);
		repaint();
	}
}
