package org.underConstruct.main;

import org.engine.particles.ParticleEmmiter;
import org.strategy.component.CameraStrategy;
import org.strategy.core.CoreGame;
import org.strategy.rendering.RenderingEngineStrategy;

import glib.util.vector.GVector3f;

public class ParticleTester extends CoreGame{
	private static final long serialVersionUID = 1L;

	public void init() {
		setRenderingEngine(new RenderingEngineStrategy());
		setCamera(new CameraStrategy());
		ParticleEmmiter e = new ParticleEmmiter(new GVector3f(0,-1,-5), "smoke.gp");
//		e.saveToFile("smoke.gp");
		addToScene(e);
	}

}
