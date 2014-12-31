package GameEngine;

import java.util.ArrayList;

import javax.swing.JButton;

import GameEngine.core.MainComponents;
import GameEngine.core.Options;
import GameEngine.core.ResourceLoader;
import GameEngine.core.Time;
import GameEngine.core.Transform;
import GameEngine.core.Vector2f;
import GameEngine.core.Vector3f;
import GameEngine.rendering.Attenuation;
import GameEngine.rendering.BaseLight;
import GameEngine.rendering.Camera;
import GameEngine.rendering.DirectionalLight;
import GameEngine.rendering.Material;
import GameEngine.rendering.Mesh;
import GameEngine.rendering.PhongShader;
import GameEngine.rendering.PointLight;
import GameEngine.rendering.RenderUtil;
import GameEngine.rendering.SpotLight;
import GameEngine.rendering.Vertex;

 
public class Game {
	private Mesh mesh,mesh2;
	private PhongShader shader;
	private Transform transform;
	private Camera camera;
	private ArrayList<Mesh> scena = new ArrayList<Mesh>();
	private Material material;
	private Options options;
	private float temp = 0.0f;
	
	PointLight pLight1 = new PointLight(new BaseLight(new Vector3f(1,0,0),0.8f),new Attenuation(0.0f, 0.0f, 1.0f),new Vector3f(-2,1,5f),5);
	PointLight pLight2 = new PointLight(new BaseLight(new Vector3f(0,0,1),0.8f),new Attenuation(0.0f, 0.0f, 1.0f),new Vector3f(2,1,7f),5);
	
	SpotLight sLight1 = new SpotLight(new PointLight(new BaseLight(new Vector3f(0,1,1),0.8f),new Attenuation(0.0f, 0.0f, 1.0f),new Vector3f(-2,0,5f),30),
									  new Vector3f(1,1,1),0.7f);
	
	public Game(){
		//options = options;
		mesh = new Mesh();
		mesh2 = new Mesh();
		//mesh = ResourceLoader.loadMesh("box_triangel.obj");
		material = new Material(ResourceLoader.loadTexture("dirt.jpg"),new Vector3f(1,1,1));
		shader = new PhongShader();
		transform = new Transform();
		camera = new Camera();
		Transform.setProjection(70f, MainComponents.WIDTH, MainComponents.HEIGHT, 0.1f, 1000);
		Transform.setCamera(camera);
		PhongShader.setAmbientLight(new Vector3f(0.1f,0.1f,0.1f));
		PhongShader.setDirectionalLight( new DirectionalLight(new BaseLight(new Vector3f(1,1,1),0.8f),new Vector3f(1,1,1)));
		
		
//		PhongShader.setPointLight(new PointLight[]{pLight1, pLight2});
		PhongShader.setSpotLight(new SpotLight[]{sLight1});

		Vertex[] data = new Vertex[]{new Vertex(new Vector3f(-1.0f ,-1.0f ,0.5f), new Vector2f(0.0f, 0.0f)),
									 new Vertex(new Vector3f( 0.0f , -1.0f , -1.5f), new Vector2f(0.5f, 0.0f)),
									 new Vertex(new Vector3f( 1.0f ,-1.0f ,0.5f), new Vector2f(1.0f, 0.0f)),
									 new Vertex(new Vector3f( 0.0f ,1.0f , 0.0f), new Vector2f(0.5f, 1.0f))};
		
		int[] indices = new int[]{3,1,0,
								  2,1,3,
								  0,1,2,
								  0,2,3};
		mesh.addVertices(data,indices,true);
		scena.add(mesh);
		
		float size = 10;
		Vertex[] data2 = new Vertex[]{new Vertex(new Vector3f(-size  ,-1.0f ,-size  ), new Vector2f(0.0f, 0.0f)),
									 new Vertex(new Vector3f(-size   ,-1.0f , size*3), new Vector2f(0.0f, size)),
									 new Vertex(new Vector3f( size*3 ,-1.0f ,-size  ), new Vector2f(size, 0.0f)),
									 new Vertex(new Vector3f( size*3 ,-1.0f , size*3), new Vector2f(size, size))};
		
		int[] indices2 = new int[]{0,1,2,
								   2,1,3};
		
		mesh2.addVertices(data2,indices2,true);
		scena.add(mesh2);
	}
	
	public void input(){
		camera.input();
	}
	
	public void update(){
		temp += Time.getDelta();
		float sinTemp = (float)Math.sin(temp);
		//transform.setTranslation(sinTemp, 0, 5);
		//transform.setRotation(0, sinTemp*180, 0);
		//transform.setScale(0.7f * sinTemp, 0.7f * sinTemp, 0.7f * sinTemp);
		pLight1.setPosition(new Vector3f(3, 1, 8.0f*(float)(sinTemp+0.5)+10));
		pLight2.setPosition(new Vector3f(7, 1, 8.0f*(float)(Math.cos(temp)+0.5)+10));
		
		sLight1.getPointLight().setPosition(camera.getPos());
		sLight1.setDirection(camera.getForward());
		
		options.getAllData();
	}
	
	public void render(){
		RenderUtil.setClearColor(Transform.getCamera().getPos().Div(2048f).Abs());
		shader.bind();
		shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(),material);
		for(Mesh obj : scena){
			obj.draw();
		}
		//mesh.draw();
	}
	
	public void addWindow(Options option){
		this.options = option;
		options.addLight(pLight1);
	}
}
