package org.engine.gui.components.swing;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.engine.core.CoreEngine;

import glib.swing.GVector3Editor;

public class DirectionalLightPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	GVector3Editor sunColor;
	GVector3Editor sunDirection;
	CoreEngine core;
	
	public DirectionalLightPanel(CoreEngine core){
		this.core = core;

		setPreferredSize(new Dimension(300,300));
		
		sunColor = new GVector3Editor("Sun color", 255, 0 , core.getRenderingEngine().getSun().getColor().mul(255));
		sunColor.addChangeListener(a ->changeColor());
		add(sunColor);
		
		sunDirection = new GVector3Editor("Sun direction", 180, -180 , core.getRenderingEngine().getSun().getRotation().mul(180));
		sunDirection.addChangeListener(a ->changeDirection());
		add(sunDirection);
		
	}
	
	private void changeColor(){
		core.getRenderingEngine().getSun().setColor(sunColor.getValues().div(255));
	}
	
	private void changeDirection(){
		core.getRenderingEngine().getSun().setRotation(sunDirection.getValues().div(180));
	}
}
