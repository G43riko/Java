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
	private JPanel createLeftPartOfView(){	//vytovr� lav� �as� okna
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(tPanel = new PanelTop(),BorderLayout.NORTH);
		panel.add(bPanel = new PanelBottom(this),BorderLayout.CENTER);
		return panel;
	}
		
	/**
	 * @param selectedQuest
	 */
	public void changeSelectedQuest(Quest selectedQuest) {	//zmen� aktualny quest
		bPanel.setActQuest(selectedQuest);
		tPanel.setActQuest(selectedQuest);
	}

	/**
	 * 
	 */
	public void updateQuests() {	//aktualizuje quest po pridan� alebo odobrat� questu
		rPanel.clear();
		for(Quest q : parent.getQuests()){
			rPanel.addQuest(q);
		}
	}

	/**
	 * @param finishedQuest
	 */
	public void finishQuest(int finishedQuest) {	//po dokon�en� quest
		rPanel.removeQuest(parent.getQuests().get(finishedQuest));	//vyma�e quest s prav�ho panela
		bPanel.removeAll();	//vyma�e spodn� panel
		parent.finishQuest(finishedQuest);	//po�le rodi�ovy spr�vu o dokon�en� questu
		tPanel.setVisible(false);	//zneviditeln� vrch� panel s inform�ciami o quest 
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
