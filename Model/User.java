package Model;

public class User {

	private String username;
	private String password;
	
	public User(String u, String p) {
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
}
