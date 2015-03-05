package main;

import java.net.ServerSocket;
import java.net.Socket;

public class Driver {

	public static void main(String... args) throws Exception {
		ServerSocket server = new ServerSocket(1234);
		
		while(true) {
			System.out.println("Waiting...");
			Socket s = server.accept();
			
			ResponseThread thread = new ResponseThread(s);
			
			System.out.println("Conected...");
			thread.start();
		}
		
	}
}
