package org.strategy.rendering;


import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.engine.component.SkyBox;
import org.engine.light.PointLight;
import org.engine.particles.Particle;
import org.engine.rendeing.RenderingEngine;
import org.engine.rendeing.shader.GBasicShader;
import org.engine.world.Line;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.strategy.entity.enemy.BasicEnemy;
import org.strategy.entity.player.Player;
import org.strategy.object.GameObjectWithLight;
import org.strategy.world.Block;

import glib.util.vector.GVector3f;

public final class RenderingEngineStrategy extends RenderingEngine{
	
	
	
	private int view = 0;
	private boolean texture;
	 
	private PointLight sun;
	private SelectBlock selectBlock = new SelectBlock();
	
	
	public class SelectBlock{
		private Block block = null;
		private int side = -1;
		private float dist  = -1;
		
		public Block getBlock() {
			return block;
		}
		
		public int getSide() {
			return side;
		}
		
		public void reset(){
			block = null;
			dist = -1;
		}
	}
	
	//CONSTRUCTORS
	
	public RenderingEngineStrategy(){ 
		setTexture(true);
		setVariable("specular", false);
		setVariable("light", true);
		setVariable("fog", false);
	}
	
	//RENDERERS

	public void renderSky(SkyBox sky){
		if(getMainCamera() == null || view == 4){
			return;
		}
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		getShader("skyShader").bind();
		
		
		if(view == 3){
			getShader("skyShader").updateUniform("color", sky.getTexture().getAverageColor());
		}
		
		getShader("skyShader").updateUniform("transformationMatrix", sky.getTransformationMatrix());
		
		sky.getTexture().bind();

		prepareAndDraw(2, sky.getModel());
		
		disableVertex(2);
	}
	
	public void renderBlock(Block block){
		if(getMainCamera() == null || !block.isActive()){
			return;
		}
		
		getShader("entityShader").bind();
		
		getShader("entityShader").updateUniform("transformationMatrix", block.getTransformationMatrix());
		
		if(view == 3 || selectBlock.block == block){
			getShader("entityShader").updateUniform("color", block.getMaterial().getDiffuse().getAverageColor());
		}
		
		setMaterial(block.getMaterial());
		
		float blockToCamDist = block.getPosition().dist(getMainCamera().getPosition());
		boolean search = (block.isClickable() && blockToCamDist < Player.MAX_CLICK_DIST &&
						 (selectBlock.block == null || blockToCamDist < selectBlock.block.getPosition().dist(getMainCamera().getPosition())) &&
						 blockToCamDist < block.getPosition().dist(getMainCamera().getPosition().add(getMainCamera().getForward())) && view!=4);
		for(int i=0 ; i<6 ; i++){
			if(block.getSide(i)){
//				if(search && mainCamera.intersect(block.getPosition().add(block.getPoint(i, 0)), 
//				    	  						  block.getPosition().add(block.getPoint(i, 1)),  
//				    	  						  block.getPosition().add(block.getPoint(i, 2)))){
				getMainCamera().getMousePicker().update();
				if(search && GVector3f.intersectRayWithSquare(getMainCamera().getPosition(), 
						getMainCamera().getPosition().add(getMainCamera().getMousePicker().getCurrentRay().mul(1000)),
															  block.getPosition().add(block.getPoint(i, 0)), 
															  block.getPosition().add(block.getPoint(i, 1)),  
															  block.getPosition().add(block.getPoint(i, 2)))){
					float dist = block.getPosition().add(block.getPoint(i, 1).add(block.getPoint(i, 2)).div(2)).dist(getMainCamera().getPosition());
					if(selectBlock.dist<0 || selectBlock.dist>dist){
						selectBlock.dist = dist;
						selectBlock.side = i;
					}
					selectBlock.block = block;
				}
				prepareAndDraw(3, block.getModel(i));
			}
		}
			
		disableVertex(3);
	}

	public void renderParticle(Particle particle) {
		if(getMainCamera() == null){
			return;
		}
		getShader("particleShader").bind();
		
		getShader("particleShader").updateUniform("color", particle.getColor());
		
		if(particle.isFadding())
			getShader("particleShader").updateUniform("alpha", particle.getAlpha());
		
		getShader("particleShader").updateUniform("transformationMatrix",particle.getTransformationMatrix(getMainCamera().getPosition()));
		
		if(particle.getTexture() != null){
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			particle.getTexture().bind();
		}
		
		
		prepareAndDraw(2,particle.getModel());
		
		disableVertex(2);
	}
	
