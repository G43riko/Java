package game.vilage.view;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
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
	
	private PanelRight rightPanel;
	private PanelTop topPanel;
	private PanelBottom bottomPanel;
	private BasicBuilding parent;
	private ResourcesPanel resources;
	
	//CONSTRUCTORS
	
	public OtherWindow( BasicBuilding parent){
		this.parent = parent;
		init();
	}
	
	//OTHERS
	
	/**
	 * inicializuje okno
	 */
	public void init(){	
		setTitle("MOS: "+Buildings.getName(parent.getType()));
		setLayout(new BorderLayout());
		
		add(resources = new ResourcesPanel(getParrent()),BorderLayout.NORTH);
		add(rightPanel = new PanelRight(this),BorderLayout.EAST);
		add(createLeftPartOfView(),BorderLayout.CENTER);
	}
	
	/**
	 * vytovrÌ lav˙ Ëasù okna
	 * @return JPanel
	 */
	private JPanel createLeftPartOfView(){	
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(topPanel = new PanelTop(),BorderLayout.NORTH);
		panel.add(bottomPanel = new PanelBottom(this),BorderLayout.CENTER);
		return panel;
	}
	
	/**
	 * zmenÌ aktualny quest
	 * @param selectedQuest 
	 */
	public void changeSelectedQuest(Quest selectedQuest) {	
		bottomPanel.setActQuest(selectedQuest);
		topPanel.setActQuest(selectedQuest);
	}

	/**
	 * aktualizuje quest po pridanÌ alebo odobratÌ questu
	 */
	public void updateQuests() {
		rightPanel.clear();
		for(Quest q : parent.getQuests()){
			rightPanel.addQuest(q);
		}
	}

	/**
	 * dokonËÌ quest
	 * @param finishedQuestId
	 */
	public void finishQuest(int finishedQuestId) {
		Quest finishedQuest = parent.getQuests().get(finishedQuestId); //n·jde 
		removeQuest(finishedQuest);
		parent.finishQuest(finishedQuestId);	//poöle rodiËovy spr·vu o dokonËen˝ questu
		JOptionPane.showMessageDialog(this, "Quest bol ˙speöne dokonËen˝.", "Quest complete",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void removeQuest(Quest quest){
		rightPanel.removeQuest(quest);	//vymaûe quest s pravÈho panela
		bottomPanel.removeAll();	//vymaûe spodn˝ panel
		topPanel.setVisible(false);	//zneviditeln˝ vrch˝ panel s inform·ciami o quest 
	}

	/**
	 * aktualizuje suroviny
	 */
	public void updateResourcePanel() {
		resources.upateResources();
	}
	
	//GETTERS
	
	public BasicBuilding getParrent() {
		return parent;
	}

	public PanelTop getTopPanel() {
		return topPanel;
	}
}
