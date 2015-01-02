package GameEngine;

import java.util.ArrayList;

import GameEngine.components.BaseLight;
import GameEngine.components.DirectionalLight;
import GameEngine.components.MeshRenderer;
import GameEngine.components.PointLight;
import GameEngine.components.SpotLight;
//import Game3D.Window;
import GameEngine.core.Game;
import GameEngine.core.GameObject;
import GameEngine.core.ResourceLoader;
import GameEngine.core.Time;
import GameEngine.core.Transform;
import GameEngine.core.Vector2f;
import GameEngine.core.Vector3f;
import GameEngine.rendering.BasicShader;
import GameEngine.rendering.Camera;
import GameEngine.rendering.Material;
import GameEngine.rendering.Mesh;
import GameEngine.rendering.Vertex;

public class TestGame extends Game{
	private Mesh mesh;
//	private PhongShader shader;
	private Transform transform;
//	private Camera camera;
//	private ArrayList<Mesh> scena = new ArrayList<Mesh>();
	private Material material;
//	//private Options options;
//	private float temp = 0.0f;
//	
//	PointLight pLight1 = new PointLight(new BaseLight(new Vector3f(1,0,0),0.8f),new Attenuation(0.0f, 0.0f, 1.0f),new Vector3f(-2,1,5f),5);
//	PointLight pLight2 = new PointLight(new BaseLight(new Vector3f(0,0,1),0.8f),new Attenuation(0.0f, 0.0f, 1.0f),new Vector3f(2,1,7f),5);
//	
//	SpotLight sLight1 = new SpotLight(new PointLight(new BaseLight(new Vector3f(0,1,1),0.8f),new Attenuation(0.0f, 0.0f, 1.0f),new Vector3f(-2,0,5f),30),
//									  new Vector3f(1,1,1),0.7f);
	
	
	public void init(){
//		options = options;
		
//		mesh2 = new Mesh();
//		mesh = ResourceLoader.loadMesh("box_triangel.obj");
//		shader = new PhongShader();
//		camera = new Camera();
//		transform = new Transform();
		
//		PhongShader.setAmbientLight(new Vector3f(0.1f,0.1f,0.1f));
//		PhongShader.setDirectionalLight( new DirectionalLight(new BaseLight(new Vector3f(1,1,1),0.8f),new Vector3f(1,1,1)));
		
		
//		PhongShader.setPointLight(new PointLight[]{pLight1, pLight2});
//		PhongShader.setSpotLight(new SpotLight[]{sLight1});
//
//		Vertex[] data = new Vertex[]{new Vertex(new Vector3f(-1.0f ,-1.0f ,0.5f), new Vector2f(0.0f, 0.0f)),
//									 new Vertex(new Vector3f( 0.0f , -1.0f , -1.5f), new Vector2f(0.5f, 0.0f)),
//									 new Vertex(new Vector3f( 1.0f ,-1.0f ,0.5f), new Vector2f(1.0f, 0.0f)),
//									 new Vertex(new Vector3f( 0.0f ,1.0f , 0.0f), new Vector2f(0.5f, 1.0f))};
//		
//		int[] indices = new int[]{3,1,0,
//								  2,1,3,
//								  0,1,2,
//								  0,2,3};
//		Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
//		Transform.setCamera(camera);
//		mesh.addVertices(data,indices,true);
//		scena.add(mesh);
		
		float size = 10;
		Vertex[] vertices = new Vertex[]{new Vertex(new Vector3f(-size  ,-1.0f ,-size  ), new Vector2f(0.0f, 0.0f)),
									 new Vertex(new Vector3f(-size   ,-1.0f , size*3), new Vector2f(0.0f, size)),
									 new Vertex(new Vector3f( size*3 ,-1.0f ,-size  ), new Vector2f(size, 0.0f)),
									 new Vertex(new Vector3f( size*3 ,-1.0f , size*3), new Vector2f(size, size))};
		
		int[] indices = new int[]{0,1,2,
								   2,1,3};
		mesh = new Mesh();
		mesh.addVertices(vertices, indices, true);
		//scena.add(mesh);
		material = new Material(ResourceLoader.loadTexture("dirt.jpg"),new Vector3f(1,1,1));
		MeshRenderer meshRenderer = new MeshRenderer(mesh,material);
		
		GameObject planeObject = new GameObject();
		planeObject.addComponent(meshRenderer);
		
		
		GameObject directionalLightObject = new GameObject();
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0,0,1),0.4f,new Vector3f(1,1,1));
		directionalLightObject.addComponent(directionalLight);
		
		GameObject pointLightObject = new GameObject();
		PointLight pointLight = new PointLight(new Vector3f(0,1,0),0.8f,new Vector3f(0.0f, 0.0f, 1.0f));
		pointLightObject.addComponent(pointLight);
		
		GameObject spotLightObject = new GameObject();
		SpotLight spotLight = new SpotLight(new Vector3f(0,1,1),2f,0.0f, 0.0f, 0.5f,new Vector3f(1,1,1),0.7f);
		spotLightObject.addComponent(spotLight);
		spotLight.getTransform().setPosition(new Vector3f(5,0,5));
		
		getRootObject().addChild(planeObject);
		getRootObject().addChild(directionalLightObject);
		getRootObject().addChild(pointLightObject);
		getRootObject().addChild(spotLightObject);
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
