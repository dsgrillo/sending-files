package client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Driver {
	
	private static final String BASE_DIR = "C:\\Users\\Diogo\\Desktop";
	
	public static void main(String[] args) throws Exception
	{
		//connect to the server
		Socket s = new Socket("localhost",1234);
		
		//ability to read from server
		Scanner input = new Scanner(s.getInputStream());
		
		//ability to read from local client
		Scanner terminalInput = new Scanner(System.in);
		
		//ability to write to the server
		PrintWriter output = new PrintWriter(s.getOutputStream(), true);
		
		System.out.println(input.nextLine());
		String theAnswer = terminalInput.nextLine();
		output.println(theAnswer);

		if(theAnswer.equals("share"))
		{
			File myFilesDir = new File(BASE_DIR + "");
			String[] theFiles = myFilesDir.list();
			int pos = 0;
			for(String fn : theFiles)
			{
				System.out.println(pos + ": " + fn);
				pos++;
			}
			System.out.print("Which file would you like to share?");
			theAnswer = terminalInput.nextLine();
			
			String fileName = theFiles[Integer.parseInt(theAnswer)];
			System.out.println("You chose to share: " + fileName);
			
			Path path = Paths.get(BASE_DIR + "/" + fileName);
			byte[] theFile = Files.readAllBytes(path);
			
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			
			dos.writeInt(theFile.length);
			dos.write(theFile);
			dos.writeUTF(fileName);
			
			System.out.println("DONE");
			dos.close();
		}
	}
}
