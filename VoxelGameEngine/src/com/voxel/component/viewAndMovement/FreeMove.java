package com.voxel.component.viewAndMovement;

import com.voxel.component.GameComponent;
import com.voxel.core.Input;
import com.voxel.core.util.GVector3f;

public class FreeMove extends GameComponent{
	private float speed;
	private int forwardKey;
	private int backKey;
	private int leftKey;
	private int RightKey;
	private int upKey;
	private int downKey;
	
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
	}
	
	public void move(GVector3f dir, float amt){
		getTransform().setPosition(getTransform().getPosition().add(dir.mul(amt)));
	}
	
	public void input(){
		float delta = 1;
		float movAmt = (float)(speed * delta);
		
		if(Input.getKey(forwardKey)){
			move(getTransform().getRotation().getForward(),movAmt);
		}
		if(Input.getKey(backKey)){
			move(getTransform().getRotation().getForward(),-movAmt);
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
