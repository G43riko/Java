package GameEngine;

import static org.lwjgl.opengl.GL20.glUniform1f;

public class PhongShader extends Shader {
	private static final int MAX_POINTS_LIGHTS = 4; 
	
	private static Vector3f ambientLight = new Vector3f(0.1f,0.1f,0.1f);
	private static DirectionalLight directionalLight = new DirectionalLight(new BaseLight(new Vector3f(1,1,1),0),new Vector3f(0,0,0));
	private static PointLight[] pointLights = new PointLight[]{};
	
	public PhongShader(){
		super();
		
		addVertexShader(ResourceLoader.loadShader("phongVertex.vs"));
		addFragmentShader(ResourceLoader.loadShader("phongFragment.fs"));
		compileShader();
		
		addUniform("transform");
		addUniform("transformProjected");
		addUniform("baseColor");
		addUniform("ambientLight");
		
		addUniform("specularIntensity");
		addUniform("specularPower");
		addUniform("eyePos");
		
		addUniform("directionalLight.base.color");
		addUniform("directionalLight.base.intensity");
		addUniform("directionalLight.direction");
		
		for(int i=0 ; i<MAX_POINTS_LIGHTS ; i++){
			addUniform("pointLights["+ i +"].baseLight.color");
			addUniform("pointLights["+ i +"].baseLight.intensity");
			addUniform("pointLights["+ i +"].atten.constant");
			addUniform("pointLights["+ i +"].atten.linear");
			addUniform("pointLights["+ i +"].atten.exponent");
			addUniform("pointLights["+ i +"].position");
		}
	}
	
	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material){
		if(material.getTexture() != null){
			material.getTexture().bind();
		}
		else{
			RenderUtil.unbindTextures();
		}
		
		setUniform("transformProjected",projectedMatrix);
		setUniform("transform",worldMatrix);
		setUniform("baseColor",material.getColor());
		
		setUniform("ambientLight",ambientLight);
		setUniform("directionalLight",directionalLight);
		
		for(int i=0 ; i<pointLights.length ; i++){
			setUniform("pointLights["+i+"]",pointLights[i]);
		}
		setUniformf("specularIntensity",material.getSpecularIntensity());
		setUniformf("specularPower",material.getSpecularPower());
		
		setUniform("eyePos",Transform.getCamera().getPos());
	}

	public static Vector3f getAmbientLight() {
		return ambientLight;
	}

	public static void setAmbientLight(Vector3f ambientLight) {
		PhongShader.ambientLight = ambientLight;
	}
	
	public static void setDirectionalLight(DirectionalLight directionalLight){
		PhongShader.directionalLight = directionalLight;
	}
	
	public static void setPointLight(PointLight[] pointLights){
		
		if(pointLights.length > MAX_POINTS_LIGHTS){
			System.out.println("vela svetiel. Maximum svetiel je "+MAX_POINTS_LIGHTS);
			System.exit(1);
		}
		PhongShader.pointLights = pointLights;
	}
	
	public void setUniform(String uniformName, BaseLight baseLight){
		setUniform(uniformName +".color",baseLight.getColor());
		setUniformf(uniformName +".intensity",baseLight.getIntensity());
	}
	
	public void setUniform(String uniformName, DirectionalLight directionalLight){
		setUniform(uniformName +".base", directionalLight.getBase());
		setUniform(uniformName +".direction", directionalLight.getDirection());
	}
	
	public void setUniform(String uniformName, PointLight pointLight){
		setUniform(uniformName +".baseLight", pointLight.getBaseLight());
		setUniformf(uniformName +".atten.constant", pointLight.getAttenuation().getContstant());
		setUniformf(uniformName +".atten.linear", pointLight.getAttenuation().getLinear());
		setUniformf(uniformName +".atten.exponent", pointLight.getAttenuation().getExponent());
		setUniform(uniformName +".position", pointLight.getPosition());
	}
}
