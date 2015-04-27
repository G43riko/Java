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
	
	/**
	 * @param parent
	 */
	public OtherWindow( BasicBuilding parent){
		this.parent = parent;
		init();
	}
	
	//OTHERS
	
	/**
	 * 
	 */
	public void init(){	//inicializuje okno
		setTitle("MOS: "+Buildings.getName(parent.getType()));
		setLayout(new BorderLayout());
		
		add(resources = new ResourcesPanel(getParrent()),BorderLayout.NORTH);
		add(rPanel = new PanelRight(this),BorderLayout.EAST);
		add(createLeftPartOfView(),BorderLayout.CENTER);
	}
	
	/**
	 * @return
	 */
	private JPanel createLeftPartOfView(){	//vytovrÌ lav˙ Ëasù okna
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(tPanel = new PanelTop(),BorderLayout.NORTH);
		panel.add(bPanel = new PanelBottom(this),BorderLayout.CENTER);
		return panel;
	}
		
	/**
	 * @param selectedQuest
	 */
	public void changeSelectedQuest(Quest selectedQuest) {	//zmenÌ aktualny quest
		bPanel.setActQuest(selectedQuest);
		tPanel.setActQuest(selectedQuest);
	}

	/**
	 * 
	 */
	public void updateQuests() {	//aktualizuje quest po pridanÌ alebo odobratÌ questu
		rPanel.clear();
		for(Quest q : parent.getQuests()){
			rPanel.addQuest(q);
		}
	}

	/**
	 * @param finishedQuest
	 */
	public void finishQuest(int finishedQuest) {	//po dokonËenÌ quest
		rPanel.removeQuest(parent.getQuests().get(finishedQuest));	//vymaûe quest s pravÈho panela
		bPanel.removeAll();	//vymaûe spodn˝ panel
		parent.finishQuest(finishedQuest);	//poöle rodiËovy spr·vu o dokonËen˝ questu
		tPanel.setVisible(false);	//zneviditeln˝ vrch˝ panel s inform·ciami o quest 
	}

	/**
	 * 
	 */
	public void updateResourcePanel() {	//aktualizuje suroviny
		resources.upateResources();
	}
	
	//GETTERS
	
	/**
	 * @return
	 */
	public BasicBuilding getParrent() {
		return parent;
	}

	/**
	 * @return
	 */
	public PanelTop getTopPanel() {
		return tPanel;
	}
}
