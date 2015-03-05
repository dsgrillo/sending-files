package main;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ResponseThread extends Thread {

	private Socket socket;
	
	public ResponseThread(Socket s) {
		this.socket = s;
	}
	
	
	public void run() {
		Scanner in = null;
		PrintWriter out = null;
		
		try {
			in = new Scanner(this.socket.getInputStream());
			out = new PrintWriter(this.socket.getOutputStream());
			
			String text;
			
			while(true) {
				text = in.nextLine();
				
				if("quit".equalsIgnoreCase(text))
					break;
				
				out.print("Server says: ");
				out.println(text);
				out.flush();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null)
				in.close();
			
			if(out != null)
				out.close();
		}
		
	}
}
