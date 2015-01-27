package WebSockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket serverSocket;
	
	public static void main(String[] args) {
		Server server = new Server();
	};
	
	public Server(){
		try {
			this.serverSocket=new ServerSocket(8888);
			Socket clientSocket = this.serverSocket.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.println("Klient sa pripojil. ("+clientSocket.getInetAddress().getHostAddress()+")");
			
			while(true){
				if(in.ready()){
					String temp = in.readLine();
					System.out.println(temp);
				}
			}
			
		} catch (IOException e) {}
	};

}
