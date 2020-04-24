package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CustomerGUI extends JFrame {

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
	
	private JPanel shopPanel;
	private JLabel shopTitle = new JLabel("Jase and Afshin's tool shop");
	private JTextArea text = new JTextArea();
	private JScrollPane textScroll = new JScrollPane(text);
	private JButton addItem = new JButton("Add Tool to Cart");
	private JButton checkCart = new JButton("Check cart");
	private JButton checkOut = new JButton("Check Out");
	private JButton logout = new JButton("Logout");

	public CustomerGUI() {
		displayLogin();
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
		shopPanel = new JPanel();
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		shopPanel.add(shopTitle);

		text.setEditable(false);
		textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textScroll.setPreferredSize(new Dimension(450, 365));

		shopPanel.add(textScroll);
		shopPanel.add(addItem);
		shopPanel.add(checkCart);
		shopPanel.add(checkOut);
		shopPanel.add(logout);

		this.add(shopPanel);
	}
	
	public void closeShop() {
		shopPanel.setVisible(false);
		displayLogin();
	}

	public void addNewListener(ActionListener e) {
		newUser.addActionListener(e);
	}

	public void addExistListener(ActionListener e) {
		existingUser.addActionListener(e);
	}

	public void addAddListener(ActionListener e) {
		addItem.addActionListener(e);
	}

	public void addCartListener(ActionListener e) {
		checkCart.addActionListener(e);
	}

	public void addCheckoutListener(ActionListener e) {
		checkOut.addActionListener(e);
	}

	public void addLogoutListener(ActionListener e) {
		logout.addActionListener(e);
	}
	
	public void writeText(String s) {
		text.append(s);
	}
	
	public void clearDisplay() {
		text.setText(null);
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public String getItemName() {
		String s = JOptionPane.showInputDialog("Please enter the name of the tool you would like to add", null);
		return s;
	}

}
