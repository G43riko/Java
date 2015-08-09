package Bomberman.game.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import glib.util.vector.GVector2f;
import Bomberman.Options;
import Bomberman.core.Interactable;
import Bomberman.game.Game;
import Bomberman.game.level.Block;
import Bomberman.game.level.Map;

public class Enemy implements Interactable, Visible{
	private static GVector2f size = new GVector2f(Options.ENEMY_DEFAULT_WIDTH  - 2 * Options.ENEMY_DEFAULT_OFFSET, 
			  									  Options.ENEMY_DEFAULT_HEIGHT - 2 * Options.ENEMY_DEFAULT_OFFSET);
	private GVector2f position;
	private int direction;
	private int speed;
	private Game parent;
	
	public Enemy(GVector2f position, Game parent) {
		this(position, parent, Options.ENEMY_DEFAULT_SPEED);
	}
	
	public Enemy(GVector2f position, Game parent, int speed) {
		this.position = position;
		this.parent = parent;
		this.speed = speed;
		setRandPossibleDir(parent.getLevel().getMap());
	}

	@Override
	public void render(Graphics2D g2) {
		GVector2f pos = position.sub(parent.getOffset()).add(Options.ENEMY_DEFAULT_OFFSET);
		
		g2.setColor(Color.CYAN);
		g2.fillRoundRect(pos.getXi(), pos.getYi(), size.getXi(), size.getYi(), 20, 20);

		g2.setColor(Color.BLACK);
		g2.drawRoundRect(pos.getXi(), pos.getYi(), size.getXi(), size.getYi(), 20, 20);
	}
	
	private void setRandPossibleDir(Map mapa){
		int[] ret = mapa.getPossibleWays(getSur());
		
		if(ret.length > 0)
			direction = ret[(int)(Math.random() * ret.length)];
		else 
			direction = -1;
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
		if(position.mod(Block.SIZE).isNull())
			setRandPossibleDir(parent.getLevel().getMap());
		
		if(direction == -1)
			return;
		
		position = position.add(getMoveFromDir(direction).mul(speed));
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
