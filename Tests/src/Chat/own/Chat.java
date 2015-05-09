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
	private Server server;
	
	private String login;
	private String ip;
	private String port;
	
	private Socket clientSocket;
	private BufferedWriter writer;
	private BufferedReader reader;
	
	private boolean connected;
	
	/**
	 * Constructor
	 */
	public Chat(){
		view.showLoginView();
	}

	
	/**
	 * Vykoná potrebné operácie na zaèatie chatu
	 * @param login
	 * @param ip
	 * @param port
	 * @param isHost
	 */
	public void start(String login, String ip, String port, boolean isHost) {
		if(isHost){
			server = new Server(Integer.valueOf(port));
			ip = "localhost";
		}
		
		this.ip = ip;
		this.port = port;
		this.login = login;
		
		createSocket();
		
		view.showChatView(login);
	}
	
	
	/**
	 * Vytvori socket a propojí sa k nemu
	 * Vytvorí aj BufferedWriter a BufferedReader
	 */
	private void createSocket(){
		try {
			clientSocket = new Socket(ip,Integer.valueOf(port));
			writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			sendMessage(" ", Server.CLIENT_CONNECT);
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
		Thread acceptThread = new Thread(new Runnable(){
			public void run() {
				while(connected){
					try {
						view.appendText(reader.readLine());
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
	public void stop() {
		view.showLoginView();
		try {
			clientSocket.close();
			writer.close();
			reader.close();
			
			writer = null;
			reader = null;
			clientSocket = null;
			
			if(server != null)
				server.stop();
			
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
			System.out.println(login+" odosiela správu: "+text+" cez writter: "+writer);
			writer.write(type+" "+login+": "+text+"\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
