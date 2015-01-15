package GameEngine.components;

import GameEngine.core.util.Quaternion;
import GameEngine.core.util.Vector3f;
import GameEngine.rendering.RenderingEngine;
import GameEngine.rendering.Shader;

public class LookAt extends GameComponent
{
	private RenderingEngine renderingEngine;

	@Override
	public void update(float delta){
		if(renderingEngine != null){
			Quaternion newRot = getTransform().getLookAtRotation(renderingEngine.getMainCamera().getTransform().getTransformedPos(),
					new Vector3f(0, 1, 0));
					//GetTransform().GetRot().GetUp());

			//getTransform().setRotation(getTransform().getRotation().NLerp(newRot, delta * 5.0f, true));
			getTransform().setRotation(getTransform().getRotation().SLerp(newRot, delta * 5.0f, true));
		}
	}

	@Override
	public void render(Shader shader, RenderingEngine renderingEngine){
		this.renderingEngine = renderingEngine;
	}
}