package projekat_ORS4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
  
 private static int SERVER_PORT=12345;                //***
	private Socket socket;
	private String username;
	private BufferedReader input;
	private PrintWriter output;
	private ClientApp app;
	
	public Client(ClientApp app) {
		this.app=app;
		try {
			InetAddress address= InetAddress.getByName("localhost");
			socket= new Socket(address, SERVER_PORT);
			input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			System.out.println("Client connected");
		} catch (IOException e) {
			try {
				closeResourses();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}


  public void closeResourses() throws IOException {
		input.close();
		output.close();
		socket.close();
		                     //****
	}
  
}
