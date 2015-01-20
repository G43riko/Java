package main.both.multiplayer;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import main.MainBomber;
import main.both.components.Map;
import main.both.components.Player;
import main.both.components.PlayerForDraw;
import main.both.core.Game;
import main.both.core.utils.Logs;
import main.both.core.utils.Vector2f;
import main.game.Bomberman;

public class Client {
	private static final int PORT = 8888;
//	private static final String HOST = "localhost";
	private static final String HOST = "192.168.0.110";
	BufferedWriter writer;
	BufferedReader reader;
	private Socket socket;
	private Game game;
	
	public Client(Game game){
		this.game = game;
		try {
			socket = new Socket(HOST,PORT);
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			listen();
			write(Server.START_CODE+" "+game.player.getName());
			
		} catch (UnknownHostException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace(); }
	}
	
	private void listen() {
		Thread listenThread = new Thread(new Runnable(){
			public void run() {
				while(true){
					try {
						try {
							int n = 1;
							if(((Bomberman)game).players.size() > 1){
								n = ((Bomberman)game).players.size();
							}
							Thread.sleep(1000/MainBomber.FPS/n);
						} catch (InterruptedException e) {e.printStackTrace(); }
						if(reader.ready()){
							String line = reader.readLine();
							String[] data = line.split(" ");
							if(line.startsWith(Server.START_CODE)){
								game.player.setPosition(new Vector2f(Float.valueOf(data[1]),Float.valueOf(data[2])));
								game.player.setColor(new Color(Integer.valueOf(data[3]),Integer.valueOf(data[4]),Integer.valueOf(data[5])));
								Logs.write("pozicia: "+game.player.getPosition()+" color: "+game.player.getColor());
							}
							
							if(line.startsWith(Server.ADD_NEW_PLAYER)){
								Logs.write("v hre sa nachadza hráè "+data[1]);
								PlayerForDraw p = new PlayerForDraw();
								p.setName(data[1]);
								p.setPosition(new Vector2f(Float.valueOf(data[2]),Float.valueOf(data[3])));
								p.setColor(new Color(Integer.valueOf(data[4]),Integer.valueOf(data[5]),Integer.valueOf(data[6])));
								((Bomberman)game).createPlayer(p);
							}
							
							if(line.startsWith(Server.PLAYER_POSITION)){
								for(PlayerForDraw p:((Bomberman)game).players){
									if(p.getName().equals(data[1]) ){
										p.setPosition(new Vector2f(Float.valueOf(data[2]),Float.valueOf(data[3])));
//										Logs.write("mení to hráèovu pozíciu "+p.getPosition());
										break;
									}
								}
							}
							
							if(line.startsWith(Server.MAP_NAME)){
								Logs.write("prišiel nazov mapy: "+data[1]);
								game.mapa = new Map(data[1],game.player);
							}
						}
					} catch (IOException e) {e.printStackTrace(); }
				}
			}
		});
		listenThread.start();
	}

	public void close(){
		write(Server.END_CODE);
		try {
			writer.close();
			reader.close();
			socket.close();
		} catch (IOException e) {e.printStackTrace(); }
		
	}

	public void write(String msg) {
		try {
			writer.write(msg + "\n");
			writer.flush();
		} catch (IOException e) {e.printStackTrace(); }
	}
}
