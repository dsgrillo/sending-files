package main;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatHandler implements Runnable {

	private String name;
	
	private Socket socket;
	
	private PrintWriter output;
	
	public ChatHandler(Socket s, String name) {
		this.socket = s;
		this.name = name;
	}
	
	@Override
	public void run() {
		Scanner in = null;
		this.output = null;
		
		try {
			in = new Scanner(this.socket.getInputStream());
			this.output = new PrintWriter(this.socket.getOutputStream());
			
			output.println("You were connected and your name is: " + this.name);
			output.flush();
			
			String text;
			
			while(true) {
				
				text = in.nextLine();
				
				
				if("quit".equalsIgnoreCase(text))
					break;
				
				ChatServer.broadcast(text, this);
				//output.println(text);
				//output.flush();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null)
				in.close();
			
			if(output != null)
				output.close();
		}
	}
	
	public String getName() { 
		return this.name;
	}
	
	public void write(String line, String who) {
		if(!this.socket.isOutputShutdown()) {
			output.print(who);
			output.print(" says: ");
			output.println(line);
			output.flush();
		}
	}

}
