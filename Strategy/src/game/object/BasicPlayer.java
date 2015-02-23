package game.object;

import org.lwjgl.input.Keyboard;

import game.components.Player;
import game.world.Block;
import game.world.World;
import glib.util.vector.GVector3f;

public class BasicPlayer extends GameObject{
	public BasicPlayer(GVector3f position) {
		super(position, 12);
	}

	protected World world;
	protected Camera camera;
	
	protected GVector3f dir = new GVector3f();
	
	public boolean rotate = false;
	public boolean move = false;
	
	private float minDist = 3f;
	
	private int unlockMouseKey = Keyboard.KEY_N;
	private int lockMouseKey = Keyboard.KEY_M;
	
	private int forwardKey = Keyboard.KEY_W;
	private int backKey = Keyboard.KEY_S;
	private int leftKey = Keyboard.KEY_A;
	private int rightKey = Keyboard.KEY_D;
	
//	private int upKey = Keyboard.KEY_SPACE;
//	private int downKey = Keyboard.KEY_LSHIFT;
	
	private int jumpKey = Keyboard.KEY_SPACE;
	
	public boolean checkGround(){
		return (world.getBlock(getCamera().getPosition().add(new GVector3f(0,-Player.HEIGHT,0)))!=null);
	}
	
	public Block getFloor(){
		return world.getBlock(getCamera().getPosition().add(new GVector3f(0,-Player.HEIGHT-Block.HEIGHT,0)));
	}
	
	public void move(GVector3f direction){
		GVector3f x = new GVector3f(dir.getX(), 0, 0);
		GVector3f z = new GVector3f(0, 0, dir.getZ());
		
		if(!x.isNull() && world.getBlock(getCamera().getPosition().add(x.mul(minDist)))==null){
			camera.move(x);
			move = true;
		}
		
		if(!z.isNull() && world.getBlock(getCamera().getPosition().add(z.mul(minDist)))==null){
			camera.move(z);
			move = true;
		}
	}
	
	public void input(){
		if(Keyboard.isKeyDown(forwardKey)){
			dir = dir.add(camera.getForwardVector());
//			move(camera.getForwardVector());
		}
		
		if(Keyboard.isKeyDown(backKey)){
//			move(camera.getBackVector());
			dir = dir.add(camera.getBackVector());
		}
		
		if(Keyboard.isKeyDown(leftKey)){
//			move(camera.getLeftVector());
			dir = dir.add(camera.getLeftVector());
		}
		
		if(Keyboard.isKeyDown(rightKey)){
//			move(camera.getRightVector());
			dir = dir.add(camera.getRightVector());
		}
		
//		if(Keyboard.isKeyDown(upKey)){
//			camera.goUp();
//			move = true;
//		}
		
//		if(Keyboard.isKeyDown(downKey)){
//			camera.goDown();
//			move = true;
//		}
		
		if(Keyboard.isKeyDown(jumpKey) && dir.getY()==0){
			dir = dir.add(new GVector3f(0,Player.JUMP_STRENG,0));
			move = true;
		}
		
		if(Keyboard.isKeyDown(lockMouseKey)){
			camera.lockMouse();
		}
		
		if(Keyboard.isKeyDown(unlockMouseKey)){
			camera.unlockMouse();
		}
		
		if(camera.isMouseLocked()){
			rotate = camera.mouseMove();
		}
		
		if(move || rotate){
			camera.updateForward();
			move = rotate = false;
		}
	}

	
	public World getWorld() {
		return world;
	}

	
	public Camera getCamera() {
		return camera;
	}
}
