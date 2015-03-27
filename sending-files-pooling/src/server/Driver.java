package server;

import java.net.ServerSocket;
import java.net.Socket;

public class Driver {
	protected static final String BASE_DIR = "C:\\Users\\Diogo\\Desktop\\clone";
	
	public static void main(String[] args) throws Exception {
		
		ServerSocket server = new ServerSocket(1234);
		
		while(true) {
			System.out.println("Waiting..");
			Socket conn = server.accept();
			ConnectedClient cc = new ConnectedClient(conn);
			
			Thread t = new ResponseThread(cc);
			
			System.out.println("Connected!");
			
			t.start();
			
		}
		
	}
}
