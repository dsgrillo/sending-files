package server;

import java.io.DataInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import server.bean.SentFile;

public class ConnectedClient {

	private Socket theSocket;
	private PrintWriter output;
	private Scanner input;

	public ConnectedClient(Socket theSocket) {
		try {
			this.theSocket = theSocket;
			this.output = new PrintWriter(this.theSocket.getOutputStream(),
					true);
			this.input = new Scanner(this.theSocket.getInputStream());
		} catch (Exception e) {
			// yea right
		}
	}

	public SentFile getFile() {
		try {
			DataInputStream dis = new DataInputStream(
					this.theSocket.getInputStream());

			SentFile sf = new SentFile();
			
			int length = dis.readInt();
			
			if (length > 0) {
				byte[] file = new byte[length];
				dis.readFully(file, 0, file.length);
				sf.setTheFile(file);
				
			}
			
			sf.setName(dis.readUTF());
			return sf;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void sendMessage(String msg) {
		this.output.println(msg);
	}

	public String getMessage() {
		return this.input.nextLine();
	}
}
