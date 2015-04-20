package org.engine.water;

import glib.util.vector.GMatrix4f;
import glib.util.vector.GVector3f;

import org.engine.component.GameComponent;
import org.engine.rendeing.model.Model;
import org.engine.util.Maths;

public class Water extends GameComponent{
	public Water(GVector3f position) {
		super(position, GameComponent.WATER);
	}

	private Model model;

	public Model getModel() {
		return model;
	}
	
	public GMatrix4f getTransformationMatrix() {
		return Maths.MatrixToGMatrix(Maths.createTransformationMatrix(getPosition(), new GVector3f(), getScale()));
	}
}