	public void renderLine(Line line) {
		getShader("entityShader").bind();
		getShader("entityShader").updateUniform("transformationMatrix", line.getTransformationMatrix());
		getShader("entityShader").updateUniform("color", line.getColor());
		getShader("entityShader").updateUniform("view", 3);
		
		GL30.glBindVertexArray(line.getModel().getVaoID());
		
		GL20.glEnableVertexAttribArray(0);
		
		GL11.glDrawElements(GL11.GL_LINE_STRIP, line.getModel().getVertexCount(),GL11.GL_UNSIGNED_INT, 0);
		
		GL20.glDisableVertexAttribArray(0);
		
		getShader("entityShader").updateUniform("view", view);
	}

	public void renderObjectWithLight(GameObjectWithLight object){
		if(getMainCamera() == null)
			return;
		
		
		getShader("entityShader").bind();
		
		getShader("entityShader").updateUniform("fakeLight", object.isFakeLight());
		
		getShader("entityShader").updateUniform("transformationMatrix", object.getTransformationMatrix());
		
		getShader("entityShader").updateUniform("color", new GVector3f(1,0,0));
		
		setMaterial(object.getMaterial());
		
		prepareAndDraw(3, object.getModel());
		
		disableVertex(3);
		
		getShader("entityShader").updateUniform("fakeLight", false);
	}
		
	public void renderEnemy(BasicEnemy basicEnemy) {
		if(getMainCamera() == null){
			return;
		}
		
		getShader("entityShader").bind();
		getShader("entityShader").updateUniform("transformationMatrix", basicEnemy.getTransformationMatrix());
		getShader("entityShader").updateUniform("color", new GVector3f(1,0,0));
		
		prepareAndDraw(3, basicEnemy.getModel());
		
		disableVertex(3);
	}
	
	//OTHERS
	
	public void updateLight(PointLight light, int i){
		if(i<MAX_LIGHTS){
			getShader("entityShader").updateUniform("lightPosition"+i, light.getPosition());
			getShader("entityShader").updateUniform("lightColor"+i, light.getColor());
			getShader("entityShader").updateUniform("attenuation"+i, light.getAttenuation());
			getShader("entityShader").updateUniform("range"+i, light.getRange());
		}
	}
	
	public void addLight(ArrayList<PointLight> lights){
		lights.addAll(lights);
		
		getShader("entityShader").bind();
		for(int i=0 ;i<MAX_LIGHTS ; i++){
			if(i < lights.size()){
				getShader("entityShader").updateUniform("lightPosition"+i, lights.get(i).getPosition());
				getShader("entityShader").updateUniform("lightColor"+i, lights.get(i).getColor());
				getShader("entityShader").updateUniform("attenuation"+i, lights.get(i).getAttenuation());
			}
			else{
				getShader("entityShader").updateUniform("lightPosition"+i, new GVector3f());
				getShader("entityShader").updateUniform("lightColor"+i, new GVector3f());
				getShader("entityShader").updateUniform("attenuation"+i, new GVector3f(1,0,0));
			}
		}
	}

	//GETTERS
	
	public SelectBlock getSelectBlock() {
		return selectBlock;
	}

	//SETTERS

	public void setView(int view) {
		if(this.view == view)
			return;
		
		this.view = view;
			
		for(Entry<String, GBasicShader> s : shaders.entrySet()){
			getShader(s.getKey()).bind();
			getShader(s.getKey()).updateUniform("view", view);
		}
	}
	
	public void setTexture(boolean texture){
		if(this.texture == texture)
			return;
		this.texture = texture;
		getShader("entityShader").bind();
		getShader("entityShader").updateUniform("texture", texture);
	}
	
	public void setSun(PointLight sun){
		if(this.sun == sun)
			return;
		this.sun = sun;
		
		getShader("entityShader").bind();
		
		getShader("entityShader").updateUniform("lightPosition", sun.getPosition());
		getShader("entityShader").updateUniform("lightColor", sun.getColor());
	}
	
	public void setSelectBlock(SelectBlock selectBlock) {
		this.selectBlock = selectBlock;
	}

	public void setLights(List<PointLight> lights){
		if(this.lights == lights)
			return;
		this.lights = lights;
		
		getShader("entityShader").bind();
		for(int i=0 ;i<MAX_LIGHTS ; i++){
			if(i < lights.size()){
				getShader("entityShader").updateUniform("lightPosition"+i, lights.get(i).getPosition());
				getShader("entityShader").updateUniform("lightColor"+i, lights.get(i).getColor());
				getShader("entityShader").updateUniform("attenuation"+i, lights.get(i).getAttenuation());
			}
			else{
				getShader("entityShader").updateUniform("lightPosition"+i, new GVector3f());
				getShader("entityShader").updateUniform("lightColor"+i, new GVector3f());
				getShader("entityShader").updateUniform("attenuation"+i, new GVector3f(1,0,0));
			}
		}
	}
}
