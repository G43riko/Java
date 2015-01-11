package GameEngine.components;

import GameEngine.rendering.Material;
import GameEngine.rendering.Mesh;
import GameEngine.rendering.RenderingEngine;
import GameEngine.rendering.Shader;

public class MeshRenderer extends GameComponent{
	private Mesh mesh; 
	private Material material;
	
	public MeshRenderer(Mesh mesh, Material material){
		this.material = material;
		this.mesh = mesh;
	}
	
	public void render(Shader shader, RenderingEngine renderingEngine){
		shader.bind();
		shader.updateUniforms(getTransform(),material,renderingEngine);
		mesh.draw();
	}
}
