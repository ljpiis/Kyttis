package varaus.model;

	import java.time.LocalDate;
	import java.util.ArrayList;
	import javafx.beans.property.IntegerProperty;
	import javafx.beans.property.ObjectProperty;
	import javafx.beans.property.SimpleIntegerProperty;
	import javafx.beans.property.SimpleObjectProperty;
	import javafx.beans.property.SimpleStringProperty;
	import javafx.beans.property.StringProperty;
	import varaus.MainApp;

	/**
	 * Model class for a User.
	 *
	 * Nyt on paljon infoa, tuskin tarvitaan kaikkea.
	 *
	 */
	public class User {

	    private StringProperty firstName;
	    private StringProperty lastName;	    
	    private StringProperty userId;
	    private StringProperty password; 
	    private boolean admin; // ylläpitäjä vai ei
	    private boolean loggedIn; //onko käyttäjä kirjautunut vai ei
	    private ArrayList<Ticket> tickets = new ArrayList<Ticket>(3); 
	    

	    /**
	     * Default constructor.
	     */
	    public User() {
	        this.userId = null;
	        this.password = null;
	    }
	    
	    public User(String firstName, String lastName, String userId, String password, boolean admin) {
	    	
	    	this.firstName = new SimpleStringProperty(firstName);
	    	this.lastName = new SimpleStringProperty(lastName);
	    	this.userId = new SimpleStringProperty(userId);
	    	this.password = new SimpleStringProperty(password);
	    	this.admin = admin;
	    	// loggedIn ja tickets
	    }

	    /**
	     * Konstruktori, jossa meille aluksi oleelliset
	     * Luo tavallisen käyttäjän
	     * ei vielä osaa tarkistaa, onko tunnus jo käytössä -- toiminnallisuus main-methodissa
	     * 
	     * @param userId
	     * @param password
	     */
	    public User(String userId, String password, boolean admin){
	    	this.userId = new SimpleStringProperty(userId);
	    	this.password = new SimpleStringProperty(password);
	        this.admin = admin;
	        this.tickets = new ArrayList<Ticket>();
	           
	    }
	
	    
	    
	    //METHODS
	    
	    public boolean changePassword(String newPassword) {
	    	this.setPassword(newPassword);
	    	return true;
	    }
	    
	    
	    //GETTERS AND SETTERS

		public String getFirstName() {
			return firstName.get();
		}
		public void setFirstName(String firstName) {
			this.firstName.set(firstName);
		}
		public StringProperty firstNameProperty() {
			return firstName;
		}
		
		
		public String getLastName() {
			return lastName.get();
		}
		public void setLastName(String lastName) {
			this.lastName.set(lastName);
		}
		public StringProperty lastNameProperty() {
	        return lastName;
	    }

		public String getUserId() {
			return userId.get();
		}
		public void setUserId(String userId) {
			this.userId.set(userId);
		}
		public StringProperty userIdProperty() {
			return userId;
		}

		public String getPassword() {
			return password.get();
		}
		public void setPassword(String password) {
			this.password.set(password);
		}

		public boolean isAdmin() {
			return admin;
		}
		public void setAdmin(boolean admin) {
			this.admin = admin;
		}
		public boolean isLoggedIn() {
			return loggedIn;
		}

		public void setLoggedIn(boolean loggedIn) {
			this.loggedIn = loggedIn;
		}
		
		
		
		/* After a ticket is bought, it should be added to the list of Tickets
		* ..from which it is retrieved to the 00UserView -view
		* Olisiko syytä lisätä jokin ehtolause; asetetaanko yläraja lipuille?
		*/
		
		public void addTicket(Ticket t) {
			tickets.add(t);
		}
	
		
		
	}
	    
	    
	    

