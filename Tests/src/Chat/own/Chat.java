package Chat.own;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Chat {
	private View view = new View(this);
	private Server server;
	
	private String login;
	private String ip;
	private String port;
	
	private Socket clientSocket;
	private BufferedWriter writer;
	private BufferedReader reader;
	
	private boolean connected;
	
	public Chat(){
		view.showLoginView();
	}

	public void start(String login, String ip, String port, boolean isHost) {
		if(isHost){
			server = new Server(Integer.valueOf(port));
			ip = "localhost";
		}
		
		this.ip = ip;
		this.port = port;
		this.login = login;
		
		try {
			clientSocket = new Socket(ip,Integer.valueOf(port));
			System.out.println("vytvoril sa socket na ip: "+ip+" a porte: "+port);
			writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (NumberFormatException | IOException e) {
			serverDoNotRespond(e);
		}
		listen();
		view.showChatView(login);
	}

	private void serverDoNotRespond(Exception e){
		e.printStackTrace();
	}
	
	private void listen(){
		connected = true;
		Thread acceptThread = new Thread(new Runnable(){
			public void run() {
				while(connected){
				}
			}
		});
		acceptThread.start();
	}
	
	public void writeMessage(String msg){
		
	}
	
	public void stop() {
		view.showLoginView();
	}

	public void sendMessage(String text) {
		System.out.println("idem odoslaù spr·vu: "+text);
		try {
			writer.write(text);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Spr·vu sa nepodarilo odoslaù");
		}
	}
}
