package GameEngine;

import GameEngine.components.Camera;
import GameEngine.components.DirectionalLight;
import GameEngine.components.FreeLook;
import GameEngine.components.MeshRenderer;
import GameEngine.core.Game;
import GameEngine.core.GameObject;
import GameEngine.core.Quaternion;
import GameEngine.core.Vector2f;
import GameEngine.core.Vector3f;
import GameEngine.rendering.Material;
import GameEngine.rendering.Mesh;
import GameEngine.rendering.Texture;
import GameEngine.rendering.Vertex;
import GameEngine.rendering.Window;

public class VoxelGame extends Game{
	public void init(){
		Material material = new Material();//ResourceLoader.loadTexture("dirt.jpg"),new Vector3f(1,1,1));
		material.addTexture("diffuse", new Texture("stallTexture.png"));
		material.addFloat("specularIntensity", 1);
		material.addFloat("specularPower", 8);
		
//		material.addTexture("diffuse", new Texture("stallTexture.png"));
//		material.addFloat("specularIntensity",0.4f);
//		material.addFloat("specularPower", 16);
		float size = 10;
		Vertex[] vertices = new Vertex[]{new Vertex(new Vector3f(-size  ,-1.0f ,-size  ), new Vector2f(0.0f, 0.0f)),
									 	 new Vertex(new Vector3f(-size   ,-1.0f , size*3), new Vector2f(0.0f, size)),
									 	 new Vertex(new Vector3f( size*3 ,-1.0f ,-size  ), new Vector2f(size, 0.0f)),
									 	 new Vertex(new Vector3f( size*3 ,-1.0f , size*3), new Vector2f(size, size))};
		
		int[] indices = new int[]{0,1,2,2,1,3};
		//Mesh mesh = new Mesh(vertices, indices, true);
		Mesh mesh = new Mesh("box.obj");
		
		//add mesh1
		addObject(new GameObject().addComponent(new MeshRenderer(mesh,material)));
		
		//add directional Light
		GameObject dirLight = new GameObject().addComponent(new DirectionalLight(new Vector3f(0,0,1),0.4f,new Vector3f(1,1,1)));
		addObject(dirLight);
		
		//add camera
		GameObject cam = new GameObject().addComponent(new FreeLook()).addComponent(new Camera((float)Math.toRadians(70),(float)Window.getWidth()/(float)Window.getHeight(),0.1f,1000f));
		addObject(cam);
	}
}
