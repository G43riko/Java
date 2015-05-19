package org.engine.gui.window;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import org.engine.gui.Gui;
import org.engine.gui.panel.PanelRight;

public class RenderWindow extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private PanelRight panelRight;
	private Gui gui;
	
	public RenderWindow(PanelRight panelRight, Gui gui){
		this.panelRight = panelRight;
		this.gui = gui;
		setPreferredSize(panelRight.getPreferredSize());
		
		gui.getCoreEngine().getRenderingEngine().getVariables().forEach((key, value) -> {
			createPanel(key, value);
		});
	}
	
	private void createPanel(String name, boolean value){
		JCheckBox j = new JCheckBox(name, value);
		j.addActionListener(a -> gui.getCoreEngine().changes.add(((JCheckBox)a.getSource()).getText()));
		add(j);
	}
}
