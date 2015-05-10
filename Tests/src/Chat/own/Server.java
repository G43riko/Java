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
	 * Spust� vl�kno na po��vanie �iadost� o pripojenie
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
	 * Spust� vl�kno na po��vanie prich�dzajucich spr�v
	 */
	private void read(){
		Thread read = new Thread(new Runnable(){
			public void run() {
				while(true){
					ArrayList<BufferedReader> tempClients =  new ArrayList<BufferedReader>(clientReaders);
					for(BufferedReader in : tempClients){
						processingMessage(in);
					}
				}
			}
		});
		read.start();
	}
	
	
	/**
	 * Spracuje prijat� spr�vu
	 * @param reader
	 */
	private void processingMessage(BufferedReader reader){
		try {
			if(!reader.ready())
				return;
			
			String msg = reader.readLine();
			
			if(msg.startsWith(CLIENT_CONNECT)){
				writeToAll("U�ivatel "+msg.replaceAll(CLIENT_CONNECT, "")+"sa pripojil");
			}
			else if(msg.startsWith(CLIENT_SEND_MSG)){
				writeToAll(msg.replaceAll(CLIENT_SEND_MSG, ""));
			}
			else if(msg.startsWith(CLIENT_DISCONNECT)){
				clientReaders.remove(reader);
				writeToAll("U�ivatel "+msg.replaceAll(CLIENT_DISCONNECT, "")+"sa odpripojil");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Nap�e spr�vu v�etk�m pripojen�m u�ivatelom
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
	 * Ukon�� server
	 */
	public void stop(){
		
	}
}
