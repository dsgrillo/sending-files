package server;

import java.io.FileOutputStream;

import server.bean.SentFile;

public class ResponseThread extends Thread {

	ConnectedClient theClient;

	public ResponseThread(ConnectedClient theClient) {
		this.theClient = theClient;
	}

	public void run() {
		this.theClient.sendMessage("Do you want to share or download?");
		String theAnswer = this.theClient.getMessage();
		this.theClient.sendMessage(theAnswer);

		
		try {
			SentFile theFile = this.theClient.getFile();
			
			FileOutputStream fos = new FileOutputStream(Driver.BASE_DIR + "/" + theFile.getName());
			fos.write(theFile.getTheFile());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
