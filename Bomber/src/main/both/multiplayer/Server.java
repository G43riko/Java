package main.both.multiplayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import main.both.core.Game;
import main.both.core.GameObject;

public class Server {
	public static final String START_CODE = "CONNECT";
	public static final String END_CODE = "THEEND";
	private final static int PORT = 8888;
	private ArrayList<OtherPlayer> players;
	private ServerSocket serverSocket;
	private Game game;
	
	public Server(Game game){
		this.game = game;
		players = new ArrayList<OtherPlayer>();
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("vytvoril sa server socket "+serverSocket.getLocalPort());
			listen();
			//close();
		} catch (IOException e) {e.printStackTrace(); }
	}
	
	private void listen(){
		Thread acceptThread = new Thread(new Runnable(){
			public void run(){
				while(true){
					try {
						Socket client = serverSocket.accept();
						OtherPlayer player = new OtherPlayer(client);
						game.addObject(new GameObject().addComponent(player));
						System.out.println(player.getNick()+" sa pripojil");
						players.add(player);
						write("nazdar "+player.getNick());
					} catch (IOException e) { e.printStackTrace(); }
				}
			}
		});
		acceptThread.start();
		
		Thread readThread = new Thread(new Runnable(){
			public void run(){
				while(true){
					ArrayList<OtherPlayer> tempPlayers = new ArrayList<OtherPlayer>(players);
					for(OtherPlayer p : tempPlayers){
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {e.printStackTrace(); }
						if(p.isWritting()){
							String line = p.readLine();
							if(line.startsWith(END_CODE)){
								System.out.println(p.getNick()+" sa odpojil");
								p.close();
								players.remove(p);
								System.exit(1);
								continue;
							}
							
							System.out.println(p.getNick()+" napísal: "+line);
						}
					}
				}
			}
		});
		readThread.start();
		
	}
	
	private void close(){
		for(OtherPlayer p: players){
			p.close();
		}
		try {
			serverSocket.close();
		} catch (IOException e) {e.printStackTrace(); }
	}
	
	private void write(String msg){
		ArrayList<OtherPlayer> tempPlayers = new ArrayList<OtherPlayer>(players);
		for(OtherPlayer p : tempPlayers){
			p.write(msg);
		}
	}
	
}
