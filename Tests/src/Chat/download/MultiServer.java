package Chat.download;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MultiServer {

	private ServerSocket serverSocket;
	private ArrayList<BufferedReader> clientReaders;
	private ArrayList<BufferedWriter> clientWriters;
	private ArrayList<String> messages;
	private String lastmsg;
	
	public static void main(String[] args) {
		MultiServer server = new MultiServer();
	};
	
	public MultiServer(){
		try {
			this.serverSocket=new ServerSocket(8888);
			this.clientReaders = new ArrayList<BufferedReader>();
			this.listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};

	public void listen(){
		Thread acceptThread = new Thread(new Runnable(){
			public void run(){
				while(true){
					try {
						Socket client = serverSocket.accept();
						clientReaders.add(new BufferedReader(new InputStreamReader(client.getInputStream())));
						clientWriters.add(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		acceptThread.start();
		while(true){
			ArrayList<BufferedReader> tempClients =  new ArrayList<BufferedReader>(this.clientReaders);
			for(BufferedReader in : tempClients){
				try {
					System.out.println(in.readLine());
					writeToAll(in.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};
	
	public void writeToAll(String msg){
		ArrayList<BufferedWriter> tempClients =  new ArrayList<BufferedWriter>(this.clientWriters);
		for(BufferedWriter out : tempClients){
			try {
				out.write(msg);
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
