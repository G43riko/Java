package game.entity.enemy;

import java.util.ArrayList;

import game.entity.Bullet;
import game.entity.player.Player;
import game.object.GameObject;
import game.rendering.RenderingEngine;
import game.rendering.model.Model;
import game.util.Loader;
import game.util.OBJLoader;
import game.world.World;
import glib.shapes.threeDimensional.Box;
import glib.util.vector.GVector3f;

public class BasicEnemy extends GameObject{
	public final static int WIDTH = 1;
	public final static int HEIGHT = 1;
	public final static int DEPTH = 1;
	private GVector3f direction;
	private Model model = new Loader().loadToVAO(Box.getVertices(WIDTH, HEIGHT, DEPTH), Box.getTextures(), Box.getNormals(), Box.getIndices());
//	private static Model model = OBJLoader.loadObjModel("stall", new Loader());
	private World world;
	private float speed = 0.2f;
	private float jumpPower = 0.4f;
	private GVector3f bulletColor;
	
	
	private GameObject target;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private int shot;
	private int shotEveryNthFrame = 10;
	
	//CONSTRUCTORS
	
	public BasicEnemy(World world) {
		super(GameObject.ENEMY);
//		setScale(0.5f);
		this.world = world;
		shot = 0;
		bulletColor = new GVector3f(0, 0, 1);
		double angle = Math.random()*360;
//		setFace(-angle);
		
		angle = Math.toRadians(angle);
		
		direction = new GVector3f((float)Math.cos(angle), 0, (float)Math.sin(angle));
	}
	
	//OVERRIDES
	
	public void update(){
		//MOVE
		
		move(direction.mul(speed).mul(new GVector3f(1, 0, 1)));
		
		GVector3f size = world.getMaxSize();
		
		if(direction.getY() == 0){
			if(System.currentTimeMillis() % 1000 > 950){
				direction.setY(jumpPower);
			}
			
		}
		else{
			move(direction.mul(new GVector3f(0, 1, 0)));
			
			direction = direction.add(Player.GRAVITY);
			
			if(direction.getY() < 0 && getPosition().getY() <= 2){
				getPosition().setY(2);
				direction.setY(0);
			}
		}
		
		
		
		
		if(getPosition().getX() <= 0 || getPosition().getX() >= size.getX()){
			direction = direction.mul(new GVector3f(-1, 1, 1));
//			setFace(Math.abs(Math.toDegrees(Math.acos(direction.getX()))));
		}
		
		if(getPosition().getZ() <= 0 || getPosition().getZ() >= size.getZ()){
			direction = direction.mul(new GVector3f(1, 1, -1));
//			setFace(Math.abs(Math.toDegrees(Math.asin(direction.getZ()))));
		}
		
		//SHOTS
		for(int i=0 ; i<bullets.size() ; i++){
			Bullet l = bullets.get(i);
			l.update();
			if(l.isDead()){
				bullets.remove(i);
				i--;
			}
		}
		shot++;
		if(target != null && shot == shotEveryNthFrame){
			shot = 0;
			GVector3f toTarget = target.getPosition().sub(getPosition()).div(20).randomize(1);
			Bullet l = new Bullet(getPosition(),getPosition().add(toTarget));
			l.setColor(bulletColor);
			bullets.add(l);
		}
		
	}
	
	public void render(RenderingEngine renderingEngine){
		renderingEngine.renderEnemy(this);
		
		for(Bullet l : bullets)
			l.render(renderingEngine);
	}
	
	//SETTERS

	public void setTarget(GameObject target) {
		this.target = target;
	}
	
//	private void setFace(double angle){
//		setRotation(new GVector3f(0, angle, 0));
//	}
	
	public void setBulletColor(GVector3f color){
		bulletColor = color;
	}
	
	//GETTERS
	
	public Model getModel() {
		return model;
	}

	public GVector3f getBulletColor() {
		return bulletColor;
	}
}
