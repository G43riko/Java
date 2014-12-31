package Chat;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Client extends JFrame{
	public String login,ip,port;
	private Socket clientSocket;
	
	public static void main(String[] args) {
		Client client=new Client();
		client.init();
	}
	
	public void init(){
		setTitle("Gabos Chat");
		setBounds(50, 50, 400, 600);
		setResizable(false);
		setVisible( true );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLayout( new BorderLayout() );
		
		SetUp setUp=new SetUp();
		setUp.setVisible(true);
		while(setUp.isDisplayable()){
			if(!setUp.isDisplayable()){
				this.login=setUp.textField_LOGIN.getText();
				this.ip=setUp.textField_IP.getText();
				this.port=setUp.textField_PORT.getText();
			}
		}
		
		try {
			this.clientSocket = new Socket(this.ip,Integer.valueOf(this.port));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendmsg(String msg){
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(this.clientSocket.getOutputStream()));
			out.write(msg);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
