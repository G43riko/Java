package game.main;

import org.engine.particles.ParticleEmmiter;
import org.engine.rendeing.RenderingEngine;

import game.component.CameraStrategy;
import game.core.CoreGame;
import glib.util.vector.GVector3f;

public class ParticleTester extends CoreGame{
	private static final long serialVersionUID = 1L;

	public void init() {
		setRenderingEngine(new RenderingEngine());
		setCamera(new CameraStrategy());
		ParticleEmmiter e = new ParticleEmmiter(new GVector3f(0,-1,-5), "smoke.gp");
//		e.saveToFile("smoke.gp");
		addToScene(e);
	}

}
