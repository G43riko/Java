package Chat.own;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author Gabriel
 */
public class Chat {
	private View view = new View(this);
	private Server2 server;
	
//	private String login;
	private String ip;
	private String port;
	
	private Client client;
	
	private boolean connected;
	private Thread acceptThread;
	
	//CONSTRUCTORS
	
	/**
	 * Constructor
	 */
	public Chat(){
		view.showLoginView();
	}

	//OTHERS
	
	/**
	 * Vykoná potrebné operácie na zaèatie chatu
	 * @param login
	 * @param ip
	 * @param port
	 * @param isHost
	 */
	public void start(String login, String ip, String port, boolean isHost) {
		if(isHost){
			server = new Server2(Integer.valueOf(port));
			ip = "localhost";
		}
		
		this.ip = ip;
		this.port = port;
		
		createSocket();
		
		client.setLogin(login);
		
		sendMessage("", Server.CLIENT_CONNECT);
		
		view.showChatView(login);
	}
	
	
	/**
	 * Vytvori socket a propojí sa k nemu
	 * Vytvorí aj BufferedWriter a BufferedReader
	 */
	private void createSocket(){
		try {
			client = new Client(new Socket(ip,Integer.valueOf(port)));
//			writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
//			reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		listen();
	}
	
	
	/**
	 * Vytvorí vlákno na poèúvanie prichádzajúcich správ
	 */
	private void listen(){
		connected = true;
		acceptThread = new Thread(new Runnable(){
			public void run() {
				while(connected){
					try {
						if(client.getReader().ready()){
							String msg = client.getReader().readLine();
							if(msg.startsWith(Server.CLIENT_DISCONNECT)){
								stop(false);
								return;
							}
							view.appendText(msg);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		acceptThread.start();
	}
	
	
	/**
	 * Zastaví chat a ukonèí socket
	 */
	public void stop(boolean sayToServer) {
		connected = false;
		
		if(sayToServer)
			sendMessage("", Server.CLIENT_DISCONNECT);
		
		view.showLoginView();

		if(isServer())
			
			server.stop();
		try {
			client.getSocket().close();
			client.getWriter().close();
			client.getReader().close();
			
			client.setReader(null);
			client.setWriter(null);
			client.setSocket(null);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Odošle správu serveru
	 * @param text
	 * @param type
	 */
	public void sendMessage(String text, String type) {
		try {
			if(client.getWriter() == null)
				return;
			
			client.getWriter().write(type+" "+client.getLogin()+": "+text+"\n");
			client.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
	//GETTERS
	
	public boolean isConnected() {
		return connected;
	}
	
	public boolean isServer(){
		return server != null;
	}
}
