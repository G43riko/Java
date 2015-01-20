package main.both.multiplayer;

import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import main.MainBomber;
import main.both.Bomberman;
import main.both.core.Game;
import main.both.core.GameObject;
import main.both.core.utils.Logs;
import main.both.core.utils.Vector2f;

public class Server {
	public static final String START_CODE = "CONNECT";
	public static final String END_CODE = "THEEND";
	public static final String PLAYER_POSITION = "PP";
	public static final String ADD_NEW_PLAYER = "ANP";
	public static final String MAP_NAME = "MN";
	private final static int PORT = 8888;
	private ServerSocket serverSocket;
	private Game game;
	
	public Server(Game game){
		this.game = game;
		try {
			serverSocket = new ServerSocket(PORT);
			Logs.write("vytvoril sa server socket na porte "+serverSocket.getLocalPort());
			listen();
		} catch (IOException e) {e.printStackTrace(); }
	}
	
	private void listen(){
		Thread acceptThread = new Thread(new Runnable(){
			public void run(){
				while(true){
					try {
						//toto  �ak� k�m sa nepripoj� nov� hr��
						Socket client = serverSocket.accept();
						//vytvor� hr��a
						OtherPlayer player = new OtherPlayer(client);
						//prid� hr��a na vykreslovanie
						game.addObject(new GameObject().addComponent(player));
						
						Logs.write("na server sa pripojil nov� hr�� "+player.getNick());
						
						player.write(MAP_NAME+" "+game.mapa.fileName);
						
						player.setPosition(new Vector2f(60,60));
						player.setColor(new Color(255,0,255));
						player.write(Server.START_CODE+" "+player.getPosition().getX()+" "+player.getPosition().getY()+" "+255+" "+0+" "+255);
						//write(game.mapa.fileName);
						Logs.write("jeho position je: "+player.getPosition()+" jeho color je "+player.getColor());
						
						player.write(Server.ADD_NEW_PLAYER+" "+game.player.getName()+" "+game.player.getPosition().getX()+" "+game.player.getPosition().getY()+" "+game.player.getColor().getRed()+" "+game.player.getColor().getGreen()+" "+game.player.getColor().getBlue());
						
//						ArrayList<OtherPlayer> tempPlayers = new ArrayList<OtherPlayer>(((Bomberman)game).players);
//						for(OtherPlayer p : tempPlayers){
//							player.write(Server.ADD_NEW_PLAYER+" "+p.getName()+" "+p.getPosition().getX()+" "+p.getPosition().getY()+" "+p.getColor().getRed()+" "+p.getColor().getGreen()+" "+p.getColor().getBlue());
//						}
						((Bomberman)game).players.add(player);
					} catch (IOException e) { e.printStackTrace(); }
				}
			}
		});
		acceptThread.start();
		
		Thread readThread = new Thread(new Runnable(){
			public void run(){
				while(true){
					ArrayList<OtherPlayer> tempPlayers = new ArrayList<OtherPlayer>(((Bomberman)game).players);
					for(OtherPlayer p : tempPlayers){
						try {
							Thread.sleep(1000/MainBomber.FPS/tempPlayers.size());
						} catch (InterruptedException e) {e.printStackTrace(); }
						if(p!=null && p.isWritting()){
							String line = p.readLine();
							if(line.startsWith(END_CODE)){
								System.out.println(p.getNick()+" sa odpojil");
								p.close();
								((Bomberman)game).players.remove(p);
								System.exit(1);
								continue;
							}
							else if(line.startsWith(PLAYER_POSITION)){
								((Bomberman)game).players.get(((Bomberman)game).players.indexOf(p)).setPosition(new Vector2f(Float.valueOf(line.split(" ")[1]),Float.valueOf(line.split(" ")[2])));
							}
						}
					}
				}
			}
		});
		readThread.start();
		
	}
	
	private void close(){
		for(OtherPlayer p: ((Bomberman)game).players){
			p.close();
		}
		try {
			serverSocket.close();
		} catch (IOException e) {e.printStackTrace(); }
	}
	
	public void write(String msg){
		ArrayList<OtherPlayer> tempPlayers = new ArrayList<OtherPlayer>(((Bomberman)game).players);
		for(OtherPlayer p : tempPlayers){
			p.write(msg);
		}
	}
	public void write(String msg,String except){
		ArrayList<OtherPlayer> tempPlayers = new ArrayList<OtherPlayer>(((Bomberman)game).players);
		for(OtherPlayer p : tempPlayers){
			if(p.getName().equals(except))
				continue;
			p.write(msg);
		}
	}
	
}
