package game.towers;

import game.maps.Block;

public class TowerA extends Tower{

	public TowerA(float x, float y, float range, float dps) {
		super((float)Math.floor(x/Block.size.getX())*Block.size.getX(), (float)Math.floor(y/Block.size.getY())*Block.size.getY(), range, dps);
	}
}
