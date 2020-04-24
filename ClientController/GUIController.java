package ClientController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import View.GUIView;

/**
 * The controller which links the GUI which takes input from a user, and a
 * client class which sends the users input to the server.
 * 
 * @author Afshin Rahman, Jase Pasay
 *
 */
public class GUIController {
	/**
	 * The graphical user interface which takes input from a user.
	 */
	private GUIView view;
	/**
	 * An instance of clientCommunicaiton which communicates with the server through
	 * sockets.
	 */
	private ClientCommunication client;

	/**
	 * assigns the GUI and the clientCommunication variables, and sets the listeners
	 * for every button in the graphical interface.
	 * 
	 * @param gv the member variable of GUI is assigned to this variable.
	 * @param c  the member variable of clientcommunication is assigned to this
	 *           variable.
	 */
	public GUIController(GUIView gv, ClientCommunication c) {
		view = gv;
		client = c;

		view.addNewListener(new newListener());
		view.addExistListener(new existListener());
		view.addLogoutListener(new logoutListener());
		view.addAddActionListener(new AddListener());
		view.addDeleteActionListener(new DeleteListener());
		view.addFindNameActionListener(new FindNameListener());
		view.addFindIDActionListener(new FindIDListener());
		view.addListActionListener(new ListListener());
		view.addQuantityActionListener(new QuantityListener());
		view.addDecreaseQuantityActionListener(new DecreaseQuantityListener());
		view.addPrintOrderActionListener(new PrintOrderListener());
	}

	class newListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int result = view.displayNewUser();

			if (result == 1 && view.newIsEmpty() == false) {
				view.closeLogin();
				String info = view.getNewUsername() + " " + view.getNewPassword();
				client.sendString("12");
				client.sendString(info);
				view.displayShop();
			} else if (result == 1 && view.newIsEmpty() == true) {
				view.showMessage("Invalid Input");
			}
		}
	}

	class existListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			int result = view.displayExistingUser();

			if (result == 1 && view.existIsEmpty() == false) {
				String info = view.getExistUsername() + " " + view.getExistPassword();
				client.sendString("13");
				client.sendString(info);

				if (client.getString().equalsIgnoreCase("true")) {
					view.closeLogin();
					view.closeExist();
					view.displayShop();
				} else {
					view.showMessage("Incorrect username or password.");
				}
			} else if (result == 1 && view.existIsEmpty() == true) {
				view.showMessage("Incorrect username or password.");
			}
		}
	}

	class logoutListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			view.closeShop();
		}
	}

	/**
	 * This class listens for the DecreaseQuantity button located on the GUI, and
	 * sends a series of strings to the socket, which the server then performs
	 * appropriate actions.
	 *
	 */
	class DecreaseQuantityListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String name = view.getDecreaseQuantity();
			if (name != null) {
				client.sendString("5");
				client.sendString(name);
				String quantity = client.getString();
				view.showItem(quantity);
			}
		}
	}

	/**
	 * This class listens for the Print Order button located on the GUI, and sends a
	 * series of strings to the socket, which the server then performs appropriate
	 * actions. Then writes the result on the GUI for the user to see.
	 *
	 */
	class PrintOrderListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			client.sendString("6");
			String text = client.getString();
			String[] split = text.split("\\*");
			view.clearDisplay();
			for (int i = 0; i < split.length; i++) {
				view.writeText(split[i]);
				view.writeText("\n");
			}
		}
	}

	/**
	 * This class listens for the Add tool button located on the GUI, and sends a
	 * series of strings to the socket, which the server then adds a tool based on
	 * the strings sent. Then writes the result on the GUI for the user to see
	 * whether the action was successfully performed or not.
	 *
	 */
	class AddListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int insert = view.createAddPanel();
			String info = view.getInsertInfo();
			if (insert == 1 && view.insertIsEmpty() == false) {
				client.sendString("7");
				client.sendString(info);
				view.showMessage(client.getString());
			} else if (insert == 1 && view.insertIsEmpty() == true) {
				view.showMessage("Invalid input.");
			}
		}
	}

	/**
	 * This class listens for the delete item button located on the GUI, and sends a
	 * series of strings to the socket, which the server then deletes a tool based
	 * on the strings received. Then writes the result on the GUI for the user to
	 * see if the action was successful or not.
	 *
	 */
	class DeleteListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String name = view.getDeletedTool();
			if (name != null) {
				client.sendString("8");
				client.sendString(name);
				view.showMessage(client.getString());
			}
		}
	}

	/**
	 * This class listens for the Find by Name button located on the GUI, and sends
	 * a series of strings to the socket, which the server then performs appropriate
	 * actions. Then writes the result on the GUI for the user to see whether the
	 * action was successfully performed or not.
	 *
	 */
	class FindNameListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String name = view.getToolName();
			if (name != null) {
				client.sendString("2");
				client.sendString(name);
				String foundName = client.getString();
				foundName = foundName.replace('*', ' ');
				view.showItem(foundName);
			}
		}
	}

	/**
	 * This class listens for the Find by ID button located on the GUI, and sends a
	 * series of strings to the socket, which the server then performs appropriate
	 * actions. Then writes the result on the GUI for the user to see whether the
	 * action was successfully performed or not.
	 *
	 */
	class FindIDListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String ID = view.getToolID();
			if (ID != null) {
				client.sendString("3");
				client.sendString(ID);
				String foundID = client.getString();
				foundID = foundID.replace('*', ' ');
				view.showItem(foundID);
			}
		}
	}

	/**
	 * This class listens for the List all Tools button located on the GUI, and
	 * sends a series of strings to the socket, which the server then performs
	 * appropriate actions. Then writes all the tools found on the server onto the
	 * GUI for the user to see.
	 *
	 */
	class ListListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			client.sendString("1");
			String text = client.getString();
			String[] split = text.split("\\*");
			view.clearDisplay();
			for (int i = 0; i < split.length; i++) {
				view.writeText(split[i]);
				view.writeText("\n");
			}
		}
	}

	/**
	 * This class listens for the check Quantity button located on the GUI, and
	 * sends a series of strings to the socket, which the server then performs
	 * appropriate actions. Then writes the result on the GUI for the user to see
	 * whether the action was successfully performed or not.
	 *
	 */
	class QuantityListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String name = view.getToolQuantity();
			if (name != null) {
				client.sendString("4");
				client.sendString(name);
				String quantity = client.getString();
				view.showItem(quantity);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		ClientCommunication aClient = new ClientCommunication("localhost", 9000);
		GUIView gui = new GUIView();
		gui.setVisible(true);
		GUIController control = new GUIController(gui, aClient);

	}

}
