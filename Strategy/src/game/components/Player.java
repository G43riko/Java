package game.components;

import game.object.BasicPlayer;
import game.object.Camera;
import game.world.Block;
import game.world.World;
import glib.util.vector.GVector3f;

public class Player extends BasicPlayer{
	public final static float HEIGHT = 1;
	public final static float MAX_FALLING_SPEED = 0.5f;
	public final static float JUMP_STRENG = 0.6f;
	public final static GVector3f GRAVITY = new GVector3f(0,0.04f,0);
	
	public Player(GVector3f position, World world, Camera camera){
		super(position);
		this.world = world;
		this.camera = camera;
		camera.setPosition(position);
	}
	
	public Player(World world, Camera camera){
		super(null);
		camera.setPosition(world.getMaxSize().div(new GVector3f(2,1,2)));
		setPosition(camera.getPosition());
		this.world = world;
		this.camera = camera;
	}
	
	public void update(){
		move(dir);
		dir = dir.mul(new GVector3f(0,1,0));
		
		Block b = getFloor();
		if(b != null && dir.getY()<0){
			dir = new GVector3f();
			camera.getPosition().setY(b.getPosition().getY()+Block.HEIGHT+HEIGHT);
		}
		if(camera.getPosition().getY() < HEIGHT){
			camera.getPosition().setY(HEIGHT);
			dir = new GVector3f(); 
		}
		if(b == null || dir.getY() > 0){
			if(dir.getY() != 0){
				camera.move(new GVector3f(0, dir.getY(), 0));
				
				dir.setY((float)(Math.max(-MAX_FALLING_SPEED, Math.min(MAX_FALLING_SPEED, dir.getY()))));
			}
			if(camera.getPosition().getY()>HEIGHT)
				dir = dir.sub(GRAVITY);
		}
	}
}
