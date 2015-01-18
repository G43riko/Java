package main.both.components;

import java.awt.Color;
import java.awt.Graphics2D;

import main.MainBomber;
import main.both.Constants;
import main.both.core.utils.Vector2f;

public class MyPlayer extends GameComponent{
	private static int keyUp = Constants.DEFAULT_UP_KEY;
	private static int keyDown= Constants.DEFAULT_DOWN_KEY;
	private static int keyRight = Constants.DEFAULT_RIGHT_KEY;
	private static int keyLeft = Constants.DEFAULT_LEFT_KEY;
	
	private String name;
	private int dosah;
	private int bombs;
	private int demage;
	private int speed;
	private Vector2f position;
	public Vector2f offset;
	public boolean[] keys = new boolean[4];
	private boolean isMoving = false;
	
	public MyPlayer(String name){
		this.name = name;
		this.speed = Constants.DEFAULT_SPEED;
		this.bombs = Constants.DEFAULT_NUMBER_OF_BOMBS;
		this.dosah = Constants.DEFAULT_EXPLOSION_RANGE;
		position = new Vector2f(0, 0);
		offset = new Vector2f(0 - MainBomber.WIDTH / 2, 0 - MainBomber.HEIGHT / 2);
	}
	
	public void render(Graphics2D g2){
		g2.setColor(Color.DARK_GRAY);
		g2.fillRect((int)(position.getX()-offset.getX()),(int)(position.getY()-offset.getY()),Map.block,Map.block);
		
	}
	
	public void input(float delta){
		delta = 1;
		//posunie hráèa
		if(this.keys[0]){
			position.addToY(-speed * delta);
		}
		if(this.keys[1]){
			position.addToX(-speed * delta);
		}
		if(this.keys[2]){
			position.addToY(speed * delta);
		}
		if(this.keys[3]){
			position.addToX(speed * delta);
		}
		
		//nastaví posun
		offset.setX(position.getX() - MainBomber.WIDTH / 2);
		offset.setY(position.getY() - MainBomber.HEIGHT / 2);

		//skontroluje posun
		if(offset.getX() < 0){
			offset.setX(0);
        }
        if(offset.getX() > (Map.getNumX() * Map.block) - MainBomber.WIDTH){
        	offset.setX((Map.getNumX() * Map.block) - MainBomber.WIDTH);
        }
        if(offset.getY() < 0){
			offset.setY(0);
        }	        
        if(offset.getY() > (Map.getNumY() * Map.block) - MainBomber.HEIGHT){
        	offset.setY((Map.getNumY() * Map.block) - MainBomber.HEIGHT); 
        }
        
        //skontroluje pozíciu
        if(position.getX() < 0){
        	position.setX(0);
        }
        if(position.getY() < 0){
        	position.setY(0);
        }
        if(position.getX() + Map.block > Map.getNumX() * Map.block){
        	position.setX(Map.getNumX() * Map.block - Map.block);
        }
        if(position.getY() + Map.block > Map.getNumY() * Map.block){
        	position.setY(Map.getNumY() * Map.block - Map.block);
        }
	}

	public void addBomb() {
		//addBomb
	}

	// getters and setters
	public float getX() {
		return position.getX();
	}

	public float getY() {
		return position.getY();
	}
	
	public Vector2f getPosition(){
		return position;
	}

	public int getDosah() {
		return dosah;
	}

}
