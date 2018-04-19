// TESTIMUUTOS 2

package varaus;

import varaus.view.*;

import java.awt.TextField;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import varaus.model.Juna;
import varaus.model.User;
import varaus.model.UserThread;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private Scene scene1, scene2;
    

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("VarausApp");

        initRootLayout();
        showRuutu00();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
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
     * Shows the Ruutu00 inside the root layout.
     */
    public void showRuutu00() {
        try {
            // Load ruutu00.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Ruutu00.fxml"));
            AnchorPane ruutu00 = (AnchorPane) loader.load();

            // Set ruutu00 into the center of root layout.
            rootLayout.setCenter(ruutu00);
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
    
    static HashMap<String, User> clients = new HashMap<String, User>();
    static HashMap<User, UserThread> clientThreads = new HashMap<User, UserThread>();
    static UserThread userThread;
    
        
        //****METHODS FOR HANDLING USER REGISTRATION AND LOGGING IN ****
    
    //login
    public static boolean logIn(String username, String password) {
    	
    	//check if the password is correct
    	if (clients.get(username).getPassword().equals(password)) {
    		System.out.println("Login successful");
    		userThread = new UserThread(clients.get(username), true);
    		System.out.println("Starting the thread...");
    		//the next line is blocked for testing purposes
    		//userThread.run();
    		return true;
    	} else {
    		System.out.println("The password is incorrect");
    		return false;
    	}
    }
    
    
    	//register a new customer
        public static boolean registerCustomer(String username, String password) {
        	//check if username already exists
        if (!clients.containsKey(username)) {
        	clients.put(username, new User(username, password));
        	System.out.println("Creating an account successful");
        	logIn(username, password);
        	return true;
        } else {
        	System.out.println("Client already exists.");
        	return false;
        }
    }
        
        public static void customerLogOut() {
        	userThread.logOut();
        }
        
     // --------- FXML METHODS - CONTROLLER CLASS ---------
    	
    	
    	//naming convention: changeTo[wanted scene name], for example: changeToLogin
    	
    	public void changeToUserView(ActionEvent event) throws IOException {
    		
    		//the next line tests binding methods to a button
    		registerCustomer("Roosa", "akuankka");
    		
    		changeScene(event, "view/UserView00.fxml");
    	}
    	
    	public void changeToLogin(ActionEvent event) throws IOException {
    		
    		//the next line tests binding a method to a button
    		customerLogOut();
    		changeScene(event, "view/Ruutu00.fxml");
    		
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
        
    	//launch the application window 
        launch(args);
        
        
        
    }
}