package Bomberman.game.multiplayer;

import java.util.HashMap;

import glib.util.vector.GVector2f;
import Bomberman.game.Player;
import Bomberman.game.entities.Bomb;
import Bomberman.game.level.Level;

public interface Communicable {
	
	public void playerMove(GVector2f move, int direction);
	public void sendMyImage();
	public void putBomb(GVector2f position);
	public Level getLevel();
	public boolean isReady();
	public int getNumberPlayersInGame();
	public void playerEatItem(GVector2f sur, int type);
	public void bombExplode(Bomb bomb);
	public GVector2f getMyPosition();
	
	public default HashMap<String, Player> getPlayers(){
		return new HashMap<String, Player>();
	};
	
	
}
