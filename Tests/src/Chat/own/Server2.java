package Chat.own;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server2 {
	public final static String CLIENT_CONNECT = "cOnEcT";
	public final static String CLIENT_DISCONNECT = "dIsCoNeCt";
	public final static String CLIENT_SEND_MSG = "mEsSaGe";
	
	private ServerSocket serverSocket;
	private ArrayList<Client> persons;
	
	/**
	 * Constructor
	 * @param port
	 */
	public Server2(int port){
		try {
			this.serverSocket=new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		persons = new ArrayList<Client>();
		
		listen();
		read();
	}
	
	
	/**
	 * Spustí vlákno na poèúvanie žiadostí o pripojenie
	 */
	private void listen(){
		Thread accept = new Thread(new Runnable(){
			public void run(){
				while(true){
					try {
						Socket client = serverSocket.accept();
						persons.add(new Client(client));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		accept.start();
	};
	
	/**
	 * Spustí vlákno na poèúvanie prichádzajucich správ
	 */
	private void read(){
		Thread read = new Thread(new Runnable(){
			public void run() {
				while(true){
					ArrayList<Client> tempClients =  new ArrayList<Client>(persons);
					for(Client in : tempClients){
						processingMessage(in);
					}
				}
			}
		});
		read.start();
	}
	
	/**
	 * Spracuje prijatú správu
	 * @param client
	 */
	private void processingMessage(Client client){
		BufferedReader reader = client.getReader();
		try {
			if(!reader.ready())
				return;
			
			String msg = reader.readLine();
			
			if(msg.startsWith(CLIENT_CONNECT)){
				msg = msg.replaceAll(CLIENT_CONNECT, "");
				System.out.println("meno je "+msg);
				client.setLogin(msg);
				writeToAll("Uživatel "+client.getLogin()+"sa pripojil");
			}
			else if(msg.startsWith(CLIENT_SEND_MSG)){
				writeToAll(msg.replaceAll(CLIENT_SEND_MSG, ""));
			}
			else if(msg.startsWith(CLIENT_DISCONNECT)){
				client.remove();
				persons.remove(client);
				writeToAll("Uživatel "+msg.replaceAll(CLIENT_DISCONNECT, "")+"sa odpojil");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Napíše správu všetkým pripojeným uživatelom
	 * @param msg
	 */
	private void writeToAll(String msg){
		ArrayList<Client> tempClients =  new ArrayList<Client>(persons);
		for(Client out : tempClients){
			if(out.write(msg)){
				out.remove();
				persons.remove(out);
			}
		}
	}
	
	/**
	 * Ukonèí server
	 */
	public void stop(){
		writeToAll(CLIENT_DISCONNECT);
	}
}
