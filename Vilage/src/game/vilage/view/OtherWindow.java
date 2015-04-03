package game.vilage.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import game.vilage.buldings.BasicBuilding;
import game.vilage.buldings.Buildings;
import game.vilage.quests.Quest;
import game.vilage.view.component.PanelBottom;
import game.vilage.view.component.PanelRight;
import game.vilage.view.component.PanelTop;
import game.vilage.view.component.ResourcesPanel;

public class OtherWindow extends Window{
	private static final long serialVersionUID = 1L;
	
	private PanelRight rPanel;
	private PanelTop tPanel;
	private PanelBottom bPanel;
	private BasicBuilding parent;
	private ResourcesPanel resources;
	
	//CONSTRUCTORS
	
	public OtherWindow( BasicBuilding parent){
		this.parent = parent;
		init();
	}
	
	//OTHERS
	
	public void init(){
		setTitle("MOS: "+Buildings.getName(parent.getType()));
		setLayout(new BorderLayout());
		
		add(resources = new ResourcesPanel(getParrent()),BorderLayout.NORTH);
		add(rPanel = new PanelRight(this),BorderLayout.EAST);
		add(createLeftPartOfView(),BorderLayout.CENTER);
	}
	
	private JPanel createLeftPartOfView(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(tPanel = new PanelTop(),BorderLayout.NORTH);
		panel.add(bPanel = new PanelBottom(this),BorderLayout.CENTER);
		return panel;
	}
		
	public void changeSelectedQuest(Quest selectedQuest) {
		bPanel.setActQuest(selectedQuest);
		tPanel.setActQuest(selectedQuest);
	}

	public void updateQuests() {
		rPanel.clear();
		for(Quest q : parent.getQuests()){
			rPanel.addQuest(q);
		}
	}

	public void finishQuest(int finishedQuest) {
		rPanel.removeQuest(parent.getQuests().get(finishedQuest));
		bPanel.removeAll();
		bPanel.updateUI();
		parent.finishQuest(finishedQuest);
		tPanel.setVisible(false);
	}

	public void updateResourcePanel() {
		resources.upateResources();
	}
	
	//GETTERS
	
	public BasicBuilding getParrent() {
		return parent;
	}

	public PanelTop getTopPanel() {
		return tPanel;
	}
}
