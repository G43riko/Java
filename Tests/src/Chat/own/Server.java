package Chat.own;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	private ServerSocket serverSocket;
	private ArrayList<BufferedReader> clientReaders;
	private ArrayList<BufferedWriter> clientWriters;
	
	public Server(int port){
		
		try {
			this.serverSocket=new ServerSocket(port);
			System.out.println("vytvoril sa server na porte: "+port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		clientReaders = new ArrayList<BufferedReader>();
		clientWriters = new ArrayList<BufferedWriter>();
		listen();
		read();
		
	}
	
	private void listen(){
		Thread accept = new Thread(new Runnable(){
			public void run(){
				while(true){
					try {
						Socket client = serverSocket.accept();
						System.out.println("niekto sa pripojil");
						clientReaders.add(new BufferedReader(new InputStreamReader(client.getInputStream())));
						clientWriters.add(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		accept.start();
	};
	
	private void read(){
		Thread read = new Thread(new Runnable(){
			public void run() {
				while(true){
					ArrayList<BufferedReader> tempClients =  new ArrayList<BufferedReader>(clientReaders);
					for(BufferedReader in : tempClients){
						try {
							System.out.println("server prijal správu: "+in.readLine());
							writeToAll(in.readLine());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		read.start();
	}
	
	
	private void writeToAll(String msg){
		ArrayList<BufferedWriter> tempClients =  new ArrayList<BufferedWriter>(this.clientWriters);
		for(BufferedWriter out : tempClients){
			try {
				out.write(msg);
				System.out.println("server posiela správu: "+msg);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
