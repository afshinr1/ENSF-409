package Model;
import java.util.Date;

import Model.OrderLine;

import java.util.ArrayList;
import java.util.Calendar;
/**
 * This class is used if there is an item which needs to be replenished, that is this class creates an order which 
 * contains many order lines of items which are low on stock.
 * @author Jase Pasay, Afshin Rahman
 *
 */
public class Order {
	
	/**
	 * the date of the order
	 */
	private Date today;
	/**
	 * the order id of the order.
	 */
	private int orderId;
	/**
	 * An arraylist of orderlines in case there are multiple items which need to be replenished.
	 */
	private ArrayList <OrderLine> orderLines;
	
	/**
	 * the constructor which initializes the date and the order line.
	 */
	public Order () {
		today = Calendar.getInstance().getTime();
		orderLines = new ArrayList <OrderLine> ();
	}
	
	/**
	 * Add an order line to the existing array of order lines.
	 * @param ol
	 */
	public void addOrderLine (OrderLine ol) {
		orderLines.add(ol);
	}
	/**
	 * get the existing order line array which contains all the items which are low on stock.
	 * @return the order line array
	 */
	public ArrayList <OrderLine> getOrderLines (){
		return orderLines;
	}
	/**
	 * Assigns the existing order line array to an order line array in the arguments.
	 * @param lines the order line the existing order line is assigned to.
	 */
	public void setOrderLines (ArrayList <OrderLine> lines){
		orderLines = lines;
	}
/**
 * gets the order ID.
 * @return the order id.
 */
	public int getOrderId() {
		return orderId;
	}

/**
 * sets the order id.
 * @param orderId the order id to be set to.
 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * provides a tostring of all the private variables found in this class.
	 */
	public String toString (){
		String order = "Order Date: " + today.toString() + "*";
		String str = "";
		for (OrderLine ol: orderLines) {
			str += ol;
			str += "------------------------*";
		}
		if (str == "")
			str = "There are corrently no orderlines";
		
		order += str;
		order += "*";
		return order;
	}

}
