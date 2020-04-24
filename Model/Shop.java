package Model;

import java.util.ArrayList;

import Model.Inventory;
import Model.Item;
import Model.Supplier;

/**
 * Class that simulates a retail tool shop and has an inventory of tools, and
 * list of suppliers.
 * 
 * @author Afshin Rahman, Jase Pasay
 *
 */
public class Shop {
	/**
	 * Instance of Inventory which contains all the tools int the shop.
	 */
	private Inventory theInventory;
	/**
	 * Instance of an ArrayList of suppliers, which contains all the suppliers used
	 * by the shop.
	 */
	private ArrayList<Supplier> supplierList;

	/**
	 * Constructor for the shop.
	 * 
	 * @param inventory gives an inventory to this shop.
	 * @param suppliers gives a list of suppliers to this shop.
	 */

	private ArrayList<Customer> customerList;
	private ArrayList<User> userList;

	public Shop(Inventory inventory, ArrayList<Supplier> suppliers, ArrayList<Customer> customers,
			ArrayList<User> users) {

		theInventory = inventory;
		supplierList = suppliers;
		customerList = customers;
		userList = users;

	}

	public String addCustomer(String username, String password) {
		if (checkExistingCustomer(username)) {
			return ("Error. Username is already taken, please enter a different username");
		} else {
			customerList.add(new Customer(username, password));
			return ("Successful!");
		}
	}

	private boolean checkExistingCustomer(String username) {
		for (Customer c : customerList) {
			if (c.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkCustomerLogin(String username, String password) {

		for (Customer c : customerList) {
			if (c.getUsername().equals(username) && c.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	public void addUser(String username, String password) {
		userList.add(new User(username, password));
	}

	public boolean checkUserLogin(String username, String password) {

		for (User u : userList) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Getter for the shops Inventory
	 * 
	 * @return this shops inventory.
	 */
	public Inventory getTheInventory() {
		return theInventory;
	}

	/**
	 * Sets an inventory to this shop.
	 * 
	 * @param inventory the inventory to be set to the shop.
	 */
	public void setTheInventory(Inventory inventory) {
		theInventory = inventory;
	}

	/**
	 * Getter for the suppliers for this shop.
	 * 
	 * @return the suppliers for this shop.
	 */
	public ArrayList<Supplier> getSupplierList() {
		return supplierList;
	}

	/**
	 * Sets a list of suppliers to this shop.
	 * 
	 * @param suppliers the list of suppliers for this shop.
	 */
	public void setSupplierList(ArrayList<Supplier> suppliers) {
		supplierList = suppliers;
	}

	/**
	 * Method used to list all the items in the shop in a string.
	 * 
	 * @return all the items in a string.
	 */
	public String listAllItems() {
		return (theInventory.toString());

	}

	public String listAllItemsCustomer() {
		return (theInventory.toStringCustomer());
	}

	/**
	 * Method used to decrease the quantity of an item in the shop.
	 * 
	 * @param name name of the item to be decreased.
	 * @return a string indicating if the items quantity was decreased.
	 */
	public String decreaseItem(String name) {
		if (theInventory.manageItem(name) == null)
			return "Couldn't not decrease item quantity!";
		else
			return "Item quantity was decreased!";
	}

	/**
	 * Method used to list all the suppliers for the shop.
	 */
	public void listAllSuppliers() {
		for (Supplier s : supplierList) {
			System.out.println(s);
		}

	}

	/**
	 * Method used to get an item by name from the shop.
	 * 
	 * @param name name of the item.
	 * @return the details of the item in a string.
	 */
	public String getItem(String name) {
		Item theItem = theInventory.searchForItem(name);
		if (theItem == null)
			return "Item " + name + " could not be found!";
		else
			return (theItem.toString());

	}

	public Item searchItem(String name) {
		Item theItem = theInventory.searchForItem(name);
		if (theItem == null)
			return null;
		else
			return theItem;
	}

	/**
	 * Method used to get an item by ID from the shop.
	 * 
	 * @param id ID of the item.
	 * @return the details of the item in a string.
	 */
	public String getItem(int id) {
		Item theItem = theInventory.searchForItem(id);
		if (theItem == null)
			return "Item number " + id + " could not be found!";
		else
			return (theItem.toString());

	}

	/**
	 * Method used to the quantity of an item by name from the shop.
	 * 
	 * @param name name of the Item.
	 * @return a string indicating the quantity of the item.
	 */
	public String getItemQuantity(String name) {
		int quantity = itemQuantity(name);
		if (quantity < 0)
			return "Item " + name + " could not be found!";
		else
			return "The quantity of Item " + name + " is: " + quantity;
	}

	public int itemQuantity(String name) {
		return theInventory.getItemQuantity(name);
	}

	/**
	 * Method used to print todays order.
	 * 
	 * @return the order in a string.
	 */
	public String printOrder() {
		return theInventory.printOrder();
	}

	/**
	 * Method used to add a new item to the shop.
	 * 
	 * @param id              ID of the item.
	 * @param name            name of the item.
	 * @param quantity        quantity of the item.
	 * @param price           price of the item.
	 * @param supplierID      supplier ID for the supplier of the item.
	 * @param supplierName    supplier name for the supplier of the item.
	 * @param supplierAddress supplier address for the supplier of the item.
	 * @param supplierContact supplier contact for the supplier of the item.
	 */
	public void addTool(int id, String name, int quantity, double price, int supplierID, String supplierName,
			String supplierAddress, String supplierContact) {

		Supplier newSup = new Supplier(supplierID, supplierName, supplierAddress, supplierContact);
		supplierList.add(newSup);
		theInventory.addItem(id, name, quantity, price, newSup);
	}

	/**
	 * Method used to remove a tool by name from the shop.
	 * 
	 * @param name name of the item to be removed.
	 */
	public boolean removeTool(String name) {
		Item i = theInventory.searchForItem(name);
		if (i != null) {
			theInventory.removeItem((theInventory.searchForItem(name)));
			return true;
		}
		return false;
	}

	public String addToolToCart(String custName, String itemName) {
		String message = "";
		Item theItem = searchItem(itemName);
		if (theItem != null) {
			for (Customer c : customerList) {
				if (c.getUsername().equals(custName)) {
					c.getItems().add(itemName);
					c.setBill(theItem.getItemPrice());
					message = "Successfully added item to cart";
				}
			}
		} else
			message = "Item doesnt exist!";
		return message;
	}

	public String getCustomerItems(String customerName) {
		String items = "";
		for (Customer c : customerList) {
			if (c.getUsername().equals(customerName)) {
				ArrayList<String> itemList = c.getItems();
				for (int i = 0; i < itemList.size(); i++) {
					items += searchItem(itemList.get(i)).getItemName() + "     "
							+ searchItem(itemList.get(i)).getItemPrice() + "*";
				}
			}
		}
		return items;
	}

	public ArrayList<String> checkoutCustomer(String name) {

		for (Customer c : customerList) {
			if (c.getUsername().equals(name)) {
				ArrayList<String> itemList = new ArrayList<String>(c.getItems());
				for (int i = 0; i < itemList.size(); i++) {
					searchItem(itemList.get(i)).decreaseItemQuantity();
				}

				c.clearCart();
				c.clearBill();
				return itemList;
			}

		}
		return null;
	}

	public double getBill(String customerName) {
		double bill = 0;
		for (Customer c : customerList) {
			if (c.getUsername().equals(customerName)) {
				bill = c.getBill();
			}
		}
		return bill;
	}

}
