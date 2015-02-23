package game.components;

import game.world.World;
import glib.util.vector.GVector3f;

public interface Collisable {
	public default boolean checkBottom(GVector3f position, World world){
		return false;
	}
}
