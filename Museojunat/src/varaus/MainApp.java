// TESTIMUUTOS 2

package varaus;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import varaus.model.Juna;
import varaus.model.User;
import varaus.model.UserThread;

public class MainApp extends Application {
	

	
	
	
	
	
    private Stage primaryStage;
    private BorderPane rootLayout;
    private Scene scene1; 
    //scene2;
    

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Museojunien VarausApp");

        initRootLayout();
        show000Ruutu();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            scene1 = new Scene(rootLayout);
            primaryStage.setScene(scene1);
            primaryStage.show();
            
            
            //scene2 = new Scene(300, 300)
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the 000Ruutu inside the root layout.
     */
    public void show000Ruutu() {
        try {
            // Load 000ruutu.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("000Ruutu.fxml"));
            AnchorPane alkuruutu = (AnchorPane) loader.load();

            // Set 000ruutu into the center of root layout.
            rootLayout.setCenter(alkuruutu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    //*** OBJECTS USED IN THE PROGRAMME ***
    
    static HashMap<String, User> userlist = new HashMap<String, User>();
    static HashMap<User, UserThread> clientThreads = new HashMap<User, UserThread>();
    static UserThread userThread;
    
        
        //****METHODS FOR HANDLING USER REGISTRATION AND LOGGING IN ****
    
    //login
    public static boolean logIn(String username, String password) {
    	
    	//check if the password is correct
    	if (userlist.get(username).getPassword().equals(password)) {
    		System.out.println("Password is correct");
    		return true;
    	} else {
    		System.out.println("The password is incorrect");
    		return false;
    	}
    }
    
    
    	//register a new customer
        public static boolean registerCustomer(String username, String password) {
        	//check if username already exists
        if (!userlist.containsKey(username)) {
        	userlist.put(username, new User(username, password, false));
        	System.out.println("Creating an account successful");
        	logIn(username, password);
        	return true;
        } else {
        	System.out.println("Client already exists.");
        	return false;
        }
    }
        
        //------- FXML COMPONENTS ----------- //
        
        //000Ruutu components
        @FXML
        private TextField userId;
        @FXML
        private PasswordField password;
        @FXML
        private Button loginButton; 
        @FXML
    	public void userLogin(ActionEvent event) throws IOException {
        	
        	//for the sake of testing, if username and password fields are empty, login is still succesful
        	// tulisi sisältää toiminnallisuus sen suhteen, mitä tehdään kun käyttäjätunnusta ei löydy ja
        	// tarvitsee luoda uusi (perus)käyttäjä -- adminit on luotuna valmiina
        	if ( (userId.getText().equals("") && password.getText().equals("")) || (userlist.get(userId.getText()).getPassword().equals(password.getText())) && !(userlist.get(userId.getText()).isAdmin()) ) {
	    		
        		System.out.println("Login succesful");
	    		changeScene(event, "00UserView.fxml");
	    		
        	} 
        	else if((userlist.get(userId.getText()).getPassword().equals(password.getText())) && ( userlist.get(userId.getText()).isAdmin() )){
        		System.out.println("AdminLogin succesful");
	    		changeScene(event, "100AdminView.fxml");
        	}
        	else {
        		System.out.println("Incorrect password");
        	}
    	}
        
        
        //00UserView, aktiiviset varaukset -listaus
        
        @FXML
        private Button toReserveNewTicket;
        @FXML
        public void toReserveTicket(ActionEvent event) throws IOException {
        	/** 
        	 * this method starts a Thread that is used to retrieve and change the information
        	 * about the system's trains.
        	 */
        	
        	//change the scene to the first page of reserving a new ticket
        	changeScene(event, "01SearchTrip.fxml");
        }
    	@FXML
        private Button to000Ruutu;
        @FXML
        public void toRuutu(ActionEvent event) throws IOException {
        	changeScene(event, "000Ruutu.fxml");
        }
        
    	
    	//01SearchTrip
        @FXML
        private Button to02ChooseTrip;
        @FXML
        public void toChooseTrip(ActionEvent event) throws IOException {
        	/** 
        	 * this method starts a Thread that is used to ...
        	 */
        	
        	//change the scene to the second page of reserving a new ticket
        	changeScene(event, "02ChooseTrip.fxml");
        }
        
        @FXML
        private Button to00UserView;
        @FXML
        public void toUserView(ActionEvent event) throws IOException {
        	/** 
        	 * Back to the previous page, to the UserView
        	 */
        	
        	//change the scene to the second page of reserving a new ticket
        	changeScene(event, "00UserView.fxml");
        }
        
    	
        //02ChooseTrip
        @FXML
        private Button to03ChooseSeats;
        @FXML
        public void toChooseSeats(ActionEvent event) throws IOException {
        	/** 
        	 * this method starts a Thread that is used to retrieve and change the information
        	 * about the seats (and car) on the chosen train.
        	 */
        	
        	//change the scene to the second page of reserving a new ticket
        	changeScene(event, "03ChooseSeats.fxml");
        }
        
      //03ChooseSeats
        @FXML
        private Button to04UserPayment;
        @FXML
        public void toUserPayment(ActionEvent event) throws IOException {
        	/** 
        	 * this method starts a Thread that is used to retrieve and change the information
        	 * about the seats (and car) on the chosen train.
        	 */
        	
        	//change the scene to the second page of reserving a new ticket
        	changeScene(event, "04UserPayment.fxml");
        }
        
        
        
    	// A general code for changing the scene in the window. Parameters are the ActionEvent (button click) and the
    	//name of the scene that you want to display.
    	public void changeScene(ActionEvent event, String sceneName) throws IOException {
    		
    		Parent wantedScene = FXMLLoader.load(getClass().getResource(sceneName));
    		Scene newScene = new Scene (wantedScene);
    		
    		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    		
    		window.setScene(newScene);
    		window.show();
    		
    	}

    	// Admin -- moving between views 
    	@FXML
        private Button to100AdminView;
        @FXML
        public void toAdminView(ActionEvent event) throws IOException {
        	changeScene(event, "100AdminView.fxml");
        }
    	@FXML
        private Button to110SearchUser;
        @FXML
        public void toSearchUser(ActionEvent event) throws IOException {
        	changeScene(event, "110SearchUser.fxml");
        }
    	@FXML
        private Button to120TrainView;
        @FXML
        public void toTrainView(ActionEvent event) throws IOException {
        	changeScene(event, "120TrainView.fxml");
        }
    	@FXML
        private Button to130RouteView;
        @FXML
        public void toRouteView(ActionEvent event) throws IOException {
        	changeScene(event, "130RouteView.fxml");
        }
        @FXML
        private Button to140TripView;
        @FXML
        public void toTripView(ActionEvent event) throws IOException {
        	changeScene(event, "140TripView.fxml");
        }

    	
    	
    	
    	
    	
    	
    	
    	//*** MAIN METHOD *****
    	
    public static void main(String[] args) {
        
    	
    	//-----------
    	//creating test users
    	User roosa = new User("Roosa", "akuankka", false);
    	userlist.put(roosa.getUserId(), roosa);
    	// creating a test admin 
    	User admini = new User("admini", "aku", true);
    	userlist.put(admini.getUserId(), admini);
    	System.out.println("Created test-users " + admini.getUserId());
    	
    	
    	
    	//--------------
    	
    	
    	//create test trains
    	
    	//launch the application window 
        launch(args);

    }
    
    public HashMap<String, User> getUserList(){
		return userlist;
	}
    
    
    
}