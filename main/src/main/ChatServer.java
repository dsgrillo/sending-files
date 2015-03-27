package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ChatServer {

	private static ServerSocket server;
	
	private static List<ChatHandler> sockets;
	
	public static void main(String... args) {
		try {
			server = new ServerSocket(1234);
			sockets = new LinkedList<ChatHandler>();
			
			int c = 1;
			
			while(true) {
				System.out.println("waiting");
				Socket socket = server.accept();
				
				String name = "Person " + c++;
				
				ChatHandler handler = new ChatHandler(socket, name);
				sockets.add(handler);
				
				Thread t = new Thread(handler);
				t.start();
				
				System.out.println("connected..");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(server != null)
				try {
					server.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public static void broadcast(String message, ChatHandler except) {
		for(ChatHandler h : sockets) {
			if(h == except)
				continue;
			h.write(message, except.getName());
		}
	}
}
