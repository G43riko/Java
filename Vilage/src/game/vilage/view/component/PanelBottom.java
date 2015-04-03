package game.vilage.view.component;

import game.vilage.quests.Quest;
import game.vilage.view.OtherWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBottom extends JPanel{
	private static final long serialVersionUID = 1L;
	public final static byte FAILURE = 0;
	public final static byte SUCCESS = 1;
	
	private OtherWindow parent;
	
	private int actQuest;
	private ArrayList<SubQuestSelector> subQuests = new ArrayList<SubQuestSelector>();
	
	//CONSTRUCTORS
	
	public PanelBottom(OtherWindow parent){
		this.parent = parent;
		setPreferredSize(new Dimension(200,200));
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	//OTHERS
	
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
				else{
					finishQuest();
				}
			}
	}

	private void finishQuest() {
		parent.finishQuest(actQuest);
	}
	
	public void finishSubQuest(byte type, byte subQuest, byte subEvent, int index) {
		parent.getParrent().finishSubQuest(type == SUCCESS, subQuest, subEvent);
		
		Quest q = parent.getParrent().getQuests().get(actQuest); 
		q.getSubQestsProgress().put(subQuest, index);
		q.completeSubQuest();
		parent.getTopPanel().upadeProgress(q.getCompletedSubQuests());
	}

	private void clear() {
		for(SubQuestSelector box : subQuests){
			box.removeAll();
			remove(box);
		}
		subQuests.clear();
	}

	//SETTERS
	
	public void setActQuest(Quest actQuest) {
		this.actQuest = parent.getParrent().getQuests().indexOf(actQuest);
		setVisible(false);
		removeAll();
		clear();
		boolean isEnable = true;
		for(Entry<Byte, Integer> e : actQuest.getSubQestsProgress().entrySet()){		
			if(e.getValue() > 0 && isEnable){
				addSubQuest(new SubQuestSelector(e.getKey(),this,e.getValue())).makeEnable(false);
			}
			else if(e.getValue() == 0){
				if(isEnable){
					addSubQuest(new SubQuestSelector(e.getKey(),this,e.getValue())).makeEnable(true);
					isEnable = false;
				}
				else
					addSubQuest(new SubQuestSelector(e.getKey(),this,e.getValue())).makeEnable(false);
			}
		}
		JButton b = new JButton("dokonËiù");
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				finishQuest();
			}
		});
		add(b);
		setVisible(true);
	}
}
