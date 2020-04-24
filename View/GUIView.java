package View;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 * Creates a graphical user interface with several buttons which interact with a user.
 * @author Afshin Rahman, Jase Pasay
 *
 */
public class GUIView extends JFrame {
	/**
	 * title of the application
	 */
	private JLabel title = new JLabel("My Tool Shop");
	/**
	 * Text area where the tools will be displayed. 
	 */
	private JTextArea text = new JTextArea();
	/**
	 * scrollpane in case there is more text than the area allocated.
	 */
	private JScrollPane textScroll = new JScrollPane(text);
	/**
	 * button for adding a tool
	 */
	private JButton add = new JButton("Add Tool");
	/**
	 * button for deleting a tool
	 */
	private JButton delete = new JButton("Delete Tool");
	/**
	 * button for finding a tool by name
	 */
	private JButton findName = new JButton("Find by Name");
	/**
	 * button for finding a tool by ID.
	 */
	private JButton findID = new JButton("Find by ID");
	/**
	 * button to list all tools avaiable.
	 */
	private JButton list = new JButton("List all Tools");
	/**
	 * button to check the quantity of an item.
	 */
	private JButton checkQuantity = new JButton("Check Quantity");
	/**
	 * button to decrease the quantity of an item.
	 */
	private JButton decreaseQuantity = new JButton("Decrease Quantity");
	/**
	 * button to print an order to the GUI.
	 */
	private JButton printOrder = new JButton("Print Order");

	/**
	 * field to obtain user input on tool name to insert.
	 */
	private JTextField toolName = new JTextField(10);
	/**
	 * field to obtain user input on tool ID to insert.
	 */
	private JTextField toolID = new JTextField(10);
	/**
	 * field to obtain user input on price to insert.
	 */
	private JTextField price = new JTextField(10);
	/**
 	 * field to obtain user input on quantity to insert.
	 */
	private JTextField quantity = new JTextField(10);
/**
 * field to obtain user input on supplier Name to insert
 */
	private JTextField supplierName = new JTextField(10);
	/**
	 * field to obtain user input on supplier ID to insert.
	 */
	private JTextField supplierID = new JTextField(10);
	/**
	 * field to obtain user input on supplier Address to insert.
	 */
	private JTextField supplierAddress = new JTextField(10);
	/**
	 * field to obtain user input on supplier contact to insert.
	 */
	private JTextField supplierContact = new JTextField(10);
	
	private JPanel loginPanel;
	private JLabel loginTitle = new JLabel("Welcome to Jase and Afshin's tool shop.");
	private JButton newUser = new JButton("New User");
	private JButton existingUser = new JButton("Existing User");

	private JPanel newPanel;
	private JLabel newTitle = new JLabel("Welcome! Please enter your desired username and password.");
	private JTextField newUsername = new JTextField(10);
	private JTextField newPassword = new JTextField(10);

	private JPanel existPanel;
	private JLabel existTitle = new JLabel("Welcome Back! Please enter your username and passord.");
	private JTextField existUsername = new JTextField(10);
	private JTextField existPassword = new JTextField(10);
	
	private JPanel mainPanel;
	private JButton logout = new JButton("Logout");

