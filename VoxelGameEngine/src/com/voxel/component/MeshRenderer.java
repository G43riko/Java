package com.voxel.component;

import com.voxel.render.Material;
import com.voxel.render.Mesh;
import com.voxel.render.RenderingEngine;
import com.voxel.render.Shader;

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
