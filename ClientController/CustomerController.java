package ClientController;

import View.CustomerGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CustomerController {

	private CustomerGUI cgui;
	private ClientCommunication customer;
	private String customerName;

	public CustomerController(CustomerGUI cg, ClientCommunication c) {
		cgui = cg;
		customer = c;

		cgui.addNewListener(new newListener());
		cgui.addExistListener(new existListener());
		cgui.addAddListener(new addListener());
		cgui.addCartListener(new cartListener());
		cgui.addCheckoutListener(new checkoutListener());
		cgui.addLogoutListener(new logoutListener());
	}

	class newListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int result = cgui.displayNewUser();

			if (result == 1 && cgui.newIsEmpty() == false) {
				String name = cgui.getNewUsername();
				String info = name + " " + cgui.getNewPassword();
				customer.sendString("9");
				customer.sendString(info);
				String message = customer.getString();
				if(message.contains("Error")) {
					cgui.showMessage(message);
				}
				else {
					customerName = name;
					cgui.showMessage(message);
				cgui.closeLogin();
				displayItems();
				cgui.displayShop();
				}
			} else if (result == 1 && cgui.newIsEmpty() == true) {
				cgui.showMessage("Invalid Input");
			}
		}
	}

	class existListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			int result = cgui.displayExistingUser();

			if (result == 1 && cgui.existIsEmpty() == false) {
				String name = cgui.getExistUsername();
				String info = name  + " " + cgui.getExistPassword();
				customer.sendString("10");
				customer.sendString(info);
				
				if (customer.getString().equalsIgnoreCase("true")) {
					customerName = name;
					cgui.closeLogin();
					cgui.closeExist();
					displayItems();
					cgui.displayShop();
				} else {
					cgui.showMessage("Incorrect username or password.");
				}
			}  else if (result == 1 && cgui.existIsEmpty() == true) {
				cgui.showMessage("Incorrect username or password.");
			}
		}
	}

	class addListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		String itemName = cgui.getItemName();
		if(itemName != null) {
			String info = customerName + "*" + itemName;
			customer.sendString("14");
			customer.sendString(info);
			String message = customer.getString();
			cgui.showMessage(message);
		}
		}
	}

	class cartListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			customer.sendString("15");
			customer.sendString(customerName);
			String items = customer.getString();
			String bill = customer.getString();
			items = items.replace('*', '\n');
			if(items.trim().equals("")) {
				cgui.showMessage("There are currently no items in your cart");
			}
			else {
				String message = "Items currently in cart: \n";
				message += "Item name followed by item price \n";
					message+=items+ "\n";

				message += "The total bill is: " + bill;
				cgui.showMessage(message);
			}
			
		}
	}

	class checkoutListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			customer.sendString("16");
			customer.sendString(customerName);
			cgui.showMessage("Successfull checked out all items in cart");
			displayItems();
		}
	}

	class logoutListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			cgui.closeShop();
		}
	}
	
	public void displayItems() {
		customer.sendString("11");
		String text = customer.getString();
		String[] split = text.split("\\*");
		cgui.clearDisplay();
		for (int i = 0; i < split.length; i++) {
			cgui.writeText(split[i]);
			cgui.writeText("\n");
		}
	}

	public static void main(String[] args) throws IOException {
		ClientCommunication aClient = new ClientCommunication("localhost", 9000);
		CustomerGUI gui = new CustomerGUI();
		gui.setVisible(true);
		CustomerController control = new CustomerController(gui, aClient);

	}
}
