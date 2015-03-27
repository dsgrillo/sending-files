package server.bean;

public class SentFile {

	private byte[] theFile;
	
	private String name;

	public byte[] getTheFile() {
		return theFile;
	}

	public void setTheFile(byte[] theFile) {
		this.theFile = theFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
