package GameEngine;

import java.util.ArrayList;

import GameEngine.components.BaseLight;
import GameEngine.components.Camera;
import GameEngine.components.DirectionalLight;
import GameEngine.components.MeshRenderer;
import GameEngine.components.PointLight;
import GameEngine.components.SpotLight;
//import Game3D.Window;
import GameEngine.core.Game;
import GameEngine.core.GameObject;
import GameEngine.core.Quaternion;
import GameEngine.core.ResourceLoader;
import GameEngine.core.Time;
import GameEngine.core.Transform;
import GameEngine.core.Vector2f;
import GameEngine.core.Vector3f;
import GameEngine.rendering.Material;
import GameEngine.rendering.Mesh;
import GameEngine.rendering.Texture;
import GameEngine.rendering.Vertex;
import GameEngine.rendering.Window;

public class TestGame extends Game{
	public void init(){

		Material material = new Material();//ResourceLoader.loadTexture("dirt.jpg"),new Vector3f(1,1,1));
		material.addTexture("diffuse", new Texture("dirt.jpg"));
		material.addTexture("diffuse", new Texture("dirt.jpg"));
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
		
		int[] indices = new int[]{0,1,2,
								   2,1,3};
		Mesh mesh = new Mesh(vertices, indices, true);
		MeshRenderer meshRenderer = new MeshRenderer(mesh,material);
		
		
		size = 1;
		vertices = new Vertex[]{new Vertex(new Vector3f(-size  ,-1.0f ,-size  ), new Vector2f(0.0f, 0.0f)),
									 new Vertex(new Vector3f(-size   ,-1.0f , size*3), new Vector2f(0.0f, size)),
									 new Vertex(new Vector3f( size*3 ,-1.0f ,-size  ), new Vector2f(size, 0.0f)),
									 new Vertex(new Vector3f( size*3 ,-1.0f , size*3), new Vector2f(size, size))};
		
		indices = new int[]{0,1,2,
								   2,1,3};
		mesh = new Mesh(vertices, indices, true);
		
		//add mesh1
		GameObject testMesh1 = new GameObject().addComponent(new MeshRenderer(mesh,material));
		testMesh1.getTransform().getPosition().Set(0,2,0);
		testMesh1.getTransform().setRotation(new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(45)));

		//add mesh2
		GameObject testMesh2 = new GameObject().addComponent(new MeshRenderer(mesh,material));
		testMesh2.getTransform().getPosition().Set(0,0,5);
		
		//add mesh3
		GameObject testMesh3 = new GameObject().addComponent(new MeshRenderer(new Mesh("stall.obj"),material));
		testMesh3.getTransform().getPosition().Set(5,5,5);
		testMesh3.getTransform().setRotation(new Quaternion(new Vector3f(0,1,0),(float)Math.toRadians(-70f)));
		
		//add mesh4
		GameObject testMesh4 = new GameObject().addComponent(new MeshRenderer(new Mesh("stall.obj"),material));
		
		//add plane
		GameObject planeObject = new GameObject().addComponent(meshRenderer);
		planeObject.getTransform().getPosition().Set(0,-1,5);
		
		//add directional light
		GameObject directionalLightObject = new GameObject().addComponent(new DirectionalLight(new Vector3f(0,0,1),0.4f,new Vector3f(1,1,1)));
	
		//add point light
		GameObject pointLightObject = new GameObject().addComponent(new PointLight(new Vector3f(0,1,0),0.8f,new Vector3f(0.0f, 0.0f, 1.0f)));
		
		//add spot light
		GameObject spotLightObject = new GameObject().addComponent(new SpotLight(new Vector3f(0,1,1),2f,0.0f, 0.0f, 0.5f, 0.7f));
		spotLightObject.getTransform().setPosition((new Vector3f(5,0,5)));
		spotLightObject.getTransform().setRotation(new Quaternion(new Vector3f(0,1,0), (float)Math.toRadians(90)));
		
		//add camera
		GameObject cam = new GameObject().addComponent(new Camera((float)Math.toRadians(70),(float)Window.getWidth()/(float)Window.getHeight(),0.1f,1000f));
		
		addObject(planeObject);
		addObject(testMesh1);
		testMesh1.addChild(testMesh2);
		addObject(testMesh3);
		addObject(testMesh4);
		addObject(directionalLightObject);
		addObject(pointLightObject);
		addObject(spotLightObject);
		addObject(cam);
//		mesh2.addVertices(data2,indices2,true);
//		scena.add(mesh2);
	}
	
//	public void input(){
//		camera.input();
//		root.input();
//	}
//	
//	float temp = 0;
//	public void update(){
//		//root.getTransform().setTranslation(0,-1,5);
//		root.update();
////		temp += Time.getDelta();
////		float sinTemp = (float)Math.sin(temp);
////		//transform.setTranslation(sinTemp, 0, 5);
////		//transform.setRotation(0, sinTemp*180, 0);
////		//transform.setScale(0.7f * sinTemp, 0.7f * sinTemp, 0.7f * sinTemp);
////		pLight1.setPosition(new Vector3f(3, 1, 8.0f*(float)(sinTemp+0.5)+10));
////		pLight2.setPosition(new Vector3f(7, 1, 8.0f*(float)(Math.cos(temp)+0.5)+10));
////		
////		sLight1.getPointLight().setPosition(camera.getPos());
////		sLight1.setDirection(camera.getForward());
////		
//////		options.getAllData();
//	}
//	
//	public void render(){
//		root.render();
////		mesh.draw();
////		//RenderUtil.setClearColor(Transform.getCamera().getPos().Div(2048f).Abs());
////		shader.bind();
////		shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(),material);
////		for(Mesh obj : scena){
////			obj.draw();
////		}
//	}
	
//	public void addWindow(Options option){
//		this.options = option;
//		options.addLight(pLight1);
//	}
}
