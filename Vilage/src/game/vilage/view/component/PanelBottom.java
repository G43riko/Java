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
	 * inicializuje spodn� panel
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
	 * zobraz� dal�� box so subQuestom v porad�
	 * alebo
	 * dokon�� quest
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
	 *	ozn�mi rodi�ovy dokon�enie questu
	 */
	private void finishQuest() {
		parent.finishQuest(actQuest);
	}
	
	/**
	 * funkcia ktor� sprav� v�etko potrebn� po dokon�en� subQuestu
	 * @param type
	 * @param subQuest
	 * @param subEvent
	 * @param index
	 */
	public void finishSubQuest(byte type, byte subQuest, byte subEvent, int index) {	//dokon�� nejak� subquest
		boolean result = type == SUCCESS;
		grandParrent.finishSubQuest(result, subQuest, subEvent);	//inrmuje rodi�a o skon�en� subquestu
		
		Quest actualQuest = grandParrent.getQuests().get(actQuest);	//najde sa aktualny quest 
		
		if(result){	//ak skon�il �spechom
			actualQuest.getSubQestsProgress().put(subQuest, index);	//zmen� sa hodnota splnen�ch subquestov
			actualQuest.completeSubQuest();	//zva�� sa po�et splnen�ch subquestov
			parent.getTopPanel().upadeProgress(actualQuest.getCompletedSubQuests());	//updatne sa progressbar
		}
		else{	//in��
			StringBuilder message = new StringBuilder();
			if(JOptionPane.showConfirmDialog(this, "Quest sa nepodaril.\n Chce� to sk�si� znovu?","Ne�spech",0) == JOptionPane.NO_OPTION){	//ak nechce sk�si� znovu
				
				message.append(grandParrent.sign()+"nesplnil misiu: ");
				message.append(actualQuest.getTitle());
				message.append(" pre: "+Buildings.getName(actualQuest.getFrom()));
				
				grandGrandParrent.appentNotice(message.toString());	//prilep� spravu o neuspechu
				grandParrent.getQuests().remove(actQuest);//odstr�ni quest
				parent.dispose();	//zavrie okno
			}
			else{	//ak chce skusi� znovu
				message.append(grandParrent.sign()+"sk��a znovu ulohu: "+SubQuests.getName(subQuest));
				grandGrandParrent.appentNotice(message.toString());	//nap�e o tom spr�vu
			}
		}
	}

	/**
	 * vyma�e cel� spodn� panel
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
	 * nastav� sa aktualny quest
	 * @param actQuest
	 */
	public void setActQuest(Quest actQuest){
		this.actQuest = grandParrent.getQuests().indexOf(actQuest);	//prirad� sa id aktualneho questu
		removeAll();	//vyma�e sa v�etko �o tu bolo predt�m
		clear();	//vyma�� sa v�etky subquesty
		
		boolean isEnable = true;
		for(Entry<Byte, Integer> e : actQuest.getSubQestsProgress().entrySet()){	//poprid�va subquesty a nastav� im vyditelnost podla aktualneho pokroku		
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
		
		//prid� sa tla��tko na r�chle dokon�enie questu 
		JButton b = new JButton("dokon�i�");
		b.addActionListener(a -> finishQuest());
		add(b);
		repaint();
	}
}
