package game.vilage.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import game.vilage.buldings.BasicBuilding;
import game.vilage.buldings.Buildings;
import game.vilage.quests.Quest;
import game.vilage.view.component.PanelBottom;
import game.vilage.view.component.PanelRight;
import game.vilage.view.component.PanelTop;

public class OtherWindow extends Window{
	private static final long serialVersionUID = 1L;
	
	private byte type;
	private PanelRight rPanel;
	private PanelTop tPanel;
	private PanelBottom bPanel;
	private BasicBuilding parent;
	
	//CONSTRUCTORS
	
	public OtherWindow(byte type, BasicBuilding parent){
		this.type = type;
		this.parent = parent;
		setTitle("MOS: "+Buildings.getName(type));
		
		setLayout(new BorderLayout());
		add(rPanel = new PanelRight(this),BorderLayout.EAST);
		add(createLeftPartOfView(),BorderLayout.CENTER);
	}
	
	//OTHERS
	
	private JPanel createLeftPartOfView(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(tPanel = new PanelTop(),BorderLayout.NORTH);
		panel.add(bPanel = new PanelBottom(this),BorderLayout.CENTER);
		return panel;
	}
	
	public void addQuest(byte type, int value, byte from){
	}
	
	public void changeSelectQuest(int id){
		for(Quest q : parent.getQuests()){	//prejde všetky questy
			if(q.getId() == id){	
				bPanel.setActQuest(parent.getQuests().indexOf(q));
				break;
			}
		}
	}

	public void updateQuests() {
		rPanel.clear();
		for(Quest q : parent.getQuests()){
			rPanel.addString(q.getDescription(), q.getId());
		}
	}

	public void finishQuest(int finishedQuest) {
		rPanel.removeString(parent.getQuests().get(finishedQuest).getDescription());
		bPanel.removeAll();
		bPanel.updateUI();
		parent.finishQuest(finishedQuest);
	}

	//GETTERS
	
	public BasicBuilding getParrent() {
		return parent;
	}
}
