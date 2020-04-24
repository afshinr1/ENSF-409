package Model;

import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

import Model.Supplier;

/**
 * The model portion of the MVC, used by the server to respond to the user
 * appropriately.
 * 
 * @author Afshin Rahman, Jase Pasay
 *
 */
public class Model implements Runnable {
	/**
	 * An ArrayList of all the suppliers that the shop currently has.
	 */
	private ArrayList<Supplier> suppliers;
	/**
	 * An instance of Inventory that contains all the items in the shop.
	 */
	private Inventory theInventory;
	/**
	 * An instance of the shop, used to call methods to manipulate and extract data
	 * from the shop.
	 */
	private Shop theShop;
	/**
	 * A socket for communicating with a client.
	 */
	private Socket aSocket;
	/**
	 * A PrintWriter for writing to the socket.
	 */
	private PrintWriter socketOut;
	/**
	 * A BufferedReader for reading inputs from a client.
	 */
	private BufferedReader socketIn;


	/**
	 * Constructor for the model.
	 * 
	 * @param s socket that connects the client to the server.
	 */
	public Model(Socket s) {
		try {
			suppliers = new ArrayList<Supplier>();
			readSuppliers();
			aSocket = s;
			theInventory = new Inventory(readItems());
			theShop = new Shop(theInventory, suppliers, new ArrayList<Customer>(), new ArrayList<User>());

			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Error in front end");
		}
	}

