package game.vilage.view.component;

import game.vilage.buldings.Buildings;
import game.vilage.quests.Quest;
import game.vilage.quests.SubQuests;
import game.vilage.view.OtherWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	private int actQuest;
	private ArrayList<SubQuestSelector> subQuests = new ArrayList<SubQuestSelector>();
	
	//ACTIONS
	
	ActionListener finishQuest = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			finishQuest();
		}
	};
	
	//CONSTRUCTORS
	
	public PanelBottom(OtherWindow parent){
		this.parent = parent;
		init();
	}
	
	//OTHERS
	
	private void init(){
		setPreferredSize(new Dimension(200,200));
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private SubQuestSelector addSubQuest(SubQuestSelector s){
		add(s);
		subQuests.add(s);
		return s;
	}

	public void showNext(SubQuestSelector box) {
		for(SubQuestSelector subQuest : subQuests)
			if(subQuest.equals(box)){
				subQuest.makeEnable(false);
				int i = subQuests.indexOf(subQuest);
				if(i+1 < subQuests.size())
					subQuests.get(i+1).makeEnable(true);
				else
					finishQuest();
			}
	}

	private void finishQuest() {
		parent.finishQuest(actQuest);
	}
	
	public void finishSubQuest(byte type, byte subQuest, byte subEvent, int index) {	//dokon�� nejak� subquest
		parent.getParrent().finishSubQuest(type == SUCCESS, subQuest, subEvent);	//inrmuje rodi�a o skon�en� subquestu
		if(type == SUCCESS){	//ak skon�il �spechom
			Quest q = parent.getParrent().getQuests().get(actQuest);	//najde sa aktualny quest
			q.getSubQestsProgress().put(subQuest, index);	//zmen� sa hodnota splnen�ch subquestov
			q.completeSubQuest();	//zva�� sa po�et splnen�ch subquestov
			parent.getTopPanel().upadeProgress(q.getCompletedSubQuests());	//updatne sa progressbar
		}
		else{	//in��
			if(JOptionPane.showConfirmDialog(this, "Quest sa nepodaril.\n Chce� to sk�si� znovu?","Ne�spech",0) == JOptionPane.NO_OPTION){	//ak nechce sk�si� znovu
				Quest q = parent.getParrent().getQuests().get(actQuest);	//najde sa quest
				String msg = parent.getParrent().sign()+"nesplnil misiu: "+q.getTitle()+" pre: "+Buildings.getName(q.getFrom());	//nap�e spravu o neuspechu
				parent.getParrent().getVillage().appentNotice(msg);	//prilep� spravu o neuspechu
				parent.getParrent().getQuests().remove(actQuest);//odstr�ni quest
				parent.dispose();	//zavrie okno
			}
			else	//ak chce skusi� znovu
				parent.getParrent().getVillage().appentNotice(parent.getParrent().sign()+"sk��a znovu ulohu: "+SubQuests.getName(subQuest));	//nap�e o tom spr�vu	
		}
	}

	private void clear() {
		for(SubQuestSelector box : subQuests){
			box.removeAll();
			remove(box);
		}
		subQuests.clear();
	}

	//SETTERS
	
	public void setActQuest(Quest actQuest) {	//nastav� sa aktualny quest
		this.actQuest = parent.getParrent().getQuests().indexOf(actQuest);	//prirad� sa id aktualneho questu
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
		b.addActionListener(finishQuest);
		add(b);
		repaint();
	}
}
