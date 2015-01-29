package com.voxel.world;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

import org.lwjgl.util.vector.Vector3f;

import com.voxel.component.MeshRenderer;
import com.voxel.component.viewAndMovement.Camera;
import com.voxel.core.GameObject;
import com.voxel.rendering.RenderingEngine;
import com.voxel.rendering.Vertex;
import com.voxel.rendering.material.Material;
import com.voxel.rendering.material.Texture;
import com.voxel.rendering.mesh.Mesh;
import com.voxel.rendering.shader.Shader;

public abstract class BasicBlock extends GameObject{
	private MeshRenderer to, bo, le, ri, ba, fo;
	private int repX;
	private int repY;
	private static Material[] materials = new Material[]{null, new Material("diffuse", new Texture("grass.jpg")),
															   new Material("diffuse", new Texture("dirt.jpg")),
															   new Material("diffuse", new Texture("rock.jpg")),
															   new Material("diffuse", new Texture("water.jpg"))};
	private boolean topB, bottomB, leftB, rightB, backB, forwardB,begin;
	protected boolean transparent;
	protected static Block[] neighboards;
	protected int type;
	protected int x, y, z;
	
	public BasicBlock(){
		super("Block");
		repX = repY = 1;
	}
	
	protected void addWalls(){
		begin = true;
		
		to = new MeshRenderer(addTop(),materials[type]);
		addComponent(to);
		
		bo = new MeshRenderer(addBottom(),materials[type]);
		addComponent(bo);
		
		fo = new MeshRenderer(addForward(),materials[type]);
		addComponent(fo);
		
		ba = new MeshRenderer(addBack(),materials[type]);
		addComponent(ba);
		
		ri = new MeshRenderer(addRight(),materials[type]);
		addComponent(ri);
		
		le = new MeshRenderer(addLeft(),materials[type]);
		addComponent(le);
		
	}
	
	public void render(Shader shader, RenderingEngine renderingEngine){
		GVector3f f = renderingEngine.getMainCamera().getTransform().getRotation().getForward().normalize();
		GVector3f b = new GVector3f(x*2,y*2,z*2).sub(renderingEngine.getMainCamera().getTransform().getPosition()).normalize();
		float angle = Vector3f.angle(new Vector3f(f.getX(),f.getY(),f.getZ()), new Vector3f(b.getX(),b.getY(),b.getZ()));
		if(angle > Camera.MAX_ANGLE)
			return;
		if(type==0)
			return;
		if(topB && to!=null){
			RenderingEngine.numOfRenderedBoxSides++;
			to.render(shader, renderingEngine);
		}
		if(bottomB && bo!=null){
			RenderingEngine.numOfRenderedBoxSides++;
			bo.render(shader, renderingEngine);
		}
		if(leftB && le!=null){
//			le.getMaterial().setSpecularIntensity((float)BlockInfo.getBlockInfo(type).getDouble("specular"));
//			le.getMaterial().setSpecularPower((float)BlockInfo.getBlockInfo(type).getDouble("exponent"));
//			
//			System.out.println(le.getMaterial().getSpecularIntensity());
			RenderingEngine.numOfRenderedBoxSides++;
			le.render(shader, renderingEngine);
		}
		if(rightB && ri!=null){
			RenderingEngine.numOfRenderedBoxSides++;
			ri.render(shader, renderingEngine);
		}
		if(backB && ba!=null){
			RenderingEngine.numOfRenderedBoxSides++;
			ba.render(shader, renderingEngine);
		}
		if(forwardB && fo!=null){
			RenderingEngine.numOfRenderedBoxSides++;
			fo.render(shader, renderingEngine);
		}
	}
	
	public Mesh addTop(){
		topB = begin;
		Vertex[] vertices = new Vertex[]{new Vertex(new GVector3f(-Block.WIDTH ,Block.HEIGHT ,-Block.DEPTH), new GVector2f(0.0f, 0.0f)),
									 	 new Vertex(new GVector3f(-Block.WIDTH ,Block.HEIGHT , Block.DEPTH), new GVector2f(0.0f, Block.DEPTH*repY)),
									 	 new Vertex(new GVector3f( Block.WIDTH ,Block.HEIGHT ,-Block.DEPTH), new GVector2f(Block.WIDTH*repX, 0.0f)),
									 	 new Vertex(new GVector3f( Block.WIDTH ,Block.HEIGHT , Block.DEPTH), new GVector2f(Block.WIDTH*repX,Block.DEPTH*repY))};
		int[] indices = new int[]{0,1,2,
								  2,1,3};
		return new Mesh(vertices, indices, true);
	}
	
	public Mesh addBottom(){
		bottomB = begin;
		Vertex[] vertices = new Vertex[]{new Vertex(new GVector3f(-Block.WIDTH ,-Block.HEIGHT ,-Block.DEPTH), new GVector2f(0.0f, 0.0f)),
									 	 new Vertex(new GVector3f(-Block.WIDTH ,-Block.HEIGHT , Block.DEPTH), new GVector2f(0.0f, Block.DEPTH*repY)),
									 	 new Vertex(new GVector3f( Block.WIDTH ,-Block.HEIGHT ,-Block.DEPTH), new GVector2f(Block.WIDTH*repX, 0.0f)),
									 	 new Vertex(new GVector3f( Block.WIDTH ,-Block.HEIGHT , Block.DEPTH), new GVector2f(Block.WIDTH*repX,Block.DEPTH*repY))};
		int[] indices = new int[]{2,1,0,
				  				  3,1,2};
		return new Mesh(vertices, indices, true);
	}
	
