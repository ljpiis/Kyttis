package varaus;

import java.io.IOException;
import java.util.HashMap;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import varaus.model.Asema;
import varaus.model.Juna;
import varaus.model.User;
import varaus.model.UserThread;
import varaus.view.Controller110SearchUser;
import varaus.view.Controller111UserEditDialog;




public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    //scene2;
    //*** OBJECTS USED IN THE PROGRAMME ***
    
    static HashMap<String, User> userlist = new HashMap<String, User>(); // Kirjautuminen nojaa tahan, tupla
    private static ObservableList<User> userData = FXCollections.observableArrayList(); // Listaus nojaa tahan
    static HashMap<User, UserThread> clientThreads = new HashMap<User, UserThread>();
    static UserThread userThread;
    
    /**
     * Constructor
     */
    public MainApp() {
    	userData.add(new User("Laasa", "Laasa", "aku", false));
    	// creating a test admin 
    	userData.add(new User("admin", "amin",  "aku", true));
    	
    }
    
    /**
     * Returns the data as an observable list of Users
     * @return
     */
    public ObservableList<User> getUserData(){
		return userData;
	}
    
    @Override
    /* The most important method, as it is automatically called when the application
     * is launched from within the main -method.
     * Receives a Stage as parameter. This is the main container in which 
     * we can then add a Scene, which then again can be switched out 
     * by another Scene. 
     * (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
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
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene1 = new Scene(rootLayout);
            primaryStage.setScene(scene1);
            primaryStage.show();
            
            
            //scene2 = new Scene(300, 300)
            
        } catch (IOException e) {
        	System.out.println("InitRootLayout-metodi");
            e.printStackTrace();
        }
    }//initRootLayout

    /**
     * Shows the 000Ruutu inside the root layout.
     */
    public void show000Ruutu() {
        try {
            // Load 000ruutu.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/000Ruutu.fxml"));
            AnchorPane alkuruutu = (AnchorPane) loader.load();

            // Set 000ruutu into the center of root layout.
            rootLayout.setCenter(alkuruutu);
          
            
        } catch (IOException e) {
        	System.out.println("show000Ruutu");
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
    
    
        
    //--------------------------------------****METHODS FOR HANDLING USER REGISTRATION AND LOGGING IN ****
    
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
        
        //-------------------------------------------- FXML COMPONENTS ----------- //
        
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
        	// tulisi sisaltaa toiminnallisuus sen suhteen, mita tehdaan kun kayttajatunnusta ei loydy ja
        	// tarvitsee luoda uusi (perus)kayttaja -- adminit on luotuna valmiina
        	if ( (userId.getText().equals("") && password.getText().equals("")) || (userlist.get(userId.getText()).getPassword().equals(password.getText())) && !(userlist.get(userId.getText()).isAdmin()) ) {
	    		
        		System.out.println("Login succesful");
	    		changeScene(event, "view/00UserView.fxml");
	    		
        	} 
        	else if((userlist.get(userId.getText()).getPassword().equals(password.getText())) && ( userlist.get(userId.getText()).isAdmin() )){
        		System.out.println("AdminLogin succesful");
	    		changeScene(event, "view/100AdminView.fxml");
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
        	changeScene(event, "view/01SearchTrip.fxml");
        }
    	@FXML
        private Button to000Ruutu;
        @FXML
        public void toRuutu(ActionEvent event) throws IOException {
        	changeScene(event, "view/000Ruutu.fxml");
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
        	changeScene(event, "view/02ChooseTrip.fxml");
        }
        
        @FXML
        private Button to00UserView;
        @FXML
        public void toUserView(ActionEvent event) throws IOException {
        	/** 
        	 * Back to the previous page, to the UserView
        	 */
        	
        	//change the scene to the second page of reserving a new ticket
        	changeScene(event, "view/00UserView.fxml");
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
        	changeScene(event, "view/03ChooseSeats.fxml");
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
        	changeScene(event, "view/04UserPayment.fxml");
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

    	// Admin -- moving between views ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    	@FXML
        private Button to100AdminView;
        @FXML
        public void toAdminView(ActionEvent event) throws IOException {
        	changeScene(event, "view/100AdminView.fxml");
        }
    	@FXML
        private Button to110SearchUser;
        @FXML
        public void toSearchUser(ActionEvent event){
        	try {
                changeScene(event, "view/110SearchUser.fxml");                

                // Give the controller access to the main app.
                FXMLLoader loader = new FXMLLoader();
                Controller110SearchUser controller = loader.getController();
                controller.setMainApp(this);  // nullpointteri tasta? 

            } catch (IOException e) {
                e.printStackTrace();
            }
        }//toSearchUser
        
        /**
         * Opens a dialog to edit details for the specified user. If ok
         * clicked, the changes are saved into the provided person object and true
         * is returned.
         * 
         * @param user the user object to be edited
         * @return true if the OK is clicked, false otherwise.
         */
        public boolean showUserEditDialog(User user) {
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("view/UserEditDialog.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Edit User");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(primaryStage);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the user into the controller.
                Controller111UserEditDialog controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setUser(user);

                // Show the dialog and wait until the user closes it
                dialogStage.showAndWait();
                return controller.isOkClicked();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }//showUserEditDialog

    	@FXML
        private Button to120TrainView;
        @FXML
        public void toTrainView(ActionEvent event) throws IOException {
        	changeScene(event, "view/120TrainView.fxml");
        }
    	@FXML
        private Button to130RouteView;
        @FXML
        public void toRouteView(ActionEvent event) throws IOException {
        	changeScene(event, "view/130RouteView.fxml");
        }
        @FXML
        private Button to140TripView;
        @FXML
        public void toTripView(ActionEvent event) throws IOException {
        	changeScene(event, "view/140TripView.fxml");
        }

    	  	
    	
    	//--------------------------------------------------------------------------*** MAIN METHOD *****
    	
    public static void main(String[] args) {
    	
       	//-----------
    	//creating test users
    	User roosa = new User("Roosa", "akuankka", false);
    	userData.add(roosa);
    	userlist.put(roosa.getUserId(), roosa); //tupla
    	
    	// creating a test admin 
    	User admini = new User("admini", "aku", true);
    	userData.add(admini);
    	userlist.put(admini.getUserId(), admini); // tupla
    	
    	userData.add(new User("admin", "aku", true));

    	System.out.println("Created test-users " + userData);
    	
    	//---------------------------------------------------------------------------------------------------------------

    	
    	// EI Oli alunperin MainAppin konstruktorissa testUserien 
    	// "Tietokanta"
    	Juna[] junat = new Juna[4];
    	Asema[] asemat = new Asema[8];
    	
    	//Luodaan asemi
    	
    	// Asemat
    	asemat[0] = new Asema("Asema 1");
    	asemat[1] = new Asema("Asema 2");
    	asemat[2] = new Asema("Asema 3");
    	asemat[3] = new Asema("Asema 4");
    	asemat[4] = new Asema("Asema 5");
    	asemat[5] = new Asema("Asema 6");
    	asemat[6] = new Asema("Asema 7");
    	asemat[7] = new Asema("Asema 8");
    	
    	// Yhteydet
    	asemat[0].lisaaYhteys(asemat[1], 15);
    	asemat[1].lisaaYhteys(asemat[2], 18);
    	asemat[2].lisaaYhteys(asemat[3], 10);
    	asemat[0].lisaaYhteys(asemat[4], 21);
    	asemat[4].lisaaYhteys(asemat[5], 13);
    	asemat[0].lisaaYhteys(asemat[6], 7);
    	asemat[6].lisaaYhteys(asemat[7], 19);
       
       	//Reitit
       	for (Asema tarkasteltava : asemat) {
       		tarkasteltava.luoReitit(tarkasteltava);	
       	}
       	
       	// Junat
    	junat[0] = new Juna("Juna 1", "h", 3, 20);
    	junat[1] = new Juna("Juna 2", "h", 4, 20);
    	junat[2] = new Juna("Juna 3", "h", 5, 20);
    	junat[3] = new Juna();
    	
    	//launch the application window 
    	launch(args);   	
    	 	
    } //main      
} //MainApp