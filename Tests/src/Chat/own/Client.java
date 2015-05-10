package Chat.own;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
	private String login;
	
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	public Client(Socket client){
		socket = client;
		try {
			reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void remove(){
		try {
			socket.close();
			
			reader.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean write(String msg){
		try {
			writer.write(msg+"\n");
			writer.flush();
		} catch (IOException e) {
			return true;
		}
		
		return false;
	}

	//GETTERS
	
	public String getLogin() {
		return login;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public BufferedWriter getWriter() {
		return writer;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	//SETTERS

	public void setLogin(String login) {
		this.login = login;
	}

	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public void setWriter(BufferedWriter writer) {
		this.writer = writer;
	}
}
