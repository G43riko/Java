package GameEngine.components;

import GameEngine.core.Transform;
import GameEngine.rendering.BasicShader;
import GameEngine.rendering.Material;
import GameEngine.rendering.Mesh;
import GameEngine.rendering.Shader;

public class MeshRenderer extends GameComponent{
	private Mesh mesh; 
	private Material material;
	
	public MeshRenderer(Mesh mesh, Material material){
		this.material = material;
		this.mesh = mesh;
	}
	public void render(Shader shader){
		shader.bind();
		shader.updateUniforms(getTransform(),material);
		mesh.draw();
	}
}