	/**
	 * GUI constructor which sets the size and frame of the GUI. Sets all the buttons and text areas.
	 */
	public GUIView() {
		displayLogin();
	}
/**
 * adding an action listener for the decrease quantity button.
 * @param e is the action listener which is added.
 */
	public void addDecreaseQuantityActionListener(ActionListener e) {
		decreaseQuantity.addActionListener(e);
	}
/**
 * adding an action listener for the print order button.
 * @param e is the action listener which is added.
 */
	public void addPrintOrderActionListener(ActionListener e) {
		printOrder.addActionListener(e);
	}
/**
 * adding an action listener for the add tool button.
 * @param e is the action listener which is added.
 */
	public void addAddActionListener(ActionListener e) {
		add.addActionListener(e);
	}
/**
 * adding an action listener for the delete tool button.
 * @param e is the action listener which is added.
 */
	public void addDeleteActionListener(ActionListener e) {
		delete.addActionListener(e);
	}
/**
 * adding an action listener for the find tool by name button.
 * @param e is the action listener which is added.
 */
	public void addFindNameActionListener(ActionListener e) {
		findName.addActionListener(e);
	}
/**
  * adding an action listener for the find tool by ID button.
 * @param e is the action listener which is added.
 */
	public void addFindIDActionListener(ActionListener e) {
		findID.addActionListener(e);
	}
/**
 * adding an action listener for the list all tools button.
 * @param e is the action listener which is added.
 */
	public void addListActionListener(ActionListener e) {
		list.addActionListener(e);
	}
/**
  * adding an action listener for the check quantity button.
 * @param e is the action listener which is added.
 */
	public void addQuantityActionListener(ActionListener e) {
		checkQuantity.addActionListener(e);
	}
/**
 * gets the user input tool name and sends it back to the controller.
 * @return the tool name provided by the user.
 */
	public String getName() {
		return toolName.getText();
	}
/**
 * gets the user input tool name and sends it back to the controller.
 * @return the tool id provided by the user
 */
	public String getID() {
		return toolID.getText();
	}
/**
 * gets the user inputed supplier name and sends it back to the controller.
 * @return the supplier name provided by the user
 */
	public String getSupplierName() {
		return supplierName.getText();
	}
/**
 * gets the user inputed supplier ID and sends it back to the controller.
 * @return the supplier id provided by the user.
 */
	public String getSupplierID() {
		return supplierID.getText();
	}
/**
 * gets the user input supplier address and sends it back to the controller.
 * @return the supplier address provided by the user.
 */
	public String getSupplierAddress() {
		return supplierAddress.getText();
	}
/**
 * gets the user input supplier contact and sends it back to the controller
 * @return the supplier contact provided by the user
 */
	public String getSupplierContact() {
		return supplierContact.getText();
	}
/**
 * gets the user inputed price and sends it back to the controller.
 * @return the tool price provided by the user.
 */
	public String getPrice() {
		return price.getText();
	}
/**
 * gets the user inputed quantity and sends it back to the controller
 * @return the tool quantity provided by the user.
 */
	public String getQuantity() {
		return quantity.getText();
	}
/**
 * Creates the add tool panel when the add tool button is pressed. That is, 
 * it creates a panel which a user can input information about a tool to add.
 * @return confirmation whether the user wants to add a tool or not.
 */
	public int createAddPanel() {
		JPanel insertPanel = new JPanel();
		insertPanel.setLayout(new GridLayout(4, 2));

		insertPanel.add(new JLabel("Tool Name: "));
		insertPanel.add(toolName);
		insertPanel.add(new JLabel("Supplier Name: "));
		insertPanel.add(supplierName);
		insertPanel.add(new JLabel("Tool ID: "));
		insertPanel.add(toolID);
		insertPanel.add(new JLabel("Supplier ID: "));
		insertPanel.add(supplierID);
		insertPanel.add(new JLabel("Price: "));
		insertPanel.add(price);
		insertPanel.add(new JLabel("Supplier Address: "));
		insertPanel.add(supplierAddress);
		insertPanel.add(new JLabel("Quantity: "));
		insertPanel.add(quantity);
		insertPanel.add(new JLabel("Supplier Contact: "));
		insertPanel.add(supplierContact);

		int result = JOptionPane.showConfirmDialog(null, insertPanel, "Add a new Tool", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			return 1;
		}
		if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
			return -1;
		}
		return 0;
	}
/**
 * gets the name of a tool which the user wants to be deleted.
 * @return the name of the tool to be deleted.
 */
	public String getDeletedTool() {

		String s = JOptionPane.showInputDialog("Please enter the name of tool you would like to delete.", null);
		return s;
	}
/**
 * gets a tool name which the user wants to find, from the user input.
 * @return the name of the tool which the user would like to be found.
 */
	public String getToolName() {

		String s = JOptionPane.showInputDialog("Please enter the name of the tool you would like to find.", null);
		return s;
	}
/**
 * gets the name of a tool which the user would like to check the quantity of.
 * @return the tool name to check its quantity.
 */
	public String getToolQuantity() {

		String s = JOptionPane
				.showInputDialog("Please enter the name of the tool you would like to check the quantity of.", null);
		return s;
	}
/**
 * gets the name of a tool which the user would like to decrease the quantity of.
 * @return the name of the tool to decrease its quantity.
 */
	public String getDecreaseQuantity() {

		String s = JOptionPane
				.showInputDialog("Please enter the name of the tool you would like to decrease the quantity of.", null);
		return s;
	}
