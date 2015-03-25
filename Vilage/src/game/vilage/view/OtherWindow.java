package game.vilage.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import game.vilage.buldings.Buildings;
import game.vilage.view.component.PanelBottom;
import game.vilage.view.component.PanelRight;
import game.vilage.view.component.PanelTop;

public class OtherWindow extends Window{
	private byte type;
	public OtherWindow(byte type){
		this.type = type;
		
		setTitle("MOS: "+Buildings.getName(type));
		
		setLayout(new BorderLayout());

		add(new PanelRight(),BorderLayout.EAST);
		add(createLeftPartOfView(),BorderLayout.CENTER);
	}
	
	private JPanel createLeftPartOfView(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new PanelTop(),BorderLayout.NORTH);
		panel.add(new PanelBottom(),BorderLayout.CENTER);
		return panel;
	}
}
