package org.engine.gui.components.swing;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.engine.core.CoreEngine;

import glib.swing.GVector3Editor;

public class PointLightPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	GVector3Editor lightColor;
	GVector3Editor lightPosition;
	GVector3Editor lightAttenuation;
	CoreEngine core;
	
	public PointLightPanel(CoreEngine core){
		this.core = core;

		setPreferredSize(new Dimension(300,300));
		
		lightColor = new GVector3Editor("Color", 255, 0 , core.getRenderingEngine().getPointLight().getPointLight().getColor().mul(255));
		lightColor.addChangeListener(a ->changeColor());
		add(lightColor);
		
		lightPosition = new GVector3Editor("Position", 100, -100 , core.getRenderingEngine().getPointLight().getPointLight().getPosition());
		lightPosition.addChangeListener(a ->changePosition());
		add(lightPosition);
		
		lightAttenuation = new GVector3Editor("Attenuation", 3, 0 , core.getRenderingEngine().getPointLight().getPointLight().getAttenuation());
		lightAttenuation.addChangeListener(a ->changeAttenuation());
		add(lightAttenuation);
		
	}

	private void changeColor() {
		core.getRenderingEngine().getPointLight().getPointLight().setColor(lightColor.getValues().div(255));
	}

	private void changePosition() {
		core.getRenderingEngine().getPointLight().setPosition(lightPosition.getValues());
	}

	private void changeAttenuation() {
		core.getRenderingEngine().getPointLight().getPointLight().setAttenuation(lightAttenuation.getValues());
	}

}
