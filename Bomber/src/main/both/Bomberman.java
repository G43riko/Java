package main.both;

import main.both.components.Log;
import main.both.components.Map;
import main.both.components.MyPlayer;
import main.both.core.Game;
import main.both.core.GameObject;
import main.both.core.utils.Window;
import main.both.multiplayer.Server;

public class Bomberman extends Game{
	private Server server;
	public int pocetHracov = 0;
	
	public Bomberman(){
		server = new Server(this);
	}
	
	public void init(){
		MyPlayer p = new MyPlayer("Gabriel");
		
		Window.keyboard.addPlayer(p);
		//GameObject mapa = new GameObject().addComponent(new Map(30,30,p));
		GameObject mapa = new GameObject().addComponent(new Map("mapa1",p));
		addObject(mapa);
		addObject(new GameObject().addComponent(p));
		
		addObject(new GameObject().addComponent(new Log(this)));
	}
}
