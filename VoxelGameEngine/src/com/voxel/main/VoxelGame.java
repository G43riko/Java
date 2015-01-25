package com.voxel.main;

import glib.util.vector.GQuaternion;
import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

import com.voxel.component.MeshRenderer;
import com.voxel.component.viewAndMovement.Camera;
import com.voxel.component.viewAndMovement.FreeLook;
import com.voxel.component.viewAndMovement.FreeMove;
import com.voxel.core.Game;
import com.voxel.core.GameObject;
import com.voxel.render.Vertex;
import com.voxel.render.material.Material;
import com.voxel.render.material.Texture;
import com.voxel.render.mesh.Mesh;

import org.lwjgl.opengl.Display;

public class VoxelGame extends Game{
	private static final long serialVersionUID = 1L;
	public static boolean isLoading = false;
	
	public void init(){
		float size = 10;
		Vertex[] vertices = new Vertex[]{new Vertex(new GVector3f(-size  ,-1.0f ,-size  ), new GVector2f(0.0f, 0.0f)),
									 	 new Vertex(new GVector3f(-size   ,-1.0f , size*3), new GVector2f(0.0f, size)),
									 	 new Vertex(new GVector3f( size*3 ,-1.0f ,-size  ), new GVector2f(size, 0.0f)),
									 	 new Vertex(new GVector3f( size*3 ,-1.0f , size*3), new GVector2f(size, size))};
		int[] indices = new int[]{0,1,2,
								   2,1,3};
		Mesh mesh = new Mesh(vertices, indices, true);
		
		GameObject cam = new GameObject().addComponent(new FreeMove(10)).addComponent(new FreeLook(0.3f)).addComponent(new Camera((float)Math.toRadians(70),(float)Display.getWidth()/(float)Display.getHeight(),0.1f,1000f));
		
		Material material = new Material();
		material.addTexture("diffuse", new Texture("dirt.jpg"));
		material.addFloat("specularIntensity", 1);
		material.addFloat("specularPower", 8);
		
		GameObject testMesh = new GameObject().addComponent(new MeshRenderer(mesh,material));
		testMesh.getTransform().getPosition().set(5,5,5);
		testMesh.getTransform().setRotation(new GQuaternion(new GVector3f(0,1,0),(float)Math.toRadians(-70f)));
		
		addObject(testMesh);
		addObject(cam);
	}
}