/**
 * gets the id of a tool the user would like to find
 * @return the tool id to find.
 */
	public String getToolID() {

		String s = JOptionPane.showInputDialog("Please enter the ID of the tool you would like to find.", null);
		return s;
	}
/**
 * gets the information about a tool a user would like to add to the tool shop.
 * @return the information about the tool which will be added.
 */
	public String getInsertInfo() {
		
		String s = getID() + "*";
		s += getName() + "*";
		s += getQuantity() + "*";
		s += getPrice() + "*";
		s += getSupplierID() + "*";
		s += getSupplierName() + "*";
		s += getSupplierAddress() + "*";
		s += getSupplierContact() + "*";

		return s;
	}
	
	public boolean insertIsEmpty() {
		
		if (getName().isEmpty() || getID().isEmpty() || getQuantity().isEmpty() || getPrice().isEmpty()
				|| getSupplierName().isEmpty() || getSupplierID().isEmpty() || getSupplierAddress().isEmpty()
				|| getSupplierContact().isEmpty()) {
			return true;
		}
		return false;
	}
	/**
	 * clears the display of the items displayed on the GUI.
	 */
	public void clearDisplay() {
		text.setText(null);
	}
/**
 * writes to the GUI for the user to see.
 * @param s the string which is to be written to the GUI.
 */
	public void writeText(String s) {
		text.append(s);
	}
/**
 * shows a message to the user.
 * @param message the message to be shown.
 */
	public void showItem(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
/**
 * shows a message to the user
 * @param message the message to be shown.
 */
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public void displayLogin() {
		loginPanel = new JPanel();
		this.setSize(300, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		loginPanel.add(loginTitle);
		loginPanel.add(newUser);
		loginPanel.add(existingUser);

		this.add(loginPanel);
	}

	public void closeLogin() {
		loginPanel.setVisible(false);
	}

	public int displayNewUser() {
		newPanel = new JPanel();

		newPanel.add(newTitle);
		newPanel.add(newUsername);
		newPanel.add(newPassword);

		int result = JOptionPane.showConfirmDialog(null, newPanel, "", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			return 1;
		}
		if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
			return -1;
		}
		return 0;
	}

	public boolean newIsEmpty() {

		if (getNewUsername().isEmpty() || getNewPassword().isEmpty()) {
			return true;
		}
		return false;
	}

	public void closeNew() {
		newPanel.setVisible(false);
	}

	public String getNewUsername() {
		return newUsername.getText();
	}

	public String getNewPassword() {
		return newPassword.getText();
	}

	public int displayExistingUser() {
		existPanel = new JPanel();

		existPanel.add(existTitle);
		existPanel.add(existUsername);
		existPanel.add(existPassword);

		int result = JOptionPane.showConfirmDialog(null, existPanel, "", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			return 1;
		}
		if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
			return -1;
		}
		return 0;
	}

	public boolean existIsEmpty() {

		if (getExistUsername().isEmpty() || getExistPassword().isEmpty()) {
			return true;
		}
		return false;
	}

	public void closeExist() {
		existPanel.setVisible(false);
	}
	
	public String getExistUsername() {
		return existUsername.getText();
	}
	
	public String getExistPassword() {
		return existPassword.getText();
	}

	public void displayShop() {
		mainPanel = new JPanel();
		this.setSize(500, 530);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel.add(title);

		text.setEditable(false);
		textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textScroll.setPreferredSize(new Dimension(450, 365));

		mainPanel.add(textScroll);
		mainPanel.add(add);
		mainPanel.add(delete);
		mainPanel.add(findName);
		mainPanel.add(findID);
		mainPanel.add(list);
		mainPanel.add(checkQuantity);
		mainPanel.add(decreaseQuantity);
		mainPanel.add(printOrder);
		mainPanel.add(logout);

		this.add(mainPanel);
	}
	
	public void closeShop() {
		mainPanel.setVisible(false);
		displayLogin();
	}

	public void addNewListener(ActionListener e) {
		newUser.addActionListener(e);
	}

	public void addExistListener(ActionListener e) {
		existingUser.addActionListener(e);
	}
	
	public void addLogoutListener(ActionListener e) {
		logout.addActionListener(e);
	}
}
