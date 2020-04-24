package ServerController;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Model.Model;
/**
 * The server which allows communication between clients and the server.
 * @author Afshin Rahman, Jase Pasay
 *
 */
public class ServerCommunication {
	/**
	 * Serversocket which connects the client to the server.
	 */
	private ServerSocket serverSocket;
	/**
	 * Threadpool which allows multiple clients to access the server at once.
	 */
	private ExecutorService pool;
	/**
	 * the model
	 */
	private Model communicate;
	
/**
 * Constructor which initializes the serverSocket and thread pool.
 * @param port
 */
	public ServerCommunication(int port, Model c) {
		try {
			serverSocket = new ServerSocket(port);
			communicate  = c;
			pool = Executors.newCachedThreadPool();
		} catch (IOException e) {
			System.out.println("Error in server");
		}

	}
/**
 * allows the model and the client to communicate via sockets.
 */
	public void communicate() {
		try {
			while (true) {

		 communicate = new Model(serverSocket.accept());
				pool.execute(communicate);

			}
		} catch (IOException e) {
			System.out.println("Error in communicate server");
		}
	}

	public static void main(String[] args) {
		Model c = null;
		ServerCommunication theServer = new ServerCommunication(9000, c);
		System.out.println("The server is running");
		theServer.communicate();

	}

}
