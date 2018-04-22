package varaus.model;

public class UserThread implements Runnable {
	
	private User user;
	private boolean loggedIn;
	//tähän alle kaikki lipun varaamiseen tarvittavat muuttujat
	
	public UserThread (User user, boolean loggedIn) {
		this.user = user;
		this.loggedIn = loggedIn;
	}
	
	@Override
	public void run() {
		while (loggedIn) {
			
		}
	}
	
	public void logOut() {
		this.setLoggedIn(false);
		System.out.println("THread is killed!");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	

}
