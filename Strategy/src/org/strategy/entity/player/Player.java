package org.strategy.entity.player;

import org.engine.physics.Enviroment;
import org.strategy.component.CameraStrategy;
import org.strategy.main.StrategyGame;
import org.strategy.world.Block;
import org.strategy.world.World;

import glib.math.GMath;
import glib.util.vector.GVector3f;

public class Player extends BasicPlayer{
	public final static float HEIGHT = 1.9f;
	public final static float MAX_FALLING_SPEED = 0.5f;
	public final static float JUMP_STRENG = 0.3f;
	public final static float MIN_DIST_FROM_BLOCK = 0.3f;
	public final static int MAX_CLICK_DIST = 100;
	
	//CONSTRUCTORS
	
	public Player(GVector3f position, World world, CameraStrategy camera){
		super(position);
		this.world = world;
		this.camera = camera;
		camera.setPosition(position);
	}
	
	public Player(World world, CameraStrategy camera){
		super(null);
		setPosition(camera.getPosition());
		this.world = world;
		this.camera = camera;
	}
	
	//OVERRIDES
	
	public void update(){
		
		//pohne pan��ikom horizont�lne
		move(dir);
		
		
		//vyma�e smerovy vektor X a Z
		dir = dir.mul(new GVector3f(0,1,0));
		
		if(StrategyGame.FLY_MODE)
			return;
		
		//zist� �i sa nach�dza podomnou podlaha 
		Block b = getFloor();
		
		//ak �no
		if(b != null){
			//a ja pad�m
			if(dir.getY()<0){
				//zastav�m sa a moja poloha sa nastav� nad kocku
				dir = new GVector3f();
				camera.getPosition().setY(b.getPosition().getY()+Block.HEIGHT*2+HEIGHT);
			}
		}
		//ak sa nach�dzam pod 0-tej urovni
		if(camera.getPosition().getY() < HEIGHT){
			//zastav� to smerovy vektor a nastav� to moju poziciu na zem
			camera.getPosition().setY(HEIGHT);
			dir = new GVector3f(); 
		}
		//ak sk��em alebo by podomnou ni� nieje
		if(b == null || dir.getY() > 0){
			//ak sa vertik�lne pohybujem
			if(dir.getY() != 0){
				//pohne kamerou a pripo��ta k ophybov�mu vektoru gravit�ciu
				camera.move(new GVector3f(0, dir.getY(), 0));
				dir.setY(GMath.between(dir.getY(), -MAX_FALLING_SPEED, MAX_FALLING_SPEED));
			}
			//ak sa nach�dzam nad 0-tou urovnou pripo��ta sa gravita�n� zrychlenie 
			if(camera.getPosition().getY()>HEIGHT)
				dir = dir.add(Enviroment.GRAVITY);
		}
		//ak sk��em
		if(dir.getY()>0){
			//a nach�dza sa nad mojou hlavou kame�
			Block h = world.getBlock(camera.getPosition().add(new GVector3f(0, MIN_DIST_FROM_BLOCK+dir.getY(), 0))); 
			if(h!=null){
				//zastav� ma to
				dir.setY(0);
			}
		}
	}

	//GETTERS
	
	public GVector3f getPosition(){
		return camera.getPosition();
	}
}