	/**
	 * Method that reads a text file and stores in an ArrayList of type Item.
	 * 
	 * @return the filled ArrayList.
	 */
	private ArrayList<Item> readItems() {

		ArrayList<Item> items = new ArrayList<Item>();

		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ensf", "root", "centralasia");
			Statement myStatement = myConn.createStatement();
			ResultSet myRs = myStatement.executeQuery("select * from items");
			while (myRs.next()) {
				int supplierId = myRs.getInt("supplierid");

				Supplier theSupplier = findSupplier(supplierId);
				if (theSupplier != null) {
					Item myItem = new Item(myRs.getInt("itemid"), myRs.getString("itemname"),
							myRs.getInt("itemquantity"), myRs.getDouble("itemprice"), theSupplier);
					items.add(myItem);
					theSupplier.getItemList().add(myItem);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return items;
	}

	/**
	 * Finds the supplier which matches the supplierID
	 * 
	 * @param supplierId
	 * 
	 * @return theSupplier
	 */
	private Supplier findSupplier(int supplierId) {
		Supplier theSupplier = null;
		for (Supplier s : suppliers) {
			if (s.getSupId() == supplierId) {
				theSupplier = s;
				break;
			}

		}
		return theSupplier;
	}

	/**
	 * Method that reads a text file and adds the suppliers to the supplier list.
	 */
	private void readSuppliers() {

		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ensf", "root", "centralasia");
			Statement myStatement = myConn.createStatement();
			ResultSet myRs = myStatement.executeQuery("select * from supplier");
			while (myRs.next()) {
				Supplier mySupplier = new Supplier(myRs.getInt("ID"), myRs.getString("Name"), myRs.getString("Address"),
						myRs.getString("Contact"));
				suppliers.add(mySupplier);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Method that makes use of a switch statement to decipher inputs from the
	 * buttons the user presses.
	 */
	public void menu() {
		try {
			while (true) {

				int choice;
				choice = Integer.parseInt(socketIn.readLine());
				System.out.println(choice);
				switch (choice) {

				case 1:
					listAllItems();
					break;
				case 2:
					searchForItemByName();
					break;
				case 3:
					searchForItemById();
					break;
				case 4:
					checkItemQuantity();
					break;
				case 5:
					decreaseItem();
					break;
				case 6:
					printOrder();
					break;
				case 7:
					insertItem();
					break;
				case 8:
					deleteItem();
					break;
				case 9:
					addCustomer();
					break;
				case 10:
					checkCustomerLogin();
					break;
				case 11:
					sendItemsCustomer();
					break;
				case 12:
					addUser();
					break;
				case 13:
					checkUserLogin();
					break;
				case 14:
					addToolToCart();
					break;
				case 15:
					checkCart();
					break;
				case 16:
					checkOutCart();
					break;
				}
			}
		} catch (IOException e) {
			System.out.println("Client disconnected.");
		}

	}

	private void checkOutCart() {
		try {
			String name = socketIn.readLine();
			ArrayList<String> items = theShop.checkoutCustomer(name);
			for(int i = 0; i < items.size(); i++) {
				decreaseItemPreparedStatement(items.get(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void checkCart() {
		try {
			String customerName = socketIn.readLine();
			String items = theShop.getCustomerItems(customerName);
			sendString(items);
			double bill = 0;
			;
			bill = theShop.getBill(customerName);
			sendString(String.valueOf(bill));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void addToolToCart() {
		try {
			String[] info = socketIn.readLine().split("\\*");
			String custName = info[0];
			String itemName = info[1];
			String answer = theShop.addToolToCart(custName, itemName);
			sendString(answer);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method used to send all the items in the shop to the user.
	 */
	public void listAllItems() {
		sendString(theShop.listAllItems());
	}

	/**
	 * Method used to send all the orders for the day to the user.
	 */
	private void printOrder() {
		sendString(theShop.printOrder());
	}

	/**
	 * Method used to decrease the quantity of an item in the shop.
	 */
	private void decreaseItem() {
		String name = getItemName();
		String response = theShop.decreaseItem(name); 
		decreaseItemPreparedStatement(name);
		sendString(response);
	}

	private void decreaseItemPreparedStatement(String name) {
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ensf", "root", "centralasia");
			
			String query = "UPDATE items SET itemquantity = ? WHERE itemname = ?";
			PreparedStatement pStat = myConn.prepareStatement(query);
			pStat.setInt(1, theShop.itemQuantity(name));
			pStat.setString(2, name);
			pStat.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method used to get the quantity of an item, and return that number to the
	 * user.
	 */
	private void checkItemQuantity() {
		String name = getItemName();
		sendString(theShop.getItemQuantity(name));
	}

	/**
	 * Helper Method for reading a name from the socket.
	 * 
	 * @return the name sent from the user.
	 */
	private String getItemName() {
		String line = "";
		try {
			// sendString("Please enter the name of the item: \0");
			// System.out.println("Please enter the name of the item: \0");

			line = socketIn.readLine();
			// System.out.println(line);
			return line;
		} catch (IOException e) {
			System.out.println("Error in getItemName");
		}
		return line;
	}

	/**
	 * Helper Method for reading an ID from the socket.
	 * 
	 * @return the ID sent from the user.
	 */
	private int getItemId() {

		// System.out.println("Please enter the ID number of the item: \0");
		// sendString("Please enter the ID number of the item: \0");

		int choice = -1;
		try {
			choice = Integer.parseInt(socketIn.readLine());
			// System.out.println(choice);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			sendString("Invalid ID, Please enter integer numbers only");
		}
		return choice;
	}

	/**
	 * Method used to search the shop for an item using the ID sent by the user.
	 */
	private void searchForItemById() {
		int id = getItemId();
		if (id > 0)
			sendString(theShop.getItem(id));

	}

	/**
	 * Method used to search the shop for an item using the name sent by the user.
	 */
	private void searchForItemByName() {

		String name = getItemName();
		sendString(theShop.getItem(name));

	}

	/**
	 * Helper Method for writing strings to the socket for communicating with the
	 * user.
	 * 
	 * @param s the string to be written to the socket.
	 */
	private void sendString(String s) {
		socketOut.println(s);
		socketOut.flush();
	}

	/**
	 * Method used to insert an item into the shop, based on information sent by
	 * user.
	 */
	public void insertItem() {
		try {
			String[] info = socketIn.readLine().split("\\*");
			theShop.addTool(Integer.parseInt(info[0]), info[1], Integer.parseInt(info[2]), Double.parseDouble(info[3]),
					Integer.parseInt(info[4]), info[5], info[6], info[7]);
			sendString("Tool Added!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method used to remove an item from the shop, removes the name sent by user if
	 * it exists.
	 */
	public void deleteItem() {

		if (theShop.removeTool(getItemName())) {
			sendString("Tool Deleted!");
		} else {
			sendString("Tool doesn't exist, can't delete.");
		}
	}

	public void addCustomer() {
		try {
			String[] temp = socketIn.readLine().split(" ");
			String answer = theShop.addCustomer(temp[0], temp[1]);
			sendString(answer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void checkCustomerLogin() {

		try {
			String[] temp = socketIn.readLine().split(" ");
			if (theShop.checkCustomerLogin(temp[0], temp[1])) {
				sendString("true");
			} else {
				sendString("false");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendItemsCustomer() {
		sendString(theShop.listAllItemsCustomer());
	}

	public void addUser() {
		try {
			String[] temp = socketIn.readLine().split(" ");
			theShop.addUser(temp[0], temp[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void checkUserLogin() {

		try {
			String[] temp = socketIn.readLine().split(" ");
			if (theShop.checkUserLogin(temp[0], temp[1])) {
				sendString("true");
			} else {
				sendString("false");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to run the menu for reacting to user inputs.
	 */
	@Override
	public void run() {
		this.menu();
	}

}