	public Mesh addForward(){
		forwardB = begin;
		Vertex[] vertices = new Vertex[]{new Vertex(new GVector3f(-Block.WIDTH ,-Block.HEIGHT ,-Block.DEPTH), new GVector2f(0.0f, 0.0f)),
									 	 new Vertex(new GVector3f(-Block.WIDTH , Block.HEIGHT ,-Block.DEPTH), new GVector2f(0.0f, Block.HEIGHT*repY)),
									 	 new Vertex(new GVector3f( Block.WIDTH ,-Block.HEIGHT ,-Block.DEPTH), new GVector2f(Block.WIDTH*repX, 0.0f)),
									 	 new Vertex(new GVector3f( Block.WIDTH , Block.HEIGHT ,-Block.DEPTH), new GVector2f(Block.WIDTH*repX,Block.HEIGHT*repY))};
		int[] indices = new int[]{0,1,2,
				  				  2,1,3};
		return new Mesh(vertices, indices, true);
	}
	
	public Mesh addBack(){
		backB = begin;
		Vertex[] vertices = new Vertex[]{new Vertex(new GVector3f(-Block.WIDTH ,-Block.HEIGHT ,Block.DEPTH), new GVector2f(0.0f, 0.0f)),
									 	 new Vertex(new GVector3f(-Block.WIDTH , Block.HEIGHT ,Block.DEPTH), new GVector2f(0.0f, Block.HEIGHT*repY)),
									 	 new Vertex(new GVector3f( Block.WIDTH ,-Block.HEIGHT ,Block.DEPTH), new GVector2f(Block.WIDTH*repX, 0.0f)),
									 	 new Vertex(new GVector3f( Block.WIDTH , Block.HEIGHT ,Block.DEPTH), new GVector2f(Block.WIDTH*repX,Block.HEIGHT*repY))};
		int[] indices = new int[]{2,1,0,
				  				  3,1,2};
		return new Mesh(vertices, indices, true);
	}
	
	public Mesh addRight(){
		rightB = begin;
		Vertex[] vertices = new Vertex[]{new Vertex(new GVector3f(Block.WIDTH ,-Block.HEIGHT ,-Block.DEPTH), new GVector2f(0.0f, 0.0f)),
									 	 new Vertex(new GVector3f(Block.WIDTH ,-Block.HEIGHT , Block.DEPTH), new GVector2f(0.0f, Block.DEPTH*repY)),
									 	 new Vertex(new GVector3f(Block.WIDTH , Block.HEIGHT ,-Block.DEPTH), new GVector2f(Block.HEIGHT*repX, 0.0f)),
									 	 new Vertex(new GVector3f(Block.WIDTH , Block.HEIGHT , Block.DEPTH), new GVector2f(Block.HEIGHT*repX,Block.DEPTH*repY))};
		int[] indices = new int[]{2,1,0,
				  				  3,1,2};
		return new Mesh(vertices, indices, true);
	}
	
	public Mesh addLeft(){
		leftB = begin;
		Vertex[] vertices = new Vertex[]{new Vertex(new GVector3f(-Block.WIDTH ,-Block.HEIGHT ,-Block.DEPTH), new GVector2f(0.0f, 0.0f)),
									 	 new Vertex(new GVector3f(-Block.WIDTH ,-Block.HEIGHT , Block.DEPTH), new GVector2f(0.0f, Block.DEPTH*repY)),
									 	 new Vertex(new GVector3f(-Block.WIDTH , Block.HEIGHT ,-Block.DEPTH), new GVector2f(Block.HEIGHT*repX, 0.0f)),
									 	 new Vertex(new GVector3f(-Block.WIDTH , Block.HEIGHT , Block.DEPTH), new GVector2f(Block.HEIGHT*repX,Block.DEPTH*repY))};
		int[] indices = new int[]{0,1,2,
				  				  2,1,3};
		return new Mesh(vertices, indices, true);
	}

	public int getType() {return type;}

	public void setType(int type) {this.type = type;}

	public boolean isTopB() {return topB;}
	public boolean isBottomB() {return bottomB;}
	public boolean isLeftB() {return leftB;}
	public boolean isRightB() {return rightB;}
	public boolean isBackB() {return backB;}
	public boolean isForwardB() {return forwardB;}
	
	public void setTopB(boolean topB) {this.topB = topB;}
	public void setBottomB(boolean bottomB) {this.bottomB = bottomB;}
	public void setLeftB(boolean leftB) {this.leftB = leftB;}
	public void setRightB(boolean rightB) {this.rightB = rightB;}
	public void setBackB(boolean backB) {this.backB = backB;}
	public void setForwardB(boolean forwardB) {this.forwardB = forwardB;}
	
}
