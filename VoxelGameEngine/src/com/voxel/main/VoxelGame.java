package com.voxel.main;

//import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

import com.voxel.component.light.DirectionalLight;
import com.voxel.component.light.PointLight;
import com.voxel.component.viewAndMovement.Camera;
import com.voxel.component.viewAndMovement.FreeLook;
import com.voxel.component.viewAndMovement.FreeMove;
import com.voxel.core.Game;
import com.voxel.core.GameObject;
//import com.voxel.rendering.Vertex;
//import com.voxel.rendering.mesh.Mesh;
import com.voxel.world.World;

import org.lwjgl.opengl.Display;

public class VoxelGame extends Game{
	private static final long serialVersionUID = 1L;
	public static boolean isLoading = false;
	
	public void init(){
//		float size = 10;
//		Vertex[] vertices = new Vertex[]{new Vertex(new GVector3f(-size  ,-1.0f ,-size  ), new GVector2f(0.0f, 0.0f)),
//									 	 new Vertex(new GVector3f(-size   ,-1.0f , size*3), new GVector2f(0.0f, size)),
//									 	 new Vertex(new GVector3f( size*3 ,-1.0f ,-size  ), new GVector2f(size, 0.0f)),
//									 	 new Vertex(new GVector3f( size*3 ,-1.0f , size*3), new GVector2f(size, size))};
//		int[] indices = new int[]{0,1,2,
//								   2,1,3};
//		Mesh mesh = new Mesh(vertices, indices, true);
//		Mesh mesh = new Mesh("monkey3.obj");
		
		
		GameObject cam = new GameObject("camera").addComponent(new FreeMove(0.1f)).addComponent(new FreeLook(0.3f)).addComponent(new Camera((float)Math.toRadians(70),(float)Display.getWidth()/(float)Display.getHeight(),0.1f,1000f));
		cam.getTransform().setPosition(new GVector3f(-2,1.8f,-2));
//		Material material = new Material();
//		material.addTexture("diffuse", new Texture("dirt.jpg"));
//		
//		GameObject testMesh = new GameObject("").addComponent(new MeshRenderer(mesh,material));
//		testMesh.getTransform().setPosition(new GVector3f(5,0,5));
//		testMesh.getTransform().setRotation(new GQuaternion(new GVector3f(0,1,0),(float)Math.toRadians(-70f)));
		
//		GameObject pointLightObject = new GameObject("Point Light").addComponent(new PointLight(new GVector3f(0.5,0,1.1),5f,new GVector3f(0.0f, 0.0f, 1.0f)));
//		pointLightObject.getTransform().setPosition(new GVector3f(5,5,5));
//		addObject(pointLightObject);
		
		GameObject directionalLightObject = new GameObject("Dir.Light").addComponent(new DirectionalLight(new GVector3f(0.1f,0.1f,0.1f),0.4f,new GVector3f(1,20,1)));
		addObject(directionalLightObject);
		
		//addObject(new Block(2,5,2,1));
		World w = new World();
		addObject(w);
		
//		addObject(testMesh);
		addObject(cam);
	}
}
