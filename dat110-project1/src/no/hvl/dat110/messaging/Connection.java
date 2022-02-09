package no.hvl.dat110.messaging;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {

	private DataOutputStream outStream; // for writing bytes to the TCP connection
	private DataInputStream inStream; // for reading bytes from the TCP connection
	private Socket socket; // socket for the underlying TCP connection

	public Connection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream(socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) {
		byte[] data = MessageUtils.encapsulate(message);
		try {
			outStream.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Message receive() {
		
		// read a segment from the input stream and decapsulate into message
		
		Message message = null;
		byte[] data =  new byte[MessageConfig.SEGMENTSIZE];

		try {
			inStream.read(data);
			message = MessageUtils.decapsulate(data);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return message;

	}

	// close the connection by closing streams and the underlying socket
	public void close() {

		try {

			outStream.close();
			inStream.close();

			socket.close();

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}