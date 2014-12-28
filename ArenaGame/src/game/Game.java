package game;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game {
	private Window window = null;
	private ArrayList<Unit> units = new ArrayList<Unit>();
	private Map mapa;
	public boolean wantNewGame = false;
	private Player player;
	
	private void update(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, Index.WIDTH, Index.HEIGHT);
		mapa.draw(g2);
		for(Unit a:units){
			a.move(mapa.getMapa());
			a.draw(g2);
		}
		player.move();
		player.draw(g2);
		if(wantNewGame){
			init();
		}
	}
	
	public void init() {
		units.clear();
		if(window == null)
			window = new Window(this);
		player = new Player();
		mapa = new Map();
		mapa.createRandomMap(window.blocksNum.getValue());
		
		for(int i=0 ; i<window.enemyNum.getValue() ; i++){
			units.add(new UnitA(mapa.getMapa(),window));
		}
		wantNewGame = false;
	}
	
	public void loop(){
		BufferStrategy buffer = window.getCanvas().getBufferStrategy();
		if(buffer==null){
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		Graphics g = buffer.getDrawGraphics();
		Graphics2D g2 = (Graphics2D) g;
		update(g2);
		g.dispose();
		buffer.show();
	}
	

	public void setTarget(Vector2f t) {
		for(Unit a:units){
			((UnitA)a).addTarget(t);
		}
	}

	public Map getMapa() {
		return mapa;
	}

	public void everybodyCameHere(Vector2f vec) {
		PathFinder.clear(mapa.getMapa());
		PathFinder.getDist(mapa.getMapa(), new Vector2f(0,0), vec.div(Map.size));
		//PathFinder.getDirection(mapa.getMapa());
		for(Unit a:units){
			a.clear();
			a.fintPathTo(vec,mapa.getMapa());
		}
		
	}
}
