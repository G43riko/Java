package GameEngine.rendering;

import GameEngine.components.BaseLight;
import GameEngine.components.PointLight;
import GameEngine.components.SpotLight;
import GameEngine.core.Matrix4f;
import GameEngine.core.ResourceLoader;
import GameEngine.core.Transform;

public class ForwardSpot extends Shader{
private static final ForwardSpot instance = new ForwardSpot();
	
	public ForwardSpot(){
		super("forward-spot");
		
		setAttribLocation("position", 0);
		setAttribLocation("texCoord", 1);
		setAttribLocation("normal", 2);	
		
		compileShader();
		
		addUniform("model");
		addUniform("MVP");
		
		addUniform("specularIntensity");
		addUniform("specularPower");
		addUniform("eyePos");

		addUniform("spotLight.pointLight.baseLight.color");
		addUniform("spotLight.pointLight.baseLight.intensity");
		addUniform("spotLight.pointLight.atten.constant");
		addUniform("spotLight.pointLight.atten.linear");
		addUniform("spotLight.pointLight.atten.exponent");
		addUniform("spotLight.pointLight.position");
		addUniform("spotLight.pointLight.range");
		
		addUniform("spotLight.direction");
		addUniform("spotLight.cutoff");
	}
	
	public void updateUniforms(Transform transform, Material material, RenderingEngine renderingEngine){
		Matrix4f worldMatrix = transform.getTransformation();
		Matrix4f projectedMatrix = renderingEngine.getMainCamera().getViewProjection().Mul(worldMatrix);
		material.getTexture("diffuse").bind();
		
//		setUniform("MVP",projectionMatrix);
//		setUniform("ambientIntensity",getRenderingEngine().getAmbientLight());
		setUniform("model",worldMatrix);
		setUniform("MVP",projectedMatrix);
		
		setUniformf("specularIntensity",material.getFloat("specularIntensity"));
		setUniformf("specularPower",material.getFloat("specularPower"));
		
		setUniform("eyePos",renderingEngine.getMainCamera().getTransform().getPosition());
		
		setUniformSpotLight("spotLight",(SpotLight)renderingEngine.getActiveLight());
		
	}

	public static ForwardSpot getInstance(){
		return instance;
	}
	
	public void setUniformBaseLight(String uniformName, BaseLight baseLight){
		setUniform(uniformName +".color",baseLight.getColor());
		setUniformf(uniformName +".intensity",baseLight.getIntensity());
	}
	
	public void setUniformPointLight(String uniformName, PointLight pointLight){
		setUniformBaseLight(uniformName +".baseLight", pointLight);
		setUniformf(uniformName +".atten.constant", pointLight.getConstant());
		setUniformf(uniformName +".atten.linear", pointLight.getLinear());
		setUniformf(uniformName +".atten.exponent", pointLight.getExponent());
		setUniform(uniformName +".position", pointLight.getTransform().getPosition());
		setUniformf(uniformName +".range", pointLight.getRange());
	}
	
	public void setUniformSpotLight(String uniformName, SpotLight spotLight){
		setUniformPointLight(uniformName +".pointLight", spotLight);
		setUniform(uniformName +".direction", spotLight.getDirection());
		setUniformf(uniformName +".cutoff", spotLight.getCutoff());
	}
}
