package Model;
import java.util.ArrayList;

public class Customer {

	
	private ArrayList<String> boughtItems;
	private double bill;
	private String username;
	private String password;
	
	public Customer(String u, String p) {
		boughtItems = new ArrayList<String>();
		username = u;
		password = p;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String s) {
		username = s;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String s) {
		password = s;
	}
	
	public ArrayList<String> getItems() {
		return boughtItems;
	}
	
	public double getBill() {
		return bill;
	}
	
	public void setBill(double d) {
		bill += d;
	}
	
	public String itemsToString() {
		String s = "";
		for(int i = 0; i < boughtItems.size(); i++) {
			s += boughtItems.get(i) + "\n";
		}
		return s;
	}

	public void clearCart() {
			boughtItems.clear();
		
	}

	public void clearBill() {
	bill = 0;
		
	}
}
