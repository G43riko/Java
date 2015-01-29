package com.voxel.component;

import com.voxel.rendering.RenderingEngine;
import com.voxel.rendering.material.Material;
import com.voxel.rendering.mesh.Mesh;
import com.voxel.rendering.shader.Shader;

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

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
}
