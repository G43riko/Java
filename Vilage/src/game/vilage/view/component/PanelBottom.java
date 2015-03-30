package game.vilage.view.component;

import game.vilage.view.OtherWindow;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Map.Entry;

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
	}
	
	//OTHERS
	
	public void addSubQuest(SubQuestSelector s){
		add(s);
		s.makeEnable(subQuests.isEmpty());
		subQuests.add(s);
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
	
	public void finishSubQuest(byte type, byte subQuest, byte subEvent) {
		parent.getParrent().finishSubQuest(type == SUCCESS, subQuest, subEvent);
		parent.getParrent().getQuests().get(actQuest).getSubQestsProgress().put(subQuest, type == SUCCESS);
	}

	public void clear() {
		for(SubQuestSelector box : subQuests){
			box.removeAll();
			remove(box);
		}
		subQuests.clear();
	}

	//SETTERS
	
	public void setActQuest(int actQuest) {
		setVisible(false);
		removeAll();
		clear();
		this.actQuest = actQuest;
		for(Entry<Byte, Boolean> e : parent.getParrent().getQuests().get(actQuest).getSubQestsProgress().entrySet()){
			addSubQuest(new SubQuestSelector(e.getKey(),this,e.getValue()));
		}
		setVisible(true);
	}
}
