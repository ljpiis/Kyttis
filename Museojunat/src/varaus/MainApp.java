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
    
    static HashMap<String, User> customers = new HashMap<String, User>();
    static HashMap<User, UserThread> clientThreads = new HashMap<User, UserThread>();
    static UserThread userThread;
    
        
        //****METHODS FOR HANDLING USER REGISTRATION AND LOGGING IN ****
    
    //login
    public static boolean logIn(String username, String password) {
    	
    	//check if the password is correct
    	if (customers.get(username).getPassword().equals(password)) {
    		System.out.println("Login successful");
    		return true;
    	} else {
    		System.out.println("The password is incorrect");
    		return false;
    	}
    }
    
    
    	//register a new customer
        public static boolean registerCustomer(String username, String password) {
        	//check if username already exists
        if (!customers.containsKey(username)) {
        	customers.put(username, new User(username, password, false));
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
    	public void customerLogin(ActionEvent event) throws IOException {
        	
        	//for the sake of testing, if username and password fields are empty, login is still succesful
        	if ((userId.getText().equals("") && password.getText().equals("")) || (customers.get(userId.getText()).getPassword().equals(password.getText()))) {
	    		
        		System.out.println("Login succesful");
	    		changeScene(event, "00UserView.fxml");
	    		
        	} else {
        		System.out.println("Incorrect password");
        	}
    	}
        
        //00UserView
        
        @FXML
        private Button reserveNewTicket;
    	
        @FXML
        public void reserveTicket(ActionEvent event) throws IOException {
        	/** 
        	 * this method starts a Thread that is used to retrieve and change the information
        	 * about the system's trains.
        	 */
        	
        	//change the scene to the first page of reserving a new ticket
        	changeScene(event, "01SearchTrip.fxml");
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

    	//*** MAIN METHOD *****
    	
    public static void main(String[] args) {
        
    	//creating test users
    	User roosa = new User("Roosa", "akuankka", false);
    	customers.put(roosa.getUserId(), roosa);
    	
    	//create test trains
    	
    	//launch the application window 
        launch(args);
        
        
        
    }
}