package Bomberman.game.multiplayer.core;


import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.Consumer;

import Bomberman.Options;
import Bomberman.game.CoreGame;
import Bomberman.game.multiplayer.ClientPlayer;

public abstract class Server {
	public final static int TS_PLAYER_NAME 		= 0;
	public final static int FS_PLAYER_POSITION  = 1;
	public final static int FS_ACTUAL_LEVEL 	= 2;
	public final static int FS_NEW_PLAYER 		= 3;
	public final static int TS_PLAYER_MOVE 		= 4;
	public final static int FS_PLAYER_NEW_POS 	= 4;
	public final static int FS_EVERITHING_SEND 	= 5;
	public final static int TS_PLAYER_IMAGE 	= 6;	//hr�� po�le serveru obr�zok
	public final static int FS_PLAYER_IMAGE 	= 7;	//server po�le v�detk�m hr��om obr�zok
	public final static int TS_ADD_BOMB 		= 8;
	public final static int FS_AFFECTED_BLOCKS  = 9;
	public final static int FS_PLAYER_WAS_HIT   = 10;
	public final static int FS_PLAYER_NAME   	= 11;
	public final static int FS_ADD_EXPLOSION   	= 12;
	public final static int FS_ADD_ITEM		   	= 13;
	public final static int FS_PLAYER_EAT_ITEM	= 14;
	public final static int TS_PLAYER_EAT_ITEM	= 15;
	
	private ServerSocket serverSocket;
	private CoreGame parent;
	private boolean readerIsRunning = true;
	private boolean accepterIsRunning = true;
	
	Thread acceptThread;
	Thread listenThread;
	
	private HashMap<String, ClientPlayer> players = new HashMap<String, ClientPlayer>();
	
	protected void renameClient(String oldName, String newName){
		ClientPlayer temp = players.get(oldName); 
		temp.setName(newName);
		players.remove(oldName);
		players.put(newName, temp);
	}
	
	protected int getNumberOfClients(){
		return players.size();
	}
	
	public ClientPlayer getClient(String name){
		return players.get(name);
	}
	
	public Server(CoreGame parent){
		this.parent = parent;
		try {
			serverSocket = new ServerSocket(Options.PORT);
		} catch (IOException e) {
			System.out.println("nepodarilo sa vytvori� server");
		}
		
		listen();
		accept();
	}
	
	private void listen() {
		listenThread = new Thread(new Runnable(){
			public void run(){
				while(readerIsRunning){
					new HashMap<String, ClientPlayer>(players).entrySet()
															  .stream()
															  .filter(a -> a != null)
															  .forEach(a -> preccessObject(a));
				}
			}
		});
		listenThread.start();
	}
	
	protected abstract void proccesMsg(String msg, Entry<String, ClientPlayer> temp);
	
	private void preccessObject(Entry<String, ClientPlayer> a){
		Object o = a.getValue().read();
		if(o == null)
			return;
		
		
		if(o instanceof String)
			proccesMsg((String)o, a);
		else
			System.out.println("server prijal spr�vu " + o);
	}
	
	private void accept(){
		acceptThread = new Thread(new Runnable(){
			public void run(){
				while(accepterIsRunning){
					try {
						Socket client = serverSocket.accept();
						
						if(canConnect()){
							String name = System.currentTimeMillis() + " ";
							ClientPlayer player = new ClientPlayer(parent.getGame(), client, name);
							players.put(name, player);
							establishConnection(name,player);
						}
						
					} catch (IOException e) {
						System.out.println("server bol ukon�en�");
					}
				}
			}
		});
		acceptThread.start();
	}

	public boolean canConnect(){return true;};
	
	protected void establishConnection(String name, ClientPlayer player){};
		
	public void doForAllClients(Consumer<? super Entry<String,ClientPlayer>> action){
		new HashMap<String, ClientPlayer>(players).entrySet()
                                                  .parallelStream()
                                                  .forEach(action);
	}
	
	public void write(Serializable o){
		new HashMap<String, ClientPlayer>(players).entrySet()
												  .stream()
												  .forEach(a -> a.getValue().write(o));
	}
	
	public void cleanUp(){
		readerIsRunning = false;
		accepterIsRunning = false;
		
		players.entrySet()
		       .stream()
			   .forEach(a -> a.getValue().cleanUp());
		
		players.clear();
		
		try {
			if(serverSocket != null)
				serverSocket.close();
		} catch (IOException e) {
			System.out.println("nepodarilo sa zavrie� server");
		}
		serverSocket = null;
	}
}
