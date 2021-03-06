package com.voxel.component.viewAndMovement;

import glib.util.vector.GVector3f;

import com.voxel.component.GameComponent;
import com.voxel.core.Input;

public class FreeMove extends GameComponent{
	private float speed;
	private int forwardKey;
	private int backKey;
	private int leftKey;
	private int RightKey;
	private int upKey;
	private int downKey;
	private boolean vertical;
	
	public FreeMove(float speed){
		this(speed,Input.KEY_W,Input.KEY_S,Input.KEY_A,Input.KEY_D);
	}
	public FreeMove(float speed,int forwardKey, int backKey, int leftKey, int RightKey){
		this(speed,Input.KEY_W,Input.KEY_S,Input.KEY_A,Input.KEY_D,Input.KEY_SPACE,Input.KEY_LSHIFT);
	}
	
	public FreeMove(float speed,int forwardKey, int backKey, int leftKey, int RightKey,int upKey, int downKey){
		this.speed = speed;
		this.forwardKey = forwardKey;
		this.backKey = backKey;
		this.leftKey = leftKey;
		this.RightKey = RightKey;
		this.upKey = upKey;
		this.downKey = downKey;
		this.vertical = false;
	}
	
	public void move(GVector3f dir, float amt){
		getTransform().setPosition(getTransform().getPosition().add(dir.mul(amt)));
	}
	
	public void input(){
		float delta = 1;
		float movAmt = (float)(speed * delta);
		
		if(Input.getKey(forwardKey)){
			if(vertical)
				move(getTransform().getRotation().getForward(),movAmt);
			else
				move(getTransform().getRotation().getRight().cross(new GVector3f(0,1,0)),movAmt);
		}
		if(Input.getKey(backKey)){
			if(vertical)
				move(getTransform().getRotation().getForward(),-movAmt);
			else
				move(getTransform().getRotation().getLeft().cross(new GVector3f(0,1,0)),movAmt);
		}
		if(Input.getKey(leftKey)){
			move(getTransform().getRotation().getLeft(),movAmt);
		}
		if(Input.getKey(RightKey)){
			move(getTransform().getRotation().getRight(),movAmt);
		}
		if(Input.getKey(upKey)){
			move(new GVector3f(0,1,0),movAmt);
		}
		if(Input.getKey(downKey)){
			move(new GVector3f(0,1,0),-movAmt);
		}
		
	}
}
