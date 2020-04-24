package Model;

import Model.OrderLine;
import Model.Supplier;

/**
 * Class that acts as a tool that will be sold by a retail tool shop.
 * 
 * @author Afshin Rahman, Jase Pasay
 *
 */
public class Item {
	/**
	 * ID for the item
	 */
	private int itemId;
	/**
	 * Name of the item.
	 */
	private String itemName;
	/**
	 * Quantity of the item.
	 */
	private int itemQuantity;
	/**
	 * Price of the item.
	 */
	private double itemPrice;
	/**
	 * boolean to check if an item has already been ordered.
	 */
	private boolean alreadyOrdered;
	/**
	 * a supplier of this item.
	 */
	private Supplier theSupplier;
	/**
	 * Default amount of an item to be ordered.
	 */
	private static final int ORDERQUANTITY = 40;
	/**
	 * Minimum threshold for an items quantity before more is ordered.
	 */
	private static final int MINIMUMUMBER = 20;

	/**
	 * Constructor for the item.
	 * 
	 * @param id       the ID of the item.
	 * @param name     the name of the item.
	 * @param quanitiy the quantity of the item.
	 * @param price    the price of the item.
	 * @param sup      a supplier for this item.
	 */
	public Item(int id, String name, int quanitiy, double price, Supplier sup) {

		itemId = id;
		itemName = name;
		itemQuantity = quanitiy;
		itemPrice = price;
		sup = theSupplier;
		setAlreadyOrdered(false);
	}

	/**
	 * Method used to decrease the quantity of this item.
	 * 
	 * @return true if the items quantity was decreased, else false.
	 */
	public boolean decreaseItemQuantity() {
		if (itemQuantity > 0) {
			itemQuantity--;
			return true;
		} else
			return false;

	}
	/**
	 * Method used to place an order for this item if the
	 * minimum threshold was met.
	 * @return an order line for this item if it wasn't already ordered and reached the threshold, else null.
	 */
	public OrderLine placeOrder() {
		OrderLine ol;
		if (getItemQuantity() < MINIMUMUMBER && alreadyOrdered == false) {
			ol = new OrderLine(this, ORDERQUANTITY);
			alreadyOrdered = true;
			return ol;
		}
		return null;
	}
	/**
	 * Getter for the items ID.
	 * @return the items ID.
	 */
	public int getItemId() {
		return itemId;
	}
	/**
	 * Setter for an items ID
	 * @param itemId the new ID.
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	/**
	 * Getter for items name.
	 * @return the items name.
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * Setter for an items name. 
	 * @param itemName the new name.
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * Getter for an items quantity.
	 * @return the items quantity.
	 */
	public int getItemQuantity() {
		return itemQuantity;
	}
	/**
	 * Setter for an items quantity.
	 * @param itemQuantity the new quantity.
	 */
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	/** 
	 * Getter for an items price.
	 * @return the items price.
	 */
	public double getItemPrice() {
		return itemPrice;
	}
	/**
	 * Setter for an items price.
	 * @param itemPrice the new price.
	 */
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public void setTheSupplier(Supplier sup) {
		theSupplier = sup;
	}
	/**
	 * Getter for the items supplier.
	 * @return the items supplier.
	 */
	public Supplier getTheSupplier() {
		return theSupplier;
	}
	/**
	 * Convert the details of an item to a String
	 * @return the details of an item in a String.
	 */
	public String toString() {
		return "Item ID: " + itemId + ", Item Name: " + itemName + ", Item Quantity: " + itemQuantity + "*";
	}
	
	public String toStringCustomer() {
		return "Item Name: " + itemName + ", Item Quantity: " + itemQuantity + "*";
	}
	/**
	 * Method to check if an item has already been ordered.
	 * @return true if the item has been ordered, else false.
	 */
	public boolean isAlreadyOrdered() {
		return alreadyOrdered;
	}
	/**
	 * Setter for setting if an item has been ordered.
	 * @param alreadyOrdered true or false depending on if the items been ordered.
	 */
	public void setAlreadyOrdered(boolean alreadyOrdered) {
		this.alreadyOrdered = alreadyOrdered;
	}

}
