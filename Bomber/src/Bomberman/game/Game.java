package Bomberman.game;

import glib.util.vector.GVector2f;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import util.ResourceLoader;
import Bomberman.Options;
import Bomberman.core.Input;
import Bomberman.core.Interactable;
import Bomberman.game.entities.Bomb;
import Bomberman.game.entities.Enemy;
import Bomberman.game.entities.Explosion;
import Bomberman.game.entities.Item;
import Bomberman.game.entities.Visible;
import Bomberman.game.level.Block;
import Bomberman.game.level.Level;
import Bomberman.game.multiplayer.Communicable;
import Bomberman.game.other.PlayerInfo;
import Bomberman.gui.Logger;

public class Game implements Interactable{
	private Logger logger;
	private CoreGame parent;
	private Level level;
	private MyPlayer myPlayer;
	private HashMap<String, Bomb> bombs = new HashMap<String, Bomb>();
	private HashMap<String, Player> players = new HashMap<String, Player>();
	private HashMap<String, Item> items = new HashMap<String, Item>();
	private ArrayList<Explosion> explosions = new ArrayList<Explosion>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private Communicable connection;
	private float zoom = 1f;
	private PlayerInfo info;
	private boolean ready;
	
	//CONSTRUCTORS
	
	public Game(CoreGame parent){
		this.parent = parent;
		connection = parent.getConnection();
		do{
			level = connection.getLevel();
		}while(level == null);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		logger = new Logger(parent.getCanvas().getWidth() + 10,0);
//		logger.show();
		logger.addText("Logger uspešne vytvorený");
		
		myPlayer = new MyPlayer(connection.getMyPosition(), parent.getCanvas(), this);
		info = new PlayerInfo(myPlayer);
		
//		parent.getConnection().sendMyImage();
	}
	
	public void renamePlayer(String oldName, String newName){
		Player tmp = players.get(oldName);
		tmp.setName(newName);
		players.remove(oldName);
		players.put(newName, tmp);
	}
	
	//OVERRIDES
	
	@Override
	public void render(Graphics2D g2) {
		level.render(g2);
		myPlayer.render(g2);
		
//		connection.getPlayers()
//		          .entrySet()
//		          .forEach(a -> a.getValue().render(g2));
		new HashMap<String, Player>(players).entrySet()
											.stream()
											.map(a -> a.getValue())
											.filter(this::isVisible)
											.forEach(a -> a.render(g2));
		
		new HashMap<String, Bomb>(bombs).entrySet()
		                                .stream()
		                                .map(a -> a.getValue())
		                                .filter(this::isVisible)
		                                .forEach(a -> a.render(g2));
		
		new ArrayList<Explosion>(explosions).stream()
											.filter(this::isVisible)
				  							.forEach(a -> a.render(g2));
		
		new ArrayList<Enemy>(enemies).stream()
									 .filter(this::isVisible)	
									 .forEach(a -> a.render(g2));
		
		new HashMap<String, Item>(items).entrySet()
										.stream()
										.map(a -> a.getValue())
										.filter(this::isVisible)
										.forEach(a -> a.render(g2));
		
		
		info.render(g2);
	}
	
	@Override
	public void update(float delta) {
		myPlayer.update(delta);
		
		new HashMap<String, Bomb>(bombs).entrySet()
				 						.stream()
				 						.peek(a -> a.getValue().update(delta))
				 						.filter(a -> a.getValue().isDead())
				 						.forEach(a -> bombs.remove(a.getKey()));
		
		explosions.removeAll(explosions.stream()
									   .filter(a -> a.isDead())
									   .collect(Collectors.toCollection(ArrayList<Explosion>::new)));
		
		enemies.stream()
			   .forEach(a -> a.update(delta));
		
		new ArrayList<Explosion>(explosions).stream()
											.forEach(a -> a.update(delta));
	}
	
	
	@Override
	public void input() {
		if(Input.isKeyDown(Input.KEY_ESCAPE))
			parent.pausedGame();
		
		if(Input.isKeyDown(Input.KEY_E))
			enemies.add(new Enemy(new GVector2f(320,320), this));
		
		int maxEnemies = 1;
		
		if(Input.isKeyDown(Input.KEY_F))
			for(int i=0 ; i < maxEnemies ; i++)
				if(enemies.size() < maxEnemies)
					enemies.add(new Enemy(level.getMap().getRandomEmptyBlock().getPosition(),this));
		
		myPlayer.input();
	}
	
	//OTHERS
	
	public void changeZoom(float value){
		if(!Options.ALLOW_ZOOMING)
			return;
		
		zoom += value;
		
		if(level.getMap().getNumberOfBlocks().getX() * Block.WIDTH * zoom < parent.getCanvas().getWidth()){
			zoom -= value; 
			return;
		}
		
		if(zoom > 5)
			zoom -= value;
	}

	public void playerMove(){
		connection.playerMove(getMyPlayer().getPosition(), getMyPlayer().getDirection());
//		getMyPlayer().clearTotalMove();
	}

	public void checkReady(){
		ready = connection.isReady();
	}

	public boolean isVisible(Visible b){
		return !(b.getPosition().getX() + Block.WIDTH  * zoom < getOffset().getX()    || 
				 b.getPosition().getY() + Block.HEIGHT * zoom < getOffset().getY()    || 
				   getOffset().getX() + getCanvas().getWidth()  < b.getPosition().getX()   ||
				   getOffset().getY() + getCanvas().getHeight() < b.getPosition().getY());
	}
	
	public void cleanUp() {
		level.cleanUp();
	}
	
//	public void changePlayerName(String oldName, String newName){
//		
//	}
	
	public void removeItem(String key){
		items.remove(key);
	}
	
	//GETTERS

	public float getZoom() {return zoom;}
	public Level getLevel() {return level;}
	public boolean isReady() {return ready;}
	public Logger getLogger(){return logger;}
	public MyPlayer getMyPlayer() {return myPlayer;}
	public Canvas getCanvas(){return parent.getCanvas();}
	public Item getItem(String key){return items.get(key);}
	public Bomb getBomb(String key){return bombs.get(key);}
	public Communicable getConnection() {return connection;}
	public GVector2f getOffset() {return myPlayer.getOffset();}
	public HashMap<String, Player> getPlayers() {return players;};
	public boolean hasBomb(String key){return bombs.containsKey(key);}
	public boolean hasItem(String key){return items.containsKey(key);}
	
	//SETTERS
	
	public void setReady(boolean ready) {this.ready = ready;}

	//ADDERS

	public void addExplosion(GVector2f position, String name, GVector2f num){
		explosions.add(new Explosion(position,
				 					 ResourceLoader.loadTexture(name),
				 					 new GVector2f(5,5),
				 					 Options.EXPLOSION_DEFAULT_OFFSET,
				 					 Options.EXPLOSION_DEFAULT_DELAY, 
				 					 this));
	}
	
	public void addPlayer(String name, Player player) {
		players.put(name, player);
	}
	
	public void addItem(GVector2f position, int type){
		String sur = position.toString();
		
		if(!items.containsKey(sur)){
			if(getLevel().getMap().getBlock(position.getXi(), position.getYi()).getType() != Block.NOTHING)
				items.put(sur, new Item(position.mul(Block.SIZE), type, this));
		}
		else
			items.remove(sur);
	}

	public void addBomb(int bombDefaultTime, GVector2f position, int range) {
		bombs.put(position.toString(), new Bomb(Options.BOMB_DEFAULT_TIME, position, range, this));
	}
}
