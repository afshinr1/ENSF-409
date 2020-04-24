package Model;

/**
 * OrderLine is an order which contains an item and the quantity to be ordered if the item needs to be re-stocked.
 * @author Afshin Rahman, Jase Pasay
 *
 */
public class OrderLine {
	/**
	 * the item in the order.
	 */
	private Item theItem;
	/**
	 * the quantity to be replenished.
	 */
	private int orderQuantity;
	
	/**
	 * The constructor which initializes the item and the quantity of the item which needs to be replenished.
	 * @param item
	 * @param quantity
	 */
	public OrderLine (Item item, int quantity) {
		theItem = item;
		setOrderQuantity(quantity); 
		
	}
/**
 * returns the item in the order line.
 * @return the item.
 */
	public Item getTheItem() {
		return theItem;
	}
/**
 * sets the item in the order line
 * @param theItem the item which needs to be re-stocked.
 */
	public void setTheItem(Item theItem) {
		this.theItem = theItem;
	}
/**
 * gets the order quantity of the item in the order line.
 * @return the order quantity.
 */
	public int getOrderQuantity() {
		return orderQuantity;
	}
/**
 * sets the quantity of the order line of the item.
 * @param orderQuantity the quantity to be set.
 */
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	/**
	 * returns the string name of the item name, item id and the order quantity.
	 */
	public String toString (){
		return  "Item Name: " + theItem.getItemName() +
				", Item ID: " + theItem.getItemId()+ "*" + 
				"Order Quantity: " + orderQuantity + "*";
	}

}
