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

	    private String firstName;
	    private String lastName;
	    private String street;
	    private String postalCode;
	    private String city;
	    private String birthday;
	    
	    private String userId;
	    private String password; 
	    private boolean admin; // ylläpitäjä vai ei
	    private boolean loggedIn; //onko käyttäjä kirjautunut vai ei
	    private ArrayList<Ticket> tickets;
	    

	    /**
	     * Default constructor.
	     */
	    public User() {
	        this.userId = "";
	        this.password = "";
	    }
	    
	    public User(String firstName, String lastName, String street,String postalCode, String city, String birthday, String userId, String password, boolean admin) {
	    	
	    	this.firstName = firstName;
	    	this.lastName = lastName;
	    	this.street = street;
	    	this.postalCode = postalCode;
	    	this.city = city;
	    	this.birthday = birthday;
	    	this.userId = userId;
	    	this.password = password;
	    	this.admin = admin;
	    	
	    }

	    /**
	     * Konstruktori, jossa meille aluksi oleelliset
	     * Luo tavallisen käyttäjän
	     * ei vielä osaa tarkistaa, onko tunnus jo käytössä
	     * salasana2 lisäksi, niin voisi varmistaa et salasana tulee kaksi kertaa..?
	     * @param userId
	     * @param password
	     */
	    public User(String userId, String password, boolean admin){
	        this.userId = userId;
	        this.password = password;
	        this.admin = false;
	        this.tickets = new ArrayList<Ticket>();
	        
	        
	    }
	    
	    
	    /**
	     * Constructor with some initial data.
	     * 
	     * @param firstName
	     * @param lastName
	     */
	    public User(String firstName, String lastName, String tunnus) {
	    }
	    
	    
	    //METHODS
	    
	    public boolean changePassword(String newPassword) {
	    	this.setPassword(newPassword);
	    	return true;
	    }
	    
	    
	    //GETTERS AND SETTERS

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getPostalCode() {
			return postalCode;
		}

		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getBirthday() {
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
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
	}
	    
	    
	    

