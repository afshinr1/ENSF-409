package Model;

import java.util.ArrayList;

import Model.Item;
import Model.Order;
import Model.OrderLine;
import Model.Supplier;
/**
 * Class that acts as the inventory that contains tools
 * for a retail tool shop.
 * @author Afshin Rahman, Jase Pasay
 *
 */
public class Inventory {
	/**
	 * An ArrayList that contains all the items in the inventory.
	 */
	private ArrayList<Item> itemList;
	/**
	 * An instance of Order used to place orders
	 * for an item.
	 */
	private Order myOrder;
	/**
	 * Constructor for the inventory.
	 * @param itemList a list of items for the invetory.
	 */
	public Inventory(ArrayList<Item> itemList) {
		this.itemList = itemList;
		myOrder = new Order();
	}
	/**
	 * Getter for the list of items in the inventory.
	 * @return the list of items.
	 */
	public ArrayList<Item> getItemList() {
		return itemList;
	}
	/**
	 * Setter for a list of items for this inventory.
	 * @param itemList a list of items to be set to this inventory.
	 */
	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
	/**
	 * Method used to either place an order
	 *  for an item or decrease its quantity.
	 * @param name name of the item to be managed.
	 * @return the item.
	 */
	public Item manageItem(String name) {
		Item theItem = decreaseItem(name);

		if (theItem != null) {
			placeOrder(theItem);
		}
		return theItem;
	}
	/**
	 * Method used to add a new item to the inventory.
	 * @param ID ID of the item.
	 * @param name name of the item.
	 * @param quantity quantity of the item.
	 * @param price price of the item.
	 * @param sup a supplier for this item.
	 */
	public void addItem(int ID, String name, int quantity, double price, Supplier sup) {
		itemList.add(new Item(ID, name, quantity, price, sup));
	}
	/**
	 * Method used to remove an item from the inventory.
	 * @param i the item to be removed.
	 */
	public void removeItem(Item i) {
		itemList.remove(i);
	}
	/**
	 * Method used to create a new order line for the order
	 * if the quantity threshold is reached.
	 * @param theItem the item to be ordered.
	 */
	public void placeOrder(Item theItem) {
		OrderLine ol = theItem.placeOrder();
		if (ol != null) {
			myOrder.addOrderLine(ol);
		}
	}
	/**
	 * Method used to decrease the quantity of an item.
	 * @param name the name of the item.
	 * @return the item after changing its quantity.
	 */
	private Item decreaseItem(String name) {

		Item theItem = searchForItem(name);

		if (theItem == null)
			return null;

		if (theItem.decreaseItemQuantity() == true) {

			return theItem;
		}
		return null;

	}
	/**
	 * Method used to get the quantity of an item.
	 * @param name name of the item.
	 * @return the items quantity.
	 */
	public int getItemQuantity(String name) {
		Item theItem = searchForItem(name);
		if (theItem == null)
			return -1;
		else
			return theItem.getItemQuantity();
	}
	/**
	 * Method used to search for an item by name.
	 * @param name the name of the item.
	 * @return the item if found.
	 */
	public Item searchForItem(String name) {
		for (Item i : itemList) {
			if (i.getItemName().equalsIgnoreCase(name))
				return i;
		}
		return null;
	}
	/**
	 * Convert the inventory and its contents to a string.
	 * @return a string of all the contents in the inventory.
	 */
	public String toString() {
		String str = "";
		for (Item i : itemList) {
			str += i;
		}
		return str;
	}
	
	public String toStringCustomer() {
		String str = "";
		for(Item i: itemList) {
			str += i.toStringCustomer();
		}
		return str;
	}
	/**
	 * Method used to search for an item by its ID.
	 * @param id the ID of the item. 
	 * @return the item if found, else null.
	 */
	public Item searchForItem(int id) {
		for (Item i : itemList) {
			if (i.getItemId() == id)
				return i;
		}
		return null;
	}
	/**
	 * Method used to print todays order.
	 * @return a String containing the order.
	 */
	public String printOrder() {
		return myOrder.toString();
	}

}
