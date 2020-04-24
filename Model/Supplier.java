package Model;
import java.util.ArrayList;
/**
 * Class that acts as a supplier of a type of tool for
 * a tool shop.
 * @author Afshin Rahman, Jase Pasay
 *
 */
public class Supplier {
	/**
	 * The ID associated with this supplier.
	 */
	private int supId;
	/**
	 * The name of this supplier.
	 */
	private String supName;
	/**
	 * The address of this supplier.
	 */
	private String supAddress;
	/**
	 * The contact name for this supplier.
	 */
	private String supContactName;
	/**
	 * An ArrayList of all the items this supplier has.
	 */
	private ArrayList <Item> itemList;
	
	/**
	 * Constructor for a supplier.
	 * @param id the ID of the supplier.
	 * @param name name of the supplier.
	 * @param address address of the supplier.
	 * @param contactName contact name of the supplier.
	 */
	public Supplier (int id, String name, String address, String contactName) {
		
		supId = id;
		supName = name;
		supAddress = address;
		supContactName = contactName;
		itemList = new ArrayList <Item>();
	}

	/**
	 * Getter for retrieving the supplier ID.
	 * @return the supplier ID.
	 */
	public int getSupId() {
		return supId;
	}

	/** 
	 * Setter for the suppliers ID.
	 * @param supId supplier ID to be set.
	 */
	public void setSupId(int supId) {
		this.supId = supId;
	}

	/** 
	 * Getter for retrieving the supplier name.
	 * @return the supplier name.
	 */
	public String getSupName() {
		return supName;
	}

	/**
	 * Setter for suppliers name.
	 * @param supName name to be set to the supplier.
	 */
	public void setSupName(String supName) {
		this.supName = supName;
	}

	/**
	 * Getter for the suppliers address.
	 * @return the suppliers address.
	 */
	public String getSupAddress() {
		return supAddress;
	}

	/**
	 * Setter for the suppliers address.
	 * @param supAddress address to be set to the supplier.
	 */
	public void setSupAddress(String supAddress) {
		this.supAddress = supAddress;
	}

	/**
	 * Getter for the suppliers contact name.
	 * @return the suppliers contact name.
	 */
	public String getSupContactName() {
		return supContactName;
	}

	/**
	 * Setter for the suppliers contact name.
	 * @param supContactName the contact name to be set for the supplier.
	 */
	public void setSupContactName(String supContactName) {
		this.supContactName = supContactName;
	}
	public String toString () {
		return supName + " Supplier ID: " + supId+ "\n";
		
	}

	/**
	 * Getter used to retrieve the list of items this supplier offers.
	 * @return the list of items this supplier offers.
	 */
	public ArrayList <Item> getItemList() {
		return itemList;
	}

	/**
	 * Setter for setting a list of items to this supplier.
	 * @param itemList the list of items to be set to this supplier.
	 */
	public void setItemList(ArrayList <Item> itemList) {
		this.itemList = itemList;
	}
	

}
