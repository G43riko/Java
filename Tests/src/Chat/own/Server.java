package Chat.own;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author Gabriel
 */
public class Server {
	public final static String CLIENT_CONNECT = "cOnEcT";
	public final static String CLIENT_DISCONNECT = "dIsCoNeCt";
	public final static String CLIENT_SEND_MSG = "mEsSaGe";
	
	private ServerSocket serverSocket;
	private ArrayList<BufferedReader> clientReaders;
	private ArrayList<BufferedWriter> clientWriters;
	
	/**
	 * Constructor
	 * @param port
	 */
	public Server(int port){
		
		try {
			this.serverSocket=new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		clientReaders = new ArrayList<BufferedReader>();
		clientWriters = new ArrayList<BufferedWriter>();
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
						BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
						clientReaders.add(reader);
						clientWriters.add(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())));
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
					ArrayList<BufferedReader> tempClients =  new ArrayList<BufferedReader>(clientReaders);
					for(BufferedReader in : tempClients){
						try {
							if(in.ready())
								processingMessage(in.readLine());
						} catch (IOException | NullPointerException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		read.start();
	}
	
	
	/**
	 * Spracuje prijatú správu
	 * @param msg
	 */
	private void processingMessage(String msg){
		if(msg.startsWith(CLIENT_CONNECT)){
			writeToAll("Uživatel "+msg.replaceAll(CLIENT_CONNECT, "")+"sa pripojil");
		}
		else if(msg.startsWith(CLIENT_SEND_MSG)){
			writeToAll(msg.replaceAll(CLIENT_SEND_MSG, ""));
		}
	}
	
	
	/**
	 * Napíše správu všetkým pripojeným uživatelom
	 * @param msg
	 */
	private void writeToAll(String msg){
		ArrayList<BufferedWriter> tempClients =  new ArrayList<BufferedWriter>(clientWriters);
		for(BufferedWriter out : tempClients){
			try {
				out.write(msg+"\n");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * Ukonèí server
	 */
	public void stop(){
		
	}
}
