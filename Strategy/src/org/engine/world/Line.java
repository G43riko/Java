package org.engine.world;

import org.engine.component.GameComponent;
import org.engine.rendeing.model.Model;
import org.engine.util.Loader;
import org.strategy.rendering.RenderingEngineStrategy;

import glib.util.vector.GVector3f;

public class Line extends GameComponent{
	protected GVector3f a;
	protected GVector3f b;
	protected Model model;

	protected GVector3f color;
	
	//CONSTRUCTORS
	
	public Line(GVector3f a, GVector3f b, Loader loader) {
		super(GameComponent.LINE);
		setPosition(a);
		this.a = a;
		this.b = b;
		this.model = createModel(loader);
		this.color = new GVector3f(1,0,1);
	}
	
	public void render(RenderingEngineStrategy renderingEngine) {
		renderingEngine.renderLine(this);
	}

	//CREATORS
	
	private Model createModel(Loader loader){
		float[] position = new float[]{0, 0, 0,
									   a.getXi()-b.getXi(), a.getYi()-b.getYi(), a.getZi()-b.getZi()};

		int[] indices = new int[]{0, 1};
		return loader.loadToVAO(position, indices);
	}
	
	//GETTERS
	
	public GVector3f getColor() {
		return color;
	}
	
	public Model getModel() {
		return model;
	}

}
