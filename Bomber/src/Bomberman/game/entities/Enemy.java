package Bomberman.game.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import glib.util.vector.GVector2f;
import Bomberman.Options;
import Bomberman.core.Interactable;
import Bomberman.game.Game;
import Bomberman.game.level.Block;
import Bomberman.game.level.Map;

public class Enemy implements Interactable, Visible{
	private GVector2f position;
//	private Image image;
	private int direction;
	private int speed;
	private Game parent;
	
	public Enemy(GVector2f position, Game parent) {
		this.position = position;
		this.parent = parent;
		speed = Options.ENEMY_DEFAULT_SPEED;
		setRandPossibleDir(parent.getLevel().getMap());
	}

	@Override
	public void render(Graphics2D g2) {
		g2.setColor(Color.CYAN);
		GVector2f pos = position.sub(parent.getOffset());
		g2.fillRoundRect(pos.getXi(), pos.getYi(), Options.ENEMY_DEFAULT_WIDTH, Options.ENEMY_DEFAULT_HEIGHT, 20, 20);
	}
	
	private void setRandDir(){
		direction = (int)(Math.random() * 4);
	}
	
	private void setRandPossibleDir(Map mapa){
		System.out.println(getPosition() + " == " + getSur() + " == " + mapa.getBlockOnPosition(getPosition()));
		ArrayList<Integer> dirs = new ArrayList<Integer>(Arrays.asList(0,1,2,3));
		Collections.shuffle(dirs);
		Block b;
		int dir;
	}

	public GVector2f getSur(){
		return position.div(Block.SIZE).toInt();
	}
	
	private GVector2f getMoveFromDir(int dir){
		switch(direction){
			case 0:
				return new GVector2f(00, -1);
			case 1:
				return new GVector2f(01, 00);
			case 2:
				return new GVector2f(00, 01);
			case 3:
				return new GVector2f(-1, 00);
			default:
				return new GVector2f();
		}
	}
	
	@Override
	public void update(float delta) {
		
		if(position.mod(Block.SIZE).isNull()){
			setRandPossibleDir(parent.getLevel().getMap());
//			Block b;
//			do{
//				direction = setRandDir();
//				b = parent.getLevel().getMap().getBlock(position.add(getMoveFromDir(direction).mul(Block.SIZE)).toString());
//			}while(b == null || b.getType() != Block.NOTHING);
		}
			
		
		GVector2f move = getMoveFromDir(direction);
		
		position = position.add(move.mul(speed));
	}

	@Override
	public GVector2f getPosition() {
		return position;
	}
	
	@Override
	public GVector2f getSize() {
		return new GVector2f(Options.ENEMY_DEFAULT_WIDTH, Options.ENEMY_DEFAULT_HEIGHT);
	} 
}
