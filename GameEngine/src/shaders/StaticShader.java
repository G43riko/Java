package shaders;

import lights.Light;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import utils.Maths;
import entities.Camerka;

public class StaticShader extends ShaderProgram{

	private static final String VERTEX_FILE = "vertexShader.txt";
	private static final String FRAGMENT_FILE = "fragmentShader.txt";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	
	private int location_lightPosition;
	private int location_lightColor;
	
	private int location_changeColor;
	private int location_color;
	private int location_typeOfView;
	
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		super.bindAttribute(2, "normal");
		
	}

	@Override
	protected void getAllUniformsLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		
		location_lightPosition = super.getUniformLocation("lightPosition");
		location_lightColor = super.getUniformLocation("lightColor");
		
		location_changeColor = super.getUniformLocation("changeColor");
		location_color = super.getUniformLocation("color");
		location_typeOfView = super.getUniformLocation("typeOfView");
		
	}
	
	
	public void loadLight(Light light){
		super.loadVector(location_lightPosition, light.getPosition());
		super.loadVector(location_lightColor, light.getColor());
	}
	
	// nasledujuce prepÌsaù do jedneho
	public void loadTransformationMatrix(Matrix4f matrix){
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadViewMatrix(Camerka camera){
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix){
		super.loadMatrix(location_projectionMatrix, matrix);
	}
	
	public void loadChangeColor(boolean val){
		super.loadInt(location_changeColor, val?1:0);
	}
	
	public void loadColor(Vector3f color){
		super.loadVector(location_color, color);
	}
	
	public void loadTypeOfView(int type){
		super.loadInt(location_typeOfView, type);
	}

}
