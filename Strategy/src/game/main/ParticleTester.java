package game.main;

import game.component.Camera;
import game.core.CoreGame;
import game.particle.ParticleEmmiter;
import game.rendering.RenderingEngine;
import glib.util.vector.GVector3f;

public class ParticleTester extends CoreGame{
	private static final long serialVersionUID = 1L;

	public void init() {
		setRenderingEngine(new RenderingEngine());
		setCamera(new Camera());
		ParticleEmmiter e = new ParticleEmmiter(new GVector3f(0,-1,-5), "smoke.gp");
//		e.saveToFile("smoke.gp");
		addToScene(e);
	}

}
