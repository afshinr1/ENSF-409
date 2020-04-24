package ClientController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientCommunication {
	/**
	 * writes to socket
	 */
	private PrintWriter socketOut;
	/**
	 * socket used to communicate with the server
	 */
	private Socket clientSocket;

	/**
	 * to receive input from server
	 */
	private BufferedReader socketIn;

	/**
	 * initializes all communication variables with server.
	 * 
	 * @param serverName
	 * @param portNumber
	 */
	public ClientCommunication(String serverName, int portNumber) {
		try {
			InetAddress address = InetAddress.getByName(serverName);
			clientSocket = new Socket(address, portNumber);
			//stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			socketOut = new PrintWriter((clientSocket.getOutputStream()), true);

		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}

	/**
	 * Continuously receives input from server and provides user input to server
	 * through sockets.
	 */
	public void communicate() {

		try {
			while (true) {
				String line = "";

				while (true) {

					if (line.contains("\0")) {
						line = line.replace("\0", "");
						break;
					}

					if (line.equals("QUIT"))
						return;
		
					
					line = socketIn.readLine();
					System.out.println(line);

				}
				socketOut.println(line);
				socketOut.flush();

			}
		} catch (IOException e) {
			System.out.println("Error in client");
		} finally {
			try {
				socketOut.close();
				socketIn.close();
				clientSocket.close();
			} catch (IOException e) {
				System.out.println("Failed to close");
			}
		}

	}
/**
 * sends a string to the socket which the server recieves to perform actions on.
 * @param guiChoice a choice made by the user on the GUI.
 */
	public void sendString(String guiChoice) {
		socketOut.println(guiChoice);
	}
/**
 * Gets a string from the socket.
 * @return	the string to the controller which allows additional operations
 */
	public String getString() {
		try {
			String serverResponse = "";
			serverResponse = socketIn.readLine();
			return serverResponse;
		} catch (IOException e) {
			System.out.println("error in get string");
		}
		return null;
	}

}
